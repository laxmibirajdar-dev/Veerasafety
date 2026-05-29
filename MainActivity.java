package com.example.veerasafety;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private Button btnConnect, btnSos, btnSaveContact;
    private EditText etPhone, etName;
    private TextView tvStatus;

    private MediaRecorder mediaRecorder;
    private String audioFilePath;
    private boolean isRecording = false;
    private LocationManager locationManager;
    private String currentLatitude = "18.5204"; // Default Pune Latitude
    private String currentLongitude = "73.8567"; // Default Pune Longitude

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = findViewById(R.id.btnConnect);
        btnSos = findViewById(R.id.btnSos);
        btnSaveContact = findViewById(R.id.btnSaveContact);
        etPhone = findViewById(R.id.etPhone);
        etName = findViewById(R.id.etName);
        tvStatus = findViewById(R.id.tvStatus);

        checkPermissions();
        setupLocationListener();

        btnSaveContact.setOnClickListener(v -> saveContact());
        btnConnect.setOnClickListener(v -> connectToHardware());
        btnSos.setOnClickListener(v -> triggerEmergencyActions("Screen SOS Button"));
    }

    private void saveContact() {
        String name = etName.getText().toString();
        String phoneNumber = etPhone.getText().toString();

        SharedPreferences prefs = getSharedPreferences("VeeraSafetyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("saved_name", name);
        editor.putString("saved_phone", phoneNumber);
        editor.apply();

        // Toast message will now only display the name of the guardian
        Toast.makeText(MainActivity.this, "Contact Saved: " + name, Toast.LENGTH_SHORT).show();
    }

    private void connectToHardware() {
        new Thread(() -> {
            try {
                Socket socket = new Socket("192.168.4.1", 80);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                runOnUiThread(() -> {
                    tvStatus.setText("Wi-Fi Connected!");
                    Toast.makeText(MainActivity.this, "Wi-Fi Connected!", Toast.LENGTH_SHORT).show();
                });

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("SOS_BUTTON")) {
                        triggerEmergencyActions("Hardware Push Button");
                    } else if (line.contains("SOS_FALL")) {
                        triggerEmergencyActions("Fall or Jerk Detection");
                    }
                }
                socket.close();
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private void triggerEmergencyActions(String source) {
        runOnUiThread(() -> {
            Toast.makeText(MainActivity.this, "EMERGENCY! " + source + " Detected!", Toast.LENGTH_LONG).show();
            sendRealSms(source);
            startAudioRecording();
        });
    }
