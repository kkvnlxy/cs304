CREATE TABLE Item
(
	upc 	CHAR(4) not null PRIMARY KEY,
	title	VARCHAR(50) not null,
	type	ENUM('CD', 'DVD') not null,
	category ENUM('rock', 'pop', 'rap', 'country', 'classical', 'new age', 'instrumental') not null,
	company	VARCHAR(50) not null,
	year	DATE not null,
	price	DOUBLE not null,
	stock	SMALLINT not null,
);

CREATE TABLE LeadSinger
(
	upc	CHAR(4) not null,
	name	VARCHAR(50) not null,

	PRIMARY KEY(upc, name),
	FOREIGN KEY (upc) REFERENCES Item
);

CREATE TABLE HasSong
(
	upc	CHAR(4) not null,
	title	VARCHAR(30) not null,
	
	PRIMARY KEY (upc, title),
	FOREIGN KEY (upc) REFERENCES Item
);

CREATE TABLE Purchase
(
	receiptId	CHAR(30) not null PRIMARY KEY,
	pDate		DATE not null,
	cid		CHAR(16),
	cardNum		CHAR(16),
	expiryDate	DATE,
	expectedDate	DATE,
	deliveredDate	DATE,

	FOREIGN KEY (cid) REFERENCES Customer
);

CREATE TABLE PurchaseItem
(
	receiptId	CHAR(30) not null,
	upc		CHAR(4) not null,
	quantity	SMALLINT not null,

	PRIMARY KEY (receiptId, upc),
	FOREIGN KEY (receiptId) REFERENCES Purchase,
	FOREIGN KEY (upc) 	REFERENCES Item
);

CREATE TABLE Customer
(
	cid		CHAR(16) not null PRIMARY KEY,
	pswd		VARCHAR(25) not null,
	name		VARCHAR(50) not null,
	address		VARCHAR(50) not null,
	phone		CHAR(10) not null,
);

CREATE TABLE Return
(
	retid		CHAR(30) not null PRIMARY KEY,
	rDate		DATE not null,
	receiptId	CHAR(30) not null,

	FOREIGN KEY (receiptId) REFERENCES Purchase
);

CREATE TABLE ReturnItem
(
	retid		CHAR(30) not null,
	upc		CHAR(4) not null,
	quantity	SMALLINT not null,

	PRIMARY KEY (retid, upc),
	FOREIGN KEY (retid) REFERENCES Return,
	FOREIGN KEY (upc) REFERENCES Item
);
