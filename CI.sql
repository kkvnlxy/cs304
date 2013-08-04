CREATE ASSERTION hasLeadSinger
CHECK 
(NOT EXISTS ((SELECT upc FROM Item) EXCEPT (SELECT upc FROM LeadSinger)));

CREATE ASSERTION containsSong
CHECK 
(NOT EXISTS ((SELECT upc FROM Item) EXCEPT (SELECT upc FROM HasSong)));

CREATE ASSERTION processPurchaseItem
CHECK 
(NOT EXISTS ((SELECT receiptId FROM Purchase) EXCEPT (SELECT receiptId FROM PurchaseItem)));

CREATE ASSERTION processReturnItem
CHECK 
(NOT EXISTS ((SELECT retid FROM Refund) EXCEPT (SELECT retid FROM RefundItem)));
