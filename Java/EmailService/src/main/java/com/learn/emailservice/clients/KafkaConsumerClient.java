package com.learn.emailservice.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.emailservice.Dtos.EmailDto;
import com.learn.emailservice.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

@Component
public class KafkaConsumerClient {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "UserSignUp", groupId = "email-service")
    public void consumeMessage(String message) throws JsonProcessingException {
        EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailDto.getFrom(), "jxxb erif qudu auaw");
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, emailDto.getTo(), emailDto.getSubject(), emailDto.getBody());
        System.out.println("Consumed message: " + message);
    }
}
