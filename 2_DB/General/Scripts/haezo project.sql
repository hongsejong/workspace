BEGIN
    ----------------------------------------------------------------------
    -- (1) FK/PK 제약조건 DROP
    ----------------------------------------------------------------------
    FOR c IN (
        SELECT 'ALTER TABLE "' || table_name || '" DROP CONSTRAINT "' || constraint_name || '"' AS sqlstmt
          FROM (
            -- ▼ FK 목록
            SELECT 'REQUEST' AS table_name, 'FK_BOARD_TO_REQUEST_1' AS constraint_name FROM DUAL
            UNION ALL SELECT 'REQUEST', 'FK_CATEGORY_TO_REQUEST_1' FROM DUAL
            UNION ALL SELECT 'CHATTING_ROOM', 'FK_MEMBER_TO_CHATTING_ROOM_1' FROM DUAL
            UNION ALL SELECT 'CHATTING_ROOM', 'FK_MEMBER_TO_CHATTING_ROOM_2' FROM DUAL
            UNION ALL SELECT 'BOARD_LIKE', 'FK_BOARD_TO_BOARD_LIKE_1' FROM DUAL
            UNION ALL SELECT 'BOARD_LIKE', 'FK_MEMBER_TO_BOARD_LIKE_1' FROM DUAL
            UNION ALL SELECT 'REQUEST_SUPPORTER', 'FK_REQUEST_TO_REQUEST_SUPPORTER_1' FROM DUAL
            UNION ALL SELECT 'REQUEST_SUPPORTER', 'FK_MEMBER_TO_REQUEST_SUPPORTER_1' FROM DUAL
            UNION ALL SELECT 'REPORT', 'FK_MEMBER_TO_REPORT_1' FROM DUAL
            UNION ALL SELECT 'REPORT', 'FK_BOARD_TO_REPORT_1' FROM DUAL
            UNION ALL SELECT 'COMMENT', 'FK_BOARD_TO_COMMENT_1' FROM DUAL
            UNION ALL SELECT 'COMMENT', 'FK_MEMBER_TO_COMMENT_1' FROM DUAL
            UNION ALL SELECT 'FILE', 'FK_BOARD_TO_FILE_1' FROM DUAL
            UNION ALL SELECT 'BOARD', 'FK_MEMBER_TO_BOARD_1' FROM DUAL
            UNION ALL SELECT 'BOARD', 'FK_BOARD_TYPE_TO_BOARD_1' FROM DUAL
            UNION ALL SELECT 'REVIEW', 'FK_REQUEST_TO_REVIEW_1' FROM DUAL
            UNION ALL SELECT 'MESSAGE', 'FK_CHATTING_ROOM_TO_MESSAGE_1' FROM DUAL
            UNION ALL SELECT 'MESSAGE', 'FK_MEMBER_TO_MESSAGE_1' FROM DUAL
            UNION ALL SELECT 'USER_BAN', 'FK_MEMBER_TO_USER_BAN_1' FROM DUAL
            
            UNION ALL
            -- ▼ PK 목록
            SELECT 'BOARD_TYPE', 'PK_BOARD_TYPE' FROM DUAL
            UNION ALL SELECT 'MEMBER', 'PK_MEMBER' FROM DUAL
            UNION ALL SELECT 'CATEGORY', 'PK_CATEGORY' FROM DUAL
            UNION ALL SELECT 'CHATTING_ROOM', 'PK_CHATTING_ROOM' FROM DUAL
            UNION ALL SELECT 'BOARD', 'PK_BOARD' FROM DUAL
            UNION ALL SELECT 'REQUEST', 'PK_REQUEST' FROM DUAL
            UNION ALL SELECT 'BOARD_LIKE', 'PK_BOARD_LIKE' FROM DUAL
            UNION ALL SELECT 'REQUEST_SUPPORTER', 'PK_REQUEST_SUPPORTER' FROM DUAL
            UNION ALL SELECT 'REPORT', 'PK_REPORT' FROM DUAL
            UNION ALL SELECT 'COMMENT', 'PK_COMMENT' FROM DUAL
            UNION ALL SELECT 'FILE', 'PK_FILE' FROM DUAL
            UNION ALL SELECT 'REVIEW', 'PK_REVIEW' FROM DUAL
            UNION ALL SELECT 'MESSAGE', 'PK_MESSAGE' FROM DUAL
            UNION ALL SELECT 'USER_BAN', 'PK_USER_BAN' FROM DUAL
        )
    )
    LOOP
        BEGIN
            EXECUTE IMMEDIATE c.sqlstmt;
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
        END;
    END LOOP;

    ----------------------------------------------------------------------
    -- (2) 13개 테이블 DROP (CASCADE CONSTRAINTS)
    ----------------------------------------------------------------------
    FOR rec IN (
        SELECT 'DROP TABLE "' || table_name || '" CASCADE CONSTRAINTS' AS sqlstmt
          FROM (
            SELECT 'BOARD_TYPE' AS table_name FROM DUAL
            UNION ALL SELECT 'MEMBER' AS table_name FROM DUAL
            UNION ALL SELECT 'CATEGORY' AS table_name FROM DUAL
            UNION ALL SELECT 'CHATTING_ROOM' AS table_name FROM DUAL
            UNION ALL SELECT 'BOARD' AS table_name FROM DUAL
            UNION ALL SELECT 'REQUEST' AS table_name FROM DUAL
            UNION ALL SELECT 'BOARD_LIKE' AS table_name FROM DUAL
            UNION ALL SELECT 'REQUEST_SUPPORTER' AS table_name FROM DUAL
            UNION ALL SELECT 'REPORT' AS table_name FROM DUAL
            UNION ALL SELECT 'COMMENT' AS table_name FROM DUAL
            UNION ALL SELECT 'FILE' AS table_name FROM DUAL
            UNION ALL SELECT 'REVIEW' AS table_name FROM DUAL
            UNION ALL SELECT 'MESSAGE' AS table_name FROM DUAL
            UNION ALL SELECT 'USER_BAN' AS table_name FROM DUAL
        )
    )
    LOOP
        BEGIN
            EXECUTE IMMEDIATE rec.sqlstmt;
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
        END;
    END LOOP;

    ----------------------------------------------------------------------
    -- (3) 스키마 내 모든 시퀀스 DROP
    ----------------------------------------------------------------------
    FOR seq IN (SELECT sequence_name FROM user_sequences)
    LOOP
        EXECUTE IMMEDIATE 'DROP SEQUENCE ' || seq.sequence_name;
    END LOOP;
END;


---------------------------------------------------------------------------
-- 1. 상위 테이블 생성: BOARD_TYPE, MEMBER, CATEGORY
---------------------------------------------------------------------------

CREATE TABLE "BOARD_TYPE" (
	"BOARD_CODE"	NUMBER		NOT NULL,
	"BOARD_NAME"	VARCHAR2(30)		NOT NULL
);
COMMENT ON COLUMN "BOARD_TYPE"."BOARD_CODE" IS '게시판 코드';
COMMENT ON COLUMN "BOARD_TYPE"."BOARD_NAME" IS '게시판 이름';

-- BOARD_TYPE: PK = BOARD_CODE
CREATE SEQUENCE SEQ_BOARD_TYPE
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
CREATE OR REPLACE TRIGGER TRG_BOARD_TYPE
BEFORE INSERT ON "BOARD_TYPE"
FOR EACH ROW
BEGIN
  IF :NEW."BOARD_CODE" IS NULL THEN
    :NEW."BOARD_CODE" := SEQ_BOARD_TYPE.NEXTVAL;
  END IF;
END;

CREATE TABLE "MEMBER" (
	"MEMBER_NO"	NUMBER		NOT NULL,
	"MEMBER_EMAIL"	VARCHAR2(50)		NOT NULL,
	"MEMBER_PW"	VARCHAR2(100)		NOT NULL,
	"MEMBER_NICKNAME"	VARCHAR2(30)		NOT NULL,
	"MEMBER_TEL"	CHAR(11)		NOT NULL,
	"MEMBER_ADDR"	VARCHAR2(300)		NULL,
	"PROFILE_IMG"	VARCHAR2(300)		NULL,
	"ENROLL_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"MEMBER_SELFINTRO"	VARCHAR2(350)		NULL,
	"MEMBER_BAN"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"MEMBER_DER_REASON"	VARCHAR2(2000)		NULL,
	"MEMBER_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL
);
COMMENT ON COLUMN "MEMBER"."MEMBER_NO" IS '회원 번호';
COMMENT ON COLUMN "MEMBER"."MEMBER_EMAIL" IS '회원 이메일';
COMMENT ON COLUMN "MEMBER"."MEMBER_PW" IS '회원 비밀번호';
COMMENT ON COLUMN "MEMBER"."MEMBER_NICKNAME" IS '회원 닉네임';
COMMENT ON COLUMN "MEMBER"."MEMBER_TEL" IS '전화 번호';
COMMENT ON COLUMN "MEMBER"."MEMBER_ADDR" IS '회원 주소';
COMMENT ON COLUMN "MEMBER"."PROFILE_IMG" IS '프로필 이미지 저장 경로';
COMMENT ON COLUMN "MEMBER"."ENROLL_DATE" IS '회원 가입일';
COMMENT ON COLUMN "MEMBER"."MEMBER_SELFINTRO" IS '회원 자기소개';
COMMENT ON COLUMN "MEMBER"."MEMBER_BAN" IS '제재 여부(N: 제재X,  Y: 제재O)';
COMMENT ON COLUMN "MEMBER"."MEMBER_DER_REASON" IS '탈퇴 사유';
COMMENT ON COLUMN "MEMBER"."MEMBER_DEL_FL" IS '탈퇴 여부(N: 탈퇴X, Y: 탈퇴O)';

-- MEMBER: PK = MEMBER_NO
CREATE SEQUENCE SEQ_MEMBER_NO
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
CREATE OR REPLACE TRIGGER TRG_MEMBER_NO
BEFORE INSERT ON "MEMBER"
FOR EACH ROW
BEGIN
  IF :NEW."MEMBER_NO" IS NULL THEN
    :NEW."MEMBER_NO" := SEQ_MEMBER_NO.NEXTVAL;
  END IF;
END;

CREATE TABLE "CATEGORY" (
	"CATEGORY_ID"	NUMBER		NOT NULL,
	"CATEGORY_NAME"	VARCHAR2(50)		NOT NULL,
	"PARENT_NO"	NUMBER		NULL
);
COMMENT ON COLUMN "CATEGORY"."CATEGORY_ID" IS '카테고리 ID';
COMMENT ON COLUMN "CATEGORY"."CATEGORY_NAME" IS '카테고리 이름';
COMMENT ON COLUMN "CATEGORY"."PARENT_NO" IS '부모 카테고리 번호';

-- CATEGORY: PK = CATEGORY_ID
CREATE SEQUENCE SEQ_CATEGORY_ID
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
CREATE OR REPLACE TRIGGER TRG_CATEGORY_ID
BEFORE INSERT ON "CATEGORY"
FOR EACH ROW
BEGIN
  IF :NEW."CATEGORY_ID" IS NULL THEN
    :NEW."CATEGORY_ID" := SEQ_CATEGORY_ID.NEXTVAL;
  END IF;
END;

---------------------------------------------------------------------------
-- 2. 중위 테이블 생성: CHATTING_ROOM, BOARD
---------------------------------------------------------------------------

CREATE TABLE "CHATTING_ROOM" (
	"CHATTING_NO"	NUMBER		NOT NULL,
	"ROOM_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"CH_CREATE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"OPEN_MEMBER"	NUMBER		NOT NULL,
	"PARTICIPANT"	NUMBER		NOT NULL
);
COMMENT ON COLUMN "CHATTING_ROOM"."CHATTING_NO" IS '채팅방 번호';
COMMENT ON COLUMN "CHATTING_ROOM"."ROOM_FL" IS '채팅방 상태(N: 유지, Y: 삭제)';
COMMENT ON COLUMN "CHATTING_ROOM"."CH_CREATE_DATE" IS '채팅방 생성일';
COMMENT ON COLUMN "CHATTING_ROOM"."OPEN_MEMBER" IS '개설자 회원번호';
COMMENT ON COLUMN "CHATTING_ROOM"."PARTICIPANT" IS '참여자 회원번호';

-- CHATTING_ROOM: PK = CHATTING_NO
CREATE SEQUENCE SEQ_CHATTING_NO
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
CREATE OR REPLACE TRIGGER TRG_CHATTING_NO
BEFORE INSERT ON "CHATTING_ROOM"
FOR EACH ROW
BEGIN
  IF :NEW."CHATTING_NO" IS NULL THEN
    :NEW."CHATTING_NO" := SEQ_CHATTING_NO.NEXTVAL;
  END IF;
END;

CREATE TABLE "BOARD" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"BOARD_TITLE"	VARCHAR2(150)		NOT NULL,
	"BOARD_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"B_CREATE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"B_UPDATE_DATE"	DATE		NULL,
	"READ_COUNT"	NUMBER	DEFAULT 0	NOT NULL,
	"BOARD_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"BOARD_CODE"	NUMBER		NULL,
	"BOARD_STATUS"	VARCHAR2(30)		NULL
);
COMMENT ON COLUMN "BOARD"."BOARD_NO" IS '게시글 번호';
COMMENT ON COLUMN "BOARD"."BOARD_TITLE" IS '게시글 제목';
COMMENT ON COLUMN "BOARD"."BOARD_CONTENT" IS '게시글 내용';
COMMENT ON COLUMN "BOARD"."B_CREATE_DATE" IS '게시글 작성일';
COMMENT ON COLUMN "BOARD"."B_UPDATE_DATE" IS '마지막 수정일(수정 시 UPDATE)';
COMMENT ON COLUMN "BOARD"."READ_COUNT" IS '조회수';
COMMENT ON COLUMN "BOARD"."BOARD_DEL_FL" IS '삭제 여부(N : 삭제X , Y : 삭제O)';
COMMENT ON COLUMN "BOARD"."MEMBER_NO" IS '작성자 회원 번호';
COMMENT ON COLUMN "BOARD"."BOARD_CODE" IS '게시판 코드';
COMMENT ON COLUMN "BOARD"."BOARD_STATUS" IS '문의 답변여부';

-- BOARD: PK = BOARD_NO
CREATE SEQUENCE SEQ_BOARD_NO
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
CREATE OR REPLACE TRIGGER TRG_BOARD_NO
BEFORE INSERT ON "BOARD"
FOR EACH ROW
BEGIN
  IF :NEW."BOARD_NO" IS NULL THEN
    :NEW."BOARD_NO" := SEQ_BOARD_NO.NEXTVAL;
  END IF;
END;

---------------------------------------------------------------------------
-- 3. 하위 테이블 생성: REQUEST, BOARD_LIKE, REQUEST_SUPPORTER, REPORT,
--    COMMENT, FILE, REVIEW, MESSAGE, USER_BAN
---------------------------------------------------------------------------

CREATE TABLE "REQUEST" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"REQUEST_CONDITION"	VARCHAR2(4000) NOT NULL,
	"REQUEST_START_DATE"	DATE	DEFAULT SYSDATE NOT NULL,
	"REQUEST_DUEDATE"	DATE		NULL,
	"REQUEST_PRICE"	NUMBER		NOT NULL,
	"REQUEST_STATUS"	VARCHAR2(10)		NOT NULL,
	"REQUEST_LOCATION"	VARCHAR2(150)		NOT NULL,
	"REQUEST_ETC"	VARCHAR2(4000)		NULL,
	"CATEGORY_ID"	NUMBER		NOT NULL
);
COMMENT ON COLUMN "REQUEST"."BOARD_NO" IS '게시글 번호';
COMMENT ON COLUMN "REQUEST"."REQUEST_CONDITION" IS '의뢰 조건';
COMMENT ON COLUMN "REQUEST"."REQUEST_START_DATE" IS '의뢰 시작일';
COMMENT ON COLUMN "REQUEST"."REQUEST_DUEDATE" IS '의뢰 종료일';
COMMENT ON COLUMN "REQUEST"."REQUEST_PRICE" IS '의뢰 가격';
COMMENT ON COLUMN "REQUEST"."REQUEST_STATUS" IS '의뢰 진행상태';
COMMENT ON COLUMN "REQUEST"."REQUEST_LOCATION" IS '의뢰 요청 위치';
COMMENT ON COLUMN "REQUEST"."REQUEST_ETC" IS '기타 요청사항';
COMMENT ON COLUMN "REQUEST"."CATEGORY_ID" IS '카테고리 ID';
-- REQUEST는 BOARD와 1:1 매핑 (PK로 BOARD_NO 사용)
-- 별도의 시퀀스/트리거는 BOARD 테이블에서 생성된 BOARD_NO를 사용

CREATE TABLE "BOARD_LIKE" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL
);
COMMENT ON COLUMN "BOARD_LIKE"."BOARD_NO" IS '게시글 번호';
COMMENT ON COLUMN "BOARD_LIKE"."MEMBER_NO" IS '회원 번호';
-- 복합키로 정의되어 시퀀스 불필요

CREATE TABLE "REQUEST_SUPPORTER" (
	"SUPPORTER_NO"	NUMBER		NOT NULL,
	"REQUEST_CONFIRM"	CHAR(1) DEFAULT 'N' NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"BOARD_NO"	NUMBER		NOT NULL
);
COMMENT ON COLUMN "REQUEST_SUPPORTER"."SUPPORTER_NO" IS '조력자 번호';
COMMENT ON COLUMN "REQUEST_SUPPORTER"."REQUEST_CONFIRM" IS '의뢰 수락 여부(Y: 수락, N: 미수락)';
COMMENT ON COLUMN "REQUEST_SUPPORTER"."MEMBER_NO" IS '회원 번호';
COMMENT ON COLUMN "REQUEST_SUPPORTER"."BOARD_NO" IS '게시글 번호';
-- REQUEST_SUPPORTER: PK = SUPPORTER_NO
CREATE SEQUENCE SEQ_REQUEST_SUPPORTER
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
CREATE OR REPLACE TRIGGER TRG_REQUEST_SUPPORTER
BEFORE INSERT ON "REQUEST_SUPPORTER"
FOR EACH ROW
BEGIN
  IF :NEW."SUPPORTER_NO" IS NULL THEN
    :NEW."SUPPORTER_NO" := SEQ_REQUEST_SUPPORTER.NEXTVAL;
  END IF;
END;

CREATE TABLE "REPORT" (
	"REPORT_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"BOARD_NO"	NUMBER		NOT NULL,
	"REPORT_CONTENT"	VARCHAR2(4000) NOT NULL,
	"REPORT_TITLE"	VARCHAR2(150) NOT NULL,
	"REPORT_TYPE"	NUMBER		NOT NULL,
	"REPORT_RESULT"	CHAR(1) DEFAULT 'A' NOT NULL
);
COMMENT ON COLUMN "REPORT"."REPORT_NO" IS '신고 번호';
COMMENT ON COLUMN "REPORT"."MEMBER_NO" IS '신고자 회원번호';
COMMENT ON COLUMN "REPORT"."BOARD_NO" IS '신고한 게시글 번호';
COMMENT ON COLUMN "REPORT"."REPORT_CONTENT" IS '신고 내용';
COMMENT ON COLUMN "REPORT"."REPORT_TITLE" IS '신고 제목';
COMMENT ON COLUMN "REPORT"."REPORT_TYPE" IS '신고 유형(1~5)';
COMMENT ON COLUMN "REPORT"."REPORT_RESULT" IS '처리 결과(A:처리 전,  B:무효 , C:글 삭제)';
-- REPORT: PK = (REPORT_NO, MEMBER_NO, BOARD_NO)
-- 시퀀스는 REPORT_NO에 대해 생성
CREATE SEQUENCE SEQ_REPORT_NO
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
CREATE OR REPLACE TRIGGER TRG_REPORT_NO
BEFORE INSERT ON "REPORT"
FOR EACH ROW
BEGIN
  IF :NEW."REPORT_NO" IS NULL THEN
    :NEW."REPORT_NO" := SEQ_REPORT_NO.NEXTVAL;
  END IF;
END;

CREATE TABLE "COMMENT" (
	"COMMENT_NO"	NUMBER		NOT NULL,
	"COMMENT_CONTENT"	VARCHAR2(4000) NOT NULL,
	"C_CREATE_DATE"	DATE DEFAULT SYSDATE NOT NULL,
	"COMMENT_DEL_FL"	CHAR DEFAULT 'N' NOT NULL,
	"PARENT_NO"	NUMBER		NULL,
	"BOARD_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL
);
COMMENT ON COLUMN "COMMENT"."COMMENT_NO" IS '댓글 번호';
COMMENT ON COLUMN "COMMENT"."COMMENT_CONTENT" IS '댓글 내용';
COMMENT ON COLUMN "COMMENT"."C_CREATE_DATE" IS '댓글 작성일';
COMMENT ON COLUMN "COMMENT"."COMMENT_DEL_FL" IS '댓글 삭제 여부(N:삭제X, Y:삭제O)';
COMMENT ON COLUMN "COMMENT"."PARENT_NO" IS '부모 댓글 번호';
COMMENT ON COLUMN "COMMENT"."BOARD_NO" IS '댓글이 작성된 게시글 번호';
COMMENT ON COLUMN "COMMENT"."MEMBER_NO" IS '댓글 작성자 회원 번호';
-- COMMENT: PK = COMMENT_NO
CREATE SEQUENCE SEQ_COMMENT_NO
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
CREATE OR REPLACE TRIGGER TRG_COMMENT_NO
BEFORE INSERT ON "COMMENT"
FOR EACH ROW
BEGIN
  IF :NEW."COMMENT_NO" IS NULL THEN
    :NEW."COMMENT_NO" := SEQ_COMMENT_NO.NEXTVAL;
  END IF;
END;

CREATE TABLE "FILE" (
	"FILE_NO"	NUMBER		NOT NULL,
	"FILE_PATH"	VARCHAR2(300) NOT NULL,
	"FILE_RENAME"	VARCHAR2(30) NOT NULL,
	"FILE_ORIGINAL"	VARCHAR2(300) NOT NULL,
	"FILE_ORDER"	NUMBER		NOT NULL,
	"BOARD_NO"	NUMBER		NULL
);
COMMENT ON COLUMN "FILE"."FILE_NO" IS '파일 번호';
COMMENT ON COLUMN "FILE"."FILE_PATH" IS '파일 저장 폴더 경로';
COMMENT ON COLUMN "FILE"."FILE_RENAME" IS '변경된 파일 이름';
COMMENT ON COLUMN "FILE"."FILE_ORIGINAL" IS '원본 파일 이름';
COMMENT ON COLUMN "FILE"."FILE_ORDER" IS '파일 순서 번호';
COMMENT ON COLUMN "FILE"."BOARD_NO" IS '파일이 첨부된 게시글 번호';
-- FILE: PK = FILE_NO
CREATE SEQUENCE SEQ_FILE_NO
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
CREATE OR REPLACE TRIGGER TRG_FILE_NO
BEFORE INSERT ON "FILE"
FOR EACH ROW
BEGIN
  IF :NEW."FILE_NO" IS NULL THEN
    :NEW."FILE_NO" := SEQ_FILE_NO.NEXTVAL;
  END IF;
END;

CREATE TABLE "REVIEW" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"REVIEW_CONTENT"	VARCHAR2(4000) NOT NULL,
	"REVIEW_RATING"	NUMBER		NOT NULL
);
COMMENT ON COLUMN "REVIEW"."BOARD_NO" IS '게시글 번호';
COMMENT ON COLUMN "REVIEW"."REVIEW_CONTENT" IS '리뷰 내용';
COMMENT ON COLUMN "REVIEW"."REVIEW_RATING" IS '별점';
-- REVIEW는 BOARD_NO를 기본키로 공유하므로 별도의 시퀀스/트리거는 필요 없음

CREATE TABLE "MESSAGE" (
	"MESSAGE_NO"	NUMBER		NOT NULL,
	"MESSAGE_CONTENT"	VARCHAR2(4000) NOT NULL,
	"READ_FL"	CHAR DEFAULT 'N' NOT NULL,
	"SEND_TIME"	DATE DEFAULT SYSDATE NOT NULL,
	"CHATTING_NO"	NUMBER		NOT NULL,
	"SENDER_NO"	NUMBER		NOT NULL
);
COMMENT ON COLUMN "MESSAGE"."MESSAGE_NO" IS '메세지 번호';
COMMENT ON COLUMN "MESSAGE"."MESSAGE_CONTENT" IS '메세지 내용';
COMMENT ON COLUMN "MESSAGE"."READ_FL" IS '읽음 여부(N: 안 읽음, Y: 읽음)';
COMMENT ON COLUMN "MESSAGE"."SEND_TIME" IS '메세지 보낸 시간';
COMMENT ON COLUMN "MESSAGE"."CHATTING_NO" IS '채팅방 번호';
COMMENT ON COLUMN "MESSAGE"."SENDER_NO" IS '보낸 회원의 번호';
-- MESSAGE: PK = MESSAGE_NO
CREATE SEQUENCE SEQ_MESSAGE_NO
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
CREATE OR REPLACE TRIGGER TRG_MESSAGE_NO
BEFORE INSERT ON "MESSAGE"
FOR EACH ROW
BEGIN
  IF :NEW."MESSAGE_NO" IS NULL THEN
    :NEW."MESSAGE_NO" := SEQ_MESSAGE_NO.NEXTVAL;
  END IF;
END;

CREATE TABLE "USER_BAN" (
	"BAN_START"	DATE		NOT NULL,
	"BAN_END"	DATE		NOT NULL,
	"REPORT_COUNT"	NUMBER DEFAULT 0 NOT NULL,
	"BAN_COUNT"	NUMBER DEFAULT 0 NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL
);
COMMENT ON COLUMN "USER_BAN"."BAN_START" IS '정지 시작일';
COMMENT ON COLUMN "USER_BAN"."BAN_END" IS '정지종료일';
COMMENT ON COLUMN "USER_BAN"."REPORT_COUNT" IS '신고당한횟수';
COMMENT ON COLUMN "USER_BAN"."BAN_COUNT" IS '정지당한횟수';
COMMENT ON COLUMN "USER_BAN"."MEMBER_NO" IS '회원 번호';
-- USER_BAN: PK 미정의 (필요 시 추가 가능)

---------------------------------------------------------------------------
-- 4. 제약조건 추가 (상위 → 중위 → 하위 순)
---------------------------------------------------------------------------

-- 상위 테이블 PK
ALTER TABLE "BOARD_TYPE" ADD CONSTRAINT "PK_BOARD_TYPE" PRIMARY KEY ("BOARD_CODE");
ALTER TABLE "MEMBER" ADD CONSTRAINT "PK_MEMBER" PRIMARY KEY ("MEMBER_NO");
ALTER TABLE "CATEGORY" ADD CONSTRAINT "PK_CATEGORY" PRIMARY KEY ("CATEGORY_ID");

-- 중위 테이블 PK
ALTER TABLE "CHATTING_ROOM" ADD CONSTRAINT "PK_CHATTING_ROOM" PRIMARY KEY ("CHATTING_NO");
ALTER TABLE "BOARD" ADD CONSTRAINT "PK_BOARD" PRIMARY KEY ("BOARD_NO");

-- 하위 테이블 PK
ALTER TABLE "REQUEST" ADD CONSTRAINT "PK_REQUEST" PRIMARY KEY ("BOARD_NO");
ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "PK_BOARD_LIKE" PRIMARY KEY ("BOARD_NO", "MEMBER_NO");
ALTER TABLE "REQUEST_SUPPORTER" ADD CONSTRAINT "PK_REQUEST_SUPPORTER" PRIMARY KEY ("SUPPORTER_NO");
ALTER TABLE "REPORT" ADD CONSTRAINT "PK_REPORT" PRIMARY KEY ("REPORT_NO", "MEMBER_NO", "BOARD_NO");
ALTER TABLE "COMMENT" ADD CONSTRAINT "PK_COMMENT" PRIMARY KEY ("COMMENT_NO");
ALTER TABLE "FILE" ADD CONSTRAINT "PK_FILE" PRIMARY KEY ("FILE_NO");
ALTER TABLE "REVIEW" ADD CONSTRAINT "PK_REVIEW" PRIMARY KEY ("BOARD_NO");
ALTER TABLE "MESSAGE" ADD CONSTRAINT "PK_MESSAGE" PRIMARY KEY ("MESSAGE_NO");
-- USER_BAN PK: 필요 시 추가

-- 상위 테이블 FK
ALTER TABLE "REQUEST" ADD CONSTRAINT "FK_BOARD_TO_REQUEST_1" FOREIGN KEY ("BOARD_NO")
    REFERENCES "BOARD" ("BOARD_NO");
ALTER TABLE "REQUEST" ADD CONSTRAINT "FK_CATEGORY_TO_REQUEST_1" FOREIGN KEY ("CATEGORY_ID")
    REFERENCES "CATEGORY" ("CATEGORY_ID");

-- 중위 테이블 FK
ALTER TABLE "CHATTING_ROOM" ADD CONSTRAINT "FK_MEMBER_TO_CHATTING_ROOM_1" FOREIGN KEY ("OPEN_MEMBER")
    REFERENCES "MEMBER" ("MEMBER_NO");
ALTER TABLE "CHATTING_ROOM" ADD CONSTRAINT "FK_MEMBER_TO_CHATTING_ROOM_2" FOREIGN KEY ("PARTICIPANT")
    REFERENCES "MEMBER" ("MEMBER_NO");
ALTER TABLE "BOARD" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_1" FOREIGN KEY ("MEMBER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");
ALTER TABLE "BOARD" ADD CONSTRAINT "FK_BOARD_TYPE_TO_BOARD_1" FOREIGN KEY ("BOARD_CODE")
    REFERENCES "BOARD_TYPE" ("BOARD_CODE");

-- 하위 테이블 FK
ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "FK_BOARD_TO_BOARD_LIKE_1" FOREIGN KEY ("BOARD_NO")
    REFERENCES "BOARD" ("BOARD_NO");
ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_LIKE_1" FOREIGN KEY ("MEMBER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");
ALTER TABLE "REQUEST_SUPPORTER" ADD CONSTRAINT "FK_MEMBER_TO_REQUEST_SUPPORTER_1" FOREIGN KEY ("MEMBER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");
ALTER TABLE "REQUEST_SUPPORTER" ADD CONSTRAINT "FK_REQUEST_TO_REQUEST_SUPPORTER_1" FOREIGN KEY ("BOARD_NO")
    REFERENCES "REQUEST" ("BOARD_NO");
ALTER TABLE "REPORT" ADD CONSTRAINT "FK_MEMBER_TO_REPORT_1" FOREIGN KEY ("MEMBER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");
ALTER TABLE "REPORT" ADD CONSTRAINT "FK_BOARD_TO_REPORT_1" FOREIGN KEY ("BOARD_NO")
    REFERENCES "BOARD" ("BOARD_NO");
ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_BOARD_TO_COMMENT_1" FOREIGN KEY ("BOARD_NO")
    REFERENCES "BOARD" ("BOARD_NO");
ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_MEMBER_TO_COMMENT_1" FOREIGN KEY ("MEMBER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");
ALTER TABLE "FILE" ADD CONSTRAINT "FK_BOARD_TO_FILE_1" FOREIGN KEY ("BOARD_NO")
    REFERENCES "BOARD" ("BOARD_NO");
ALTER TABLE "REVIEW" ADD CONSTRAINT "FK_REQUEST_TO_REVIEW_1" FOREIGN KEY ("BOARD_NO")
    REFERENCES "REQUEST" ("BOARD_NO");
ALTER TABLE "MESSAGE" ADD CONSTRAINT "FK_CHATTING_ROOM_TO_MESSAGE_1" FOREIGN KEY ("CHATTING_NO")
    REFERENCES "CHATTING_ROOM" ("CHATTING_NO");
ALTER TABLE "MESSAGE" ADD CONSTRAINT "FK_MEMBER_TO_MESSAGE_1" FOREIGN KEY ("SENDER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");
ALTER TABLE "USER_BAN" ADD CONSTRAINT "FK_MEMBER_TO_USER_BAN_1" FOREIGN KEY ("MEMBER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");

COMMIT;
---------------------------------------------------------------------------

-- 5. 더미 데이터 삽입
---------------------------------------------------------------------------

-- 회원 정보 50명
BEGIN
    FOR i IN 1..50 LOOP
        INSERT INTO "MEMBER" (
            "MEMBER_EMAIL",
            "MEMBER_PW",
            "MEMBER_NICKNAME",
            "MEMBER_TEL",
            "MEMBER_ADDR"
        ) VALUES (
            'user' || i || '@kh.or.kr',
            'pass01!',
            '유저' || i,
            '010' || LPAD(i, 8, '0'),
            '서울 어디동 ' || i || '번지'
        );
    END LOOP;
    COMMIT;
END;

-- 게시판 타입 7개
INSERT INTO "BOARD_TYPE"("BOARD_NAME") VALUES('전체게시판');
INSERT INTO "BOARD_TYPE"("BOARD_NAME") VALUES('공지사항');
INSERT INTO "BOARD_TYPE"("BOARD_NAME") VALUES('자유게시판');
INSERT INTO "BOARD_TYPE"("BOARD_NAME") VALUES('댓글게시판');
INSERT INTO "BOARD_TYPE"("BOARD_NAME") VALUES('의뢰게시판');
INSERT INTO "BOARD_TYPE"("BOARD_NAME") VALUES('문의게시판');
INSERT INTO "BOARD_TYPE"("BOARD_NAME") VALUES('신고게시판');
COMMIT;

-- 카테고리 (상위 5개 + 하위 15개, 총 20개)
INSERT ALL
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(1, '취업/직무/입시', NULL)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(2, '영상/사진/음향', NULL)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(3, '설치/수리', NULL)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(4, '취미/자기계발', NULL)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(5, '단순의뢰', NULL)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(6, '면접 컨설팅', 1)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(7, '취업 컨설팅', 1)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(8, '포트폴리오 컨설팅', 1)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(9, '입시 컨설팅', 1)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(10, '영상 외주', 2)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(11, '사진 외주', 2)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(12, '음향 외주', 2)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(13, '가전제품 설치', 3)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(14, '문/창문 설치', 3)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(15, '수도/보일러/전기 설치', 3)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(16, '악기', 4)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(17, '스포츠', 4)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(18, '미술', 4)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(19, '그 외', 4)
    INTO "CATEGORY" ("CATEGORY_ID", "CATEGORY_NAME", "PARENT_NO") VALUES(20, '단순 의뢰', 5)
SELECT 1 FROM DUAL;
COMMIT;

-- 더미 게시글 (공지사항, 자유게시판, 문의게시판, 신고게시판) 각 100건
BEGIN
    FOR i IN 1..100 LOOP
        INSERT INTO "BOARD"(
            "BOARD_TITLE",
            "BOARD_CONTENT",
            "BOARD_CODE",
            "MEMBER_NO",
            "B_CREATE_DATE"
        ) VALUES(
            '공지사항 제목 ' || i,
            '공지사항 내용 ' || i,
            2,
            TRUNC(DBMS_RANDOM.VALUE(1,51)),
            SYSDATE - (((100 - i)/100.0)*30)
        );
    END LOOP;
    
    FOR i IN 1..100 LOOP
        INSERT INTO "BOARD"(
            "BOARD_TITLE",
            "BOARD_CONTENT",
            "BOARD_CODE",
            "MEMBER_NO",
            "B_CREATE_DATE"
        ) VALUES(
            '자유게시판 제목 ' || i,
            '자유게시판 내용 ' || i,
            3,
            TRUNC(DBMS_RANDOM.VALUE(1,51)),
            SYSDATE - (((100 - i)/100.0)*30)
        );
    END LOOP;
    
    FOR i IN 1..100 LOOP
        INSERT INTO "BOARD"(
            "BOARD_TITLE",
            "BOARD_CONTENT",
            "BOARD_CODE",
            "MEMBER_NO",
            "B_CREATE_DATE",
            "BOARD_STATUS"
        ) VALUES(
            '문의게시판 제목 ' || i,
            '문의게시판 내용 ' || i,
            6,
            TRUNC(DBMS_RANDOM.VALUE(1,51)),
            SYSDATE - (((100 - i)/100.0)*30),
            '답변 전'
        );
    END LOOP;
    
    FOR i IN 1..100 LOOP
        INSERT INTO "BOARD"(
            "BOARD_TITLE",
            "BOARD_CONTENT",
            "BOARD_CODE",
            "MEMBER_NO",
            "B_CREATE_DATE"
        ) VALUES(
            '신고게시판 제목 ' || i,
            '신고게시판 내용 ' || i,
            7,
            TRUNC(DBMS_RANDOM.VALUE(1,51)),
            SYSDATE - (((100 - i)/100.0)*30)
        );
    END LOOP;
    COMMIT;
END;

---------------------------------------------------------------------------
-- 의뢰 게시글 더미 데이터 (BOARD와 REQUEST 1:1 매핑, 의뢰게시판 BOARD_CODE=5)
---------------------------------------------------------------------------
DECLARE
    v_board_no NUMBER;
BEGIN
    FOR i IN 1..100 LOOP
        INSERT INTO "BOARD" (
            "BOARD_TITLE",
            "BOARD_CONTENT",
            "BOARD_CODE",
            "MEMBER_NO",
            "B_CREATE_DATE"
        ) VALUES (
            '의뢰게시판 제목 ' || i,
            '의뢰게시판 내용 ' || i,
            5,
            TRUNC(DBMS_RANDOM.VALUE(1,51)),
            SYSDATE
        )
        RETURNING "BOARD_NO" INTO v_board_no;
        
        INSERT INTO "REQUEST" (
            "BOARD_NO",
            "REQUEST_CONDITION",
            "REQUEST_START_DATE",
            "REQUEST_DUEDATE",
            "REQUEST_PRICE",
            "REQUEST_STATUS",
            "REQUEST_LOCATION",
            "REQUEST_ETC",
            "CATEGORY_ID"
        ) VALUES (
            v_board_no,
            '의뢰 조건 ' || i,
            SYSDATE,
            SYSDATE + TRUNC(DBMS_RANDOM.VALUE(1,10)),
            CASE WHEN MOD(i,5) = 0 THEN '50000' ELSE '30000' END,
            '대기',
            '서울 어딘가 ' || i,
            '기타 요청사항 ' || i,
            TRUNC(DBMS_RANDOM.VALUE(6,21))
        );
    END LOOP;
    COMMIT;
END;


---------------------------------------------------------------------------
-- BOARD_LIKE 테이블 더미 데이터 (500건, 중복 무시)
---------------------------------------------------------------------------
BEGIN
    FOR i IN 1..500 LOOP
        BEGIN
            INSERT INTO "BOARD_LIKE"("MEMBER_NO", "BOARD_NO")
            VALUES (
                TRUNC(DBMS_RANDOM.VALUE(1,51)),
                TRUNC(DBMS_RANDOM.VALUE(1,401))
            );
        EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
                NULL;
        END;
    END LOOP;
    COMMIT;
END;

--
SELECT * FROM REPORT ;
SELECT * FROM BOARD
WHERE BOARD_CODE =6;

UPDATE "BOARD" SET BOARD_STATUS ='N'
WHERE BOARD_CODE =6;


DECLARE
    v_report_board_no NUMBER;
BEGIN
    FOR i IN 1..50 LOOP
        -- 자유게시판(BOARD_CODE = 3)와 의뢰 게시글(REQUEST에 존재하는 BOARD_NO)의 합집합에서 무작위로 하나 선택
        SELECT board_no
        INTO v_report_board_no
        FROM (
            SELECT board_no 
            FROM (
                SELECT board_no FROM board WHERE board_code = 3
                UNION
                SELECT board_no FROM request
            )
            ORDER BY DBMS_RANDOM.VALUE
        )
        WHERE ROWNUM = 1;
        
        INSERT INTO "REPORT" (
            "REPORT_NO",
            "MEMBER_NO",
            "BOARD_NO",
            "REPORT_CONTENT",
            "REPORT_TITLE",
            "REPORT_TYPE",
            "REPORT_RESULT"
        ) VALUES (
            NULL,  -- 트리거/시퀀스를 통해 자동 증가
            TRUNC(DBMS_RANDOM.VALUE(1,51)),  -- 1~50 사이의 임의 회원 번호
            v_report_board_no,
            '신고 내용 ' || i,
            '신고 제목 ' || i,
            TRUNC(DBMS_RANDOM.VALUE(1,6)),   -- 신고 유형 1~5 중 하나
            CASE TRUNC(DBMS_RANDOM.VALUE(1,4))
                WHEN 1 THEN 'A'  -- 처리 전
                WHEN 2 THEN 'B'  -- 무효
                WHEN 3 THEN 'C'  -- 글 삭제
            END
        );
    END LOOP;
    COMMIT;
END;
