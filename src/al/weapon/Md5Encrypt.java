package al.weapon;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Encrypt {

	private static MessageDigest digest = null;

	private static final int ENCRYPT_COUNT = 2;

	public static final String toMD5High(String content) {
		
		int count = ENCRYPT_COUNT;
		
		while (count-- > 0) content = toMD5(content);
		
		return content;
	}

	

	public static final String toMD5(String str) {
		
		if (digest == null) {
			try { digest = MessageDigest.getInstance("MD5"); } 
			catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
		}
		
		digest.update(str.getBytes());
		
		return toHex(digest.digest());
	}

	public static final String toHex(byte hash[]) {
		
		StringBuilder builder = new StringBuilder(hash.length * 2);
		
		for (int i = 0; i < hash.length; i++) {
			
			if (((int) hash[i] & 0xff) < 0x10) builder.append("0");
			
			builder.append(Long.toString((int) hash[i] & 0xff, 16));
		}
		
		return builder.toString();
	}
}
