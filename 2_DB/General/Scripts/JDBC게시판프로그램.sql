
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- [관리자 계정]
CREATE USER member_hsj IDENTIFIED BY member1234;
-- [관리자 계정]
GRANT CONNECT, RESOURCE TO member_hsj;

--[관리자 계정]
ALTER USER member_hsj DEFAULT TABLESPACE SYSTEM QUOTA
UNLIMITED ON SYSTEM;

-- member 이니셜 계정 접속 방법 추가


-- [멤버 계정]
-- 테이블명 : MEMBER
-- 컬럼명 : MEMBER_NO 숫자 기본키 '회원 번호(PK)'
--        MEMBER_ID VARCHAR2(20) NULL허용 X  '회원 아이디'
--        MEMBER_PW VARCHAR2(20) NULL허용 X '회원 비밀번호'
--        MEMBER_NM VARCHAR2(30) NULL허용 X '회원 이름'
--         MEMBER_GENDER CHAR(1) M 또는 F만 입력 가능 '회원 성별(M/F)'
--        ENROLL_DATE DATE 기본값 현재날짜 '회원 가입일'
--        SECESSION_FL CHAR(1) 기본값 'N', Y 또는 N만 입력 가능 '탈퇴여부(Y/N)'

DROP TABLE MEMBER;
CREATE TABLE MEMBER(
		MEMBER_NO NUMBER PRIMARY KEY,
        MEMBER_ID VARCHAR2(20) NOT NULL, 
        MEMBER_PW VARCHAR2(20) NOT NULL,
        MEMBER_NM VARCHAR2(30)  NOT NULL,
        MEMBER_GENDER CHAR(1)  CHECK(MEMBER_GENDER IN('M','F')),
        ENROLL_DATE DATE  DEFAULT SYSDATE,
        SECESSION_FL CHAR(1)  DEFAULT 'N' CHECK(SECESSION_FL IN('Y','N'))
);
        COMMENT ON COLUMN MEMBER.MEMBER_NO IS '회원 번호(PK)';
        COMMENT ON COLUMN MEMBER.MEMBER_ID IS '회원 아이디';
        COMMENT ON COLUMN MEMBER.MEMBER_PW IS '회원 비밀번호';
        COMMENT ON COLUMN MEMBER.MEMBER_NM IS '회원 이름';
        COMMENT ON COLUMN MEMBER.MEMBER_GENDER IS '회원 성별(M/F)';
        COMMENT ON COLUMN MEMBER.ENROLL_DATE IS '회원 가입일';
        COMMENT ON COLUMN MEMBER.SECESSION_FL IS '탈퇴여부(Y/N)';





CREATE TABLE USER_USED_PK(
	USER_NO NUMBER /*CONSTRAINT USER_NO_PK *//*PRIMARY KEY*/, -- 컬럼레벨 PK 지정,
					--제약조건명 생략 가능
	USER_ID VARCHAR2(20),
	USER_PWD VARCHAR2(30),
	USER_NAME VARCHAR2(30),
	GENDER VARCHAR2(1),
	PHONE VARCHAR2(30),
	EMAIL VARCHAR2(50),
	CONSTRAINT USER_NO_PK PRIMARY KEY(USER_NO) --테이블 레벨 PK 지정
);



-- 게시판 테이블

CREATE TABLE BOARD(
    BOARD_NO NUMBER PRIMARY KEY,
    BOARD_TITLE VARCHAR2(200) NOT NULL,
    BOARD_CONTENT VARCHAR2(4000) NOT NULL,
    CREATE_DATE DATE DEFAULT SYSDATE,
    READ_COUNT NUMBER DEFAULT 0,
    MEMBER_NO NUMBER REFERENCES MEMBER  -- MEMBER 테이블 PK값 참조
);

COMMENT ON COLUMN BOARD.BOARD_NO        IS '게시글 번호';
COMMENT ON COLUMN BOARD.BOARD_TITLE     IS '게시글 제목';
COMMENT ON COLUMN BOARD.BOARD_CONTENT   IS '게시글 내용';
COMMENT ON COLUMN BOARD.CREATE_DATE     IS '게시글 작성일';
COMMENT ON COLUMN BOARD.READ_COUNT      IS '조회수';
COMMENT ON COLUMN BOARD.MEMBER_NO       IS '회원 번호(작성자)';

-- 댓글 테이블
CREATE TABLE REPLY(
    REPLY_NO NUMBER PRIMARY KEY,
    REPLY_CONTENT VARCHAR2(500) NOT NULL,
    CREATE_DATE DATE DEFAULT SYSDATE,
    MEMBER_NO NUMBER REFERENCES MEMBER, -- MEMBER 테이블 PK 참조
    BOARD_NO NUMBER REFERENCES BOARD -- BOARD 테이블 PK 참조
);

COMMENT ON COLUMN REPLY.REPLY_NO         IS '댓글 번호(PK)';
COMMENT ON COLUMN REPLY.REPLY_CONTENT    IS '댓글 내용';
COMMENT ON COLUMN REPLY.CREATE_DATE      IS '댓글 작성일';
COMMENT ON COLUMN REPLY.MEMBER_NO        IS '회원 번호(작성자)';
COMMENT ON COLUMN REPLY.BOARD_NO         IS '게시글 번호(어떤 게시글의 댓글인지 확인)';

-- 각 테이블 PK 생성용 시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER_NO; -- 1부터 1씩 증가, 반복 X
CREATE SEQUENCE SEQ_BOARD_NO;
CREATE SEQUENCE SEQ_REPLY_NO;

SELECT * FROM BOARD; 
SELECT * FROM REPLY ;

----------------------------------------------------------

--아이디 중복 검사 

SELECT COUNT(*)  FROM MEMBER WHERE MEMBER_ID ='USER01' AND SECESSION_FL ='N'; -- 탈퇴 안한 계정
SELECT * FROM "MEMBER" ;

--회원 가입

-- 2)  INSERT INTO 테이블명(컬럼명, 컬럼명, 컬럼명,...)
-- VALUES (데이터1, 데이터2, 데이터3, ...);
-- 테이블에 내가 선택한 컬럼에 대한 값만 INSERT할 때 사용
-- 선택안된 컬럼은 값이 NULL이 들어감
INSERT INTO MEMBER VALUES(SEQ_MEMBER_NO.NEXTVAL, ?,?,?,?,DEFAULT,DEFAULT);

--회원 목록 조회
SELECT MEMBER_ID, MEMBER_NM, ENROLL_DATE
FROM MEMBER
WHERE SECESSION_FL ='N'; 


SELECT * FROM MEMBER ;



--UPDATE 테이블명 SET 컬럼명 = 바꿀값 [WHERE 컬럼명 비교연산자 비교값];

UPDATE MEMBER SET 
MEMBER_NM ='유저일이' ,
MEMBER_GENDER ='F'
WHERE MEMBER_NO ='회원번호';


UPDATE MEMBER SET MEMBER_PW = '새비밀번호' WHERE MEMBER_NO = '회원번호'
AND MEMBER_PW = '기존비밀번호';
COMMIT;

UPDATE MEMBER SET 
SECESSION_FL ='Y' 
WHERE MEMBER_NO= ?
AND MEMBER_PW=? ;

ROLLBACK;

	SELECT MEMBER_NO, MEMBER_ID, MEMBER_NM, MEMBER_GENDER,MEMBER_PW,
		ENROLL_DATE FROM
		MEMBER WHERE MEMBER_ID = 'user01' AND SECESSION_FL = 'N' AND
		MEMBER_PW = 'pass01';
		
	
	
	
-------게시글 목록 조회 + 댓글 개수(상관 서브쿼리 + 스칼라 서브쿼리)
SELECT  BOARD_NO, BOARD_TITLE, CREATE_DATE,READ_COUNT,MEMBER_NM,
	(SELECT COUNT(*) FROM REPLY R
	WHERE R.BOARD_NO=B.BOARD_NO) REPLY_COUNT
FROM BOARD B
JOIN MEMBER USING (MEMBER_NO)
ORDER BY BOARD_NO DESC ;

--댓글 개수 조회(특정 게시글)
SELECT COUNT(*) FROM REPLY
WHERE BOARD_NO='1';






-- 게시글 샘플 데이터
INSERT INTO BOARD
VALUES(SEQ_BOARD_NO.NEXTVAL,'샘플 게시글 1','샘플1 내용입니다.',DEFAULT,DEFAULT,2);
INSERT INTO BOARD
VALUES(SEQ_BOARD_NO.NEXTVAL,'샘플 게시글 2','샘플2 내용입니다.',DEFAULT,DEFAULT,2);
INSERT INTO BOARD
VALUES(SEQ_BOARD_NO.NEXTVAL,'샘플 게시글 3','샘플3 내용입니다.',DEFAULT,DEFAULT,2);

COMMIT;
SELECT * FROM BOARD; 
SELECT * FROM MEMBER;

--댓글 샘플 데이터 삽입
SELECT * FROM REPLY ;

INSERT INTO REPLY 
VALUES(SEQ_REPLY_NO.NEXTVAL,'샘플1의 댓글1',DEFAULT,2,2);

INSERT INTO REPLY 
VALUES(SEQ_REPLY_NO.NEXTVAL,'샘플1의 댓글2',DEFAULT,2,1);

INSERT INTO REPLY 
VALUES(SEQ_REPLY_NO.NEXTVAL,'샘플1의 댓글3',DEFAULT,2,1);

COMMIT;


--특정 게시글 상세 조회


SELECT B.* , MEMBER_NM
FROM BOARD B
JOIN MEMBER M ON(B.MEMBER_NO=M.MEMBER_NO)
WHERE BOARD_NO=1;
--댓글 목록에서 최근에 작성한 글은 제일위?  제일아래?
--						  SNS    카페,커뮤니티

--특정 게시글의 댓글 목록 조회
SELECT R.* , MEMBER_NM
FROM REPLY R
JOIN MEMBER M ON (R.MEMBER_NO= M.MEMBER_NO)
WHERE BOARD_NO =1
ORDER BY REPLY_NO DESC ;-- 최근 댓글 상단
--ORDER BY REPLY_NO; -- 최근 댓글 하단

--조회수 증가
UPDATE BOARD SET 
READ_COUNT= READ_COUNT+1
WHERE BOARD_NO=?;


SELECT * FROM "MEMBER" ; 
SELECT * FROM BOARD ; 
INSERT INTO BOARD
VALUES(SEQ_BOARD_NO.NEXTVAL,'샘플 게시글 1','샘플1 내용입니다.',DEFAULT,DEFAULT,23);

DELETE FROM BOARD
WHERE BOARD_NO=1;
ROLLBACK;
COMMIT;



--게시글 삭제
--SQL Error [2292] [23000]: ORA-02292: 무결성 제약조건(MEMBER_HSJ.SYS_C007534)이 위배되었습니다- 자식 레코드가 발견되었습니다
DELETE FROM BOARD WHERE BOARD_NO=1;


SELECT *FROM BOARD WHERE BOARD_NO=2; --BOARD 테이블 1번 게시글
SELECT *FROM REPLY WHERE BOARD_NO=1; -- REPLY 테이블에서 BOARD 테이블 1번 게시글을 참조하는 댓글

-->기본적으로 삭제 불가
--> 삭제 옵션을 추가하면 가능
--ON DELETE SET NULL(자식 컬럼 NULL) / ON DELETE CASCADE(참조하던 자식 행도 삭제)

--제약조건은 ALTER(변경) 없음 -> 삭제 후 다시 추가

--기존 REPLY 테이블 FK 제약조건 삭제
-- 제약조건 삭제 : ALTER TABLE 테이블명
--                 DROP CONSTRAINT 제약조건명;
ALTER TABLE REPLY DROP CONSTRAINT SYS_C007534;
-- 삭제 옵션이 추가된 FK를 다시 추가
--제약조건 추가 : FOREIGN KEY(컬럼명) REFERENCES 참조 테이블명(참조컬럼명)
ALTER TABLE REPLY 
ADD FOREIGN KEY(BOARD_NO) REFERENCES BOARD ON DELETE CASCADE; 

ROLLBACK;



--UPDATE 테이블명 SET 컬럼명 = 바꿀값 [WHERE 컬럼명 비교연산자 비교값];
UPDATE BOARD SET 
BOARD_TITLE=? , BOARD_CONTENT =?
WHERE BOARD_NO =?;


SELECT * FROM REPLY ;

INSERT INTO REPLY 
VALUES(SEQ_REPLY_NO.NEXTVAL,'샘플1의 댓글3',DEFAULT,2,2);

INSERT INTO REPLY 
VALUES(SEQ_REPLY_NO.NEXTVAL,?,DEFAULT,?,?);
COMMIT;

UPDATE REPLY SET REPLY_CONTENT=? 
WHERE REPLY_NO=? ;


SELECT R.* , MEMBER_NM
		FROM REPLY R
		JOIN MEMBER M ON
		(R.MEMBER_NO=M.MEMBER_NO)
		WHERE BOARD_NO =2 
		ORDER BY REPLY_NO DESC
--댓글 수정
UPDATE REPLY SET 
REPLY_CONTENT = ?
WHERE REPLY_NO = ?;
--댓글 삭제
DELETE FROM REPLY 
WHERE REPLY_NO = ?;

SELECT * FROM BOARD ;
--게시글 등록 // 제목,내용,회원번호 필요
INSERT INTO REPLY VALUES(SEQ_REPLY_NO.NEXTVAL,?,?,DEFAULT,DEFAULT,?);

--게시글 검색
--제목 검색

SELECT  BOARD_NO, BOARD_TITLE, CREATE_DATE,READ_COUNT,MEMBER_NM,
	(SELECT COUNT(*) FROM REPLY R
	WHERE R.BOARD_NO=B.BOARD_NO) REPLY_COUNT
FROM BOARD B
JOIN MEMBER USING (MEMBER_NO)
WHERE BOARD_TITLE LIKE '%수정%' --제목 검색
ORDER BY BOARD_NO DESC ;

--내용 검색
SELECT  BOARD_NO, BOARD_TITLE, CREATE_DATE,READ_COUNT,MEMBER_NM,
	(SELECT COUNT(*) FROM REPLY R
	WHERE R.BOARD_NO=B.BOARD_NO) REPLY_COUNT
FROM BOARD B
JOIN MEMBER USING (MEMBER_NO)
WHERE BOARD_CONTENT LIKE '%2%' --내용 검색
ORDER BY BOARD_NO DESC ;
--제목 +내용 검색
SELECT  BOARD_NO, BOARD_TITLE, CREATE_DATE,READ_COUNT,MEMBER_NM,
	(SELECT COUNT(*) FROM REPLY R
	WHERE R.BOARD_NO=B.BOARD_NO) REPLY_COUNT
FROM BOARD B
JOIN MEMBER USING (MEMBER_NO)
WHERE (BOARD_CONTENT LIKE '%게시글%' OR
BOARD_CONTENT LIKE '%샘플%')--제목 +내용 검색
ORDER BY BOARD_NO DESC ;
--작성자 검색
SELECT  BOARD_NO, BOARD_TITLE, CREATE_DATE,READ_COUNT,MEMBER_NM,
	(SELECT COUNT(*) FROM REPLY R
	WHERE R.BOARD_NO=B.BOARD_NO) REPLY_COUNT
FROM BOARD B
JOIN MEMBER USING (MEMBER_NO)
WHERE MEMBER_NM LIKE '%길동%' --내용 검색
ORDER BY BOARD_NO DESC ;




	