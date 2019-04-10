package com.zyy.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexDemo {

    @Test
    public void testRegex() {
        printResult("[[[Ljava.lang.String", ("\\[+L.+"));
        printResult("[[[I", ("\\[+[ZBCDFIJS]"));
    }

    @Test
    public void testReplaceGroup() {
        String str = "%20b%20c%20d";
        Pattern p = Pattern.compile("%\\d+");
        Matcher matcher = p.matcher(str);

        // while, str.replace(group, target);
        if (matcher.find()) {
            String group = matcher.group();
            char ch = (char) Integer.parseInt(group.substring(1, group.length()), 16);
            String a = matcher.replaceAll(ch + "");
            System.out.println(a);
        }
    }

    @Test
    public void testGroup() {
        // (?:</a></li>) 非捕获组
        String content = "<li><a target=\"_blank\" href=\"http://quote.eastmoney.com/sh166105.html\">信达增利(166105)</a></li>";
        Pattern pattern = Pattern.compile("<li><a target=\"_blank\" href=\"http://quote.eastmoney.com/(sh|sz)\\S{1,20}\\.html\">(\\S{1,10}?)\\((\\S{4,7})\\)(?:</a></li>)");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            /* group
             0. <li><a target="_blank" href="http://quote.eastmoney.com/sh166105.html">信达增利(166105)</a></li>
             1. sh
             2. 信达增利
             3.166105
            */
            for (int i=0; i<=matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
    }

    /**
     * @param name
     * i_f01
     * ali_001
     * b01
     */
    public void getEmotion(String name) {
        if (name.matches("^i_f(0?[1-9]|[1-4][0-9]|5?[0-5])$")) {
        } else if (name.matches("^ali_0(0?[1-9]|[1-6][0-9]|70)$")) {
        } else if (name.matches("^b(0?[1-9]|[1-5][0-9]|6?[0-2])$")) {
        }
    }

    private void printResult(String str, String regex) {
        System.out.println(String.format("%s [match] %s [regex] %b", str, regex, str.matches(regex)));
    }
}
