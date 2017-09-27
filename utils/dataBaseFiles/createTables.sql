CREATE TABLE Dentist(
dentistID INT PRIMARY KEY AUTO_INCREMENT,
salt VARCHAR(64),
username VARCHAR(20),
password VARCHAR(64),
firstName VARCHAR(20),
lastName VARCHAR(20),
email VARCHAR(20),
address1 VARCHAR(20),
address2 VARCHAR(20),
address3 VARCHAR(20),
address4 VARCHAR(20),
telephone VARCHAR(20)
)

CREATE TABLE Patient(
patientID INT PRIMARY KEY AUTO_INCREMENT,
firstName VARCHAR(20),
lastName VARCHAR(20),
email VARCHAR(20),
address1 VARCHAR(20),
address2 VARCHAR(20),
address3 VARCHAR(20),
address4 VARCHAR(20),
telephone VARCHAR(20),
balance VARCHAR(20),
dentistID INT
)

CREATE TABLE Invoice(
invoiceID INT PRIMARY KEY AUTO_INCREMENT,
invoiceAmnt VARCHAR(20),
invoiceLeftToPay VARCHAR(20),
date VARCHAR(64),
isPaid VARCHAR(5),
patientID INT
)

CREATE TABLE Payment(
paymentID INT PRIMARY KEY AUTO_INCREMENT,
paymentAmnt VARCHAR(20),
paymentType VARCHAR(20),
date VARCHAR(64),
InvoiceID INT
)

CREATE TABLE Pprocedure(
procedureID INT PRIMARY KEY AUTO_INCREMENT,
procName VARCHAR(20),
proc VARCHAR(20),
date VARCHAR(64),
InvoiceID INT
);


CREATE TABLE Authors(authorID INT PRIMARY KEY, firstName VARCHAR(20),lastName varchar(20))
