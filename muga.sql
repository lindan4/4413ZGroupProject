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
    price DOUBLE(20, 2) NOT NULL,
    category VARCHAR(20) NOT NULL,
    PRIMARY KEY(bid)
);
#
# Adding data for table 'Book'
#
INSERT INTO Book(bid, title, price, category)
VALUES(
    'b001',
    'Little Prince',
    20.00,
    'Fiction'
);
INSERT INTO Book(bid, title, price, category)
VALUES('b002', 'Physics', 201.00, 'Science');
INSERT INTO Book(bid, title, price, category)
VALUES(
    'b003',
    'Mechanics',
    100.00,
    'Engineering'
);
#
/* Address
* id: address id
*
*/

INSERT INTO Book(bid, title, price, category) VALUES
('b004', 'The Hobbit', 49.99, 'Fantasy'),
('b005', 'The Two Towers', 11.16, 'Fantasy'),
('b006', 'Introduction to Algorithms, 3rd Edition', 144.89, 'Educational'),
('b007', 'Harry Potter and the Order of the Phoenix', 9.52, 'Fantasy'),
('b008', 'Harry Potter and the Chamber of Secrets', 9.52, 'Fantasy'),
('b009', 'A Wrinkle in Time', 7.17, 'Fantasy'),
('b010', 'The Great Alone', 14.34, 'Historical'),
('b011', 'Still Me: A Novel', 14.09, 'Ramance'),
('b012', 'Origin', 14.97, 'Mystery'),
('b013', 'Beneath a Scarlet Sky', 5.99, 'Historical'),
('b014', 'A Gentleman in Moscow', 14.09, 'Historical'),
('b015', 'Fire and Fury: Inside the Trump White House', 14.34, 'Historical'),
('b016', 'Sapiens: A Brief History of Humankind', 15.44, 'Educational'),
('b017', 'How to Win Friends and Influence People', 11.65, 'Educational'),
('b018', 'Principles: Life and Work', 18.83, 'Educational'),
('b019', 'Born a Crime', 12.44, 'Biography'),
('b020', 'Grant', 19.38, 'Biography'),
('b021', 'The Power of Habit', 14.95, 'Educational'),
('b022', 'The 7 Habits of Highly Effective People', 5.96, 'Educational'),
('b023', 'I\'ll Be Gone in the Dark', 15.72, 'Mystery'),
('b024', 'You Are a Badass', 10.13, 'Educational'),
('b025', 'Leonardo da Vinci', 17.03, 'Biography'),
('b026', 'Educated: A Memoir', 12.45, 'Biography'),
('b027', 'Hell\'s Princess', 4.88, 'Crime'),
('b028', 'The Last Black Unicorn', 11.65, 'Biography'),
('b029', 'Alexander Hamilton', 13.21, 'Biography'),
('b030', 'Enlightenment Now', 16.74, 'Philosophy'),
('b031', 'White Rose, Black Forest', 4.99, 'Romance'),
('b032', 'Eat What You Watch', 16.51, 'Cookbook'),
('b033', 'The Sous Vide Cookbook', 14.11, 'Cookbook'),
('b034', 'Learning Python, 5th Edition', 31.24, 'Educational'),
('b035', 'Python Crash Course', 27.12, 'Educational'),
('b036', 'Effective Java (3rd Edition)', 47.42, 'Educational'),
('b037', 'Compilers: Principles, Techniques, and Tools', 22.93, 'Educational'),
('b038', 'Introduction to the Theory of Computation', 16.54, 'Educational'),
('b039', 'Computer Systems: A Programmer\'s Perspective ', 12.82, 'Educational'),
('b040', 'The Algorithm Design Manual', 76.00, 'Educational'),
('b041', 'C Programming Language, 2nd Edition', 18.44, 'Educational'),
('b042', 'Types and Programming Languages (MIT Press)', 82.85, 'Educational'),
('b043', 'Computer Networks (4th Edition)', 12.47, 'Educational'),
('b044', 'Ready Player One', 8.81, 'Thriller'),
('b045', 'Oathbringer', 16.14, 'Fantasy'),
('b046', 'Russian Roulette', 15.59, 'Adventure'),
('b047', 'A Brief History of Time', 14.09, 'Science'),
('b048', 'The 5 Love Languages', 6.87, 'Educational'),
('b049', 'The Bridge at Andau', 2.64, 'Historical'),
('b050', 'A Thread of Grace: A Novel', 15.86, 'Historical'),
('b051', 'The Man from St. Petersburg', 12.33, 'Thriller'),
('b052', 'Crooked House', 1.74, 'Mystery'),
('b053', 'The Valley of Amazement', 12.13, 'Historical');

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