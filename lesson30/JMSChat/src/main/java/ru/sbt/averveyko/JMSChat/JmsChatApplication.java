package ru.sbt.averveyko.JMSChat;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import ru.sbt.averveyko.JMSChat.service.JMService;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;

@SpringBootApplication
@EnableJms
public class JmsChatApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JmsChatApplication.class, args);

		//Test JMS
//		JMService service = context.getBean(JMService.class);
//        service.testJM();
	}

	@Bean
    public ConnectionFactory jmsFactory() {
        return new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
    }

    @Bean
    public Topic destination() {
	    return new ActiveMQTopic("TEST");
        //return new ActiveMQTempTopic("TEST");
    }
}
