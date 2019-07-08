/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jmsclientserver;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.LoggerFactory;

public class NotificatoreQuotazioni implements MessageListener {
	
        private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NotificatoreQuotazioni.class);  
    
        Properties properties = null;
        Context jndiContext = null;
	private TopicConnectionFactory connectionFactory = null;
	private TopicConnection connection = null;
	private TopicSession session = null;
	private Topic destination = null;
	private TopicSubscriber subscriber = null;
	private TopicPublisher publisher = null;
	
	private Random randomGen = new Random();

	public void start() throws NamingException, JMSException {
		
            InitialContext ctx = null;

	try {
            
                properties = new Properties();
		properties.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
                properties.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");
                jndiContext = new InitialContext(properties);        
                
        } catch (NamingException e) {
            LOG.info("ERROR in JNDI: " + e.toString());
            System.exit(1);
        }
                
                
		ctx = new InitialContext(properties);
		this.connectionFactory =
			(TopicConnectionFactory) ctx.lookup("ConnectionFactory");
		this.destination =
			(Topic) ctx.lookup("dynamicTopics/Quotazioni");

		this.connection =
			this.connectionFactory.createTopicConnection();
		this.session =
			this.connection.createTopicSession(
					false, Session.AUTO_ACKNOWLEDGE
				);
		this.subscriber =
			this.session.createSubscriber(this.destination, null, true);
		this.publisher =
			this.session.createPublisher(this.destination);
		this.connection.start();
		
		Logger.getLogger(
				this.getClass().getName()
			).info("In attesa di richieste di acquisto...");
		
		subscriber.setMessageListener(this);
	}

        @Override
	public void onMessage(Message mex) {
            
            String nome;
            float valore;
            
            try {
                nome = mex.getStringProperty("Nome");
                valore = mex.getFloatProperty("Valore");
            } catch (Exception e) {
                    e.printStackTrace();
                    return;
            }
            
            System.out.println("Nome: " + nome + ", Valore: " + valore);
                
	}
}
