package com.ambiongltb.fulldashboardededdneddy;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {
        // Save the FCM registration token to a secure place (e.g., server, shared preferences)
        // You can use this token to send notifications to this device
        // Update the userToken in the model class accordingly
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Handle incoming messages (if needed)
    }
}
