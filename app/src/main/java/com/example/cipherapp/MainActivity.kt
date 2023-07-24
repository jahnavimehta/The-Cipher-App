package com.example.cipherapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cipherapp.ui.theme.CipherAppTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cipherHandler = CipherHandler()
        setContent {
            CipherAppTheme {
                val messageToEncodeState = remember { mutableStateOf(" ") }
                val messageToDecodeState = remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        BackgroundImage()
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(32.dp)
                        ) {
                            TextField(
                                value = messageToEncodeState.value,
                                onValueChange = { messageToEncodeState.value = it },
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text(text = "Encode string") }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row {
                                Button(
                                    onClick = {
                                        val bytes = messageToEncodeState.value.encodeToByteArray()
                                        val file = File(filesDir, "secret.txt")
                                        if (!file.exists()) {
                                            file.createNewFile()
                                        }
                                        val fos = FileOutputStream(file)

                                        messageToDecodeState.value = cipherHandler.encode(
                                            bytes = bytes,
                                            outputStream = fos
                                        ).decodeToString()
                                    }
                                ) {
                                    Text(text = "Encode the data")
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Button(
                                    onClick = {
                                        val file = File(filesDir, "secret.txt")
                                        messageToEncodeState.value = cipherHandler.decode(
                                            inputStream = FileInputStream(file)
                                        ).decodeToString()
                                    }
                                ) {
                                    Text(text = "Decode the data")
                                }
                            }
                            Text(text = messageToDecodeState.value)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun BackgroundImage() {
        Image(
            painter = painterResource(id = R.drawable.your_background_image), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}
