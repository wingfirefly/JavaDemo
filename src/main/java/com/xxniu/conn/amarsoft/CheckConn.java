package com.xxniu.conn.amarsoft;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class CheckConn {
	private static String dirString = "E:/eclipse/workspacebak/huaxia-cai-laon/hxadmin/src";
	private static boolean isPrintFileName = true;
	private static boolean check = false;
	private static File outfile = null;
	private Vector<ConnObject> rsNotclose = new Vector<ConnObject>();
//	private Vector<ConnObject> rsYeslose = new Vector<ConnObject>();

	public static void main(String[] paramArrayOfString) {
		if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
			dirString = paramArrayOfString[0];
		File localFile = new File(dirString);
		outfile = new File(localFile.getParentFile().getPath(), "连接关闭查询结果.txt");
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss:SSSS");
		CheckConn localCheckConn = new CheckConn();
		Calendar localCalendar = Calendar.getInstance();
		System.out
				.println(localSimpleDateFormat.format(localCalendar.getTime())
						+ "-------文件读取开始-----------------");
		Long localLong = Long.valueOf(System.nanoTime());
		localCalendar = Calendar.getInstance();
		localCheckConn.readFileOrPath(localFile);
		if (!(check))
			return;
		System.out
				.println(localSimpleDateFormat.format(localCalendar.getTime())
						+ "--------文件读取结束------"
						+ ((System.nanoTime() - localLong.longValue()) / 1000000000.0D)
						+ "s");
		localCheckConn.writeOut();
		localCalendar = Calendar.getInstance();
		System.out
				.println(localSimpleDateFormat.format(localCalendar.getTime())
						+ "-------查找完成，结果保存在文件：" + outfile.getPath() + "中。");
	}

	public void readFileOrPath(File paramFile) {
		if (paramFile.isFile()) {
			if ((!(paramFile.getName().endsWith("jsp"))) && (!(paramFile.getName().endsWith("java"))))
				return;
			readFile(paramFile);
		} else {
			File[] arrayOfFile1 = paramFile.listFiles();
			for (File localFile : arrayOfFile1)
				readFileOrPath(localFile);
		}
		// System.out.println("工具已过时，不建议使用，请申请最新的工具！");
	}

	private boolean checkDate() {
		if (check)
			return true;
		try {
			if (new Date().getTime() > 1533171655303L)
				return false;
			check = true;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return check;
	}

	@SuppressWarnings("unused")
	private boolean checkMac() {
		if (check)
			return true;
		String str1 = "877966f521f20b98cd096b1a51bd9184c6c637d7";
		String str2 = "";
		try {
			Enumeration<?> localEnumeration = NetworkInterface
					.getNetworkInterfaces();
			while (localEnumeration.hasMoreElements()) {
				NetworkInterface localNetworkInterface = (NetworkInterface) localEnumeration
						.nextElement();
				byte[] arrayOfByte1 = localNetworkInterface
						.getHardwareAddress();
				if (arrayOfByte1 == null)
					continue;
				if (arrayOfByte1.length < 5)
					continue;
				str2 = hexByte(arrayOfByte1[0]) + "-"
						+ hexByte(arrayOfByte1[1]) + "-"
						+ hexByte(arrayOfByte1[2]) + "-"
						+ hexByte(arrayOfByte1[3]) + "-"
						+ hexByte(arrayOfByte1[4]) + "-"
						+ hexByte(arrayOfByte1[5]);
				try {
					MessageDigest localMessageDigest = MessageDigest
							.getInstance("SHA");
					localMessageDigest.update(str2.toUpperCase().getBytes());
					byte[] arrayOfByte2 = localMessageDigest.digest();
					StringBuffer localStringBuffer = new StringBuffer();
					for (int i = 0; i < arrayOfByte2.length; ++i) {
						int j = arrayOfByte2[i];
						localStringBuffer.append(
								Integer.toString(j >> 4 & 0xF, 16)).append(
								Integer.toString(j & 0xF, 16));
					}
					if (str1.equals(localStringBuffer.toString())) {
						check = true;
						return check;
					}
				} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
					localNoSuchAlgorithmException.printStackTrace();
				}
			}
		} catch (SocketException localSocketException) {
			localSocketException.printStackTrace();
		}
		return false;
	}

	private String hexByte(byte paramByte) {
		String str = "000000" + Integer.toHexString(paramByte);
		return str.substring(str.length() - 2);
	}

	public void readFile(File paramFile) {
		Vector<String> localVector = new Vector<String>();
		HashMap<String, ConnObject> localHashMap = new HashMap<String, ConnObject>();
		BufferedReader localBufferedReader = null;
		ConnObject localConnObject = null;
		int i = 0;
		int j = 0;
		String str1 = "";
		String str2 = "";
		String str3 = "";
		int k = 0;
		try {
			str3 = paramFile.getPath().replace(dirString + "\\", "");
			if (isPrintFileName)
				System.out.println("-----" + str3);
			localBufferedReader = new BufferedReader(new FileReader(paramFile));
			str2 = localBufferedReader.readLine();
			if (str2 != null)
				str2 = str2.trim();//去前后空格
			++j;
			String str4;
			while (str2 != null) {
				if ((str2 == "") || (str2.startsWith("//"))) {//如果本行为空 或者为注释 读下一行
					str2 = localBufferedReader.readLine();
					if (str2 != null)
						str2 = str2.trim();
					++j;
				}
				if ((str2.matches(".*=\\s*Transaction\\.createTransaction\\(\\s?tx\\d?\\s?\\).*")||
							str2.matches(".*=\\s*\\(Transaction\\)\\s?allTransaction\\.get.*")||
							str2.matches(".*=\\s*Transaction\\.buildTransaction.*")||
							str2.matches(".*=\\s*Transaction\\.call\\(.*")||
							str2.matches(".*=\\s*messageForJSON.*|.*=\\s*messageForFixsize.*|.*=\\s*messageForXml.*|.*=\\s*ResourceConfig.*"))) {
					str2 = localBufferedReader.readLine();
					if (str2 != null)
						str2 = str2.trim();
					++j;
//					continue;
				}
				if (str2.indexOf("/*") != -1) {//如果本行为注释 读下一行
					while (str2.indexOf("*/") == -1) {
						str2 = localBufferedReader.readLine();
						if (str2 != null) {
							str2 = str2.trim();
						} else {
							System.out.println("-------文件：" + str3
									+ "--------------------注释不完整--------");
							break;
						}
						++j;
					}
					str2 = str2.substring(str2.indexOf("*/") + 2).trim();
				}
				i = str2.indexOf("//");//截取注释
				if (i != -1)
					str2 = str2.substring(0, i);
				i = str2.indexOf("<%");//如果是JSP 去掉<%
				if (i != -1)
					str2 = str2.substring(i + 2).trim();
				if ((str2.indexOf("Connection ")>-1)
						|| (str2.indexOf("Transaction ")>-1)
						|| (str2.indexOf("JBOTransaction ")>-1)) {
					i = str2.indexOf("=");
					if ((str2.startsWith("Transaction"))
							&& (str2.matches(".*=\\s*Transaction\\.createTransaction\\(\\s?tx\\d?\\s?\\).*"))) {
						str2 = localBufferedReader.readLine();
						if (str2 != null)
							str2 = str2.trim();
						++j;
					}
					int l;
					int i1;
					if ((i == -1) || (str2.matches(".*=\\s*null\\W.*"))) {
						if(str2.matches(".*\\(.*Transaction.*\\).*|.*\\(.*Connection.*\\).*|.*\\(.*JBOTransaction.*\\).*")){
							break;
						}
						l = str2.indexOf("{");
						if (l > 0)
							str2 = str2.substring(0, l);
						l = str2.indexOf(" throws ");
						if (l > 0)
							str2 = str2.substring(0, l);
						String[] arrayOfString = str2
								.replaceAll("=\\s*null", " ")
								.replaceAll("\\(|\\)|\\.|\\<|\\>", " ")
								.replaceAll("\\s{2,}", " ").split("\\,|;");
						for (i1 = 0; i1 < arrayOfString.length; ++i1) {
							if ((arrayOfString[i1] == null)
									|| (arrayOfString[i1].length() <= 0))
								continue;
							str1 = arrayOfString[i1].trim();
							if (i1 == 0){
								String[] list = str1.split("\\W");
								str1 = list[list.length-1];
							}
								
							else if (str1.split("\\W").length > 1)
								break;
							if (localVector.contains(str1))
								continue;
							localVector.add(str1);
							localConnObject = new ConnObject(str1, "", "", 0,
									0, str3);
							localConnObject.sType = str2.split("\\W")[0];
							localHashMap.put(str1, localConnObject);
						}
					} else {
						str1 = str2.substring(str2.indexOf(" "), i).trim();
						str1 = str1.split("\\W")[0];
						l = 1;
						i1 = str2.indexOf(";");
						if (i1 > 0) {
							String str5 = str2.substring(i + 1, i1).trim();
							if (str5.matches("\\w*"))
								l = 0;
						}
						if (!(localVector.contains(str1))) {
							localVector.add(str1);
							localConnObject = new ConnObject(str1, "", "", 0,
									0, str3);
							localConnObject.sType = str2.split("\\W")[0];
							localHashMap.put(str1, localConnObject);
						}
						if (l != 0) {
							((ConnObject) localHashMap.get(str1)).iopen += 1;
							((ConnObject) localHashMap.get(str1)).setOpenRows(j
									+ ",");
							((ConnObject) localHashMap.get(str1))
									.setRowString(str2);
						}
					}
				} else {
					Iterator<String> localIterator = localVector.iterator();
					while (localIterator.hasNext()) {
						str4 = (String) localIterator.next();
						k = 0;
						try {
							if (str2.matches(".*=\\s*Transaction\\.createTransaction\\(\\s?tx\\d?\\s?\\).*")||
									str2.matches(".*=\\s*\\(Transaction\\)\\s?allTransaction\\.get.*")||
									str2.matches(".*=\\s*Transaction\\.buildTransaction.*")||
									str2.matches(".*=\\s*Transaction\\.call\\(.*")||
									str2.matches(".*=\\s*messageForJSON.*|.*=\\s*messageForFixsize.*|.*=\\s*messageForXml.*|.*=\\s*ResourceConfig.*")) {
								k = 1;
								break;
							}
							if (str2.matches(str4 + "\\s*=.*")) {
								i = str2.indexOf("=");
								int i2 = str2.indexOf(";");
								if (i2 > 0) {
									String str6 = str2.substring(i + 1, i2)
											.trim();
									if (str6.matches("\\w*")) {
										k = 1;
										break;
									}
								}
								if (str2.matches(".*=\\s*null.*")) {
									k = 1;
									break;
								}
								((ConnObject) localHashMap.get(str4)).iopen += 1;
								((ConnObject) localHashMap.get(str4)).iopenrow = j;
								((ConnObject) localHashMap.get(str4)).icloserow = 0;
								((ConnObject) localHashMap.get(str4))
										.setOpenRows(j + ",");
								((ConnObject) localHashMap.get(str4))
										.setRowString(str2);
								k = 1;
								break;
							}
							if ((str2.matches(".*" + str4 + "\\..*close().*"))
									|| (str2.matches(".*" + str4
											+ "\\..*disConnect().*"))
									|| ((str2.matches(".*" + str4
											+ "\\..*commit().*")) && (((((ConnObject) localHashMap
											.get(str4)).sType
											.equals("JBOTransaction")) || (((ConnObject) localHashMap
											.get(str4)).sType
											.equals("Transaction")))))
									|| (str2.matches("\\s*return\\s*" + str4
											+ "\\s*;.*"))
									||(str2.matches("Tools.closeDB\\("+ str4 +".*"))) {
								((ConnObject) localHashMap.get(str4)).iclose += 1;
								((ConnObject) localHashMap.get(str4)).icloserow = j;
								((ConnObject) localHashMap.get(str4))
										.setCloseRows(j + ",");
								k = 1;
								break;
							}
						} catch (Exception localException6) {
							System.out
									.println("---=="
											+ ((ConnObject) localHashMap
													.get(str4)).sType
											+ "--------" + str4 + "===" + str2);
							localException6.printStackTrace();
						}
					}
					if ((k == 0) && (str2.matches("\\s*return\\s*.*"))) {
						localIterator = localVector.iterator();
						while (localIterator.hasNext()) {
							str4 = (String) localIterator.next();
							if ((((ConnObject) localHashMap.get(str4)).iopenrow > 0)
									&& (((ConnObject) localHashMap.get(str4)).iopenrow < j)
									&& (((ConnObject) localHashMap.get(str4)).icloserow == 0)) {
								((ConnObject) localHashMap.get(str4)).iopen += 10;
								((ConnObject) localHashMap.get(str4))
										.setOpenRows("r" + j + ",");
							}
						}
					}
				}
				str2 = localBufferedReader.readLine();
				if (str2 != null)
					str2 = str2.trim();
				++j;
			}
			Iterator<String> localIterator = localVector.iterator();
			while (localIterator.hasNext()) {
				str4 = (String) localIterator.next();
				localConnObject = (ConnObject) localHashMap.get(str4);
				if (localConnObject.iclose < localConnObject.iopen)
					this.rsNotclose.add(localConnObject);
				else {
//					this.rsYeslose.add(localConnObject);
				}
			}
		} catch (FileNotFoundException localException2) {
			System.out.println("文件[" + paramFile.getAbsolutePath() + "] 不存在!");
			localException2.printStackTrace();
		} catch (IOException localException3) {
			localException3.printStackTrace();
		} catch (Exception localException5) {
			System.out.println("===================" + str2);
			localException5.printStackTrace();
		} finally {
			try {
				localBufferedReader.close();
			} catch (Exception localException7) {
				localException7.printStackTrace();
			}
		}
	}

	public void writeOut() {
		OutputStreamWriter localOutputStreamWriter = null;
		BufferedWriter localBufferedWriter = null;
		FileOutputStream localFileOutputStream = null;
		try {
			localFileOutputStream = new FileOutputStream(outfile, false);
			localOutputStreamWriter = new OutputStreamWriter(
					localFileOutputStream);
			localBufferedWriter = new BufferedWriter(localOutputStreamWriter);
			localBufferedWriter.append("文件名称@类型@名称@赋值/关闭@次数@赋值/关闭结果集的行数@代码");
			localBufferedWriter.newLine();
			Iterator<ConnObject> localIterator = this.rsNotclose.iterator();
			while (localIterator.hasNext()) {
				ConnObject localConnObject = (ConnObject) localIterator.next();
				localBufferedWriter.append(localConnObject.getFileName())
						.append("@").append(localConnObject.sType).append("@")
						.append(localConnObject.getRsName()).append("@赋值@")
						.append(localConnObject.getIopen() + "@")
						.append(localConnObject.getOpenRows() + "@")
						.append(localConnObject.getRowString());
				localBufferedWriter.newLine();
				localBufferedWriter.append(
						"@@@关闭@" + localConnObject.getIclose() + "@").append(
						localConnObject.getCloseRows());
				localBufferedWriter.newLine();
			}
			localBufferedWriter.flush();
		} catch (FileNotFoundException localIOException2) {
			System.out.println("文件[" + outfile.getAbsolutePath() + "] 不存在!");
			localIOException2.printStackTrace();
		} catch (IOException localIOException4) {
			localIOException4.printStackTrace();
		} finally {
			try {
				localBufferedWriter.close();
				localOutputStreamWriter.close();
				localFileOutputStream.close();
			} catch (IOException localIOException5) {
				localIOException5.printStackTrace();
			}
		}
	}
}

/*
 * Location: E:\项目\jcpt\批量\转发：wd 关于系统上线前结果集与连接未关闭程序扫描的提醒事宜\checkconn_2017.jar
 * Qualified Name: com.amarsoft.checkconn.CheckConn JD-Core Version: 0.5.3
 */