package edu.kh.jdbc.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.model.dto.Employee;
import oracle.jdbc.replay.driver.NonTxnReplayableNClob;

//DAO(Data Access Object) : 데이터 접근 객체
//DB와 연결되어 SQL을 수행하고 결과를 반환 받는 역할
public class EmployeeDAO {

	//JDBC 객체 저장용 참조 변수 필드 선언

	private Connection conn; // DB 연결 정보를 담은 객체(Java - DB 사이의 통로 역할)

	private Statement stmt; // Connection을 통해 SQL을 수행하고 결과를 반환 받는 객체
	
	private PreparedStatement pstmt;
	//Statement의 자식으로 좀 더 향상된 기능을 제공
	//-?(위치홀더)를 이용하여 SQL에 작성되어지는 리터럴을 동적으로 제어함
	// -> 오타 위험 감소, 가독성 상승

	private ResultSet rs; // SELECT 수행 후 반환되는 객체


	/**전체 사원 정보 조회 DAO
	 * @return empList
	 */
	public List<Employee> selectALL() {

		//결과 저장용 변수 선언
		List<Employee> empList = new ArrayList<Employee>();

		try {
			// 1. Oracle JDBC Driver 메모리 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");


			//2. DB연결 작업(Connection) 얻어오기
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버가 thin 타입 이라는 뜻

			String ip= "localhost"; // DB서버 컴퓨터 IP
			// localhost == 127.0.0.1

			String port = ":1521";

			String sid = ":XE"; //DB이름

			String user = "KH_HSJ"; //사용자명

			String pw="KH1234";//비밀번호

			conn = DriverManager.getConnection(type+ip+port+sid,user,pw);
			//3. 수행할 SQL 작성 (작성 시 세미콜론 제외!!)
			String sql = "SELECT * FROM EMPLOYEE2";
			//4. Statement 객체 생성

			stmt = conn.createStatement();
			//5. SQL 수행 후 결과(ResultSet) 반환 받기
			rs=stmt.executeQuery(sql);
			//6. 결과를 List에 옮겨 담기
			// -> ResultSet을 한 행씩 접근하여 컬럼 값을 얻어와
			//	  한 행의 정보가 담긴 Employee 객체 생성하고
			//	  이를 empList에 추가
			while(rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode = rs.getString("JOB_CODE");
				String salLevel = rs.getString("SAL_LEVEL");
				int salary = rs.getInt("SALARY");
				double bonus = rs.getDouble("BONUS");
				int managerId = rs.getInt("MANAGER_ID");
				Date hireDate = rs.getDate("HIRE_DATE");
				Date entDate = rs.getDate("ENT_DATE");
				char entYn = rs.getString("ENT_YN").charAt(0);
				// rs.getChar()는 존재하지 않음
				//왜 ? 자바에서는 문자하나 (char)의 개념이 있지만
				//  DB는 오로지 문자열 개념만 존재함
				//->String.charAt(0)을 사용

				Employee emp = new Employee(empId, empName, empNo, email, phone, deptCode,
						jobCode, salLevel, salary, bonus, managerId, hireDate,
						entDate, entYn);

				//empList에 추가
				empList.add(emp);

			}


			//7. 사용한 JDBC 자원 반환 (역순으로 close)
			try {
				if(rs!=null)	rs.close();
				if(stmt!=null)	stmt.close();
				if(conn!=null)	conn.close();
			}catch(SQLException e) {

			}

			//8. List 호출부로 반환


		}catch(Exception e) {
			e.printStackTrace();
		}



		return empList;
	}


	/**사번으로 사원 정보 조회 DAO
	 * @param input
	 * @return
	 */
	public Employee selectOne(int input) {
		Employee emp = null; //결과 저장용 변수

		try {


			Class.forName("oracle.jdbc.driver.OracleDriver");


			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버가 thin 타입 이라는 뜻

			String ip= "localhost"; // DB서버 컴퓨터 IP

			String port = ":1521";

			String sid = ":XE"; //DB이름

			String user = "KH_HSJ"; //사용자명

			String pw="KH1234";//비밀번호

			conn = DriverManager.getConnection(type+ip+port+sid,user,pw);

			String sql =String.format("SELECT  * FROM EMPLOYEE2 WHERE EMP_ID ='%d'",input);

			//4. Statement 객체 생성
			stmt = conn.createStatement();

			//5.SQL을 Statement에 적재 후 DB로 전달하여 수행한 후
			//	결과를 반환 받아와 rs 변수에 대입

			rs = stmt.executeQuery(sql);



			//조회 결과가 있는 경우 객체 생성하여 값 담기
			//조회 결과가 있다면 1행 밖에 나오지 않으므로
			//while대신 if문 사용
			if(rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode = rs.getString("JOB_CODE");
				String salLevel = rs.getString("SAL_LEVEL");
				int salary = rs.getInt("SALARY");
				double bonus = rs.getDouble("BONUS");
				int managerId = rs.getInt("MANAGER_ID");
				Date hireDate = rs.getDate("HIRE_DATE");
				Date entDate = rs.getDate("ENT_DATE");
				char entYn = rs.getString("ENT_YN").charAt(0);


				emp = new Employee(empId, empName, empNo, 
						email, phone, deptCode, jobCode, salLevel, 
						salary, bonus, managerId, hireDate, entDate, entYn);


			}


		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			System.out.println("OJDBC 라이브러리 미등록 또는 경로 오타");
		}finally {


			try {
				//NullPointerException 방지를 위해 if문 추가
				if(rs!=null)	rs.close();
				if(stmt!=null)	stmt.close();
				if(conn!=null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return emp; //조회 결과가 있으면 주소값, 없으면 null 반환

	}


	/**입력 받은 급여 이상으로 받는 모든 직원 조회 DAO
	 * @param input
	 * @return
	 */
	public List<Employee> selectSalary(int input) {
		
		List<Employee> empList = new ArrayList<Employee>();


		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버가 thin 타입 이라는 뜻
			String ip= "localhost"; // DB서버 컴퓨터 IP
			String port = ":1521";
			String sid = ":XE"; //DB이름
			String user = "KH_HSJ"; //사용자명
			String pw="KH1234";//비밀번호
			conn = DriverManager.getConnection(type+ip+port+sid,user,pw);
			String sql =String.format("SELECT *FROM EMPLOYEE2 \r\n"
					+ "WHERE SALARY >=%d", input);
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode = rs.getString("JOB_CODE");
				String salLevel = rs.getString("SAL_LEVEL");
				int salary = rs.getInt("SALARY");
				double bonus = rs.getDouble("BONUS");
				int managerId = rs.getInt("MANAGER_ID");
				Date hireDate = rs.getDate("HIRE_DATE");
				Date entDate = rs.getDate("ENT_DATE");
				char entYn = rs.getString("ENT_YN").charAt(0);

				Employee emp = new Employee(empId, empName, empNo, email, phone, deptCode,
						jobCode, salLevel, salary, bonus, managerId, hireDate,
						entDate, entYn);

				//empList에 추가
				empList.add(emp);

			}


			//7. 사용한 JDBC 자원 반환 (역순으로 close)
			try {
				if(rs!=null)	rs.close();
				if(stmt!=null)	stmt.close();
				if(conn!=null)	conn.close();
			}catch(SQLException e) {

			}

			//8. List 호출부로 반환


		}catch(Exception e) {
			e.printStackTrace();
		}

		return empList;

	}


	/**새로운 사원 정보 추가 DAO
	 * @param emp
	 * @return result
	 */
	public int insertEmployee(Employee emp) {
		int result = 0; // 결과 저장용 변수
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버가 thin 타입 이라는 뜻
			String ip= "localhost"; // DB서버 컴퓨터 IP
			String port = ":1521";
			String sid = ":XE"; //DB이름
			String user = "KH_HSJ"; //사용자명
			String pw="KH1234";//비밀번호
			conn = DriverManager.getConnection(type+ip+port+sid,user,pw);
			conn.setAutoCommit(false); // 자동 커밋 비활성화
			//-> 활성화 상태일 경우 SQL이 수행되자마자 commit이 되버림
			
			String sql="INSERT INTO EMPLOYEE2 VALUES(?, ?, ?, ?,\r\n"
					+ "?, ?, ?, 'S1', ?, ?,200,SYSDATE,NULL,'N')";
							// ? 기호 = 위치 홀더
			
			//Statement : 커넥션 생성 - SQL 작성 - Statement 객체 생성- SQL수행 후 결과 반환
			
			//PreparedStatement : 커넥션 생성 - SQL 작성(? 사용) - PreparedStatement 객체 생성(sql 적재)
			//					-위치홀더에 알맞은 값 대입 -SQL 수행 후 결과 반환
			
			
			
			
			//4.PreparedStatement 객체 생성(sql적재)
			pstmt = conn.prepareStatement(sql);
			
			//5.위치 홀더에 알맞은 값 대입
			//pstmt.set[Type](위치홀더 순서, 값)
			
			pstmt.setInt(1, emp.getEmpId()); // 입력 받은 사번을 1번 ?(위치홀더)에 세팅
			pstmt.setString(2, emp.getEmpName()); // 입력 받은 사번을 2번 ?(위치홀더)에 세팅
			pstmt.setString(3, emp.getEmpNo()); // 입력 받은 사번을 3번 ?(위치홀더)에 세팅
			pstmt.setString(4, emp.getEmail()); // 입력 받은 사번을 4번 ?(위치홀더)에 세팅
			pstmt.setString(5, emp.getPhone()); // 입력 받은 사번을 5번 ?(위치홀더)에 세팅
			pstmt.setString(6, emp.getDeptCode()); // 입력 받은 사번을 6번 ?(위치홀더)에 세팅
			pstmt.setString(7, emp.getJobCode()); // 입력 받은 사번을 7번 ?(위치홀더)에 세팅
			pstmt.setInt(8, emp.getSalary()); // 입력 받은 사번을 8번 ?(위치홀더)에 세팅
			pstmt.setDouble(9, emp.getBonus()); // 입력 받은 사번을 9번 ?(위치홀더)에 세팅
			
			
			
			//6.SQL 수행 후 결과 반환
			
			//1)Statement - SELECT		 :stmt.executeQuery(sql);
			//2)PreparedStatement -SELECT:pstmt.excuteQuery(); <-sql 다시 담지 않음
			
			//***DML 수행 시 executeUpdate 사용 ***
			//3)Statement - DML			:stmt.executeUpdate(sql);
			//4)PreparedStatement-DML	:pstmt.executeUpdate();
			
			result = pstmt.executeUpdate(); //DML(INSERT, UPDATE,DELETE) 수행 후
											//성공한 행의 개수를 반환
											//조건에 맞는 행이 없으면 0 반환
			
			//트랜잭션 제어
			if(result >0) conn.commit(); //DML 성공 시 commit 수행
			else conn.rollback(); // DML 실패 시 rollback 수행

			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			//7.사용한 자원 반환
			try {
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}


	/** 사번으로 정보 삭제
	 * @param empId
	 * @return result
	 */
	public int deleteEmployee(int empId) {
		int result = 0; 

	       try {
	           Class.forName("oracle.jdbc.driver.OracleDriver");

	           String type = "jdbc:oracle:thin:@";
	           String ip = "localhost"; // DB 서버 컴퓨터 IP
	           String port = ":1521"; 
	           String sid = ":XE"; // DB 이름
	           String user = "KH_HSJ"; // 사용자명
	           String pw = "KH1234"; // 비밀번호
	           conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
	           conn.setAutoCommit(false);

	           String sql = "DELETE FROM EMPLOYEE2 WHERE EMP_ID = ?";

	           pstmt = conn.prepareStatement(sql);

	           pstmt.setInt(1, empId);

	           result = pstmt.executeUpdate();
	           
				//트랜잭션 제어
				if(result >0) conn.commit(); //DML 성공 시 commit 수행
				else conn.rollback(); // DML 실패 시 rollback 수행

	       } catch (Exception e) {
	           e.printStackTrace();
	       } finally {
	           try {
	               if (pstmt != null) pstmt.close();
	               if (conn != null) conn.close();
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
	       }

	       return result;

	}


	/**사번으로 사원 정보 수정
	 * @param empId
	 * @param email
	 * @param phone
	 * @param salary
	 * @return
	 */
	public int updateEmployee(int empId, String email, String phone, int salary) {
		int result = 0; 

	       try {
	           Class.forName("oracle.jdbc.driver.OracleDriver");

	           String type = "jdbc:oracle:thin:@";
	           String ip = "localhost"; // DB 서버 컴퓨터 IP
	           String port = ":1521"; 
	           String sid = ":XE"; // DB 이름
	           String user = "KH_HSJ"; // 사용자명
	           String pw = "KH1234"; // 비밀번호
	           conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
	           conn.setAutoCommit(false);

	           String sql = "UPDATE EMPLOYEE2 SET EMAIL =?, PHONE =?, SALARY =? WHERE EMP_ID =?";

	           pstmt = conn.prepareStatement(sql);

	           
	           pstmt.setString(1, email);
	           pstmt.setString(2, phone);
	           pstmt.setInt(3, salary);
	           pstmt.setInt(4, empId);

	           result = pstmt.executeUpdate();
	           
				//트랜잭션 제어
				if(result >0) conn.commit(); //DML 성공 시 commit 수행
				else conn.rollback(); // DML 실패 시 rollback 수행

	       } catch (Exception e) {
	           e.printStackTrace();
	       } finally {
	           try {
	               if (pstmt != null) pstmt.close();
	               if (conn != null) conn.close();
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
	       }
		
		
		
		return result;
	}
	
	


	public int updateEmployee(String deptCode, double bonus) {
		int result=0;
		try {
	           Class.forName("oracle.jdbc.driver.OracleDriver");

	           String type = "jdbc:oracle:thin:@";
	           String ip = "localhost"; // DB 서버 컴퓨터 IP
	           String port = ":1521"; 
	           String sid = ":XE"; // DB 이름
	           String user = "KH_HSJ"; // 사용자명
	           String pw = "KH1234"; // 비밀번호
	           conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
	           conn.setAutoCommit(false);

	           String sql = "UPDATE EMPLOYEE2 SET BONUS =? WHERE DEPT_CODE =?";

	           pstmt = conn.prepareStatement(sql);

	           
	           pstmt.setDouble(1, bonus);
	           pstmt.setString(2, deptCode);
	          //setString()을 통해 위치홀더에 문자열 값을 대입하면
	           //양쪽으로 ''(홑따옴표) 자동으로 추가된다!

	           result = pstmt.executeUpdate();
	           
				//트랜잭션 제어
				if(result >0) conn.commit(); //DML 성공 시 commit 수행
				else conn.rollback(); // DML 실패 시 rollback 수행

	       } catch (Exception e) {
	           e.printStackTrace();
	       } finally {
	           try {
	               if (pstmt != null) pstmt.close();
	               if (conn != null) conn.close();
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
	       }
		return result;
	}
	


	

}

