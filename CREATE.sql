CREATE TABLE Item
(
	upc 	CHAR(4) PRIMARY KEY,
	title	VARCHAR(50),
	type	ENUM('CD', 'DVD'),
	category ENUM('rock', 'pop', 'rap', 'country', 'classical', 'new age', 'instrumental') not null,
	company	VARCHAR(50),
	year	DATE,
	price	DOUBLE,
	stock	SMALLINT,

--	PRIMARY KEY (upc)
);

CREATE TABLE LeadSinger
(
	upc	CHAR(4),
	name	VARCHAR(50),

	PRIMARY KEY(upc, name),
	FOREIGN KEY (upc) REFERENCES Item
);

CREATE TABLE HasSong
(
	upc	CHAR(4) not null,
	title	VARCHAR(30),
	
	PRIMARY KEY (upc, title),
	FOREIGN KEY (upc) REFERENCES Item
);

CREATE TABLE Purchase
(
	receiptId	CHAR(30) PRIMARY KEY,-- TODO
	pDate		DATE,
	cid		CHAR(16),-- TODO
	cardNum		CHAR(16),
	expiryDate	DATE,
	expectedDate	DATE,
	deliveredDate	DATE,

	FOREIGN KEY (cid) REFERENCES Customer
);

CREATE TABLE PurchaseItem
(
	receiptId	CHAR(30),
	upc		CHAR(4),
	quantity	SMALLINT,

	PRIMARY KEY (receiptId, upc),
	FOREIGN KEY (receiptId) REFERENCES Purchase,
	FOREIGN KEY (upc) 	REFERENCES Item
);

CREATE TABLE Customer
(
	cid		CHAR(16) PRIMARY KEY,
	password	VARCHAR(25) NOT NULL,
	name		VARCHAR(50),
	address		VARCHAR(50),
	phone		CHAR(10),
);

CREATE TABLE Return
(
	retid		CHAR(30) PRIMARY KEY,
	rDate		DATE,
	receiptId	CHAR(30),

	FOREIGN KEY (receiptId) REFERENCES Purchase
);

CREATE TABLE ReturnItem
(
	retid		CHAR(30),
	upc		CHAR(4),
	quantity	SMALLINT,

	PRIMARY KEY (retid, upc),
	FOREIGN KEY (retid) REFERENCES Return,
	FOREIGN KEY (upc) REFERENCES Item
);
