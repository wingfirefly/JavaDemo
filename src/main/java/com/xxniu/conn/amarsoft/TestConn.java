package com.xxniu.conn.amarsoft;

/**
 * 安硕信息排查未关闭conn问题
 * 说明: TODO
 * @author niuxinxing
 * @version
 */
public class TestConn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str2 = "Tools.closeDB(conn, ps, rs);";
		System.out.println(str2.matches("Tools.closeDB\\(conn.*"));
	}

}
