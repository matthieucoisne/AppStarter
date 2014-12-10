package com.appstarter.utils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;
import android.util.Log;

public class CryptoUtils {

	private static final String TAG = "AppStarter.CryptoUtils";

	public static final String PBKDF2_DERIVATION_ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

	private static String DELIMITER = "]";

	private static int KEY_LENGTH = 256;
	// minimum values recommended by PKCS#5, increase as necessary
	private static int ITERATION_COUNT = 1000;
	private static final int PKCS5_SALT_LENGTH = 8;

	private static SecureRandom random = new SecureRandom();

	private CryptoUtils() {
	}

    public static String encrypt(String plaintext, String password) {
    	String ret = "";
        try {
            byte[] salt = generateSalt();
            SecretKey key = deriveKeyPbkdf2(salt, password);
            Log.d(TAG, "Generated key: " + toHex(key.getEncoded()));
            
			ret = encrypt(plaintext, key, salt);
		} catch (Exception e) {
		}
        return ret;
    }

    public static String decrypt(String ciphertext, String password) {
    	String ret = "";
        try {
			ret = decryptPbkdf2(ciphertext, password);
		} catch (Exception e) {
		}
        return ret;
    }
    
	private static String encrypt(String plaintext, SecretKey key, byte[] salt) throws GeneralSecurityException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

		byte[] iv = generateIv(cipher.getBlockSize());
		Log.d(TAG, "IV: " + toHex(iv));
		IvParameterSpec ivParams = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);
		Log.d(TAG, "Cipher IV: " + (cipher.getIV() == null ? null : toHex(cipher.getIV())));
		byte[] cipherText = cipher.doFinal(plaintext.getBytes("UTF-8"));

		if (salt != null) {
			return String.format("%s%s%s%s%s", toBase64(salt), DELIMITER, toBase64(iv), DELIMITER, toBase64(cipherText));
		}

		return String.format("%s%s%s", toBase64(iv), DELIMITER, toBase64(cipherText));
	}

	private static String decrypt(byte[] cipherBytes, SecretKey key, byte[] iv) throws GeneralSecurityException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		IvParameterSpec ivParams = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, key, ivParams);
		Log.d(TAG, "Cipher IV: " + toHex(cipher.getIV()));
		byte[] plaintext = cipher.doFinal(cipherBytes);
		String plainrStr = new String(plaintext, "UTF-8");

		return plainrStr;
	}

	private static String decryptPbkdf2(String ciphertext, String password) throws GeneralSecurityException, UnsupportedEncodingException {
		String[] fields = ciphertext.split(DELIMITER);
		if (fields.length != 3) {
//			throw new IllegalArgumentException("Invalid encypted text format");
			return "";
		}

		byte[] salt = fromBase64(fields[0]);
		byte[] iv = fromBase64(fields[1]);
		byte[] cipherBytes = fromBase64(fields[2]);
		SecretKey key = deriveKeyPbkdf2(salt, password);

		return decrypt(cipherBytes, key, iv);
	}
	
	private static SecretKey deriveKeyPbkdf2(byte[] salt, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		long start = System.currentTimeMillis();
		KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_DERIVATION_ALGORITHM);
		byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
		Log.d(TAG, "key bytes: " + toHex(keyBytes));

		SecretKey result = new SecretKeySpec(keyBytes, "AES");
		long elapsed = System.currentTimeMillis() - start;
		Log.d(TAG, String.format("PBKDF2 key derivation took %d [ms].", elapsed));

		return result;
	}

	private static byte[] generateIv(int length) {
		byte[] b = new byte[length];
		random.nextBytes(b);
		return b;
	}

	private static byte[] generateSalt() {
		byte[] b = new byte[PKCS5_SALT_LENGTH];
		random.nextBytes(b);
		return b;
	}
	
	private static String toBase64(byte[] bytes) {
		return Base64.encodeToString(bytes, Base64.NO_WRAP);
	}

	private static byte[] fromBase64(String base64) {
		return Base64.decode(base64, Base64.NO_WRAP);
	}
	
	private static String toHex(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (byte b : bytes) {
			buff.append(String.format("%02X", b));
		}
		return buff.toString();
	}

}