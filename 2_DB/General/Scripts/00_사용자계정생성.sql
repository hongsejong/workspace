-- 한줄 주석 ctrl + /

/*범위 주석 ctrl + shift + /  */

-- 예전 버전(11g 이전 버전) 오라클 구문 사용하기
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

CREATE USER test_hsj IDENTIFIED BY member1234;
		--사용자명					 비밀번호
GRANT CONNECT, RESOURCE TO test_hsj;

--CONNECT : DB 연결 권한 ROLE
--RESOURCE : DB 기본 객체 생선 권한 ROLE

--객체(테이블 등)가 생성될 수 있는 공간 할당량 지정
ALTER USER test_hsj DEFAULT TABLESPACE SYSTEM QUOTA
UNLIMITED ON SYSTEM;



