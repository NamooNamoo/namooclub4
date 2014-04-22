-- �����
CREATE TABLE `USER` (
	`EMAIL`  VARCHAR(40) NOT NULL COMMENT '�����ID(�̸���)', -- �����ID(�̸���)
	`NAME`   VARCHAR(50) NOT NULL COMMENT '�̸�', -- �̸�
	`PASSWD` VARCHAR(50) NOT NULL COMMENT '��й�ȣ' -- ��й�ȣ
)
COMMENT '�����';

-- �����
ALTER TABLE `USER`
	ADD CONSTRAINT `PK_USER` -- ����� �⺻Ű
		PRIMARY KEY (
			`EMAIL` -- �����ID(�̸���)
		);

-- Ŀ�´�Ƽ
CREATE TABLE `COMMUNITY` (
	`COMM_NO`     INTEGER     NOT NULL COMMENT 'Ŀ�´�Ƽ��ȣ', -- Ŀ�´�Ƽ��ȣ
	`NAME`        VARCHAR(50) NOT NULL COMMENT '�̸�', -- �̸�
	`DESCRIPTION` TEXT        NULL     COMMENT '����', -- ����
	`REG_DATE`    TIMESTAMP   NOT NULL DEFAULT NOW() COMMENT '�����Ͻ�' -- �����Ͻ�
)
COMMENT 'Ŀ�´�Ƽ';

-- Ŀ�´�Ƽ
ALTER TABLE `COMMUNITY`
	ADD CONSTRAINT `PK_COMMUNITY` -- Ŀ�´�Ƽ �⺻Ű
		PRIMARY KEY (
			`COMM_NO` -- Ŀ�´�Ƽ��ȣ
		);

ALTER TABLE `COMMUNITY`
	MODIFY COLUMN `COMM_NO` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'Ŀ�´�Ƽ��ȣ';

-- Ŭ��
CREATE TABLE `CLUB` (
	`CLUB_NO`     INTEGER     NOT NULL COMMENT 'Ŭ����ȣ', -- Ŭ����ȣ
	`NAME`        VARCHAR(50) NOT NULL COMMENT '�̸�', -- �̸�
	`DESCRIPTION` TEXT        NULL     COMMENT '����', -- ����
	`REG_DATE`    TIMESTAMP   NOT NULL DEFAULT NOW() COMMENT '�����Ͻ�', -- �����Ͻ�
	`COMM_NO`     INTEGER     NOT NULL COMMENT 'Ŀ�´�Ƽ��ȣ', -- Ŀ�´�Ƽ��ȣ
	`CATEGORY_NO` INTEGER     NULL     COMMENT 'ī�װ���ȣ' -- ī�װ���ȣ
)
COMMENT 'Ŭ��';

-- Ŭ��
ALTER TABLE `CLUB`
	ADD CONSTRAINT `PK_CLUB` -- Ŭ�� �⺻Ű
		PRIMARY KEY (
			`CLUB_NO` -- Ŭ����ȣ
		);

ALTER TABLE `CLUB`
	MODIFY COLUMN `CLUB_NO` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'Ŭ����ȣ';

-- ����ȸ��
CREATE TABLE `MEMBER` (
	`USER_ID`    VARCHAR(40) NOT NULL COMMENT '�����ID', -- �����ID
	`GROUP_TYPE` INTEGER     NOT NULL COMMENT '����', -- ����
	`GROUP_NO`   INTEGER     NOT NULL COMMENT '���ӹ�ȣ', -- ���ӹ�ȣ
	`LEVEL`      INTEGER     NOT NULL COMMENT '���' -- ���
)
COMMENT '����ȸ��';

-- ����ȸ��
ALTER TABLE `MEMBER`
	ADD CONSTRAINT `PK_MEMBER` -- ����ȸ�� �⺻Ű
		PRIMARY KEY (
			`USER_ID`,    -- �����ID
			`GROUP_TYPE`, -- ����
			`GROUP_NO`    -- ���ӹ�ȣ
		);

-- ī�װ�
CREATE TABLE `CATEGORY` (
	`CATEGORY`    VARCHAR(50) NOT NULL COMMENT 'ī�װ�', -- ī�װ�
	`CATEGORY_NO` INTEGER     NOT NULL COMMENT 'ī�װ���ȣ', -- ī�װ���ȣ
	`COMM_NO`     INTEGER     NOT NULL COMMENT 'Ŀ�´�Ƽ��ȣ' -- Ŀ�´�Ƽ��ȣ
)
COMMENT 'ī�װ�';

-- ī�װ�
ALTER TABLE `CATEGORY`
	ADD CONSTRAINT `PK_CATEGORY` -- ī�װ� �⺻Ű
		PRIMARY KEY (
			`CATEGORY_NO` -- ī�װ���ȣ
		);

ALTER TABLE `CATEGORY`
	MODIFY COLUMN `CATEGORY_NO` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'ī�װ���ȣ';

-- Ŭ��
ALTER TABLE `CLUB`
	ADD CONSTRAINT `FK_COMMUNITY_TO_CLUB` -- Ŀ�´�Ƽ -> Ŭ��
		FOREIGN KEY (
			`COMM_NO` -- Ŀ�´�Ƽ��ȣ
		)
		REFERENCES `COMMUNITY` ( -- Ŀ�´�Ƽ
			`COMM_NO` -- Ŀ�´�Ƽ��ȣ
		);

-- Ŭ��
ALTER TABLE `CLUB`
	ADD CONSTRAINT `FK_CATEGORY_TO_CLUB` -- ī�װ� -> Ŭ��
		FOREIGN KEY (
			`CATEGORY_NO` -- ī�װ���ȣ
		)
		REFERENCES `CATEGORY` ( -- ī�װ�
			`CATEGORY_NO` -- ī�װ���ȣ
		);

-- ����ȸ��
ALTER TABLE `MEMBER`
	ADD CONSTRAINT `FK_USER_TO_MEMBER` -- ����� -> ����ȸ��
		FOREIGN KEY (
			`USER_ID` -- �����ID
		)
		REFERENCES `USER` ( -- �����
			`EMAIL` -- �����ID(�̸���)
		);

-- ī�װ�
ALTER TABLE `CATEGORY`
	ADD CONSTRAINT `FK_COMMUNITY_TO_CATEGORY` -- Ŀ�´�Ƽ -> ī�װ�
		FOREIGN KEY (
			`COMM_NO` -- Ŀ�´�Ƽ��ȣ
		)
		REFERENCES `COMMUNITY` ( -- Ŀ�´�Ƽ
			`COMM_NO` -- Ŀ�´�Ƽ��ȣ
		);