package com.xxniu.file.FileEncrypter;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JFileChooser;

public class FileEncrypter {
	public static final String KEY_CG = "AD67EA2F3BE6E5ADD368DFE03120B5DF92A8FD8FEC2F0746";
	private static String Type = "jiami";
	private static String FileDir = "jiami";

	public static void main(String[] args) {
		if ((args != null) && (args.length >= 0)) {
			for (int i = 0; i < args.length; ++i) {
				System.out.println("第" + (i + 1) + "个参数：" + args[i]);
				if (args[i].startsWith("Type="))
					Type = args[i].replace("Type=", "").trim();
				else if (args[i].startsWith("FileDir=")) {
					FileDir = args[i].replace("FileDir=", "").trim();
				}
			}
		}

		if ("jiami".equals(Type))
			cg_jiami(FileDir);
		else if ("jiemi".equals(Type))
			cg_jiemi(FileDir);
	}

	private static void cg_jiemi(String dir) {
		try {
			File file = new File(dir);
			if ((file.exists()) && (file.isDirectory())) {
				File[] listFiles = file.listFiles(new FileFilter() {
					public boolean accept(File pathname) {
						return pathname.getName().endsWith(".txt");
					}
				});
				for (int i = 0; i < listFiles.length; ++i) {
					System.out.println(listFiles[i].getAbsolutePath());
					new FileEncrypter().DEncypt(listFiles[i].getAbsolutePath(),
							"AD67EA2F3BE6E5ADD368DFE03120B5DF92A8FD8FEC2F0746");
				}
				return;
			}
			file.mkdir();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void cg_jiami(String dir) {
		try {
			File file = new File(dir);
			if ((file.exists()) && (file.isDirectory())) {
				File[] listFiles = file.listFiles(new FileFilter() {
					public boolean accept(File pathname) {
						return pathname.getName().endsWith(".txt");
					}
				});
				for (int i = 0; i < listFiles.length; ++i) {
					System.out.println(listFiles[i].getAbsolutePath());
					new FileEncrypter().Encrypt(listFiles[i].getAbsolutePath(),
							"AD67EA2F3BE6E5ADD368DFE03120B5DF92A8FD8FEC2F0746");
				}
				return;
			}
			file.mkdir();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Encrypt(String fileIn, String sKey) {
		System.out.println("key:" + sKey);
		File file = new File(fileIn);
		if (file.isDirectory()) {
			for (File f : file.listFiles())
				encrypt(f, sKey);
		} else if (file.isFile())
			encrypt(file, sKey);
	}

	public void DEncypt(String fileIn, String sKey) throws Exception {
		File file = new File(fileIn);
		System.out.println("开始解密。。。。。");
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				System.out.println("正在解密" + f.getName() + "******");
				decrypt(f, sKey);
				System.out.println("解密成功" + f.getName() + "******");
			}
		} else if (file.isFile()) {
			System.out.println("正在解密" + file.getName() + "******");
			decrypt(file, sKey);
			System.out.println("解密成功" + file.getName() + "******");
		}
		System.out.println("完成解密。。。。。");
	}

	private void encrypt(File fileIn, String sKey) {
		byte[] bytOut = null;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			if (sKey.length() == 48) {
				byte[] bytK1 = getKeyByStr(sKey.substring(0, 16));
				byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));
				byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));

				fis = new FileInputStream(fileIn);
				byte[] bytIn = new byte[(int) fileIn.length()];
				for (int i = 0; i < fileIn.length(); ++i) {
					bytIn[i] = (byte) fis.read();
				}
				bytOut = encryptByDES(encryptByDES(encryptByDES(bytIn, bytK1), bytK2), bytK3);
				String fileOut = fileIn.getPath();
				fos = new FileOutputStream(fileOut);
				for (int i = 0; i < bytOut.length; ++i)
					fos.write(bytOut[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fis = null;
			fos = null;
		}
	}

	private void decrypt(File fileIn, String sKey) throws Exception {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			if (sKey.length() == 48) {
				String strPath = fileIn.getPath();
				System.out.println(strPath);
				strPath = strPath.substring(0, strPath.length());
				System.out.println(strPath);
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setSelectedFile(new File(strPath));
				System.out.println(sKey.length());
				byte[] bytK1 = getKeyByStr(sKey.substring(0, 16));
				byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));
				byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));

				fis = new FileInputStream(fileIn);
				byte[] bytIn = new byte[(int) fileIn.length()];
				for (int i = 0; i < fileIn.length(); ++i) {
					bytIn[i] = (byte) fis.read();
				}

				byte[] bytOut = decryptByDES(decryptByDES(decryptByDES(bytIn, bytK3), bytK2), bytK1);
				File fileOut = chooser.getSelectedFile();
				fileOut.createNewFile();
				fos = new FileOutputStream(fileOut);
				for (int i = 0; i < bytOut.length; ++i)
					fos.write(bytOut[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("解密错误！" + e);
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fis = null;
			fos = null;
		}
	}

	private byte[] encryptByDES(byte[] bytP, byte[] bytKey) throws Exception {
		DESKeySpec desKS = new DESKeySpec(bytKey);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		Cipher cip = Cipher.getInstance("DES");
		cip.init(1, sk);
		return cip.doFinal(bytP);
	}

	private byte[] decryptByDES(byte[] bytE, byte[] bytKey) throws Exception {
		DESKeySpec desKS = new DESKeySpec(bytKey);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		Cipher cip = Cipher.getInstance("DES");
		cip.init(2, sk);
		return cip.doFinal(bytE);
	}

	private byte[] getKeyByStr(String str) {
		byte[] bRet = new byte[str.length() / 2];
		for (int i = 0; i < str.length() / 2; ++i) {
			Integer itg = new Integer(16 * getChrInt(str.charAt(2 * i)) + getChrInt(str.charAt(2 * i + 1)));
			bRet[i] = itg.byteValue();
		}
		return bRet;
	}

	private int getChrInt(char chr) {
		int iRet = 0;
		if (chr == "0".charAt(0))
			iRet = 0;
		if (chr == "1".charAt(0))
			iRet = 1;
		if (chr == "2".charAt(0))
			iRet = 2;
		if (chr == "3".charAt(0))
			iRet = 3;
		if (chr == "4".charAt(0))
			iRet = 4;
		if (chr == "5".charAt(0))
			iRet = 5;
		if (chr == "6".charAt(0))
			iRet = 6;
		if (chr == "7".charAt(0))
			iRet = 7;
		if (chr == "8".charAt(0))
			iRet = 8;
		if (chr == "9".charAt(0))
			iRet = 9;
		if (chr == "A".charAt(0))
			iRet = 10;
		if (chr == "B".charAt(0))
			iRet = 11;
		if (chr == "C".charAt(0))
			iRet = 12;
		if (chr == "D".charAt(0))
			iRet = 13;
		if (chr == "E".charAt(0))
			iRet = 14;
		if (chr == "F".charAt(0))
			iRet = 15;
		return iRet;
	}

	public String md5s(String plainText) {
		String str = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte[] b = md.digest();

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; ++offset) {
				int i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
	}
}