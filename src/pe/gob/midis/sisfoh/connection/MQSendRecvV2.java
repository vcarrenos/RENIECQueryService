package pe.gob.midis.sisfoh.connection;

import java.io.IOException;

import javax.jms.JMSException;

import pe.gob.midis.sisfoh.exception.RQSConnectionException;
import pe.gob.midis.sisfoh.exception.RQSException;
import pe.gob.midis.sisfoh.type.PropertiesType;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;

public class MQSendRecvV2 {

	private PropertiesType qParams;
	
	MQQueue queueIn = null;
	MQQueueManager QM = null;
	MQQueue queueOut = null;
	String idqueueManager = null;

	public MQSendRecvV2(PropertiesType qParams) throws MQException, Exception {
		super();
		this.qParams = qParams;
		
		inicializarMQ();
	}

    private MQQueue inicializarMQ()
            throws MQException, Exception
        {
            try {
                final String sQueueManagerName = qParams.getApplMQQueueManager();
                final String sChannelName = qParams.getApplMQChannel();
                final String sHostnameMQ = qParams.getApplMQHostName();
                final String sPort = String.valueOf(qParams.getApplMQPortNumber());
                final String sQueueReq = qParams.getApplMQQueueNameRequest();
                final String sQueueRes = qParams.getApplMQQueueNameResponse();
                MQEnvironment.hostname = sHostnameMQ; // HHA: host to connect to
                MQEnvironment.port = Integer.parseInt(sPort);// 1415 port to connect to.
                MQEnvironment.channel = sChannelName;
                
                MQEnvironment.userID = "mqm"; 
                MQEnvironment.password = "mqm";

    			System.out.println("Conectando como: userID='mqm', password='mqm'");
                
                MQPoolManager.init();

                this.QM = new MQQueueManager(sQueueManagerName);
                this.queueOut = this.QM.accessQueue(sQueueReq, CMQC.MQOO_OUTPUT | CMQC.MQOO_FAIL_IF_QUIESCING);
                this.queueIn = this.QM.accessQueue(sQueueRes, CMQC.MQOO_INPUT_AS_Q_DEF | CMQC.MQOO_FAIL_IF_QUIESCING);
                return this.queueOut;
            } catch (final MQException ex1) {
                throw ex1;
            } catch (final Exception ex) {
                throw ex;
            }
        }

	public String MQSendMessage(String messageIn, Integer lengthIn) 
			throws JMSException, IOException, RQSConnectionException, RQSException, MQException {
		
	    {
	        byte[] ret = null;
	        try {
	            if (this.queueOut != null) {
	                final MQMessage custDetails = new MQMessage();
	                custDetails.encoding = CMQC.MQENC_NATIVE;
	                custDetails.format = CMQC.MQFMT_STRING;
	                custDetails.writeString(messageIn);
	                this.queueOut.put(custDetails);
	                ret = custDetails.messageId;
	                
	        		// After sending a message we get message id 
	        		System.out.println(String.format("Sending message: [%s][%d]", messageIn, lengthIn));
	        		System.out.println(String.format("Message id [%s]", custDetails.messageId));
	                
	            }
	        } catch (final MQException ex1) {
	            throw ex1;
	        } catch (final Exception ex) {
	            throw ex;
	        } finally {
	            if (this.queueOut != null) {
	                this.queueOut.close();
	                this.queueOut = null;
	            }
	        }
	        return ret == null ? new String(new byte[1]) : new String(ret);
	    }
	}
	
	public String MQReceivesMessage(String messageID) 
			throws JMSException, IOException, RQSConnectionException, RQSException, MQException {
		
        byte[] ret = null;
        try {
            if (this.queueIn != null) {
            	final MQMessage mqMessageRead = new MQMessage();
                 mqMessageRead.messageId = messageID.getBytes();
                final MQGetMessageOptions gmo = new MQGetMessageOptions();
                gmo.options = CMQC.MQGMO_WAIT | CMQC.MQOO_FAIL_IF_QUIESCING;
                gmo.waitInterval = 60000;
                this.queueIn.get(mqMessageRead, gmo);
                final int longitud = mqMessageRead.getMessageLength(); // messageLength;
                ret = new byte[longitud];
                mqMessageRead.readFully(ret, 0, longitud);
                
        		System.out.println(String.format("Response message [%s]", new String(ret)));
            }
        } catch (final MQException ex1) {
            throw ex1;
        } catch (final Exception ex) {
            throw ex;
        } finally {
            if (this.queueIn != null) {
                this.queueIn.close();
                this.QM.disconnect();
                this.queueIn = null;
            }
        }
        return ret == null ? new String(new byte[1]) : new String(ret);
	}
}
