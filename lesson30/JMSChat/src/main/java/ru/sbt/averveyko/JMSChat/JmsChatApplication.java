package ru.sbt.averveyko.JMSChat;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.*;

@SpringBootApplication
@EnableJms
public class JmsChatApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JmsChatApplication.class, args);
	}

	@Bean
    public ConnectionFactory jmsFactory() {
        return new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
    }

    @Bean
    public Topic destination() {
	    return new ActiveMQTopic("JMSCHAT");
    }
}
