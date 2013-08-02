CREATE TABLE Item(
	upc 	CHAR(4) not null PRIMARY KEY,
	title	VARCHAR(50) not null,
	type	VARCHAR(3) CHECK (type in ('CD', 'DVD')),
	category VARCHAR(12) CHECK (category in ('ROCK', 'POP', 'RAP', 'COUNTRY', 'CLASSICAL', 'NEW_AGE', 'INSTRUMENTAL')),
	company	VARCHAR(25) not null,
	year	DATE not null,
	price	FLOAT(2) not null,
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
	title	VARCHAR(30) not null,
	PRIMARY KEY (upc, title),
	FOREIGN KEY (upc) REFERENCES Item
		ON DELETE CASCADE);

CREATE TABLE Customer(
	cid		CHAR(16) not null PRIMARY KEY,
	pswd		VARCHAR(25) not null,
	name		VARCHAR(20) not null,
	address		VARCHAR(50) not null,
	phone		CHAR(20) not null);

CREATE TABLE Purchase(
	receiptId	CHAR(10) not null PRIMARY KEY,
	pDate		DATE not null,
	cid		CHAR(16),
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
		ON DELETE NO ACTION 
	FOREIGN KEY (upc) REFERENCES Item
		ON DELETE NO ACTION
	CHECK(quantity > 0));

CREATE TABLE Refund(
	retid		CHAR(30) not null PRIMARY KEY,
	rDate		DATE not null,
	receiptId	CHAR(10) not null,
	FOREIGN KEY (receiptId) REFERENCES Purchase
		ON DELETE NO ACTION);

CREATE TABLE ReturnItem(
	retid		CHAR(30) not null,
	upc		CHAR(4) not null,
	quantity	SMALLINT not null,
	PRIMARY KEY (retid, upc),
	FOREIGN KEY (retid) REFERENCES Return,
		ON DELETE NO ACTION
	FOREIGN KEY (upc) REFERENCES Item
		ON DELETE NO ACTION
	CHECK (quantity > 0);
