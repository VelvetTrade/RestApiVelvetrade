package com.Velvetrade.Velvetrade.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitializer {
    @PostConstruct
    public void initializeFirebase() throws IOException {
        System.out.println("Starting firebase");
        FileInputStream serviceAccount =
                new FileInputStream("C:\\Users\\Davin\\Documents\\GitHub\\VelveTrade\\RestApiVelvetrade\\Velvetrade\\src\\main\\java\\com\\Velvetrade\\Velvetrade\\serviceAccountKey.json");
        System.out.println("Starting firebase "+ serviceAccount.available());
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://trade-master-f6378.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

    }
}
