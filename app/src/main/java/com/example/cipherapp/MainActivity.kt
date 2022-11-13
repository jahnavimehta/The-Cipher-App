package com.example.cipherapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cipherapp.ui.theme.CipherAppTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FilterInputStream

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val CipherHandler = CipherHandler()
        setContent {
            CipherAppTheme {

                var messageToEncode by remember {
                    mutableStateOf(" ")
                }

                var messageToDecode by remember {
                    mutableStateOf("")
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    TextField(value = messageToEncode,
                        onValueChange = { messageToEncode = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Encode string")}
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Button(onClick = {
                            val bytes = messageToEncode.encodeToByteArray()
                            val file =File(filesDir, "secret.txt")
                            if (!file.exists()){
                                file.createNewFile()
                            }
                            val fos = FileOutputStream(file)

                            messageToDecode = CipherHandler.encode(
                                bytes = bytes,
                                outputStream = fos
                            ).decodeToString()

                        }) {
                                Text(text = "Encode the data")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = {
                            val file = File(filesDir,"secret.txt")
                            messageToEncode = CipherHandler.decode(
                                inputStream =  FileInputStream(file)
                            ).decodeToString()
                        }) {
                              Text(text = "Decode the data")
                        }
                    }
                    Text(text = messageToDecode)
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    CipherAppTheme {
//        Greeting("Android")
//    }
//}