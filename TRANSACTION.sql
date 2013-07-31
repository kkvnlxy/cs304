-- NOTE: This file is just template

-- legend: 	*_input := must not be null and should be provided
--		*_gen := machine gend value
--		= null := attribute takes null value

---------------------------------------------------------------
-- Clerk:
---------------------------------------------------------------
-- purchase in store:
INSERT INTO Purchase VALUES(receiptId_gen, pDate_input, cid = null, cardNum, expiryDate, expectedDate = null, deliveredDate = null);
INSERT INTO PurchaseItem VALUES (receiptId_input, upc_input, quantity_input);
UPDATE Item
SET Item.stock = Item.stock - quantity_input
WHERE Item.upc = upc_input;

-- returning an item
SELECT pDate
FROM Purchase
WHERE Purchase.receiptId = receiptId_input;
INSERT INTO Return VALUES (retid_gen, rDate_input, receiptId_input);
INSERT INTO ReturnItem VALUES (retid_input, upc_input, quantity_input);
UPDATE Item
SET Item.stock = Item.stock + quantity_input
WHERE Item.upc = upc_input;

---------------------------------------------------------------
-- Customer:
---------------------------------------------------------------
-- registration
INSERT INTO Customer VALUES (cid_input, pswd_input, name_input, address_input, phone_input);

-- online purchase
INSERT INTO Purchase VALUES(receiptId_gen, pDate_gen, cid_input, cardNum_input, expiryDate_input, expectedDate_gen, deliveredDate = null); --TODO: how do we calculate/generate the pDate and expectedDate
INSERT INTO PurchaseItem VALUES (receiptId_input, upc_input, quantity_input);
UPDATE Item
SET Item.stock = Item.stock - quantity_input
WHERE Item.upc = upc_input;

---------------------------------------------------------------
-- Manager:
---------------------------------------------------------------
-- adding an item
INSERT INTO Item VALUES (upc_input, title_input, type_input, category_input, year_input, price_input, stock_input);
INSERT INTO HasSong VALUES (upc_input, title_input);
INSERT INTO LeadSinger VALUES (upc_input, name_input);

-- process a delivery
UPDATE Purchase
SET deliveredDate = deliveredDate_input
WHERE receiptId = receiptId_input;

-- daily sale report
SELECT I.upc, I.category, I.price, PI.quantity
FROM Purchase P, PurchaseItem PI, Item I
WHERE P.receiptId = PI.receiptId AND PI.upc = I.upc AND P.pDate = pDate_input;
ORDER BY I.category

-- top n selling item
SELECT SUM(PI.quantity)
FROM Purchase P, PurchaseItem PI
WHERE P.pDate = pDate_input AND P.receiptId = PI.receiptId AND PI.quantity > 0
GROUP BY PI.upc
ORDER BY SUM(PI.quantity) DESC LIMIT n_input;

