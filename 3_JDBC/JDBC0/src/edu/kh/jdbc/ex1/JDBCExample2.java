package edu.kh.jdbc.ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {
	public static void main(String[] args) {
		//입력 받은 급여보다 많이 받는 사원의
		//사번,이름 ,급여, 직급명을 급여 내림차순 조회
		
		Scanner sc = new Scanner(System.in);
		int input=0;
		System.out.println("급여 입력");
		input =sc.nextInt();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버가 thin 타입 이라는 뜻
			String ip= "localhost"; // DB서버 컴퓨터 IP
			String port = ":1521";
			String sid = ":XE"; //DB이름
			String user = "KH_HSJ"; //사용자명
			String pw="KH1234";//비밀번호
			
			conn = DriverManager.getConnection(type+ip+port+sid,user,pw);
			String sql = "SELECT EMP_ID,EMP_NAME,SALARY,JOB_NAME FROM EMPLOYEE JOIN JOB USING(JOB_CODE) ORDER BY SALARY DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int empId= rs.getInt("EMP_ID");
				String empName= rs.getString("EMP_NAME");
				int salary= rs.getInt("SALARY");
				String jobName= rs.getString("JOB_Name");
				if(salary>input) {
					
					System.out.printf("사번 : %d 이름 :%s 급여 : %d 직급명: %s\n",
							empId,empName,salary,jobName);
				}
			}
		}catch(SQLException e) {
			//SQLException : DB 연결 관련 예외의 최상위 부모
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			System.out.println("OJDBC 라이브러리 미등록 또는 경로 오타");
		}finally {
			try {
			if(rs!=null)	rs.close();
			if(stmt!=null)	stmt.close();
			if(conn!=null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
