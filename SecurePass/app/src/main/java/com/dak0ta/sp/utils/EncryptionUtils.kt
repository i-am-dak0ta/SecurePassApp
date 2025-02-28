package com.dak0ta.sp.utils

import android.util.Base64
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object EncryptionUtils {

    private const val KEY = "1234567890123456"
    private const val IV = "abcdefghijklmnop"

    private const val TRANSFORMATION = "AES/CBC/PKCS5Padding"
    private const val ALGORITHM = "AES"

    fun encrypt(plainText: String): ByteArray {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val keySpec = SecretKeySpec(KEY.toByteArray(Charset.forName("UTF-8")), ALGORITHM)
        val ivSpec = IvParameterSpec(IV.toByteArray(Charset.forName("UTF-8")))
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        return cipher.doFinal(plainText.toByteArray(Charset.forName("UTF-8")))
    }

    fun decrypt(encryptedData: ByteArray): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val keySpec = SecretKeySpec(KEY.toByteArray(Charset.forName("UTF-8")), ALGORITHM)
        val ivSpec = IvParameterSpec(IV.toByteArray(Charset.forName("UTF-8")))
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        return String(cipher.doFinal(encryptedData), Charset.forName("UTF-8"))
    }

    fun encryptToBase64(plainText: String): String =
        Base64.encodeToString(encrypt(plainText), Base64.NO_WRAP)
}
