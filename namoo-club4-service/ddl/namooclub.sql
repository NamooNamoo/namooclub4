-- 사용자
CREATE TABLE `USER` (
	`EMAIL`  VARCHAR(40) NOT NULL COMMENT '사용자ID(이메일)', -- 사용자ID(이메일)
	`NAME`   VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
	`PASSWD` VARCHAR(50) NOT NULL COMMENT '비밀번호' -- 비밀번호
)
COMMENT '사용자';

-- 사용자
ALTER TABLE `USER`
	ADD CONSTRAINT `PK_USER` -- 사용자 기본키
		PRIMARY KEY (
			`EMAIL` -- 사용자ID(이메일)
		);

-- 커뮤니티
CREATE TABLE `COMMUNITY` (
	`COMM_NO`     INTEGER     NOT NULL COMMENT '커뮤니티번호', -- 커뮤니티번호
	`NAME`        VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
	`DESCRIPTION` TEXT        NULL     COMMENT '설명', -- 설명
	`REG_DATE`    TIMESTAMP   NOT NULL DEFAULT NOW() COMMENT '생성일시' -- 생성일시
)
COMMENT '커뮤니티';

-- 커뮤니티
ALTER TABLE `COMMUNITY`
	ADD CONSTRAINT `PK_COMMUNITY` -- 커뮤니티 기본키
		PRIMARY KEY (
			`COMM_NO` -- 커뮤니티번호
		);

ALTER TABLE `COMMUNITY`
	MODIFY COLUMN `COMM_NO` INTEGER NOT NULL AUTO_INCREMENT COMMENT '커뮤니티번호';

-- 클럽
CREATE TABLE `CLUB` (
	`CLUB_NO`     INTEGER     NOT NULL COMMENT '클럽번호', -- 클럽번호
	`NAME`        VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
	`DESCRIPTION` TEXT        NULL     COMMENT '설명', -- 설명
	`REG_DATE`    TIMESTAMP   NOT NULL DEFAULT NOW() COMMENT '생성일시', -- 생성일시
	`COMM_NO`     INTEGER     NOT NULL COMMENT '커뮤니티번호', -- 커뮤니티번호
	`CATEGORY_NO` INTEGER     NULL     COMMENT '카테고리번호' -- 카테고리번호
)
COMMENT '클럽';

-- 클럽
ALTER TABLE `CLUB`
	ADD CONSTRAINT `PK_CLUB` -- 클럽 기본키
		PRIMARY KEY (
			`CLUB_NO` -- 클럽번호
		);

ALTER TABLE `CLUB`
	MODIFY COLUMN `CLUB_NO` INTEGER NOT NULL AUTO_INCREMENT COMMENT '클럽번호';

-- 모임회원
CREATE TABLE `MEMBER` (
	`USER_ID`    VARCHAR(40) NOT NULL COMMENT '사용자ID', -- 사용자ID
	`GROUP_TYPE` INTEGER     NOT NULL COMMENT '구분', -- 구분
	`GROUP_NO`   INTEGER     NOT NULL COMMENT '모임번호', -- 모임번호
	`LEVEL`      INTEGER     NOT NULL COMMENT '등급' -- 등급
)
COMMENT '모임회원';

-- 모임회원
ALTER TABLE `MEMBER`
	ADD CONSTRAINT `PK_MEMBER` -- 모임회원 기본키
		PRIMARY KEY (
			`USER_ID`,    -- 사용자ID
			`GROUP_TYPE`, -- 구분
			`GROUP_NO`    -- 모임번호
		);

-- 카테고리
CREATE TABLE `CATEGORY` (
	`CATEGORY`    VARCHAR(50) NOT NULL COMMENT '카테고리', -- 카테고리
	`CATEGORY_NO` INTEGER     NOT NULL COMMENT '카테고리번호', -- 카테고리번호
	`COMM_NO`     INTEGER     NOT NULL COMMENT '커뮤니티번호' -- 커뮤니티번호
)
COMMENT '카테고리';

-- 카테고리
ALTER TABLE `CATEGORY`
	ADD CONSTRAINT `PK_CATEGORY` -- 카테고리 기본키
		PRIMARY KEY (
			`CATEGORY_NO` -- 카테고리번호
		);

ALTER TABLE `CATEGORY`
	MODIFY COLUMN `CATEGORY_NO` INTEGER NOT NULL AUTO_INCREMENT COMMENT '카테고리번호';

-- 클럽
ALTER TABLE `CLUB`
	ADD CONSTRAINT `FK_COMMUNITY_TO_CLUB` -- 커뮤니티 -> 클럽
		FOREIGN KEY (
			`COMM_NO` -- 커뮤니티번호
		)
		REFERENCES `COMMUNITY` ( -- 커뮤니티
			`COMM_NO` -- 커뮤니티번호
		);

-- 클럽
ALTER TABLE `CLUB`
	ADD CONSTRAINT `FK_CATEGORY_TO_CLUB` -- 카테고리 -> 클럽
		FOREIGN KEY (
			`CATEGORY_NO` -- 카테고리번호
		)
		REFERENCES `CATEGORY` ( -- 카테고리
			`CATEGORY_NO` -- 카테고리번호
		);

-- 모임회원
ALTER TABLE `MEMBER`
	ADD CONSTRAINT `FK_USER_TO_MEMBER` -- 사용자 -> 모임회원
		FOREIGN KEY (
			`USER_ID` -- 사용자ID
		)
		REFERENCES `USER` ( -- 사용자
			`EMAIL` -- 사용자ID(이메일)
		);

-- 카테고리
ALTER TABLE `CATEGORY`
	ADD CONSTRAINT `FK_COMMUNITY_TO_CATEGORY` -- 커뮤니티 -> 카테고리
		FOREIGN KEY (
			`COMM_NO` -- 커뮤니티번호
		)
		REFERENCES `COMMUNITY` ( -- 커뮤니티
			`COMM_NO` -- 커뮤니티번호
		);