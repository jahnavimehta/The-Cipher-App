package com.example.cipherapp

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import java.io.InputStream
import java.io.OutputStream
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

@RequiresApi(Build.VERSION_CODES.M)
class CipherHandler{

    private val keyStore= KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private val encodeCipher = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.ENCRYPT_MODE, getKey())
    }

    private fun getDecodeCipherForIv(iv: ByteArray): Cipher{
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }


    private fun getKey(): SecretKey{
        val existingKey = keyStore.getEntry("secret", null ) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey{
        return KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    "secret",
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }

    //public functions
    fun encode(bytes: ByteArray, outputStream: OutputStream): ByteArray{
        val encodedBytes = encodeCipher.doFinal(bytes)
        outputStream.use{
            it.write(encodeCipher.iv.size)
            it.write(encodeCipher.iv)
            it.write(encodedBytes.size)
            it.write(encodedBytes)
        }
        return encodedBytes
    }

    fun decode(inputStream: InputStream): ByteArray{
        return inputStream.use{
            val ivSize =it.read()
            val iv= ByteArray(ivSize)
            it.read(iv)

            val encodedBytesSize = it.read()
            val encodedBytes= ByteArray(encodedBytesSize)
            it.read(encodedBytes)

            getDecodeCipherForIv(iv).doFinal(encodedBytes)
        }
    }
    companion object{
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"

    }
}