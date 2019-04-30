package com.xxniu.desktop;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
/**
 * 打开文件
 * 打开图片
 * 打开链接
 * 打开邮件
 * 说明: TODO
 * @author niuxinxing
 * @version
 */
public class OpenFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("G:\\DesktopPhoto\\1..jpg");
		try {
			URI uri = new URI("https://www.baidu.com/");
			Desktop desktop = Desktop.getDesktop();
//			desktop.open(file);
			desktop.browse(uri);
//			desktop.mail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
