package qthfile;

import java.io.*;
import java.security.*;
import java.util.*;
import javax.crypto.*;
//文件加密类
public class DESencryption {
	Key key;

	public DESencryption() {
	}

	public DESencryption(String str) throws NoSuchAlgorithmException {
		getKey(str);// 用输入的str生成key
	}

	// 用输入的参数strKey和内置的DES表示的算法生成DES加密需要的的密钥
	public void getKey(String strKey) throws NoSuchAlgorithmException {// 生成密钥的函数
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(new SecureRandom(strKey.getBytes()));
		this.key =  keyGenerator.generateKey();
		keyGenerator = null;
	}

	/*
	 * 对文件oldFile加密保存到newFile中 oldFile 要加密的文件如E：/test1.txt newFile
	 * 加密后的文件如E:/test2.txt
	 */
	public void encrypt(String oldFile, String newFile) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, this.key);
		InputStream is = new FileInputStream(oldFile);
		OutputStream out = new FileOutputStream(newFile);
		CipherInputStream cis = new CipherInputStream(is, cipher);
		byte[] buffer = new byte[1024];
		int k;
		while ((k = cis.read(buffer)) > 0) {
			out.write(buffer, 0, k);
		}
		cis.close();
		is.close();
		out.close();
	}

	/*
	 * 对newFile解密保存到decFile中 newFile是待解密的文件 decFile是解密后的文件
	 */
	public void decrypt(String newFile, String decFile) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, this.key);
		InputStream is = new FileInputStream(newFile);
		OutputStream out = new FileOutputStream(decFile);
		CipherOutputStream cos = new CipherOutputStream(out, cipher);
		byte[] buffer = new byte[1024];
		int k;
		while ((k = is.read()) >= 0) {
			cos.write(buffer, 0, k);
		}
		cos.close();
		out.close();
		is.close();
	}

	public static void DES() throws Exception {
		System.out.println("inuput encrypted password: ");
		Scanner scan = new Scanner(System.in);
		String encpasswordassword = scan.nextLine(); // 以上两行用来实现字符串的输入。
		DESencryption desEncryption = new DESencryption(encpasswordassword);// 对称加密的密码所在
		System.out.println("encrypt: inuput source file and target file: ");
		String sourcee = scan.next();
		String target = scan.next();
		desEncryption.encrypt(sourcee, target); // 加密
		System.out.println(" encryption is complete！");
		System.out.println("decrypt:inuput decryptioned password:");
		Scanner scanf = new Scanner(System.in);
		String decPassword = scanf.nextLine();
		DESencryption tdd = new DESencryption(decPassword);
		System.out.println("decrypt: inuput source file and target file: ");
		String sourced = scan.next();
		String targetd = scan.next();
		tdd.decrypt(sourced, targetd); // 解密
		System.out.println(" decryption is complete");
	}
}