package com.example.demo;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class Listener {

	@JmsListener(destination = "inbound.queue")
	
	public void receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		String response = null;
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			//Map map = new Gson().fromJson(messageData, Map.class);
			response  = "Hello " + messageData;
			System.out.println("actual Message: "+response);
		}
		//return response;
	}

}
