START TRANSACTION;

SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS Users;
CREATE TABLE Users
(
    firstname VARCHAR(256),
    lastname  VARCHAR(256),
    email     VARCHAR(256) NOT NULL,
    password  VARCHAR(256) NOT NULL,
    user_type ENUM ('Customer', 'Administrator', 'Partner'),
    PRIMARY KEY (email)

);

INSERT INTO Users(firstname, lastname, email, password, user_type)
VALUES ('Julian', 'Galati', 'firstcust@example.com', '12345678', 'Customer'),
       ('Ryan', 'Lee', 'firstadmin@example.com', '12345678', 'Administrator'),
       ('Ell', 'Tay', 'firstpartner@example.com', '12345678', 'Partner');


DROP TABLE IF EXISTS BookReview;

CREATE TABLE BookReview
(
    bid           VARCHAR(20)   NOT NULL,
    reviewer_name VARCHAR(60)   NOT NULL,
    rating        INT CHECK (rating >= 1 and rating <= 5),
    content       VARCHAR(8000) NOT NULL,
    reviewDate    DATE,
    FOREIGN KEY (bid) REFERENCES Book (bid)
);

INSERT INTO BookReview(bid, reviewer_name, rating, content, reviewDate)
VALUES ('b0001', 'Unknown', 4, 'Worth a read', '2020-03-05');

DROP TABLE IF EXISTS
    Address;
CREATE TABLE Address
(
    id       INT UNSIGNED NOT NULL AUTO_INCREMENT,
    street   VARCHAR(100) NOT NULL,
    province VARCHAR(20)  NOT NULL,
    country  VARCHAR(20)  NOT NULL,
    zip      VARCHAR(20)  NOT NULL,
    phone    VARCHAR(20),
    PRIMARY KEY (id)
);

INSERT INTO Address(id, street, province, country, zip, phone)
VALUES (1, '123 Yonge St', 'ON', 'Canada', 'K1E 6T5', '647-123-4567'),
       (2, '445 Avenue rd', 'ON', 'Canada', 'M1C 6K5', '416-123-8569'),
       (3, '789 Keele St.', 'ON', 'Canada', 'K3C 9T5', '416-123-9568');

DROP TABLE IF EXISTS
    PO;
CREATE TABLE PO
(
    id      INT UNSIGNED                           NOT NULL AUTO_INCREMENT,
    lname   VARCHAR(20)                            NOT NULL,
    fname   VARCHAR(20)                            NOT NULL,
    STATUS  ENUM
                ('ORDERED', 'PROCESSED', 'DENIED') NOT NULL,
    address INT UNSIGNED                           NOT NULL,
    date    DATE                                   NOT NULL,
    email   VARCHAR(256)                           NOT NULL,
    PRIMARY KEY (id),
    INDEX (address),
    FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE
);

INSERT INTO PO(id, lname, fname, STATUS, address, date, email)
VALUES (1, 'John', 'White', 'PROCESSED', '1', '2020-04-16', 'johnwhite@example.com'),
       (2, 'Peter', 'Black', 'DENIED', '2', '2020-04-16', 'pb@example.com'),
       (3, 'Andy', 'Green', 'ORDERED', '3', '2020-04-16', 'agreen@example.com'),
       (4, 'Andy', 'Green', 'ORDERED', '4', '2020-04-16', 'agreen@example.com');

DROP TABLE IF EXISTS
    POItem;
CREATE TABLE POItem
(
    id    INT UNSIGNED NOT NULL,
    bid   VARCHAR(20)  NOT NULL,
    price INT UNSIGNED NOT NULL,
    PRIMARY KEY (id, bid),
    INDEX (id),
    FOREIGN KEY (id) REFERENCES PO (id) ON DELETE CASCADE,
    INDEX (bid),
    FOREIGN KEY (bid) REFERENCES Book (bid) ON DELETE CASCADE
);


INSERT INTO POItem(id, bid, price)
VALUES (1, 'b0001', 20),
       (2, 'b0002', 201),
       (3, 'b0003', 100),
       (4, 'b0003', 100);

Drop TABLE IF EXISTS VisitEvent;
CREATE TABLE VisitEvent
(
    DAY       VARCHAR(8)                        NOT NULL,
    bid       VARCHAR(20)                       NOT NULL REFERENCES Book (bid),
    eventtype ENUM ('VIEW', 'CART', 'PURCHASE') NOT NULL,
    FOREIGN KEY (bid) REFERENCES Book (bid)
);

INSERT INTO VisitEvent(DAY, bid, eventtype)
VALUES ('12202015', 'b0001', 'VIEW'),
       ('12242015', 'b0001', 'CART'),
       ('12252015', 'b0001', 'PURCHASE');

SET FOREIGN_KEY_CHECKS = 1;
COMMIT;