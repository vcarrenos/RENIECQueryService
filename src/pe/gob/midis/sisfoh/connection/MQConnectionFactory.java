package pe.gob.midis.sisfoh.connection;

import javax.jms.JMSException;
import javax.jms.QueueConnection;

import org.apache.log4j.Logger;

import pe.gob.midis.sisfoh.exception.RQSConnectionException;
import pe.gob.midis.sisfoh.exception.RQSException;
import pe.gob.midis.sisfoh.type.PropertiesType;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class MQConnectionFactory  {

	private static final Logger LOG = Logger.getLogger(MQConnectionFactory.class);
	
	public static QueueConnection getMQConnection(PropertiesType params) 
		throws RQSConnectionException, RQSException,JMSException {
		// Creating the MQ context manager
		MQQueueConnectionFactory mqcf = new MQQueueConnectionFactory();
		
		try {
			mqcf.setQueueManager(params.getApplMQQueueManager());
			mqcf.setChannel(params.getApplMQChannel());
            mqcf.setHostName(params.getApplMQHostName());
			mqcf.setPort(params.getApplMQPortNumber());
			mqcf.setTransportType(WMQConstants.WMQ_CM_CLIENT);
			
			mqcf.setClientReconnectOptions(WMQConstants.WMQ_CLIENT_RECONNECT);
			mqcf.setClientReconnectTimeout(5);			
			
			/* Create Connection */
			return mqcf.createQueueConnection("mqm", "mqm");
		
		} catch (Exception e) {
			
			LOG.error("MQError:", e);
			throw new RQSConnectionException(params);
		}

	}
}


