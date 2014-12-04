package com.appstarter.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Usage:
 * 
 * <pre>
 * String crypto = SimpleCrypto.encrypt(masterpassword, cleartext)
 * ...
 * String cleartext = SimpleCrypto.decrypt(masterpassword, crypto)
 * </pre>
 *
 * http://www.androidsnippets.com/encryptdecrypt-strings
 */

public class SimpleCryptoUtils {

	private final static String HEX = "0123456789ABCDEF";

	public static String encrypt(String seed, String cleartext) {
		String ret = "";
		try {
			byte[] rawKey = getRawKey(seed.getBytes());
			byte[] result = encrypt(rawKey, cleartext.getBytes());
			ret = toHex(result);
		} catch (Exception ignored) {
		}
		return ret;
	}

	public static String decrypt(String seed, String encrypted) {
		String ret = "";
		try {
			byte[] rawKey = getRawKey(seed.getBytes());
			byte[] enc = toByte(encrypted);
			byte[] result = decrypt(rawKey, enc);
			ret = toHex(result);
		} catch (Exception ignored) {
		}
		return ret;
	}

	private static byte[] getRawKey(byte[] seed) {
		byte[] raw;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
			sr.setSeed(seed);
			kgen.init(128, sr); // 192 and 256 bits may not be available
			SecretKey skey = kgen.generateKey();
			raw = skey.getEncoded();
		} catch (Exception e) {
			raw = new byte[0];
		}
		return raw;
	}

	private static byte[] encrypt(byte[] raw, byte[] clear) {
		byte[] encrypted;
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			encrypted = cipher.doFinal(clear);
		} catch (Exception e) {
			encrypted = new byte[0];
		}
		return encrypted;
	}

	private static byte[] decrypt(byte[] raw, byte[] encrypted) {
		byte[] decrypted;
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			decrypted = cipher.doFinal(encrypted);
		} catch (Exception e) {
			decrypted = new byte[0];
		}
		return decrypted;
	}

	public static String toHex(String txt) {
		return toHex(txt.getBytes());
	}

	public static String fromHex(String hex) {
		return new String(toByte(hex));
	}

	public static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
		return result;
	}

	public static String toHex(byte[] buf) {
		String ret = "";
		if (buf != null) {
			StringBuffer result = new StringBuffer(2 * buf.length);
			for (int i = 0; i < buf.length; i++) {
				appendHex(result, buf[i]);
			}
			ret = result.toString();
		}
		return ret;
	}

	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}

}
