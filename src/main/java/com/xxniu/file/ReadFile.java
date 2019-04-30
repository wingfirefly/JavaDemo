package com.xxniu.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("E:/1512704196626.txt");
		readFile(file);
	}
	public static void readFile(File file){
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String info;
			StringBuffer sb = new StringBuffer();
			while((info = br.readLine())!=null){
			    sb.append(info);
			}
			System.out.println(sb.toString());
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
