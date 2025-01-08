-- 예전 버전(11g 이전 버전) 오라클 구문 사용하기
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

CREATE USER community_hsj IDENTIFIED BY community1234;
      -- 사용자명             비밀번호

GRANT CONNECT, RESOURCE TO community_hsj;
-- CONNECT : DB 연결 권한 ROLE
-- RESOURCE : DB 기본 객체 생성 권한 ROLE

-- 객체(테이블 등)가 생성될 수 있는 공간 할당량 지정
ALTER USER community_hsj DEFAULT TABLESPACE SYSTEM QUOTA 
UNLIMITED ON SYSTEM;

--------------------------------------------------------


CREATE TABLE "MEMBER" ( 
   "MEMBER_NO"   NUMBER   PRIMARY KEY,
   "MEMBER_EMAIL"   VARCHAR2(50) NOT NULL,
   "MEMBER_PW"   VARCHAR2(30) NOT NULL,
   "MEMBER_NICK"   VARCHAR2(30) NOT NULL,
   "MEMBER_TEL"   CHAR(11) NOT NULL,
   "MEMBER_ADDR"   VARCHAR2(500),
   "PROFILE_IMG"   VARCHAR2(200),
   "ENROLL_DT"   DATE DEFAULT SYSDATE ,
   "SECESSION_FL"   CHAR(1)   DEFAULT 'N'
);

COMMENT ON COLUMN "MEMBER"."MEMBER_NO" IS '회원 번호';
COMMENT ON COLUMN "MEMBER"."MEMBER_EMAIL" IS '회원 이메일(아이디)';
COMMENT ON COLUMN "MEMBER"."MEMBER_PW" IS '회원 비밀번호';
COMMENT ON COLUMN "MEMBER"."MEMBER_NICK" IS '회원 닉네임(중복x)';
COMMENT ON COLUMN "MEMBER"."MEMBER_TEL" IS '전화번호(- 미포함)';
COMMENT ON COLUMN "MEMBER"."MEMBER_ADDR" IS '회원 주소';
COMMENT ON COLUMN "MEMBER"."PROFILE_IMG" IS '회원 프로필 이미지';
COMMENT ON COLUMN "MEMBER"."ENROLL_DT" IS '회원 가입일';
COMMENT ON COLUMN "MEMBER"."SECESSION_FL" IS '탈퇴여부(Y:탈퇴, N:미탈퇴)';

-- 회원 번호 시퀀스
CREATE SEQUENCE SEQ_MEMBER_NO;

INSERT INTO MEMBER
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user01@kh.or.kr', 'pass01!', 
    '유저일', '01012341234', '04540,,서울특별시 강남구 테헤란로 14길 6 5층',
     NULL, DEFAULT, DEFAULT);

COMMIT;

SELECT * FROM MEMBER;

-- 이메일, 비밀번호가 일치하는 회원 조회
SELECT  MEMBER_NO, MEMBER_EMAIL, MEMBER_NICK, MEMBER_TEL, MEMBER_ADDR,
      PROFILE_IMG, TO_CHAR(ENROLL_DT, 'YYYY-MM-DD HH24:MI:SS') AS ENROLL_DT
FROM "MEMBER" 
WHERE MEMBER_EMAIL = 'user01@kh.or.kr'
AND MEMBER_PW = 'pass01!'
AND SECESSION_FL = 'N';

--암호화된 비밀번호로 업데이트
UPDATE MEMBER SET
MEMBER_PW='aBN5hiegXlvAovJLipPoPd5LB+xjPrAeu1tcAVg0p5MKGocvo6l825SD+ZMCOcHBFeGB7MnzH31SAnDzYYsSdg=='
WHERE MEMBER_NO=1;

COMMIT;


--MEMBER TABLE 비밀번호 컬럼 크기 변경
ALTER TABLE "MEMBER" 
MODIFY MEMBER_PW VARCHAR2(100);

------------------------------------------
DROP TABLE "BOARD_TYPE";

CREATE TABLE "BOARD_TYPE" (
	"BOARD_CD"	NUMBER		NOT NULL,
	"BOARD_NM"	VARCHAR2(50)		NOT NULL
);

COMMENT ON COLUMN "BOARD_TYPE"."BOARD_CD" IS '게시판코드';

COMMENT ON COLUMN "BOARD_TYPE"."BOARD_NM" IS '게시판 이름';


ALTER TABLE BOARD_TYPE
ADD PRIMARY KEY(BOARD_CD); --제약 조건명 생략(SYS_XXXXX)



DROP TABLE "BOARD";

CREATE TABLE "BOARD" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"BOARD_TITLE"	VARCHAR2(150)		NOT NULL,
	"BOARD_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"CREATE_DT"	DATE	DEFAULT SYSDATE	NOT NULL,
	"UPDATE_DT"	DATE		NULL,
	"READ_COUNT"	NUMBER	DEFAULT 0	NOT NULL,
	"BOARD_ST"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"BOARD_CD"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "BOARD"."BOARD_NO" IS '게시글번호(시퀀스)';

COMMENT ON COLUMN "BOARD"."BOARD_TITLE" IS '게시글 제목';

COMMENT ON COLUMN "BOARD"."BOARD_CONTENT" IS '게시글 내용';

COMMENT ON COLUMN "BOARD"."UPDATE_DT" IS '마지막수정일';

COMMENT ON COLUMN "BOARD"."READ_COUNT" IS '조회수';

COMMENT ON COLUMN "BOARD"."BOARD_ST" IS '게시글상태(정상N,삭제Y)';

COMMENT ON COLUMN "BOARD"."MEMBER_NO" IS '회원번호(시퀀스)';

COMMENT ON COLUMN "BOARD"."BOARD_CD" IS '게시판코드';




SELECT * FROM BOARD ;

-- BOARD 테이블 제약조건 추가

--BOARD_NO에 PK 추가

ALTER TABLE BOARD
ADD PRIMARY KEY(BOARD_NO); --제약 조건명 생략(SYS_XXXXX)

---BOARD_CD에 FK 추가 / 제약 조건명 : FK_BOARD_CD
ALTER TABLE BOARD 
ADD CONSTRAINT "FK_BOARD_CD" -- 제약 조건명 지정 
FOREIGN KEY (BOARD_CD)  --BOARD의 BOARD_CD 컬럼에 FK 지정
REFERENCES BOARD_TYPE; -- 참조할 테이블
 

--BOARD_ST에 CHECK 제약조건 추가 / 제약조건명 : CHK_BOARD_ST

ALTER TABLE BOARD
ADD CONSTRAINT CHK_BOARD_ST CHECK (BOARD_ST IN ('Y','N'));


--MEMBER_NO에 FK 추가 / 제약 조건명 : FK_BOARD_MEMBER
ALTER TABLE BOARD 
ADD CONSTRAINT "FK_BOARD_MEMBER" 
FOREIGN KEY (MEMBER_NO)  
REFERENCES "MEMBER"; 



--BOARD_NO용 시퀀스 생성 / 시퀀스명 : SEQ_BOARD_NO

CREATE SEQUENCE SEQ_BOARD_NO NOCACHE;

--BOARD_TYPE 데이터 삽입
INSERT INTO BOARD_TYPE  VALUES (1, '공지사항');
INSERT INTO BOARD_TYPE  VALUES (2, '자유게시판');
INSERT INTO BOARD_TYPE  VALUES (3, '질문게시판');



SELECT * FROM BOARD_TYPE;

--BOARD 테이블 샘플 데이터 삽입(PL/SQL)

BEGIN
    FOR I IN 1..500 LOOP
    
        INSERT INTO BOARD
        VALUES(SEQ_BOARD_NO.NEXTVAL,
               SEQ_BOARD_NO.CURRVAL || '번째 게시글',
               SEQ_BOARD_NO.CURRVAL || '번째 게시글 내용입니다.',
               DEFAULT, DEFAULT, DEFAULT, DEFAULT, 1, 3
        );
    END LOOP;
END;
/
;
SELECT * FROM BOARD ;


--공지사항 게시판 조회
SELECT COUNT(*) FROM BOARD WHERE BOARD_CD=1;
--자유 게시판 조회
SELECT COUNT(*) FROM BOARD WHERE BOARD_CD=2;
--질문 게시판 조회
SELECT COUNT(*) FROM BOARD WHERE BOARD_CD=3;

COMMIT;

SELECT * FROM BOARD;

UPDATE MEMBER SET MEMBER_ADDR='04540,,서울특별시 강남구 테헤란로 14길 6,,5층';

SELECT * FROM MEMBER ;


--회원 정보 수정 SQL문

UPDATE MEMBER SET 
    MEMBER_NICK = ?,
    MEMBER_TEL = ?, 
    MEMBER_ADDR = ?
WHERE MEMBER_NO = ?;

--비밀번호 변경 sql

UPDATE MEMBER 
SET MEMBER_PW='새로운비밀번호'
WHERE MEMBER_NO ='로그인한회원번호'
AND MEMBER_PW='현재 로그인한 비밀번호';

--회원탈퇴 / SECESSION_FL 변경
UPDATE MEMBER 
SET SECESSION_FL ='Y'
WHERE MEMBER_NO =? 
AND MEMBER_PW=?;
--홍길동 살리기
UPDATE MEMBER 
SET SECESSION_FL ='N'
WHERE MEMBER_NO =1 ;
COMMIT;


--이메일 중복 검사
SELECT count(*) FROM MEMBER
WHERE MEMBER_EMAIL='user01@kh.or.kr';

SELECT COUNT(*) FROM MEMBER
WHERE MEMBER_NICK='유저일'
AND SECESSION_FL = 'N';





