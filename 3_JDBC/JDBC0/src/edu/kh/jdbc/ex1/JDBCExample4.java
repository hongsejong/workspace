package edu.kh.jdbc.ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {
	public static void main(String[] args) {
		//부서명을 입력 받아
		//해당 부서에 근무하는 모든 사원의
		//사번,이름,부서명,직급명을 직급코드 오름차순 조회
		
		//만약 해당 부서가 존재하지 않는다면
		//일치하는 부서가 없습니다. 출력
		
		
		Connection conn = null; //DB 연결 정보를 저장한 객체
		Statement stmt = null; //DB에 SQL 수행 -> 결과를 반환 받는 객체
		ResultSet rs = null; // SELECT 결과를 저장하는 객체
		
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("부서명 입력 :");
			String inputTitle=sc.nextLine();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버가 thin 타입 이라는 뜻
			
			String ip= "localhost"; // DB서버 컴퓨터 IP
			// localhost == 127.0.0.1
			
			String port = ":1521";
			
			String sid = ":XE"; //DB이름
			
			String user = "KH_HSJ"; //사용자명
			
			String pw="KH1234";//비밀번호 
			
			conn = DriverManager.getConnection(type+ip+port+sid,user,pw);
			
			//입력 받은 문자열이 저장된 변수를 SQL에 추가할 때
			//양쪽에 홑따옴표('') 반드시 작성!!
	
			String sql =String.format("SELECT EMP_ID,EMP_NAME,DEPT_TITLE ,JOB_NAME\r\n"
					+ "FROM EMPLOYEE JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID) JOIN JOB USING (JOB_CODE)\r\n"
					+ "WHERE DEPT_TITLE ='%s'",inputTitle);
			
			stmt = conn.createStatement();
			
	
			
			rs = stmt.executeQuery(sql);
			boolean flag= true; // true이면 조회 결과없음, false면 있음
	
			while(rs.next()) {
				
				
				int empId= rs.getInt("EMP_ID");
				String empName= rs.getString("EMP_NAME");
				String deptTitle= rs.getString("DEPT_TITLE");
				String jobName= rs.getString("JOB_NAME");
				
				System.out.printf("사번 : %d 이름 :%s 부서명 : %s 직급명: %s\n",
									empId,empName,deptTitle,jobName);
			flag=false; //while문이 한 번이라도 수행되면 false 변경
			}
			if(flag) { // while문 종료 후 falg == true -> 조회결과 없음
				System.out.println("일치하는 부서가 없습니다.");
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

