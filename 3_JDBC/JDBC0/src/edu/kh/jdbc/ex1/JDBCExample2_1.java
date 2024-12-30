package edu.kh.jdbc.ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JDBCExample2_1 {
	public static void main(String[] args) {

		// [1단계] : JDBC 객체 참조 변수 선언 (java.sql 패키지)

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {


			//1. DB 연결에 필요한 Oracle JDBC Driver 메모리 로드하기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//ClassNotFoundException 발생 가능성 있음


			// 2. 연결 정보를 담은 Connection을 생성
			//	 (이 때, DriverManager 객체가 필요함)
			// DriverManager : JDBC 드라이버를 통해 Connection 객체를 만드는 역할
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버가 thin 타입 이라는 뜻
			String ip= "localhost"; // DB서버 컴퓨터 IP

			String port = ":1521";

			String sid = ":XE"; //DB이름

			String user = "KH_HSJ"; //사용자명

			String pw="KH1234";//비밀번호
			conn = DriverManager.getConnection(type+ip+port+sid,user,pw);
			//jdbc:oracle:thin:@localhost:1521:XE
			Scanner sc = new Scanner(System.in);
			System.out.print("급여 입력 :");
			int input = sc.nextInt();
			sc.nextLine();
			//\r\n==엔터(신경x)
			String sql = "SELECT EMP_ID,EMP_NAME,SALARY,JOB_NAME\r\n"
					+ "FROM EMPLOYEE\r\n"
					+ "JOIN JOB USING(JOB_CODE)\r\n"
					+ "WHERE SALARY>"+input +"\r\n"
					+ "ORDER BY SALARY DESC";

			//4. Statement 객체 생성
			stmt = conn.createStatement();

			//5.SQL을 Statement에 적재 후 DB로 전달하여 수행한 후
			//	결과를 반환 받아와 rs 변수에 대입

			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				//rs.next() : 참조하고 있는 ResultSet 객체의
				//			  첫 번째 컬럼부터 순서대로 한 행씩 이동하며
				//			  다음 행이 있을 경우 true를 반환

				//rs.get[Type]("컬럼명") : 현재 가리키고 있는 행의 특정 컬럼 값을 얻어옴
				int empId= rs.getInt("EMP_ID");
				String empName= rs.getString("EMP_NAME");
				int salary= rs.getInt("SALARY");
				String jobName= rs.getString("JOB_NAME");

				//조회 결과 출력
				System.out.printf("사번 : %d 이름 :%s 급여 : %d 부서코드: %s\n",
						empId,empName,salary,jobName);


			}



		}catch(SQLException e) {
			//SQLException : DB 연결 관련 예외의 최상위 부모
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			System.out.println("OJDBC 라이브러리 미등록 또는 경로 오타");
		}catch(InputMismatchException e) {
			System.out.println("숫자만 입력하세요");
		}
		finally {
			//[4단계] 사용한 JDBC 객체 자원 반환(close)
			//-> 자원 반환 순서는 객체 생성 순서의 "역순"!

			//생성 순서 :Connection, Statement,ResultSet
			//반환 순서 : ResultSet, Statment,Connection


			try {
				//NullPointerException 방지를 위해 if문 추가
				if(rs!=null)	rs.close();
				if(stmt!=null)	stmt.close();
				if(conn!=null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

}
