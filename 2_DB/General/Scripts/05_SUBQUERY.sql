/* **SUBQUERY(서브쿼리)**
 * -하나의 SQL문 안에 포함된 또 다른 SQL(SELECT)문
 * -메인쿼리 (기존쿼리)를 위해 보조 역할을 하는 쿼리문
 * -SELECT,FROM,WHERE,HAVING 절에서 사용 가능
 *  
 */

--서브쿼리 예시 1.
--부서 코드가 노옹철사원과 같은 소속의 
--직원 이름,부서코드 조회

-- 1) 사원명이 노옹철인 사람의 부서 코드 조회

SELECT DEPT_CODE
FROM EMPLOYEE 
WHERE EMP_NAME ='노옹철'; --'D9'

--2)부서코드가 'D9'인 직원을 조회

SELECT EMP_NAME ,DEPT_CODE 
FROM EMPLOYEE 
WHERE DEPT_CODE ='D9';

--3) 부서코드가 노옹철 사원과 같은 소속의 직원 이름,부서코드 조회
-->1,2 단계를 하나의 쿼리로 !

--메인쿼리
SELECT EMP_NAME ,DEPT_CODE  FROM EMPLOYEE 
					--서브쿼리
WHERE DEPT_CODE =(SELECT DEPT_CODE FROM EMPLOYEE 
					WHERE EMP_NAME ='노옹철');

	
--서브쿼리 예시 2
--전 직원의 평균 급여보다 많은 급여를 받고 있는 직원의
--사번,이름,직급코드,급여 조회

--1).전 직원의 평균 급여 조회
SELECT AVG(SALARY)
FROM EMPLOYEE;
--2)직원들 중 급여가 평균(1의 조회결과) 이상인 사원들의 사번,이름,직급코드 급여 조회
SELECT EMP_ID ,EMP_NAME ,JOB_CODE ,SALARY 
FROM EMPLOYEE 
WHERE SALARY > 3047662;
				
--3) 전 직원의 평균 급여보다 많이 받고있는 직원의 사번,이름 직급코드,급여 조회
--> 1,2를 하나의 쿼리로 작성 가능
SELECT EMP_ID ,EMP_NAME ,JOB_CODE ,SALARY 
FROM EMPLOYEE                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
WHERE SALARY > (SELECT AVG(SALARY) FROM EMPLOYEE);

-------------------------------------------------------------------

/*  서브쿼리 유형

    - 단일행 (+ 단일열) 서브쿼리 : 서브쿼리의 조회 결과 값의 개수가 1개일 때 
    
    - 다중행 (+ 단일열) 서브쿼리 : 서브쿼리의 조회 결과 값의 개수가 여러개일 때
    
    - 다중열 서브쿼리 : 서브쿼리의 SELECT 절에 자열된 항목수가 여러개 일 때
    
    - 다중행 다중열 서브쿼리 : 조회 결과 행 수와 열 수가 여러개일 때 
    
    - 상관 서브쿼리 : 서브쿼리가 만든 결과 값을 메인 쿼리가 비교 연산할 때 
                     메인 쿼리 테이블의 값이 변경되면 서브쿼리의 결과값도 바뀌는 서브쿼리
                     
    - 스칼라 서브쿼리 : 상관 쿼리이면서 결과 값이 하나인 서브쿼리
    
   * 서브쿼리 유형에 따라 서브쿼리 앞에 붙은 연산자가 다름
    
*/


-- 1. 단일행 서브쿼리 (SINGLE ROW SUBQUERY)
--    서브쿼리의 조회 결과 값의 개수가 1개인 서브쿼리
--    단일행 서브쿼리 앞에는 비교 연산자 사용
--    <, >, <=, >=, =, !=/^=/<>


-- 전 직원의 급여 평균보다 많은 급여를 받는 직원의 
-- 이름, 직급, 부서, 급여를 직급 순으로 정렬하여 조회

SELECT AVG(SALARY)
FROM EMPLOYEE ;

SELECT EMP_NAME ,JOB_CODE,DEPT_CODE ,SALARY 
FROM EMPLOYEE 
WHERE SALARY >(SELECT AVG(SALARY) FROM EMPLOYEE)
ORDER BY JOB_CODE ;

--가장 적은 급여를 받는 직원의
--사번,이름,직급코드,부서코드,급여,입사일 조회


SELECT EMP_ID, EMP_NAME ,JOB_CODE ,DEPT_CODE ,SALARY ,HIRE_DATE
FROM EMPLOYEE  
WHERE SALARY=(SELECT MIN(SALARY) FROM EMPLOYEE) ;


-- 노옹철 사원의 급여보다 많이 받는 직원의
--사번,이름,부서코드,직급코드,급여 조회

SELECT EMP_ID ,EMP_NAME,DEPT_CODE ,JOB_CODE ,SALARY 
FROM EMPLOYEE 
WHERE SALARY >(SELECT SALARY  FROM EMPLOYEE 
WHERE EMP_NAME ='노옹철');


--부서별(부서가 없는 사람 포함) 급여의 합계 중 가장 큰 부서의
--부명,급여 합계를 조회
--1)
SELECT MAX(SUM(SALARY))
FROM EMPLOYEE 
GROUP BY DEPT_CODE ; --17700000
--2)
SELECT DEPT_TITLE ,SUM(SALARY)
FROM EMPLOYEE LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
GROUP BY DEPT_TITLE 
HAVING SUM(SALARY) = 17700000;

--3) 위의 두 쿼리를 합쳐 부서별 급여 합이 큰 부서의 부서명,급여합 조회

SELECT DEPT_TITLE ,SUM(SALARY)
FROM EMPLOYEE LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
GROUP BY DEPT_TITLE 
HAVING SUM(SALARY) = (SELECT MAX(SUM(SALARY)) FROM EMPLOYEE 
GROUP BY DEPT_CODE);


-------------------------------------------

-- 2. 다중행 서브쿼리 (MULTI ROW SUBQUERY)
--    서브쿼리의 조회 결과 값의 개수가 여러행일 때 

/*
    >> 다중행 서브쿼리 앞에는 일반 비교연산자 사용 x
    
    - IN / NOT IN : 여러 개의 결과값 중에서 한 개라도 일치하는 값이 있다면
                    혹은 없다면 이라는 의미(가장 많이 사용!)
                    
    - > ANY, < ANY : 여러개의 결과값 중에서 한개라도 큰 / 작은 경우
                     가장 작은 값보다 큰가? / 가장 큰 값 보다 작은가?
                     
    - > ALL, < ALL : 여러개의 결과값의 모든 값보다 큰 / 작은 경우
                     가장 큰 값 보다 큰가? / 가장 작은 값 보다 작은가?
                     
    - EXISTS / NOT EXISTS : 값이 존재하는가? / 존재하지 않는가?
    
*/

-- 부서별 최고 급여를 받는 직원의 
-- 이름, 직급, 부서, 급여를 부서 순으로 정렬하여 조회

SELECT MAX(SALARY) 
FROM EMPLOYEE 
GROUP BY DEPT_CODE;-- 7행 1열(다중행)

SELECT EMP_NAME,JOB_CODE,DEPT_CODE,SALARY
FROM EMPLOYEE
WHERE SALARY IN (SELECT MAX(SALARY) 
				FROM EMPLOYEE 
				GROUP BY DEPT_CODE)	
ORDER BY DEPT_CODE ;


--사수에 해당하는 직원에 대해 조회
--사번,이름,부서명,직급명,구분(사수/직원)

--1) 사수에 해당하는 사원 번호 조회

SELECT DISTINCT MANAGER_ID
FROM EMPLOYEE 
WHERE MANAGER_ID IS NOT NULL; --7명



--2) 직원의 사번,이름,부서명,직급명 조회(부서 없는 사람 포함)


SELECT EMP_ID, EMP_NAME ,DEPT_TITLE ,JOB_NAME 
FROM EMPLOYEE E LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN JOB USING(JOB_CODE);


--3)사수에 해당하는 직원에 대한 정보 조회(이때, 구분은 '사수')

SELECT EMP_ID, EMP_NAME ,DEPT_TITLE ,JOB_NAME, '사수' 구분
FROM EMPLOYEE E LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN JOB USING(JOB_CODE)

WHERE EMP_ID IN (SELECT DISTINCT MANAGER_ID
FROM EMPLOYEE 
WHERE EMP_ID IS NOT NULL);


--4)일반 직원에 해당하는 사원들 정보 조회(이때, 구분은 '사원'으로)
SELECT EMP_ID, EMP_NAME ,DEPT_TITLE ,JOB_NAME, '사원' 구분
FROM EMPLOYEE E LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN JOB USING(JOB_CODE)

WHERE EMP_ID NOT IN (SELECT DISTINCT MANAGER_ID
FROM EMPLOYEE 
WHERE MANAGER_ID IS NOT NULL);


--5) 3,4 의 조회 결과를 하나로 합침 -->SELECT절 SUBQUERY
--* SELECT 절에도 서브쿼리 사용 가능

SELECT EMP_ID, EMP_NAME ,DEPT_TITLE ,JOB_NAME, 
CASE 
	WHEN EMP_ID NOT IN (SELECT DISTINCT MANAGER_ID
				FROM EMPLOYEE 
				WHERE MANAGER_ID IS NOT NULL)
				
				THEN '사원'
				ELSE'사수'
END 구분

FROM EMPLOYEE E LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN JOB USING(JOB_CODE);



---------------------------------------
--UNION 사용

SELECT EMP_ID, EMP_NAME ,DEPT_TITLE ,JOB_NAME, '사수' 구분
FROM EMPLOYEE E LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN JOB USING(JOB_CODE)

WHERE EMP_ID IN (SELECT DISTINCT MANAGER_ID
FROM EMPLOYEE 
WHERE EMP_ID IS NOT NULL)

UNION

SELECT EMP_ID, EMP_NAME ,DEPT_TITLE ,JOB_NAME, '사원' 구분
FROM EMPLOYEE E LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN JOB USING(JOB_CODE)

WHERE EMP_ID NOT IN (SELECT DISTINCT MANAGER_ID
FROM EMPLOYEE 
WHERE MANAGER_ID IS NOT NULL);


--대리 직급의 직원들 중에서 과장 직급의 최소 급여보다 많이 받는 직원의 
-- 사번,이름,직급,급여를 조회
--> ANY 혹은 <ANY 연산자 사용

--1) 직급이 대리인 직원들의 사번,이름 직급명,급여 조회
SELECT EMP_ID ,EMP_NAME ,JOB_NAME ,SALARY 
FROM EMPLOYEE JOIN JOB  USING(JOB_CODE)
WHERE JOB_NAME='대리';

--2) 직급이 과장인 직원들의 급여 조회
SELECT SALARY 
FROM EMPLOYEE JOIN JOB  USING(JOB_CODE)
WHERE JOB_NAME='과장';
--2200000(최소),2500000,3760000

--3) 대리 직급의 직원들 중에서 과장 직급의 최소 급여보다 많이 받는 직원의 
--3-1) MIN을 이용하여 단일행 서브쿼리 만듦

SELECT EMP_ID ,EMP_NAME ,JOB_NAME ,SALARY 
FROM EMPLOYEE JOIN JOB  USING(JOB_CODE)
WHERE JOB_NAME='대리'
AND SALARY> (SELECT MIN(SALARY) 
					FROM EMPLOYEE 
						JOIN JOB  USING(JOB_CODE)
					WHERE JOB_NAME='과장');

-- 3-2)ANY를 이용하여 과장 중 가장 급여가 적은 인원을 초과하는 대리 조회
SELECT EMP_ID ,EMP_NAME ,JOB_NAME ,SALARY 
FROM EMPLOYEE JOIN JOB  USING(JOB_CODE)
WHERE JOB_NAME='대리'
AND SALARY> ANY(SELECT SALARY 
					FROM EMPLOYEE 
					JOIN JOB  USING(JOB_CODE)
					WHERE JOB_NAME='과장');
				
--A> ANY B: A가 B의 여러 결과 중 하나라도 크면 TRUE
			-->A가 B의 최소값보다 큰 경우
				
				
				
				
--A < ANY B: A가 B의 여러 결과 중 하나라도 작으면 TRUE
			-->A가 B의 최대값보다 작은 경우
				
				
				
--차장 직급의 급여의 가장 큰 값 보다 많이 받는 과장 직급의 직원
-- 사번,이름,직급명,급여 조회
--> ALL 혹은 <ALL연산자 사용.
--	- > ALL, < ALL : 여러개의 결과값의 모든 값보다 큰 / 작은 경우
--      가장 큰 값 보다 큰가? / 가장 작은 값 보다 작은가?
SELECT SALARY 
FROM EMPLOYEE JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME='차장';

SELECT EMP_ID ,EMP_NAME ,JOB_NAME,SALARY 
FROM EMPLOYEE JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME='과장' AND SALARY >ALL (SELECT SALARY 
FROM EMPLOYEE JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME='차장');


--서브쿼리 중첩 사용
-- LOCATION 테이블에서 NATIONAL_CODE가 KO인 경우의 LOCAL_CODE와
-- DEPARTMENT 테이블의 LOCATION_ID와 동일한 DEPT_ID가 
-- EMPLOYEE테이블의 DEPT_CODE와 동일한 사원을 구하시오.

--1)LOCATION 테이블에서 NATIONAL_CODE가 KO인 경우의 LOCAL_CODE
SELECT LOCAL_CODE 
FROM LOCATION
WHERE  NATIONAL_CODE ='KO'; --L1

--2)DEPARTMENT 테이블에서 위의 결과와 동일한 LOCATION_ID를 가지고있는 DEPT_ID를 조회
SELECT DEPT_ID 
FROM DEPARTMENT
WHERE LOCATION_ID =(SELECT LOCAL_CODE 
FROM LOCATION
WHERE  NATIONAL_CODE ='KO');


--3)EMPLOYEE테이블에서 위의 결과와 동일한  DEPT_CODE를 가지는 사람 조회

SELECT EMP_NAME,DEPT_CODE 
FROM EMPLOYEE 
WHERE DEPT_CODE IN (SELECT DEPT_ID 
FROM DEPARTMENT
WHERE LOCATION_ID =(SELECT LOCAL_CODE 
FROM LOCATION
WHERE  NATIONAL_CODE ='KO'));

--> 한국에 있는 부서에서 일하는 사원 조회


----------------------------------------------------

--3. 다중열 서브쿼리 (단일행 = 결과값은 한 행)
--   서브쿼리 SELECT절에 나열된 컬럼의 수가 여러개 일 때

-- 퇴사한 여직원과 같은 부서,같은 직급에 해당하는
--사원의 이름,직급,부서,입사일을 조회

--1) 퇴사한 여직원 조회
SELECT EMP_NAME,JOB_CODE ,DEPT_CODE ,HIRE_DATE 
FROM EMPLOYEE 
WHERE ENT_YN ='Y'
AND SUBSTR(EMP_NO,8,1) ='2';


SELECT EMP_NAME, JOB_CODE,DEPT_CODE,HIRE_DATE
FROM EMPLOYEE 
WHERE DEPT_CODE =(SELECT DEPT_CODE  
FROM EMPLOYEE 
WHERE ENT_YN ='Y'
AND SUBSTR(EMP_NO,8,1) ='2')


AND  JOB_CODE= (SELECT JOB_CODE 
FROM EMPLOYEE 
WHERE ENT_YN ='Y'
AND SUBSTR(EMP_NO,8,1) ='2');

------------------------------

SELECT EMP_NAME, JOB_CODE,DEPT_CODE,HIRE_DATE
FROM EMPLOYEE 
WHERE (DEPT_CODE,JOB_CODE) =(SELECT DEPT_CODE ,JOB_CODE 
FROM EMPLOYEE 
WHERE ENT_YN ='Y'
AND SUBSTR(EMP_NO,8,1) ='2');


-----------------------------------------
-- 4. 다중행 다중열 서브쿼리
--    서브쿼리 조회 결과 행 수와 열 수가 여러개 일 때

-- 본인 직급의 평균 급여를 받고 있는 직원의
-- 사번, 이름, 직급, 급여를 조회하세요
-- 단, 급여와 급여 평균은 만원단위로 계산하세요 TRUNC(컬럼명, -4) 


--1) 급여를 200만,600만원 받는 직원 조회 (200만,600만이 평균 급여라고 가정)
SELECT EMP_ID ,EMP_NAME JOB_CODE ,SALARY 
FROM EMPLOYEE 
WHERE SALARY IN(2000000,6000000);


--2)직급별 평균 급여 (만원단위로)

SELECT JOB_CODE,TRUNC(AVG(SALARY),-4)  
FROM EMPLOYEE 
GROUP BY JOB_CODE ;

--3)본인 직급의 평균 급여를 받고 있는 직원

SELECT EMP_ID ,EMP_NAME JOB_CODE ,SALARY 
FROM EMPLOYEE 
WHERE (JOB_CODE ,SALARY) IN(SELECT JOB_CODE,TRUNC(AVG(SALARY),-4)  
                           FROM EMPLOYEE 
                           GROUP BY JOB_CODE);


-----------------------------------------------------------
--5.상[호연]관 서브쿼리 (메인쿼리 1행씩 우선 해석, 서브쿼리 나중에 해석)
--상관 쿼리는 메인쿼리가 사용하는 테이블값을 서브쿼리가 이용해서 결과를 만듦
--메인 쿼리의 테이블 값이 변경되면 서브쿼리의 결과값도 바뀌게 되는 구조

                          
--1) 메인쿼리 1행 해석
--2) 해석된 메인쿼리 1행을 이용해서 서브쿼리 조회
--3) 서브쿼리 결과를 이용해 메인쿼리 해석 중인 1행을 대상으로 조회
                          
                          
                          
                          
--직급별 급여 평균보다 급여를 많이 받는 직원의
--이름,직급코드,급여 조회
                          

                          
SELECT EMP_NAME ,JOB_CODE ,SALARY 
FROM EMPLOYEE E1
WHERE SALARY > (SELECT AVG(SALARY)  FROM EMPLOYEE E2
				WHERE E2.JOB_CODE = E1.JOB_CODE); 

		
--부서별 입사일이 가장 빠른 사원의
--사번,이름,부서명(NULL인 경우 '소속없음'),직급명, 입사일을 조회하고
--입사일이 빠른 순으로 조회
--단, 퇴사한 직원은 제외

--부서별 입사일이 가장 빠른 날짜
SELECT DEPT_CODE,MIN(HIRE_DATE)
FROM EMPLOYEE
WHERE ENT_YN ='N'
GROUP BY DEPT_CODE;

SELECT EMP_ID,EMP_NAME, NVL(DEPT_TITLE,'소속없음'), JOB_NAME,HIRE_DATE

FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID) 
JOIN JOB USING(JOB_CODE)
WHERE (DEPT_CODE,HIRE_DATE)IN(SELECT DEPT_CODE,MIN(HIRE_DATE)
							FROM EMPLOYEE
							WHERE ENT_YN ='N'
							GROUP BY DEPT_CODE)
ORDER BY HIRE_DATE;	


--상관쿼리 이용
SELECT EMP_ID,EMP_NAME, NVL(DEPT_TITLE,'소속없음'), JOB_NAME,HIRE_DATE
FROM EMPLOYEE MAIN
LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID) 
JOIN JOB USING(JOB_CODE)
WHERE HIRE_DATE = (SELECT MIN(HIRE_DATE) 
					FROM EMPLOYEE SUB
					WHERE ( MAIN.DEPT_CODE=SUB.DEPT_CODE
					OR (SUB.DEPT_CODE  IS NULL AND MAIN.DEPT_CODE IS NULL))
					AND ENT_YN='N')
					
					ORDER BY HIRE_DATE;

--특정 부서에서 가장 빠른 입사일
SELECT MIN(HIRE_DATE) 
FROM EMPLOYEE
WHERE DEPT_CODE='D1'; 
			
			
--사수가 있는 직원의 사번,이름,부서명,사수사번 조회
--EXISTS : 서브쿼리에 해당하는 행이 1개라도 존재하면 조회결과에 포함

SELECT EMP_ID,EMP_NAME,DEPT_TITLE ,MANAGER_ID
FROM EMPLOYEE MAIN
LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
WHERE EXISTS (SELECT EMP_ID FROM EMPLOYEE SUB
WHERE SUB.EMP_ID = MAIN.MANAGER_ID);
			
SELECT * FROM EMPLOYEE 
WHERE EMP_ID = 200;

----------------------------------------------------

--6.스칼라 서브쿼리
-- SELECT절에 사용되는 서브쿼리 결과로 1행(단일행)만 반환
-->SELECT절에 작성하는 단일행 서브쿼리
--SQL에서 단일 값을 '스칼라' 라고 함


SELECT EMP_NAME,SALARY, (SELECT AVG(SALARY) FROM EMPLOYEE ) 평균
FROM EMPLOYEE ;

--각 직원들이 속한 직급의 급여 평균 조회 (스칼라 + 상관쿼리)


SELECT EMP_NAME, SALARY,JOB_CODE ,(SELECT FLOOR(AVG(SALARY)) FROM EMPLOYEE SUB
									WHERE SUB.JOB_CODE = MAIN.JOB_CODE) 급여평균
FROM EMPLOYEE MAIN;



SELECT FLOOR(AVG(SALARY)) FROM EMPLOYEE
WHERE JOB_CODE = 'J1';

                          
-- 모든 사원의 사번,이름 관리자 사번,관리자 명을 조회
--단, 관리자가 없는경우 '없음'으로 표시

SELECT EMP_ID, EMP_NAME, MANAGER_ID
	,NVL((SELECT EMP_NAME FROM EMPLOYEE SUB 
		WHERE SUB.EMP_ID=MAIN.MANAGER_ID),'없음')관리자명
FROM EMPLOYEE MAIN;

----------------------------------------------------------------

--7. 인라인 뷰 (INLINE-VIEW)
--FROM 절에서 서브쿼리를 사용하는 경우로
--서브쿼리가 만든 결과의 집합(RESULT SET)을 테이블 대신에 사용

--인라인뷰를 활용은 TOP-N 분석
--전 직원중 급여가 높은 상위 5명의
-- 순위,이름,급여 조회

--1)급여 높은 순서로 조회
SELECT EMP_NAME,SALARY 
FROM EMPLOYEE 
ORDER BY SALARY DESC ;


--2) 조회되는 행 앞에 1부터 순서대로 1씩 증가하는 번호 붙이기
--ROWNUM : 행 번호를 나타내는 가상 컬럼(1부터 1씩 증가)

SELECT ROWNUM,EMP_NAME
FROM EMPLOYEE ;

--3) ROWNUM을 조건에 사용하기

SELECT ROWNUM,EMP_NAME
FROM EMPLOYEE 
WHERE ROWNUM<=5;

--4) 1,2,3번을 토대로 급여 상위 5명 조회 시도

SELECT ROWNUM,EMP_NAME,SALARY
FROM EMPLOYEE 
WHERE ROWNUM<=5
ORDER BY SALARY DESC ;
--> SELECT 해석 순서를 고려하지 않아서 제대로 조회되지 않음
--> 이를 해결하기 위해서는 [인라인 뷰]가 필요!

-- 해결 1) 급여 내림차순 조회
SELECT EMP_NAME,SALARY
FROM EMPLOYEE 
ORDER BY SALARY DESC;
-->조회 결과 (RESULT SET)을 가상의 테이블(== VIEW)로 취급할 예정

--해결 2) 해결 1의 조회 결과를 FROM 절에 사용한 후 상위 5행만 조회

SELECT ROWNUM,EMP_NAME,SALARY
FROM (SELECT EMP_NAME,SALARY
		FROM EMPLOYEE 
		ORDER BY SALARY DESC) -->FROM 절 내부에 포함된 가상의 테이블 ==인라인 뷰
WHERE ROWNUM<=5
ORDER BY SALARY DESC ;

--급여 평균이 3위 안에 드는 부서의 부서코드와 부서명, 평균급여를 조회
SELECT ROWNUM,DEPT_CODE,DEPT_TITLE, 급여평균
FROM (SELECT DEPT_CODE,DEPT_TITLE,FLOOR(AVG(SALARY)) 급여평균
		FROM EMPLOYEE 
		LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
		GROUP BY DEPT_CODE,DEPT_TITLE
		ORDER BY 3 DESC)
WHERE ROWNUM<=3;

SELECT DEPT_CODE,DEPT_TITLE,FLOOR(AVG(SALARY)) 평균급여
FROM EMPLOYEE 
LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
GROUP BY DEPT_CODE,DEPT_TITLE
ORDER BY 3 DESC ;

----------------------------------------------------------

-- 8. WITH
-- 서브쿼리에 이름을 붙여주고 사용 시 이름을 사용하게 함
--인라인뷰로 사용될 서브쿼리에 주로 사용됨
--실행 속도가 빨라진다는 장점

--전 직원의 급여 순위
--순위,이름,급여 조회

SELECT ROWNUM 순위,EMP_NAME,SALARY 
FROM (SELECT EMP_NAME ,SALARY 
	FROM EMPLOYEE 
	ORDER BY SALARY DESC);

--TOP_SAL 이라는 이름의 서브쿼리를 미리 생성
WITH TOP_SAL AS (SELECT EMP_NAME ,SALARY 
	FROM EMPLOYEE 
	ORDER BY SALARY DESC)

SELECT ROWNUM,EMP_NAME, SALARY
FROM TOP_SAL;
-----------------------------------
--9.RANK() OVER / DENSE_RANK() OVER
-- RANK() OVER : 동일한 순위 이후의 등수를 동일한 인원 수 만큼 건너뛰고 순위 계산
--               EX) 공동 1위가 2명이면 다음 순위는 2위가 아니라 3위

SELECT RANK() OVER(ORDER BY SALARY DESC) AS 순위,EMP_NAME,SALARY
FROM EMPLOYEE; 




-- DENSE_RANK() OVER : 동일한 순위 이후의 등수를 이후의 순위로 계산
--                     EX) 공동 1위가 2명이어도 다음 순위는 2위
SELECT DENSE_RANK () OVER(ORDER BY SALARY DESC) AS 순위,EMP_NAME,SALARY
FROM EMPLOYEE; 




---------------------------------------------
--연습문제1) 전지연 사원이 속해있는 부서원들 조회(전지연 제외)
SELECT DEPT_CODE 
FROM EMPLOYEE 
WHERE EMP_NAME ='전지연';
                          
SELECT EMP_ID ,EMP_NAME, PHONE ,HIRE_DATE ,DEPT_TITLE
FROM EMPLOYEE LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE DEPT_CODE =(SELECT DEPT_CODE 
FROM EMPLOYEE 
WHERE EMP_NAME ='전지연') AND EMP_NAME !='전지연';


--2)고용일이 2000년도 이후인 사원들 중 급여가 가장 높은 사원의
-- 사번 ,사원명,전화번호,급여 직급명 조회
SELECT EMP_NAME  
FROM EMPLOYEE 
WHERE TO_DATE(HIRE_DATE)>'2000/01/01';

SELECT EMP_ID ,EMP_NAME , PHONE ,SALARY ,JOB_NAME
FROM EMPLOYEE JOIN JOB USING ( JOB_CODE)
WHERE TO_DATE(HIRE_DATE)>='2000/01/01'

AND (SALARY) >=ALL (SELECT SALARY  
FROM EMPLOYEE 
WHERE TO_DATE(HIRE_DATE)>='2000/01/01')
;

--3)노옹철 사원과 같은 부서,같은직급인 사원을 조회하시오 (단 노옹철사원은 제외)
--사번,이름,부서코드,직급코드,부서명,직급명

SELECT EMP_ID,EMP_NAME,DEPT_CODE,JOB_CODE,DEPT_TITLE,JOB_NAME

FROM EMPLOYEE 
LEFT JOIN DEPARTMENT ON(DEPT_CODE=DEPT_ID)
JOIN JOB USING(JOB_CODE)
WHERE (DEPT_CODE,JOB_CODE) = (SELECT DEPT_CODE,JOB_CODE FROM EMPLOYEE WHERE EMP_NAME='노옹철')
AND EMP_NAME !='노옹철';

--4.2000년도에 입사한 사원과 부서와 직급이 같은 사원을 조회하시오
--사번,이름,부서코드,직급코드,고용일
SELECT EMP_NAME
FROM EMPLOYEE 
WHERE HIRE_DATE LIKE '00%';


SELECT EMP_ID ,EMP_NAME,DEPT_CODE ,JOB_CODE ,HIRE_DATE 
FROM EMPLOYEE 
WHERE (DEPT_CODE,JOB_CODE)=(SELECT DEPT_CODE ,JOB_CODE 
							FROM EMPLOYEE 
							WHERE HIRE_DATE LIKE '00%');



--5. 77년생 여자 사원과 동일한 부서이면서 동일한 사수를 가지고 있는 사원을 조회하시오
-- 사번,이름,부서코드,사수번호,주민번호,고용일
SELECT *FROM EMPLOYEE ;
						
SELECT EMP_NAME
FROM EMPLOYEE 
WHERE SUBSTR(EMP_NO,8,1)='2' AND SUBSTR(EMP_NO,1,2)='77' ;

SELECT EMP_ID,EMP_NAME,DEPT_CODE,MANAGER_ID ,EMP_NO,HIRE_DATE 
FROM EMPLOYEE 
WHERE (DEPT_CODE,MANAGER_ID)=(SELECT DEPT_CODE ,MANAGER_ID 
							FROM EMPLOYEE 
							WHERE SUBSTR(EMP_NO,8,1)='2' AND SUBSTR(EMP_NO,1,2)='77') ;


						
--6.부서별 입사일이 가장 빠른 사원의
--사번,이름,부서명(NULL이면 '소속없음'),직급명,입사일을 조회하고
--입사일이 빠른 순으로 조회하시오
--단,퇴사한 직원은 제외하고 조회
						

-- 퇴사한 직원을 제외하고 부서별 입사일 중 가장 빠른 날짜
SELECT MIN(HIRE_DATE) FROM EMPLOYEE
WHERE ENT_YN !='Y'
GROUP BY DEPT_CODE  ;

SELECT EMP_ID,EMP_NAME,NVL(DEPT_TITLE,'소속없음'),JOB_NAME,HIRE_DATE 
FROM EMPLOYEE LEFT JOIN DEPARTMENT ON(DEPT_ID=DEPT_CODE) LEFT JOIN JOB USING(JOB_CODE)
WHERE (HIRE_DATE) IN ((SELECT MIN(HIRE_DATE)
								FROM EMPLOYEE 
								WHERE ENT_YN='N'
								GROUP BY DEPT_CODE)) 
ORDER BY HIRE_DATE;

--상관 쿼리 사용
SELECT EMP_ID,EMP_NAME,NVL(DEPT_TITLE,'소속없음'),JOB_NAME,HIRE_DATE 
FROM EMPLOYEE E1 LEFT JOIN DEPARTMENT ON(DEPT_ID=DEPT_CODE) 
JOIN JOB USING(JOB_CODE)
WHERE HIRE_DATE =(SELECT MIN(HIRE_DATE)
					FROM EMPLOYEE E2
					WHERE( E2.DEPT_CODE = E1.DEPT_CODE
					OR(E1.DEPT_CODE IS NULL AND E2.DEPT_CODE IS NULL) )
					AND ENT_YN='N');


SELECT DEPT_CODE,MIN(HIRE_DATE)
FROM EMPLOYEE 
WHERE DEPT_CODE = 'D1'
GROUP BY DEPT_CODE
;




--7. 직급별 나이가 가장 어린 직원의
--사번 이름,직급명,나이,보너스포함 연봉을 조회하고
--나이순으로 내림차순 정렬하세요
--단 연봉은 \124,800,000 으로 출력되게 하세요 (\: 원단위 기호)

SELECT EMP_ID,EMP_NAME,JOB_NAME,
FLOOR(MONTHS_BETWEEN(SYSDATE,TO_DATE(SUBSTR(EMP_NO,1,6),'RRMMDD' ) )/12)"만나이",
TO_CHAR( SALARY * (1+NVL(BONUS,0))*12 ,'L999,999,999')"보너스 포함 연봉"
FROM EMPLOYEE E1
JOIN JOB J ON (E1.JOB_CODE=J.JOB_CODE)
WHERE FLOOR(MONTHS_BETWEEN(SYSDATE,TO_DATE(SUBSTR(EMP_NO,1,6),'RRMMDD' ) )/12) --나이
= (SELECT MIN(FLOOR(MONTHS_BETWEEN(SYSDATE,TO_DATE(SUBSTR(EMP_NO,1,6),'RRMMDD' ) )/12)) 나이
FROM EMPLOYEE E2
WHERE E1.JOB_CODE = E2.JOB_CODE)
ORDER BY "만나이" DESC ;


SELECT MIN(FLOOR(MONTHS_BETWEEN(SYSDATE,TO_DATE(SUBSTR(EMP_NO,1,6),'RRMMDD' ) )/12)) 나이
FROM EMPLOYEE 
WHERE JOB_CODE = 'J2';



SELECT EMP_ID,EMP_NAME,JOB_NAME,FLOOR( MONTHS_BETWEEN(SYSDATE,
	TO_DATE(19|| SUBSTR(EMP_NO,1,6),'YYYYMMDD' ) )/12) 나이, TO_CHAR(SALARY*(NVL(BONUS,0)+1) *12,'L999,999,999') 보너스포함연봉 
FROM EMPLOYEE JOIN JOB USING (JOB_CODE)
WHERE  (JOB_NAME,EMP_NO) IN (SELECT JOB_NAME,MAX(EMP_NO)
							FROM EMPLOYEE JOIN JOB USING (JOB_CODE)
								GROUP BY JOB_NAME)
ORDER BY EMP_NO;
 ;
SELECT JOB_NAME,MAX(EMP_NO)
FROM EMPLOYEE JOIN JOB USING (JOB_CODE)
GROUP BY JOB_NAME;






