# Veera Safety System - Preventive Women Safety Device 🛡️📱

An IoT-based wearable safety solution that integrates hardware-level motion sensing with a dedicated Android application to provide real-time, zero-latency emergency alerts.

---

## 🚀 Overview

The **Veera Safety System** is designed to act as a silent guardian in critical situations where accessing a smartphone manually is impossible. By seamlessly bridging the gap between embedded hardware and mobile computing, the system automatically detects distress signs (such as physical assault or sudden falls) and instantly coordinates multi-layered emergency protocols without requiring manual user interaction on the phone.

---

## 📂 Code & Repository Structure

The source code of this project is divided into standard architectural layers to ensure clean code management:

1. **`MainActivity.java` (Core Logic):** Contains the core backend Java logic implementing TCP Socket connectivity, Sensor Event Listeners, background emergency `SmsManager` tasks, and background `MediaRecorder` logic.
2. **`activity_main.xml` (UI Design):** Features a responsive front-end layout designed with XML components, including a dynamic connection status button and a prominent emergency SOS trigger dashboard.
3. **`AndroidManifest.xml` (Permissions & Config):** Manages the hardware network access and declares critical privacy permissions such as `SEND_SMS`, `ACCESS_FINE_LOCATION`, and `RECORD_AUDIO`.

---

## 🛠️ System Architecture & Features

### 1. Hardware Unit (Edge Processing)
* **Microcontroller:** `ESP32` (Leverages a dual-core architecture to execute non-blocking multitasking tasks).
* **Sensors:** `MPU6050` (A 6-axis Inertial Measurement Unit containing an integrated 3-axis Accelerometer and 3-axis Gyroscope).
* **Network Mode:** Configured in `Soft-AP (Access Point)` mode, enabling the device to host its own local Wi-Fi network for standalone reliability.

### 2. Android Application (Mobile Engine)
* **Stack:** Developed natively utilizing the `Java` programming language within Android Studio.
* **Networking:** Establishes a persistent, lightweight `TCP Socket Connection` (`IP: 192.168.4.1`, `Port: 80`) to stream real-time sensor flags directly from the hardware.

### 3. Emergency Execution Protocol
As soon as a hardware trigger (`SOS_BUTTON` or `SOS_FALL`) is processed:
1. **Live Tracking:** Fetches precise location coordinates via the Android `Fine Location API`.
2. **Automated Alerts:** Builds a dynamic Google Maps URL hyperlink and dispatches it via background SMS to the saved emergency contact.
3. **Evidence Logging:** Triggers the device's `MediaRecorder` silently in the background to log ambient audio for future legal verification.

---

## 🔄 Project Workflow & Output Gallery

#### 1️⃣ Step 1: Hardware Circuit Setup
**Description:** The hardware prototype features the ESP32 microcontroller connected to the MPU6050 sensor on a breadboard. Upon powering up, the ESP32 initializes the I2C communication protocol with the sensor and sets up a local Wi-Fi Access Point to broadcast its network.
<br><br>
<p align="center">
  <img src="1000885277.jpg" alt="Step 1: Hardware Setup" width="75%">
</p>
<br>

#### 2️⃣ Step 2: Wireless Hotspot Connection Established
**Description:** The user's Android smartphone connects directly to the dedicated Wi-Fi access point created by the ESP32. The interface allows entering emergency contact details securely, storing them via SharedPreferences, and initializing the persistent TCP Socket connection.
<br><br>
<p align="center">
  <img src="1000883249.jpg" alt="Step 2: WiFi Connected" width="45%">
</p>
<br>

#### 3️⃣ Step 3: Hardware Sensor / UI SOS Triggered
**Description:** When a sudden impact or fall is detected by the accelerometer threshold logic, or when the manual physical override button is triggered, an instant emergency state flag is generated. The app UI immediately updates to show that an emergency state has been detected.
<br><br>
<p align="center">
  <img src="1000883250.jpg" alt="Step 3: SOS Triggered Alert" width="45%">
</p>
<br>

#### 4️⃣ Step 4: Automated SMS Dispatched to Guardian
**Description:** The application automatically runs a background task using SmsManager to dispatch an instantaneous distress alert. The text message includes the precise longitude and latitude coordinates in a pre-formatted Google Maps hyperlink sent directly to the guardian's smartphone.
<br><br>
<p align="center">
  <img src="1000883251.jpg" alt="Step 4: SMS Dispatched" width="45%">
</p>
<br>

---

## 🔮 Future Enhancements

* **Custom PCB Design:** Miniaturizing the current breadboard prototype setup into a sleek, compact wearable device (such as a smart pendant or wristband).
* **Edge-AI Integration:** Deploying lightweight Machine Learning models directly on the microcontroller to filter out false positives during regular everyday activities.

## 👩‍💻 Project Credits

* **Developer:** Laxmi Birajdar
* **Course:** B.E. in Electronics and Telecommunication Engineering (E&TC)
