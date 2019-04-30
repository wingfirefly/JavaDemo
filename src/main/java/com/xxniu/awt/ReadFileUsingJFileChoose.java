package com.xxniu.awt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * java窗口选择文件
 * 说明: TODO
 * @author niuxinxing
 * @version
 */
public class ReadFileUsingJFileChoose {
	public static void main(String[] args) {
		JFileChooser fileChoose = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & GIF Images", "jpg", "gif","txt");
		fileChoose.setFileFilter(filter);
		try {
			if(fileChoose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				File file = fileChoose.getSelectedFile();
				System.out.println(file.getName());
			}else {
				System.out.println("No file selected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 创建一个每delay毫秒将通知其侦听器的Timer
		Timer time = new Timer(1000,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				System.out.println("啦啦啦！");
			}
		});
		time.start();
	}
}
