# Veera Safety System - Preventive Women Safety Device 🛡️📱

An IoT-based wearable safety solution that integrates hardware-level motion sensing with a dedicated Android application to provide real-time, zero-latency emergency alerts.

---

## 🚀 Overview

The **Veera Safety System** is designed to act as a silent guardian in critical situations where accessing a smartphone manually is impossible. By bridging the gap between embedded hardware and mobile computing, the system automatically detects distress signs (like physical assault or sudden falls) and instantly coordinates multi-layered emergency protocols.

---

## 📂 Code & Repository Structure

Hamare project ka source code alag-alag layers mein standard structure ke anusar divide kiya gaya hai, jise aap is repository mein dekh sakte hain:

1. **`MainActivity.java` (Core Logic):** Isme TCP Socket connectivity, Sensor Event Listener, SmsManager, aur background MediaRecorder ka main backend Java logic implement kiya gaya hai.
2. **`activity_main.xml` (UI Design):** App ka responsive front-end design, jisme dynamic Connect button aur emergency red SOS trigger box design kiya gaya hai.
3. **`AndroidManifest.xml` (Permissions):** Hardware network accessibility ke liye aur safety parameters (`SEND_SMS`, `ACCESS_FINE_LOCATION`, `RECORD_AUDIO`) ki critical configurations handle ki gayi hain.

---

## 🛠️ System Architecture & Features

### 1. Hardware Unit (Edge Processing)
* **Microcontroller:** `ESP32` (Dual-Core architecture utilized for non-blocking multitasking).
* **Sensors:** `MPU6050` (6-axis Inertial Measurement Unit containing a 3-axis Accelerometer and 3-axis Gyroscope).
* **Network Mode:** Configured in `Soft-AP (Access Point)` mode, enabling the device to create its own local Wi-Fi hotspot.

### 2. Android Application (Mobile Engine)
* **Stack:** Developed natively in `Java` using Android Studio.
* **Networking:** Establishes a highly reliable, persistent `TCP Socket Connection` (`IP: 192.168.4.1`, `Port: 80`) to stream real-time event flags from the hardware.

### 3. Emergency Execution Protocol
Upon receiving a hardware trigger (`SOS_BUTTON` or `SOS_FALL`):
1. **Live Tracking:** Fetches highly precise GPS coordinates using the Android `Fine Location API`.
2. **Automated Alerts:** Constructs a dynamic Google Maps URL and dispatches it via `SmsManager` to the saved guardian contact.
3. **Evidence Logging:** Triggers the phone's `MediaRecorder` in the background to capture ambient audio coordinates for future legal evidence.

---

## 🔄 Project Workflow & Output Gallery

Niche diye gaye sequence ke anusar aap hamare project ka complete step-by-step working aur live hardware testing screenshots dekh sakte hain:

#### 1️⃣ Step 1: Hardware Circuit Setup
Device ko power dene ke baad ESP32 aur MPU6050 Active ho jaate hain aur local hotspot transmission shuru karte hain.
<br><br>
<p align="center">
  <img src="1000885277.jpg" alt="Step 1: Hardware Setup" width="75%">
</p>
<br>

#### 2️⃣ Step 2: Wireless Hotspot Connection Established
Android Device ko ESP32 ke Access Point network se connect kiya jata hai, jisse Wi-Fi connection stable ho sake.
<br><br>
<p align="center">
  <img src="1000883249.jpg" alt="Step 2: WiFi Connected" width="45%">
</p>
<br>

#### 3️⃣ Step 3: Hardware Sensor / UI SOS Triggered
Jab device ko sudden jerk lagta hai (fall detection) ya user hardware manual button/screen button ko press karta hai, toh instant emergency state detect hoti hai.
<br><br>
<p align="center">
  <img src="1000883250.jpg" alt="Step 3: SOS Triggered Alert" width="45%">
</p>
<br>

#### 4️⃣ Step 4: Automated SMS Dispatched to Guardian
App foreground task block se automatic SMS deliver karti hai pre-saved contact number par, jisme exact live coordinates ka Maps link hota hai.
<br><br>
<p align="center">
  <img src="1000883251.jpg" alt="Step 4: SMS Dispatched" width="45%">
</p>
<br>

---

## 🔮 Future Enhancements

* **Custom PCB Design:** Miniaturizing the current breadboard prototype into a sleek wearable device.
* **Edge-AI Integration:** Deploying light Machine Learning models directly on the microcontroller to minimize false positives during everyday movements.

## 👩‍💻 Project Credits

* **Developer:** Laxmi Birajdar
* **Course:** B.E. in Electronics and Telecommunication Engineering (E&TC)
