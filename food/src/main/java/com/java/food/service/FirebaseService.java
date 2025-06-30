package com.java.food.service;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {
    public void sendPushNotification(String token, String title, String message) {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(message)
                .build();

        Message firebaseMessage = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(firebaseMessage);
            System.out.println("Notificación enviada: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
}
