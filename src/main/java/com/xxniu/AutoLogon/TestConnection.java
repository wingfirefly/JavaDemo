package com.xxniu.AutoLogon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ģ���¼ϵͳ�ļ�ʾ��
 * ��ʱ����Բ���һ���Ƿ���Ե�½�ɹ�
 */

public class TestConnection {
	static String sURL = "http://localhost:8000/ALS7/Logon.jsp";
	static String responseCookie;// ��ʾSession����

	// ���Ե�¼���ܣ����ء��Զ�����¼���ҳ��
	public static String login(String usr, String pwd) throws IOException {
		StringBuilder sbR = new StringBuilder();

		// ����URL��������Ϣ����sb��
		// �������˵�¼�ɹ��󣬷���˵Ĵ����������Ĵ���
		// response.sendRedirect("welcome.jsp");
		// ��᲻�ɹ���ԭ��(Step2��û���ϴ�jsessionidֵ������ûsession)����
		// Step1[login.jsp��¼�ɹ�]->ת��->
		// Step2[welcome.jsp���ܵõ�session���ж�û�е�¼�ɹ�]->ת��->Step3[login.jspҪ���û���¼]

		URL url = new URL(sURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);// ���������ύ��Ϣ
		connection.setRequestMethod("POST");// ��ҳĬ�ϡ�GET���ύ��ʽ

		StringBuffer sb = new StringBuffer();
		sb.append("Name=" + usr);
		sb.append("&Password=" + pwd);
		connection.setRequestProperty("Content-Length", String.valueOf(sb.toString().length()));

		OutputStream os = connection.getOutputStream();
		os.write(sb.toString().getBytes());
		os.close();

		// ȡCookie
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		responseCookie = connection.getHeaderField("Set-Cookie");// ȡ�����õ�Cookie
		System.out.println("cookie:" + responseCookie);

		// ȡ���ص�ҳ��
		String line = br.readLine();
		while (line != null) {
			sbR.append(line);
			line = br.readLine();
		}

		return sbR.toString();
	}

	// ����ҳ��
	public static String viewPage() throws IOException {
		StringBuilder sbR = new StringBuilder();

		// ��URL����
		URL url1 = new URL(sURL);
		HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();

		// ���������͵�¼���cookie
		connection1.setRequestProperty("Cookie", responseCookie);

		// ��ȡ���ص�ҳ����Ϣ��br1
		BufferedReader br1 = new BufferedReader(new InputStreamReader(connection1.getInputStream()));

		// ȡ���ص�ҳ��,br1תsbR
		String line1 = br1.readLine();
		while (line1 != null) {
			sbR.append(line1);
			line1 = br1.readLine();
		}

		return sbR.toString();
	}

	public static void main(String[] args) {
		try {
			System.out.println(login("huotingtings", "000000als"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}