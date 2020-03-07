/** bid: unique identifier of Book (like ISBN)
* title: title of Book
* price: unit price WHEN ordered
* author: name of authors
* category: as specified
*/
DROP TABLE IF EXISTS
    Book;
CREATE TABLE Book(
    bid VARCHAR(20) NOT NULL,
    title VARCHAR(60) NOT NULL,
    price INT NOT NULL,
    category ENUM('Science', 'Fiction', 'Engineering') NOT NULL,
    PRIMARY KEY(bid)
);
#
# Adding data for table 'Book'
#
INSERT INTO Book(bid, title, price, category)
VALUES(
    'b001',
    'Little Prince',
    20,
    'Fiction'
);
INSERT INTO Book(bid, title, price, category)
VALUES('b002', 'Physics', 201, 'Science');
INSERT INTO Book(bid, title, price, category)
VALUES(
    'b003',
    'Mechanics',
    100,
    'Engineering'
);
#
/* Address
* id: address id
*
*/
DROP TABLE IF EXISTS
    Address;
CREATE TABLE Address(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    street VARCHAR(100) NOT NULL,
    province VARCHAR(20) NOT NULL,
    country VARCHAR(20) NOT NULL,
    zip VARCHAR(20) NOT NULL,
    phone VARCHAR(20),
    PRIMARY KEY(id)
);
#
# Inserting data for table 'address'
#
INSERT INTO Address(
    id,
    street,
    province,
    country,
    zip,
    phone
)
VALUES(
    1,
    '123 Yonge St',
    'ON',
    'Canada',
    'K1E 6T5',
    '647-123-4567'
);
INSERT INTO Address(
    id,
    street,
    province,
    country,
    zip,
    phone
)
VALUES(
    2,
    '445 Avenue rd',
    'ON',
    'Canada',
    'M1C 6K5',
    '416-123-8569'
);
INSERT INTO Address(
    id,
    street,
    province,
    country,
    zip,
    phone
)
VALUES(
    3,
    '789 Keele St.',
    'ON',
    'Canada',
    'K3C 9T5',
    '416-123-9568'
);
#
#
/* Purchase Order
* lname: last name
* fname: first name
* id: purchase order id
* status: status of purchase
*/
DROP TABLE IF EXISTS
    PO;
CREATE TABLE PO(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    lname VARCHAR(20) NOT NULL,
    fname VARCHAR(20) NOT NULL,
    STATUS ENUM
        ('ORDERED', 'PROCESSED', 'DENIED') NOT NULL,
        address INT UNSIGNED NOT NULL,
        PRIMARY KEY(id),
        INDEX(address),
        FOREIGN KEY(address) REFERENCES Address(id) ON DELETE CASCADE
);
#
# Inserting data for table 'PO'
#
INSERT INTO PO(
    id,
    lname,
    fname,
STATUS
    ,
    address
)
VALUES(1, 'John', 'White', 'PROCESSED', '1');
INSERT INTO PO(
    id,
    lname,
    fname,
STATUS
    ,
    address
)
VALUES(2, 'Peter', 'Black', 'DENIED', '2');
INSERT INTO PO(
    id,
    lname,
    fname,
STATUS
    ,
    address
)
VALUES(3, 'Andy', 'Green', 'ORDERED', '3');
#
#
/* Items on order
* id : purchase order id
* bid: unique identifier of Book
* price: unit price
*/
DROP TABLE IF EXISTS
    POItem;
CREATE TABLE POItem(
    id INT UNSIGNED NOT NULL,
    bid VARCHAR(20) NOT NULL,
    price INT UNSIGNED NOT NULL,
    PRIMARY KEY(id, bid),
    INDEX(id),
    FOREIGN KEY(id) REFERENCES PO(id) ON DELETE CASCADE,
    INDEX(bid),
    FOREIGN KEY(bid) REFERENCES Book(bid) ON DELETE CASCADE
);
#
# Inserting data for table 'POitem'
#
INSERT INTO POItem(id, bid, price)
VALUES(1, 'b001', '20');
INSERT INTO POItem(id, bid, price)
VALUES(2, 'b002', '201');
INSERT INTO POItem(id, bid, price)
VALUES(3, 'b003', '100');
#
#
/* visit to website
* day: date
* bid: unique identifier of Book
* eventtype: status of purchase
*/
Drop TABLE IF EXISTS VisitEvent;
CREATE TABLE VisitEvent(
    DAY VARCHAR(8) NOT NULL,
    bid VARCHAR(20) NOT NULL REFERENCES Book(bid),
    eventtype ENUM('VIEW', 'CART', 'PURCHASE') NOT NULL,
    FOREIGN KEY(bid) REFERENCES Book(bid)
);
#
# Dumping data for table 'VisitEvent'
#
INSERT INTO VisitEvent(DAY, bid, eventtype)
VALUES('12202015', 'b001', 'VIEW');
INSERT INTO VisitEvent(DAY, bid, eventtype)
VALUES('12242015', 'b001', 'CART');
INSERT INTO VisitEvent(DAY, bid, eventtype)
VALUES('12252015', 'b001', 'PURCHASE');
#
#

DROP TABLE IF EXISTS BookReview;
CREATE TABLE BookReview(
    bid VARCHAR(20) NOT NULL,
    reviewer_name VARCHAR(60) NOT NULL,
    rating INT CHECK (rating >= 1 and rating <= 5),
    content VARCHAR(8000) NOT NULL,
    reviewDate DATE,
    FOREIGN KEY (bid) REFERENCES Book(bid)
); 

INSERT INTO BookReview(bid, reviewer_name, rating, content, reviewDate)
VALUES("b001", "Unknown", 4, "Worth a read", "2020-03-05");