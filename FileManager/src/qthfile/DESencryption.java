package qthfile;

import java.io.*;
import java.security.*;
import java.util.*;
import javax.crypto.*;
//�ļ�������
public class DESencryption {
	Key key;

	public DESencryption() {
	}

	public DESencryption(String str) throws NoSuchAlgorithmException {
		getKey(str);// �������str����key
	}

	// ������Ĳ���strKey�����õ�DES��ʾ���㷨����DES������Ҫ�ĵ���Կ
	public void getKey(String strKey) throws NoSuchAlgorithmException {// ������Կ�ĺ���
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(new SecureRandom(strKey.getBytes()));
		this.key =  keyGenerator.generateKey();
		keyGenerator = null;
	}

	/*
	 * ���ļ�oldFile���ܱ��浽newFile�� oldFile Ҫ���ܵ��ļ���E��/test1.txt newFile
	 * ���ܺ���ļ���E:/test2.txt
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
	 * ��newFile���ܱ��浽decFile�� newFile�Ǵ����ܵ��ļ� decFile�ǽ��ܺ���ļ�
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
		String encpasswordassword = scan.nextLine(); // ������������ʵ���ַ��������롣
		DESencryption desEncryption = new DESencryption(encpasswordassword);// �ԳƼ��ܵ���������
		System.out.println("encrypt: inuput source file and target file: ");
		String sourcee = scan.next();
		String target = scan.next();
		desEncryption.encrypt(sourcee, target); // ����
		System.out.println(" encryption is complete��");
		System.out.println("decrypt:inuput decryptioned password:");
		Scanner scanf = new Scanner(System.in);
		String decPassword = scanf.nextLine();
		DESencryption tdd = new DESencryption(decPassword);
		System.out.println("decrypt: inuput source file and target file: ");
		String sourced = scan.next();
		String targetd = scan.next();
		tdd.decrypt(sourced, targetd); // ����
		System.out.println(" decryption is complete");
	}
}