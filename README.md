# The-Cipher-App

# Cipher
Your one stop solution to protect your data from the hackers!


## Table of Contents

- [Project Description](#project-description)
- [Screenshots](#screenshots)
- [Features](#features)
- [Technology Aspect](#technology-aspect)
- [Installation](#installation)
- [Usage](#usage)
- [Development Setup](#development-setup)
- [Contributing](#contributing)
- [Contact](#contact)

## Project Description

CipherApp is a powerful and secure Android app that provides message encryption and decryption using the 
AES encryption algorithm. Leveraging the Android Keystore system, the app ensures the security and confidentiality of 
user data by securely managing cryptographic keys. With a minimalist and user-friendly design, CipherApp offers a seamless 
experience for users to protect their confidential messages with ease.


## Screenshots
<p>Some screenshots of the app for a preview of the user interface and functionalities.</p>

<p align="center">
  <img src="https://github.com/jahnavimehta/The-Cipher-App/assets/97538596/3b7bb29a-2238-400f-94c9-d2d634c20f6b" alt="Screenshot" width="250"> 
  <img src="https://github.com/jahnavimehta/The-Cipher-App/assets/97538596/7f833b91-185f-4d82-9084-2d4ba3b22f12" alt="Screenshot" width="250">
  <img src="https://github.com/jahnavimehta/The-Cipher-App/assets/97538596/c885df85-24d5-4b77-9d5b-4ca7c705e36c" alt="Screenshot" width="250">
</p>





## Features

- **Easy Message Encryption:**  CipherApp offers a user-friendly interface that allows you to quickly encrypt any text message you enter. Simply type your message, click on the "Encode the data" button, and CipherApp will use AES encryption to convert your text into a secure ciphertext.

- **Secure Decryption:** Need to retrieve the original message? No problem! CipherApp provides a simple "Decode the data" button that allows you to decrypt the ciphertext and view the original text.

- **Data Security:** Your data's security is our top priority. CipherApp leverages the Android Keystore system to securely store encryption keys and ensure your messages are protected from unauthorized access.

- **Minimalist Design:** CipherApp boasts an elegant and intuitive design, providing a seamless user experience. The minimalist UI allows you to focus on the task at hand – encrypting and decrypting your messages with ease.


## Technology Aspect:

-**AES Encryption Algorithm:** CipherApp uses the Advanced Encryption Standard (AES) algorithm for message encryption. AES is a widely used symmetric encryption algorithm known for its security and efficiency. With AES, messages are transformed into ciphertext using a secret encryption key, ensuring that only authorized parties with the key can decrypt and access the original message.

-**Android Keystore System:** To ensure the security of encryption keys and protect user data, CipherApp employs the Android Keystore system. The Android Keystore provides a secure hardware-backed storage for cryptographic keys, making it resistant to tampering and unauthorized access. By using the Keystore, CipherApp can securely generate, store, and manage the encryption keys required for AES encryption and decryption.

-**Kotlin Programming Language:** CipherApp is written in Kotlin, a modern and expressive programming language that has become the preferred choice for Android app development. Kotlin offers concise syntax, null safety, and functional programming capabilities, making the codebase more robust and maintainable.

-**Compose UI Toolkit:** CipherApp utilizes the Compose UI toolkit, introduced by Android, for building the app's user interface. Compose allows for declarative and reactive UI development, enabling developers to create dynamic and responsive UIs with less boilerplate code.



**CipherHandler Class:**
- This class handles encryption and decryption using Android's Keystore system and AES encryption algorithm in Cipher Block Chaining (CBC) mode with PKCS7 padding.
-It utilizes Android's KeyStore to securely store and manage cryptographic keys.
-The getKey() function retrieves an existing AES key from the Keystore or generates a new one if it doesn't exist.
-The createKey() function generates a new AES key using KeyGenerator and specifies various encryption properties, such as block mode, padding, and encryption requirements.
-The encode() function takes a ByteArray of data to be encrypted and an OutputStream to write the encrypted data along with the initialization vector (IV) to an output file. It returns the encrypted data as a ByteArray.
-The decode() function takes an InputStream with the encrypted data and IV, decrypts the data using the corresponding key, and returns the decrypted data as a ByteArray.


**MainActivity:**
-This is the main activity that uses the CipherHandler to perform encryption and decryption.
-It sets up a Compose-based UI for user interaction.
-messageToEncodeState and messageToDecodeState are state variables used to hold the input message to be encrypted and the decrypted message, respectively.
-The Button with "Encode the data" label triggers the encryption process. The input message is converted to a ByteArray, and the encrypted data is written to a file named -"secret.txt". The encrypted data is then displayed in the messageToDecodeState.
-The Button with "Decode the data" label triggers the decryption process. The encrypted data is read from the "secret.txt" file, decrypted using the CipherHandler, and the original message is displayed in the messageToEncodeState.

**BackgroundImage Composable:**
-This composable function is responsible for displaying the background image in the UI.

**Encryption Details:**
-AES encryption algorithm with CBC mode and PKCS7 padding is used for encryption and decryption.
-The SecretKey used for encryption is stored securely in the Android Keystore, ensuring its protection.
-Random IVs are generated and used for each encryption, enhancing the security of the encryption process.

**Android API Compatibility:**
-The code uses features and classes available in Android API level 23 (Android 6.0) and above (Build.VERSION_CODES.M).
-It ensures that the code is backward-compatible for devices running Android 6.0 and later versions.


## Installation

1. Clone the repository:


$ git clone [https://github.com/jahnavimehta/The-Cipher-App]
$ cd Cipher
Open the project in your preferred Android development environment (e.g., Android Studio).

Build and run the application on an emulator or physical device.

## Usage
- Install the app on your Android device or emulator.

- Launch CipherApp and you'll be presented with a user-friendly interface.

- Type the text message you want to encrypt in the provided text field.

- Tap the "Encode the data" button to encrypt the message into ciphertext.

- Interact with the app's UI to encode data and save it in the "secret.txt" file (or the file specified in the code). Go to Device File Exporer to access the device's file system and pull the "secret.txt" file from the app's data directory to your computer.
      
- To decrypt the ciphertext back to its original form, tap the "Decode the data" button.
  

## Development Setup
- Install Android Studio and set up your development environment.

- Open the project in Android Studio.

- Make sure you have the necessary SDKs and dependencies installed.



## Contributing
We welcome contributions to enhance the app and make it more effective. If you want to contribute to the project, follow these steps:

- Fork the repository.

- Create a new branch.

- Make your changes and commit them.

- Push the changes to your forked repository.

- Open a pull request.

- Please ensure that your contributions align with the project's guidelines and coding standards.


## Contact
If you have any questions or suggestions regarding the Cipher App, feel free to contact us:

Email: jahnaviim@gmail.com
Thank you for using the Cipher App! We hope this app proves helpful in encrypting your messages with ease, ensuring privacy and peace of mind. Stay safe, stay secure.
