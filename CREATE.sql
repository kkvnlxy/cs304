CREATE TABLE Item(
	upc 	CHAR(4) not null PRIMARY KEY,
	title	VARCHAR(50) not null,
	type	VARCHAR(3) CHECK (type in ('CD', 'DVD')),
	category VARCHAR(12) CHECK (category in ('ROCK', 'POP', 'RAP', 'COUNTRY', 'CLASSICAL', 'NEW_AGE', 'INSTRUMENTAL')),
	company	VARCHAR(40) not null,
	year	CHAR(4) not null,
	price	NUMERIC(*, 2) not null,
	stock	SMALLINT not null,
	CHECK (price > 0.00 AND stock >=0));

CREATE TABLE LeadSinger(
	upc	CHAR(4) not null,
	name	VARCHAR(30) not null,
	PRIMARY KEY(upc, name),
	FOREIGN KEY (upc) REFERENCES Item
		ON DELETE CASCADE);

CREATE TABLE HasSong(
	upc	CHAR(4) not null,
	title	VARCHAR(80) not null,
	PRIMARY KEY (upc, title),
	FOREIGN KEY (upc) REFERENCES Item
		ON DELETE CASCADE);

CREATE TABLE Customer(
	cid		VARCHAR(16) not null PRIMARY KEY,
	pswd		VARCHAR(25) not null,
	name		VARCHAR(20) not null,
	address		VARCHAR(50) not null,
	phone		CHAR(12) not null);

CREATE TABLE Purchase(
	receiptId	CHAR(10) PRIMARY KEY,
	pDate		DATE not null,
	cid		VARCHAR(16),
	cardNum		CHAR(16),
	expiryDate	DATE,
	expectedDate	DATE,
	deliveredDate	DATE,
	FOREIGN KEY (cid) REFERENCES Customer
		ON DELETE SET NULL);

CREATE TABLE PurchaseItem(
	receiptId	CHAR(10) not null,
	upc		CHAR(4) not null,
	quantity	SMALLINT not null,
	PRIMARY KEY (receiptId, upc),
	FOREIGN KEY (receiptId) REFERENCES Purchase,
	FOREIGN KEY (upc) REFERENCES Item,
	CHECK(quantity > 0));

CREATE TABLE Refund(
	retid		CHAR(10) not null PRIMARY KEY,
	rDate		DATE not null,
	receiptId	CHAR(10) not null,
	FOREIGN KEY (receiptId) REFERENCES Purchase);

CREATE TABLE RefundItem(
	retid		CHAR(10) not null,
	upc		CHAR(4) not null,
	quantity	SMALLINT not null,
	PRIMARY KEY (retid, upc),
	FOREIGN KEY (retid) REFERENCES Refund,
	FOREIGN KEY (upc) REFERENCES Item,
	CHECK (quantity > 0));

-------------------------------------------------
--Trigger
-------------------------------------------------
<<<<<<< HEAD
CREATE SEQUENCE RECEIPT_ID_SQ
INCREMENT BY 1
START WITH 10
MAXVALUE 999999999
MINVALUE 1
CYCLE
NOCACHE
ORDER;

CREATE TRIGGER RECEIPT_ID_GEN
AFTER INSERT ON Purchase
FOR EACH STATEMENT 
if :NEW. "receiptId" is null then
SELECT concact ('P', to_char(RECEIPT_ID_SQ.nextval, '000000000'))
into :NEW. "receiptId"
from dual;
end if;
=======
CREATE SEQUENCE ReceiptID_SEQ
start with 1
MAXVALUE 9999999999;
CREATE or REPLACE TRIGGER ReceiptID_TG
before insert on Purchase
for each row
begin
	select to_char(ReceiptId_SEQ.nextval) into :new.receiptID from dual;
end;
/

CREATE SEQUENCE RetID_SEQ
start with 1
MAXVALUE 9999999999;
CREATE OR REPLACE TRIGGER RetID_TG
before insert on Refund
for each row
begin
	select to_char(RetID_SEQ.nextval) into :new.retID from dual;
>>>>>>> 71fb19bbaa32a269ad8dcb779bb4e5b2865c2413
end;
/
