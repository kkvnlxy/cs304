---------------------------------------------------------------
-- Item:
---------------------------------------------------------------
insert into item values ('0001', 'We Can"t Stop', 'CD', 'POP', 'RCA Records', '2013', '1.29', '2');
insert into item values ('0002', 'Believe', 'CD', 'POP', 'The Item Def Jam Music Group', '2012', '13.99', '6');
insert into item values ('0003', 'Beethoven: The Piano Concertos', 'DVD', 'CLASSICAL', 'Deutsche Grammophon Studio', '2007', '34.99', '3');
insert into item values ('0004', 'Grand Ole Opry at Carnegie Hall', 'DVD', 'COUNTRY', 'RCA Studio', '2006', '11.98', '1');
insert into item values ('0005', 'Can"t Believe It', 'CD', 'RAP', 'Alantic Recording Cooportation', '2013', '1.29', '5');
insert into item values ('0006', 'Bonfire Heart', 'CD', 'POP', 'Warner Music UK Limited', '2013', '1.29', '4');
insert into item values ('0007', 'Shelter Valley', 'CD', 'ROCK', 'Universal Music Canada', '2013', '1.29', '12');
insert into item values ('0008', 'Music For Healing', 'CD', 'NEW_AGE', 'The Relaxation Company', '2003', '8.59', '2');
insert into item values ('0009', 'A Day To Remember', 'CD', 'INSTRUMENTAL', 'Shamrock-n-Roll, Inc.', '2002', '0.99', '5');

---------------------------------------------------------------
-- LeadSinger:
---------------------------------------------------------------
insert into leadsinger values ('0001', 'Miley Cyrus');
insert into leadsinger values ('0002', 'Justin Bieber');
insert into leadsinger values ('0003', 'Vladimir Ashkenazy'); 
insert into leadsinger values ('0004', 'Trace Adkins');
insert into leadsinger values ('0005', 'Flo Rida');
insert into leadsinger values ('0006', 'James Blunt');
insert into leadsinger values ('0007', 'Gentlemen Husbands');
insert into leadsinger values ('0008', 'Steven Halpern');
insert into leadsinger values ('0009', 'The O"Neill Brothers');

---------------------------------------------------------------
-- HasSong:
---------------------------------------------------------------
insert into hassong values ('0001', 'We Can"t Stop');
insert into hassong values ('0002', 'All Around The World');
insert into hassong values ('0002', 'Boyfriend');
insert into hassong values ('0002', 'As Long As You Love Me');
insert into hassong values ('0002', 'Catching Feelings');
insert into hassong values ('0002', 'Take You');
insert into hassong values ('0002', 'Right Here');
insert into hassong values ('0002', 'Fall');
insert into hassong values ('0002', 'Die In Your Arms');
insert into hassong values ('0002', 'Thought Of You');
insert into hassong values ('0002', 'Beauty And A Beat');
insert into hassong values ('0002', 'One Love');
insert into hassong values ('0002', 'Be Alright');
insert into hassong values ('0002', 'Believe');
insert into hassong values ('0002', 'Out Of Town Girl');
insert into hassong values ('0002', 'She Don"t Like The Lights');
insert into hassong values ('0002', 'Maria');
insert into hassong values ('0003', 'Piano Concerto No. 1 In C Major, Op. 15: I. Allegro con brio');
insert into hassong values ('0003', 'Piano Concerto No. 1 In C Major, Op. 15: II. Largo');
insert into hassong values ('0003', 'Piano Concerto No. 1 In C Major, Op. 15: III. Rondo: Allegro');
insert into hassong values ('0003', 'Six Bagatelles, Op. 126: I. Andante Con Moto, Cantabile E Compiacevole');
insert into hassong values ('0003', 'Six Bagatelles, Op. 126: II. Allegro');
insert into hassong values ('0003', 'Six Bagatelles, Op. 126: III. Andante, Cantabile E Grazioso');
insert into hassong values ('0003', 'Six Bagatelles, Op. 126: IV. Presto');
insert into hassong values ('0003', 'Six Bagatelles, Op. 126: V. Quasi Allegretto');
insert into hassong values ('0003', 'Six Bagatelles, Op. 126: VI. Presto-Andante Amabile E Con Moto');
insert into hassong values ('0003', 'Fur Ellise');
insert into hassong values ('0004', 'Songs About Me');
insert into hassong values ('0004', 'If You Ever Had Forever In Mind');
insert into hassong values ('0004', 'May The Bird Of Paradise Fly Up Your Nose');
insert into hassong values ('0004', 'She"s In Love With The Boy');
insert into hassong values ('0004', 'Tryin" To Love You');
insert into hassong values ('0004', 'Kiss An Angel Good Morning');
insert into hassong values ('0004', 'She"s Got You');
insert into hassong values ('0004', 'Walls Of Time');
insert into hassong values ('0004', 'Black-Eyed Suzie');
insert into hassong values ('0004', 'Go Rest High On That Mountain');
insert into hassong values ('0004', 'Alcohol');
insert into hassong values ('0004', 'Too Country');
insert into hassong values ('0004', 'Whiskey Lullaby');
insert into hassong values ('0004', 'Independence Day');
insert into hassong values ('0004', '"Til I Can Make It On My Own');
insert into hassong values ('0004', 'Remember When');
insert into hassong values ('0004', 'Chattahoochee');
insert into hassong values ('0005', 'Can"t Believe It');
insert into hassong values ('0006', 'Bonfire Heart');
insert into hassong values ('0007', 'Shelter Valley');
insert into hassong values ('0008', 'Interstellar Light');
insert into hassong values ('0008', 'Awakening');
insert into hassong values ('0008', 'Stillpoint');
insert into hassong values ('0008', 'Jerusalem');
insert into hassong values ('0008', 'Ascension');
insert into hassong values ('0008', 'Oneness');
insert into hassong values ('0008', 'Healing Music #1');
insert into hassong values ('0008', 'Healing Music #2');
insert into hassong values ('0009', 'Falling in Love');

---------------------------------------------------------------
-- Customer:
---------------------------------------------------------------
INSERT INTO Customer VALUES('kevin', '1234', 'Kevin Liu', '2366 Main Mall, Vancouver, BC Canada V6T 1Z4', '604-822-6894');
INSERT INTO Customer VALUES('kkvnlxy', '1234', 'Kevin Liu', '220 Yonge Street Toronto, ON
M5B 2H1', '416-598-8560');
INSERT INTO Customer VALUES('ang', '2234', 'Angela Wei', '1961 E Mall, Vancouver, BC V6T 1Z1', '514-342-2872');
INSERT INTO Customer VALUES('lucia', '3453', 'Lucia Tseng', '2053 Main Mall, Vancouver, BC V6T 1Z2, Canada', '604-822-2665');
INSERT INTO Customer VALUES('Christine', '9483204A1v', 'Christine Kim', '701 W Georgia St  Vancouver, BC V7Y 1G5', '604-688-7235');

---------------------------------------------------------------
-- In Store Purchase:
---------------------------------------------------------------
-- not returnable in cash
insert into purchase values ('P000000001', to_date('21-07-2011', 'dd-mm-yyyy'), null, null, null, null, null);
-- returnable in cash
insert into purchase values ('P000000002', to_date('03-08-2013', 'dd-mm-yyyy'), null, null, null, null, null);
-- returnable in CC
insert into purchase values ('P000000003', to_date('04-08-2013', 'dd-mm-yyyy'), null, '1111111111111111', to_date('08-15', 'mm-rr'), null, null);
-- day of presentation shopping frenzy
insert into purchase values ('P000000004', to_date('12-08-2013', 'dd-mm-yyyy'), null, null, null, null, null);

---------------------------------------------------------------
-- Online Purchase:
---------------------------------------------------------------
-- delivered, can't return
insert into purchase values ('P000000005', to_date('04-09-2012', 'dd-mm-yyyy'), 'kevin', '1234123412341234', to_date('10-16', 'mm-rr'), to_date('11-09-2012', 'dd-mm-yyyy'), to_date('11-09-2012', 'dd-mm-yyyy'));
-- delivered, can return
insert into purchase values ('P000000006', to_date('01-08-2013', 'dd-mm-yyyy'), 'ang', '1234123419361234', to_date('07-14', 'mm-rr'), to_date('08-08-2013', 'dd-mm-yyyy'), to_date('09-08-2013', 'dd-mm-yyyy'));
-- not yet delivered
insert into purchase values ('P000000007', to_date('04-08-2013', 'dd-mm-yyyy'), 'ang', '1234123419361234', to_date('07-14', 'mm-rr'), to_date('10-08-2013', 'dd-mm-yyyy'), null);
-- day of presentation shopping frenzy 
insert into purchase values ('P000000008', to_date('12-08-2013', 'dd-mm-yyyy'), 'lucia', '1235555419361234', to_date('09-14', 'mm-rr'), to_date('22-08-2013', 'dd-mm-yyyy'), null);

---------------------------------------------------------------
-- PurchaseItem:
---------------------------------------------------------------
-- ISP 1 item
insert into purchaseitem values ('P000000001', '0004', '1');
--already returned
insert into purchaseitem values ('P000000001', '0009', '1');
-- ISP 2 items [come back to return one of '0002' in cash]
insert into purchaseitem values ('P000000002', '0002', '2');
insert into purchaseitem values ('P000000002', '0001', '1');
-- ISP 1 item [come back to return in CC]
insert into purchaseitem values ('P000000003', '0002', '5');
-- OP 2 items tries to return 
insert into purchaseitem values ('P000000005', '0003', '1');
insert into purchaseitem values ('P000000005', '0009', '1');
-- OP 5 'POP' items
insert into purchaseitem values ('P000000006', '0001', '1');
insert into purchaseitem values ('P000000006', '0002', '1');
insert into purchaseitem values ('P000000006', '0006', '1');
-- OP 1 item
insert into purchaseitem values ('P000000007', '0005', '2');
-- Day of presentation purchases
-- ISPFrenzy
insert into purchaseitem values ('P000000004', '0005', '5');
insert into purchaseitem values ('P000000004', '0006', '4');
insert into purchaseitem values ('P000000004', '0007', '5');
insert into purchaseitem values ('P000000004', '0008', '2');
-- OP Frenzy
insert into purchaseitem values ('P000000008', '0007', '7');
insert into purchaseitem values ('P000000008', '0002', '5');
insert into purchaseitem values ('P000000008', '0005', '2');

---------------------------------------------------------------
-- Refund:
---------------------------------------------------------------
insert into refund values ('R000000001', to_date('28-07-2011', 'dd-mm-yyyy'), 'P000000001');

---------------------------------------------------------------
-- RefundItem:
---------------------------------------------------------------
insert into refunditem values ('R000000001', '0009', '1');
