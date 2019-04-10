package com.zyy.lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class ProcessDemo {

    public static void main(String[] args) {
        new ProcessDemo().exec("calc"); // shutdown -s -t 2000
        // new ProcessDemo().exec("D:/Program Files (x86)/Tencent/QQ/Bin/QQ.exe"); // iexpore.exe
    }

    public void exec(String command) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
            // Process process = runtime.exec("calc");
            // process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProcessIO() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("javac");
            BufferedReader result = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line=result.readLine()) != null) {
                System.out.println(line);
            }
            result.close();

            BufferedReader error = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            while ((line=error.readLine()) != null) {
                System.out.println(line);
            }
            error.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBrowser() {
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI("www.baidu.com"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
