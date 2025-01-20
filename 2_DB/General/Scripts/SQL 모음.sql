-- 예전 버전(11g 이전 버전) 오라클 구문 사용하기
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

CREATE USER community_hsj IDENTIFIED BY community1234;
--CREATE USER project_hsj IDENTIFIED BY project1234;
      -- 사용자명             비밀번호

GRANT CONNECT, RESOURCE TO community_hsj;
--GRANT CONNECT, RESOURCE TO project_hsj;
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

--회원 정보 조회
SELECT MEMBER_EMAIL ,MEMBER_NICK ,MEMBER_TEL ,MEMBER_ADDR ,
		TO_CHAR(ENROLL_DT, 'YYYY"년" MM"월" DD|"일"') AS ENROLL_DT  
FROM MEMBER
WHERE SECESSION_FL ='N'
AND MEMBER_EMAIL ='user01@kh.or.kr';


--전체 조회
	SELECT MEMBER_EMAIL ,MEMBER_NICK ,MEMBER_TEL ,MEMBER_ADDR ,
		TO_CHAR(ENROLL_DT, 'YYYY"년" MM"월" DD|"일"') AS ENROLL_DT
		FROM MEMBER
		WHERE SECESSION_FL ='N';
	
--회원 목록 조회
SELECT MEMBER_NO,MEMBER_EMAIL,MEMBER_NICK
FROM "MEMBER"
WHERE SECESSION_FL='N';

-- 게시판 이름 조회
SELECT  BOARD_NM FROM BOARD_TYPE
WHERE BOARD_CD =3;

--특정 게시판의 전체 게시글 수 조회
SELECT COUNT(*) 
FROM BOARD
WHERE BOARD_CD =3
AND BOARD_ST='N';

-- 특정 게시판의 게시글 목록 조회


SELECT BOARD_NO,BOARD_TITLE,MEMBER_NICK,
TO_CHAR(CREATE_DT,'YYYY-MM-DD') AS CREATE_DT
,READ_COUNT 
FROM BOARD 
JOIN MEMBER USING(MEMBER_NO)
WHERE BOARD_CD=1
AND BOARD_ST ='N'
ORDER BY BOARD_NO DESC ;


--인라인뷰
SELECT ROWNUM RNUM, A.* FROM(
SELECT BOARD_NO,BOARD_TITLE,MEMBER_NICK,
TO_CHAR(CREATE_DT,'YYYY-MM-DD') AS CREATE_DT
,READ_COUNT 
FROM BOARD 
JOIN MEMBER USING(MEMBER_NO)
WHERE BOARD_CD=1
AND BOARD_ST ='N'
ORDER BY BOARD_NO DESC
)A;
--인라인완성 (ROWNUM 때문에 별칭주고 두번 감싸야함)
SELECT * FROM(
	SELECT ROWNUM RNUM, A.* FROM(
		SELECT BOARD_NO,BOARD_TITLE,MEMBER_NICK,
		TO_CHAR(CREATE_DT,'YYYY-MM-DD') AS CREATE_DT
		,READ_COUNT 
	FROM BOARD 
	JOIN MEMBER USING(MEMBER_NO)
	WHERE BOARD_CD=1
	AND BOARD_ST ='N'
	ORDER BY BOARD_NO DESC
	)A
)

WHERE RNUM BETWEEN 1 AND 10;
-----------

--게시판 이미지 테이블 생성
DROP TABLE "BOARD_IMG";

CREATE TABLE "BOARD_IMG" (
	"IMG_NO"	NUMBER		PRIMARY KEY,
	"IMG_RENAME"	VARCHAR2(500)		NOT NULL,
	"IMG_ORIGINAL"	VARCHAR2(500)		NOT NULL,
	"IMG_LEVEL"	NUMBER		NOT NULL,
	"BOARD_NO"	NUMBER	REFERENCES BOARD
);

COMMENT ON COLUMN "BOARD_IMG"."IMG_NO" IS '이미지번호';

COMMENT ON COLUMN "BOARD_IMG"."IMG_RENAME" IS '이미지 저장 경로 + 변경된 이름';

COMMENT ON COLUMN "BOARD_IMG"."IMG_ORIGINAL" IS '이미지 원본명';

COMMENT ON COLUMN "BOARD_IMG"."IMG_LEVEL" IS '이미지 위치지정 번호';

COMMENT ON COLUMN "BOARD_IMG"."BOARD_NO" IS '게시글번호(시퀀스)';

--이미지 번호 시퀀스 생성
CREATE SEQUENCE SEQ_IMG_NO NOCACHE;

--샘플 데이터
INSERT INTO BOARD_IMG VALUES(
	SEQ_IMG_NO.NEXTVAL,'/resources/images/board/sample1.jpg','cat1.jpg',0,500
);

INSERT INTO BOARD_IMG VALUES(
	SEQ_IMG_NO.NEXTVAL,'/resources/images/board/sample2.jpg','cat2.jpg',1,500
);

INSERT INTO BOARD_IMG VALUES(
	SEQ_IMG_NO.NEXTVAL,'/resources/images/board/sample3.jpg','cat3.jpg',2,500
);

INSERT INTO BOARD_IMG VALUES(
	SEQ_IMG_NO.NEXTVAL,'/resources/images/board/sample4.jpg','cat4.jpg',3,500
);

INSERT INTO BOARD_IMG VALUES(
	SEQ_IMG_NO.NEXTVAL,'/resources/images/board/sample5.jpg','cat5.jpg',4,500
);

COMMIT;

-- 게시글 상세조회
SELECT BOARD_NO, BOARD_TITLE, BOARD_CONTENT,
	TO_CHAR(CREATE_DT, 'YYYY"년" MM"월" DD"일" HH24:MI:SS') AS CREATE_DT, 
	TO_CHAR(UPDATE_DT, 'YYYY"년" MM"월" DD"일" HH24:MI:SS') AS UPDATE_DT ,
	READ_COUNT , MEMBER_NO ,MEMBER_NICK , PROFILE_IMG ,BOARD_NM
FROM BOARD
JOIN MEMBER USING(MEMBER_NO)
JOIN BOARD_TYPE USING(BOARD_CD)
WHERE BOARD_NO =500
AND BOARD_ST = 'N';


-- 특정 게시글에 첨부된 이미지 목록 조회
SELECT * FROM BOARD_IMG 
WHERE BOARD_NO =500
ORDER BY IMG_LEVEL ;

--------------------------------------------------
-----------------------------------------------------
-- 댓글 테이블 생성
CREATE TABLE REPLY(
  REPLY_NO NUMBER PRIMARY KEY,
  REPLY_CONTENT VARCHAR2(1000) NOT NULL,
  REPLY_ST CHAR(1) DEFAULT 'N' CHECK (REPLY_ST IN ('Y', 'N')),
  CREATE_DT DATE DEFAULT SYSDATE,
  MEMBER_NO NUMBER REFERENCES MEMBER NOT NULL,
  BOARD_NO NUMBER REFERENCES BOARD NOT NULL
);

COMMENT ON COLUMN REPLY.REPLY_NO IS '댓글 번호(PK)';
COMMENT ON COLUMN REPLY.REPLY_CONTENT IS '댓글  내용';
COMMENT ON COLUMN REPLY.REPLY_ST IS '댓글 상태(N:정상, Y:삭제)';
COMMENT ON COLUMN REPLY.CREATE_DT IS '댓글 작성일';
COMMENT ON COLUMN REPLY.MEMBER_NO IS '댓글 작성 회원 번호';
COMMENT ON COLUMN REPLY.BOARD_NO IS '댓글이 작성된 게시글 번호';

-- 댓글 번호 시퀀스
CREATE SEQUENCE SEQ_RNO NOCACHE;

-- 가장 최근 게시글에 댓글 샘플 데이터 삽입
INSERT INTO REPLY VALUES(SEQ_RNO.NEXTVAL, '댓글 테스트1', DEFAULT, DEFAULT, 1, 500);
INSERT INTO REPLY VALUES(SEQ_RNO.NEXTVAL, '댓글 테스트2', DEFAULT, DEFAULT, 1, 500);

COMMIT;
SELECT * FROM REPLY ;


-- 댓글 목록 조회
SELECT REPLY_CONTENT ,CREATE_DT , PROFILE_IMG ,MEMBER_NICK 
FROM REPLY JOIN MEMBER USING(MEMBER_NO);

SELECT REPLY_NO,REPLY_CONTENT, 
	TO_CHAR(CREATE_DT,'YYYY"년" MM"월" DD"일" HH24:MI:SS') CREATE_DT,
	MEMBER_NO,MEMBER_NICK, PROFILE_IMG,BOARD_NO
FROM REPLY
JOIN MEMBER USING(MEMBER_NO)
WHERE REPLY_ST ='N'
AND BOARD_NO=500--'현재 게시글 번호';
ORDER BY REPLY_NO;

----
SELECT REPLY_NO,REPLY_CONTENT, 
	TO_CHAR(CREATE_DT,'YYYY"년" MM"월" DD"일" HH24:MI:SS') CREATE_DT,
	MEMBER_NO,MEMBER_NICK, PROFILE_IMG,BOARD_NO
FROM REPLY
JOIN MEMBER USING(MEMBER_NO)
WHERE REPLY_ST ='N'
AND BOARD_NO=500
ORDER BY REPLY_NO;

UPDATE MEMBER SET
PROFILE_IMG ='/resources/images/logo.jpg'
WHERE MEMBER_NO=1;
COMMIT;

SELECT * FROM "REPLY"; 
SELECT * FROM BOARD ; 

--댓글 삽입
INSERT INTO REPLY 
VALUES(SEQ_RNO.NEXTVAL, ?, DEFAULT, DEFAULT, ?,?);

--

SELECT * FROM REPLY;


-- 댓글 삭제
UPDATE REPLY SET
REPLY_ST ='Y'
WHERE REPLY_NO=?;

--댓글 수정

UPDATE REPLY SET
REPLY_CONTENT =?
WHERE REPLY_NO=?;

SELECT * FROM BOARD
ORDER BY 1;

UPDATE MEMBER SET
PROFILE_IMG =?
WHERE MEMBER_NO=?;

SELECT * FROM BOARD_IMG ;
SELECT * FROM BOARD ;

--다음 게시글 번호 조회


SELECT SEQ_BOARD_NO.NEXTVAL
FROM DUAL;
ROLLBACK;
---게시글 삽입
INSERT INTO BOARD
VALUES(?,?,?, DEFAULT,DEFAULT,DEFAULT,DEFAULT,?,?)
 
--게시글 이미지 삽입
INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL,?,?,?,?);


--게시글 목록조회(썸네일 추가)

SELECT * FROM(
	SELECT ROWNUM RNUM, A.* FROM(
		SELECT BOARD_NO,BOARD_TITLE,MEMBER_NICK,
		TO_CHAR(CREATE_DT,'YYYY-MM-DD') AS CREATE_DT
		,READ_COUNT , (SELECT IMG_RENAME FROM BOARD_IMG I
						WHERE I.BOARD_NO =B.BOARD_NO
						AND IMG_LEVEL= 0) THUMBNAIL
			FROM BOARD B
			JOIN MEMBER USING(MEMBER_NO)
			WHERE BOARD_CD=1
			AND BOARD_ST ='N'
			ORDER BY BOARD_NO DESC
		)A
	)
	WHERE RNUM BETWEEN 1 AND 10;
	
	
-- 썸네일 조회
	
SELECT IMG_RENAME FROM BOARD_IMG 
WHERE BOARD_NO =500
AND IMG_LEVEL= 0;



---

SELECT * FROM BOARD;


UPDATE BOARD SET BOARD_ST='Y'
WHERE BOARD_NO =1;

