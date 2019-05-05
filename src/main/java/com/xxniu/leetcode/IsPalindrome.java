package com.xxniu.leetcode;

import java.util.HashMap;
import java.util.Map;

public class IsPalindrome {
	public static boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		return isPalindrome(Integer.toString(x));
	}

	public static boolean isPalindrome(String str) {
		char[] ch = str.toCharArray();
		int i = ch.length - 1, j = 0;
		char[] chNew = new char[ch.length];
		while (i >= 0) {
			chNew[j++] = ch[i--];
		}
		return new String(chNew).equals(str) ? true : false;
	}

	public static void main(String[] args) {
//		System.out.println(isPalindrome("123321"));
//		System.out.println(romanToInt("MCMXCIV"));
		String[] str = {"a"};
		System.out.println(longestCommonPrefix(str));
	}

	public static int romanToInt(String s) {
		Map<Character, Integer> roman = new HashMap<Character, Integer>();
		roman.put('I', 1);
		roman.put('V', 5);
		roman.put('X', 10);
		roman.put('L', 50);
		roman.put('C', 100);
		roman.put('D', 500);
		roman.put('M', 1000);
		char[] ch = s.toCharArray();
		int digit = 0;
		for (int i = 0; i < ch.length; i++) {
			if (roman.containsKey(ch[i])) {
				if (i < ch.length - 1 && roman.containsKey(ch[i + 1])
						&& roman.get(ch[i + 1]) > roman.get(ch[i])) {
					digit += roman.get(ch[i + 1]) - roman.get(ch[i]);
					i++;
				} else {
					digit += roman.get(ch[i]);
				}
			}
		}
		return digit;
	}

	public static String longestCommonPrefix(String[] strs) {
		if (strs.length<1) {
			return "";
		}
		for (String str : strs) {
			if (str==null || "".equals(str)) {
				return "";
			}
		}
		int i=0;
		boolean flag = true;
		String prefix = "";
		while(flag){
			if(++i>strs[0].length())
				return prefix;
			prefix = strs[0].substring(0,i);
			for (String str : strs) {
				if (!str.startsWith(prefix)) {
					flag = false;
					prefix = strs[0].substring(0,i-1);
				}
			}
		}
		return prefix;
	}
}
