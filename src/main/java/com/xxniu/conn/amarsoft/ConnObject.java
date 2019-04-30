package com.xxniu.conn.amarsoft;

class ConnObject {
	String rsName = "";
	String openRows = "";
	String closeRows = "";
	int iopen = 0;
	int iclose = 0;
	String fileName = "";
	int iopenrow = 0;
	int icloserow = 0;
	String sType = "";
	String rowString = "";

	public ConnObject(String paramString1, String paramString2,
			String paramString3, int paramInt1, int paramInt2,
			String paramString4) {
		this.rsName = paramString1;
		this.openRows = paramString2;
		this.closeRows = paramString3;
		this.iopen = paramInt1;
		this.iclose = paramInt2;
		this.fileName = paramString4;
	}

	public String getRowString() {
		return this.rowString;
	}

	public void setRowString(String paramString) {
		this.rowString += paramString;
	}

	public String getRsName() {
		return this.rsName;
	}

	public void setRsName(String paramString) {
		this.rsName = paramString;
	}

	public String getOpenRows() {
		return this.openRows;
	}

	public void setOpenRows(String paramString) {
		this.openRows += paramString;
	}

	public String getCloseRows() {
		return this.closeRows;
	}

	public void setCloseRows(String paramString) {
		this.closeRows += paramString;
	}

	public int getIopen() {
		return this.iopen;
	}

	public void setIopen(int paramInt) {
		this.iopen = paramInt;
	}

	public int getIclose() {
		return this.iclose;
	}

	public void setIclose(int paramInt) {
		this.iclose = paramInt;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String paramString) {
		this.fileName = paramString;
	}
	public static void main(String[] args) {
		String str2 = "public static String getSerialNo(String sTable, String sColumn, Transaction Sqlca)";
		System.out.println(str2.matches(".*\\(.*Transaction.*\\).*|.*\\(.*Connection.*\\).*|.*\\(.*JBOTransaction.*\\).*"));
//		System.out.println(str2.matches(".*=\\s*Transaction\\.createTransaction\\(\\s?tx\\d?\\s?\\).*|abc"));
	}
}

/*
 * Location: E:\项目\jcpt\批量\转发：wd 关于系统上线前结果集与连接未关闭程序扫描的提醒事宜\checkconn_2017.jar
 * Qualified Name: com.amarsoft.checkconn.ConnObject JD-Core Version: 0.5.3
 */