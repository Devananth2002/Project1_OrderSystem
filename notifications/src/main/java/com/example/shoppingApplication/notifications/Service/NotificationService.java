package com.example.shoppingApplication.notifications.Service;

import com.example.shoppingApplication.notifications.Response.OrderResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;
    public final ObjectMapper objectMapper;

    public NotificationService(JavaMailSender mailSender, ObjectMapper objectMapper) {
        this.mailSender = mailSender;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order_notification", groupId = "order-service")
    public void notificationListener(String orderNotification){
        System.out.println(orderNotification);

        OrderResponse orderResponse = objectMapper.readValue(orderNotification, OrderResponse.class);

        String to = orderResponse.getCustomerEmail();
        String subject = "Order Successfully Placed #order id " + orderResponse.getOrderId();

        String body = "Hi " + orderResponse.getCustomerName()
                + "\nYour order was successfully placed and will be deliver to the below address within 5 working days"
                + "\n" + orderResponse.getOrderAddress()
                + "\nThank you";
        sendSimpleEmail(to, subject, body);
    }

    public void sendSimpleEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("adevananth2002@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

}
