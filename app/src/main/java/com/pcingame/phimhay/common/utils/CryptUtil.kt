package com.pcingame.phimhay.common.utils

import android.util.Base64
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object CryptUtil {
    // algorithm
    private const val ALGORITHM = "Blowfish"
    private const val MODE = "Blowfish/CBC/PKCS5Padding"

    // In the case of Blowfish, it is 64 bits (8 bytes)
    private const val IV = "abcdefgh"

    /**
     * Encrypt the argument string (Base64 compatible)
     * @param value Character string to be encrypted
     * @param secretKey Encryption key
     * @return String Encrypted string
     */
    @Throws(
        NoSuchPaddingException::class,
        NoSuchAlgorithmException::class,
        InvalidAlgorithmParameterException::class,
        InvalidKeyException::class,
        BadPaddingException::class,
        IllegalBlockSizeException::class
    )
    fun encrypt(value: String, secretKey: String): String? {
        val secretKeySpec =
            SecretKeySpec(secretKey.toByteArray(), ALGORITHM)
        val cipher = Cipher.getInstance(MODE)
        cipher.init(
            Cipher.ENCRYPT_MODE,
            secretKeySpec,
            IvParameterSpec(IV.toByteArray())
        )
        val values = cipher.doFinal(value.toByteArray())
        return Base64.encodeToString(values, Base64.DEFAULT)
    }

    /**
     * Decrypt the Base64 string of the argument
     * @param value Decryption target string
     * @param secretKey Decryption key
     * @return String Decrypted string
     */
    @Throws(
        NoSuchPaddingException::class,
        NoSuchAlgorithmException::class,
        InvalidAlgorithmParameterException::class,
        InvalidKeyException::class,
        BadPaddingException::class,
        IllegalBlockSizeException::class
    )
    fun decrypt(value: String?, secretKey: String): String {
        val values =
            Base64.decode(value, Base64.DEFAULT)
        val secretKeySpec =
            SecretKeySpec(secretKey.toByteArray(), ALGORITHM)
        val cipher = Cipher.getInstance(MODE)
        cipher.init(
            Cipher.DECRYPT_MODE,
            secretKeySpec,
            IvParameterSpec(IV.toByteArray())
        )
        return String(cipher.doFinal(values))
    }
}
