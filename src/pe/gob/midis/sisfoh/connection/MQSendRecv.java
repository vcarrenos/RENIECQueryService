package pe.gob.midis.sisfoh.connection;

import java.io.IOException;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import pe.gob.midis.sisfoh.exception.RQSConnectionException;
import pe.gob.midis.sisfoh.exception.RQSException;
import pe.gob.midis.sisfoh.type.PropertiesType;

public class MQSendRecv {

	private QueueSession qSession;
	private Queue qResponse;
	private PropertiesType qParams;

	public MQSendRecv(PropertiesType qParams) {
		super();
		this.qParams = qParams;
	}

	public String MQSendMessage(String messageIn, Integer lengthIn) 
			throws JMSException, IOException, RQSConnectionException, RQSException {
		
		QueueConnection qConnection;
		TextMessage txtMessage;
		String messageID = null;
		
		qConnection = MQConnectionFactory.getMQConnection(qParams);
		qConnection.start();
		
		// Create session 
		qSession = qConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// Create response queue 
		qResponse = qSession.createQueue(qParams.getApplMQQueueNameResponse());
		
		// Create text message 
		txtMessage = qSession.createTextMessage(messageIn);
		txtMessage.setJMSReplyTo(qResponse);
		txtMessage.setJMSType("mcd://xmlns");	// message type
		txtMessage.setJMSExpiration(qParams.getApplMQMessageExpiration());	// message expiration
		
		// Message delivery mode either persistent non-persistemnt
		txtMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT); 
		
		// Create sender queue 
		QueueSender queueSender = qSession.createSender(qSession.createQueue(qParams.getApplMQQueueNameRequest()));
		queueSender.setTimeToLive(qParams.getApplMQMessageExpiration());
		queueSender.send(txtMessage);
		
		// After sending a message we get message id 
		System.out.println(String.format("Sending message: [%s][%d]", messageIn, lengthIn));
		System.out.println(String.format("Message id [%s]", txtMessage.getJMSMessageID()));
		
		// Format the message id clause
		messageID = String.format("JMSMessageID='%s'", txtMessage.getJMSMessageID());
		
		return messageID;
		
	}
	
	public String MQReceivesMessage(String messageID) 
			throws JMSException, IOException, RQSConnectionException, RQSException {
		
		QueueReceiver qReceiver;
		String messageOut;

		// Within the session we have to create queue receiver
		qReceiver = qSession.createReceiver(qResponse, messageID);

		// Receive the message from 
		Message message = qReceiver.receive(qParams.getApplMQMessageExpiration());
		
		if ( message == null )
			throw new RQSConnectionException(qParams);
		
		TextMessage textMessage = (TextMessage) message; 
		messageOut = textMessage.getText();

		System.out.println(String.format("Response message [%s]", messageOut));
		
		return messageOut;

	}
}
