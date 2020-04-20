# MUGA Bookstore

Spring MVC Application 


## Overview  
The MUGA Bookstore is an online bookstore that allows users to browse and purchase books. A user can register for an account, or use the convenient guest checkout feature. Users can browse the product catalogue, submit a book review, and select, add or remove books to/from their shopping cart.


## Installation
The following section contains installation steps to connect the MUGA Bookstore war file to the external database manager XAMPP.

To connect to XAMPP, the main-servlet.xml contains the JDBC logic to do so. Under “dataSource” the url value “jdbc:mysql://localhost:3306:/muga_database”must be added to connect to the muga_database in XAMPP. The language of the databse is MySQL which uses port 3306 for the db connection, with username “root”.

To import the MUGA bookstore database, the Apache and MySQL server must be started in XAMPP. On the XAMPP control panel, click the “Admin” button beside MySQL. This will open phpMyAdmin. The database “muga_database” must be created, using the “New” button located on the left menu tab.

The muga_database can be populated by running 0_books.sql then 1_muga.sql (located in the project “migrations” folder) by clicking the “SQL” tab located in the header tab of phpMyAdmin. Once both SQL files have been imported, the bookstore will be able to connect to the database as long as XAMPP is running.
