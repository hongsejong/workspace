package edu.kh.jdbc.model.service;

import java.util.List;

import edu.kh.jdbc.model.dao.EmployeeDAO;
import edu.kh.jdbc.model.dto.Employee;

//Service : 요청에 맞는 기능을 수행하여 결과를 제공
//-전달 받은 데이터 또는 DAO 수행 결과 데이터를 필요한 형태로 가공처리
public class EmployeeService {
	
	private EmployeeDAO dao = new EmployeeDAO();

	/**전체 사원 정보 조회 서비스
	 * @return empList
	 */
	public List<Employee> selectAll() {
		//별도로 가공할 내용이 없으면 바로 DAO 호출
		
		List<Employee> empList= dao.selectALL();
		return empList;
	}

	/**사번으로 사원 정보 조회 서비스
	 * @param input
	 * @return
	 */
	public Employee selectOne(int input) {
		
		Employee emp = dao.selectOne(input); //DAO호출 결과를 바로 View로 반환
		return emp;
	}

	/**입력 받은 급여 이상으로 받는 모든 직원 조회 서비스
	 * @param input
	 * @return
	 */
	public List<Employee> selectSalary(int input) {
		
		List<Employee> empList = dao.selectSalary(input);
		return empList;
	}

	
	/**새로운 사원 정보 추가 서비스
	 * @param emp
	 * @return
	 */
	public int insertEmployee(Employee emp) {
		int result = dao.insertEmployee(emp);
		return result;
	}

	/**사번으로 사원 정보 삭제
	 * @param empId
	 * @return
	 */
	public int deleteEmployee(int empId) {
		
		 return dao.deleteEmployee(empId);
	}

	public int updateEmployee(int empId, String email, String phone, int salary) {
		return dao.updateEmployee(empId,email,phone,salary);
	}

	public int updateBonus(String deptCode, double bonus) {
		
		return dao.updateEmployee(deptCode,bonus);
	}

	

}
