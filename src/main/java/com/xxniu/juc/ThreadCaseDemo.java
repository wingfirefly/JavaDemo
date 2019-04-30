package com.xxniu.juc;

import java.util.concurrent.locks.*;

class Info { // ������Ϣ��
	private String name = "name";// ����name���ԣ�Ϊ��������set��name��������
	private String content = "content";// ����content���ԣ�Ϊ��������set��content��������
	private boolean flag = true; // ���ñ�־λ,��ʼʱ������
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition(); // ����һ��Condition����

	public void set(String name, String content) {
		lock.lock();
		try {
			while (!flag) {
				condition.await();
			}
			this.setName(name); // ��������
			Thread.sleep(300);
			this.setContent(content); // ��������
			flag = false; // �ı��־λ����ʾ����ȡ��
			condition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void get() {
		lock.lock();
		try {
			while (flag) {
				condition.await();
			}
			Thread.sleep(300);
			System.out.println(this.getName() + " --> " + this.getContent());
			flag = true; // �ı��־λ����ʾ��������
			condition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return this.name;
	}

	public String getContent() {
		return this.content;
	}
}

class Producer implements Runnable { // ͨ��Runnableʵ�ֶ��߳�
	private Info info = null; // ����Info����

	public Producer(Info info) {
		this.info = info;
	}

	public void run() {
		boolean flag = true; // ������λ
		for (int i = 0; i < 10; i++) {
			if (flag) {
				this.info.set("����--1", "����--1"); // ��������
				flag = false;
			} else {
				this.info.set("����--2", "����--2"); // ��������
				flag = true;
			}
		}
	}
}

class Consumer implements Runnable {
	private Info info = null;

	public Consumer(Info info) {
		this.info = info;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			this.info.get();
		}
	}
}

/**
 * ������������
 * 
 * @author niuxinxing
 * @version
 */
public class ThreadCaseDemo {
	public static void main(String args[]) {
		Info info = new Info(); // ʵ����Info����
		Producer pro = new Producer(info); // ������
		Consumer con = new Consumer(info); // ������
		new Thread(pro).start();
		// �������������̺߳��������������߳�
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(con).start();
	}
}