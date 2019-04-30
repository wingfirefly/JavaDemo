package com.xxniu.file.FileEncrypter;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xxniu.file.bean.Student;
import com.xxniu.file.bean.Teacher;

public class JsonParse {
	// private static final String JSON_OBJ_STR =
	// "{\"studentName\":\"lily\",\"studentAge\":12}";
	private static final String JSON_ARRAY_STR = "[{\"userNAmes\":\"lily\",\"studentAge\":12,\"studentHight\":179},{\"STUDENT_NAme\":\"lucy\",\"studentAge\":15}]";
	private static final String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"userNAmes\":\"lily\",\"studentAge\":12},{\"userNAmes\":\"lucy\",\"studentAge\":15}]}";

	/**
	 * json字符串-数组类型与JavaBean_List之间的转换
	 */
	public static void testJSONStrToJavaBeanList() {

		ArrayList<Student> students = JSON.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {
		});
		// ArrayList<Student> students1 = JSONArray.parseObject(JSON_ARRAY_STR,
		// new TypeReference<ArrayList<Student>>()
		// {});//因为JSONArray继承了JSON，所以这样也是可以的

		for (Student student : students) {
			System.out.println(student.getStudentName() + ":" + student.getStudentAge());
		}
	}

	public static void main(String[] args) {
		testComplexJSONStrToJavaBean();
	}

	/**
	 * 复杂json格式字符串与JavaBean_obj之间的转换
	 */
	public static void testComplexJSONStrToJavaBean() {

		Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {
		});
		// Teacher teacher1 = JSON.parseObject(COMPLEX_JSON_STR, new
		// TypeReference<Teacher>() {});//因为JSONObject继承了JSON，所以这样也是可以的
		String teacherName = teacher.getTeacherName();
		Integer teacherAge = teacher.getTeacherAge();
		List<Student> students = teacher.getStudents();
		for (Student student : students) {
			System.out.println(student.getStudentName() + ":" + student.getStudentAge());
		}
	}
}
