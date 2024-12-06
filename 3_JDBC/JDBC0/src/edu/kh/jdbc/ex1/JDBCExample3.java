package edu.kh.jdbc.ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample3 {
	
	public static void main(String[] args) {
		// 입력 받은 최소 급여 보다 많이 받고(이상)
	      // 입력 받은 최고 급여보단 적게 받는(이하)
	      // 사원의  사번, 이름, 급여를 급여 내림차순 조회
	      
	      // [실행화면]
	      // 최소 급여 : 1000000
	      // 최대 급여 : 3000000
	      
	      // (사번) / (이름) / (급여)
	      // (사번) / (이름) / (급여)
	      // (사번) / (이름) / (급여)
		
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		System.out.print("최소 급여 : ");
		int minSalary=sc.nextInt();
		sc.nextLine();
		System.out.print("최대 급여 : ");
		int maxSalary=sc.nextInt();
		sc.nextLine();
		
try {
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버가 thin 타입 이라는 뜻
			
			String ip= "localhost"; // DB서버 컴퓨터 IP
			String port = ":1521";
			String sid = ":XE"; //DB이름
			String user = "KH_HSJ"; //사용자명
			String pw="KH1234";//비밀번호
			
			conn = DriverManager.getConnection(type+ip+port+sid,user,pw);
			
		/*	String sql = "SELECT EMP_ID,EMP_NAME,SALARY\r\n"
					+ "FROM EMPLOYEE \r\n"
					+ "WHERE SALARY BETWEEN " +minSalary+  " AND " +maxSalary+" ORDER BY SALARY DESC";
			*/
			String sql= String.format("SELECT EMP_ID,EMP_NAME,SALARY\r\n"
					+ "FROM EMPLOYEE \r\n"
					+ "WHERE SALARY BETWEEN %d AND %d\r\n"
					+ "ORDER BY SALARY DESC", minSalary,maxSalary);
			stmt = conn.createStatement();
			
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int empId= rs.getInt("EMP_ID");
				String empName= rs.getString("EMP_NAME");
				int salary= rs.getInt("SALARY");
				
				System.out.printf("사번 : %d / 이름 :%s / 급여 : %d \n",
									empId,empName,salary);
			
			
			}
			
			
			
		}catch(SQLException e) {
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
