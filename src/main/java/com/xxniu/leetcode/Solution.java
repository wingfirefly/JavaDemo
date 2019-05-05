package com.xxniu.leetcode;

public class Solution {
	/**
	 * 给定一个32位有符号整数，整数的反转数字。
	 * 
	 * @param x
	 * @return
	 */
	public static int reverse(int x) {
		String str = Integer.toString(x);
		String str1 = null;
		String str2 = null;
		if (x > 0) {
			str1 = "";
			str2 = str;
		} else {
			str1 = str.substring(0, 1);
			str2 = str.substring(1);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(str1);
		char[] ch = str2.toCharArray();
		for (int i = ch.length - 1; i >= 0; i--) {
			sb.append(ch[i]);
		}
		str = null;
		str = sb.toString();
		if (str == null) {
			return 0;
		}
		int length = str.length(), offset = 0;
		/* 异常情况2：字符串长度为0 */
		if (length == 0) {
			return 0;
		}
		boolean negative = str.charAt(offset) == '-';
		/* 异常情况3：字符串为'-' */
		if (negative && ++offset == length) {
			return 0;
		}
		int result = 0;
		char[] temp = sb.toString().toCharArray();
		while (offset < length) {
			char digit = temp[offset++];
			if (digit <= '9' && digit >= '0') {
				int currentDigit = digit - '0';
				/*
				 * 异常情况4：已经等于Integer.MAX_VALUE / 10，判断要添加的最后一位的情况：
				 * 如果是负数的话，最后一位最大是8 如果是正数的话最后一位最大是7
				 */
				if (result == Integer.MAX_VALUE / 10) {

					if ((negative == false && currentDigit > 7)
							|| (negative && currentDigit > 8)) {
						return 0;
					}
					/*
					 * 异常情况5：已经大于Integer.MAX_VALUE / 10
					 * 无论最后一位是什么都会超过Integer.MAX_VALUE
					 */
				} else if (result > Integer.MAX_VALUE / 10) {
					return 0;
				}

				int next = result * 10 + currentDigit;
				result = next;
			}
		}
		if (negative) {
			result = -result;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(reverse(-0));
//		System.out.println(Long.parseLong(""));
	}
}
