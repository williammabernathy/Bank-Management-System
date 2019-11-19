DROP DATABASE IF EXISTS BankManagementSystem;
CREATE DATABASE BankManagementSystem;

USE BankManagementSystem;

DROP TABLE if EXISTS Customer;
DROP TABLE if EXISTS CustomerAccounts;
DROP TABLE if EXISTS Employee;
DROP TABLE if EXISTS Transactions;

CREATE TABLE Customer(
customerID INT(100) PRIMARY KEY AUTO_INCREMENT,
customerFname VARCHAR(40),
customerLname VARCHAR(40),
customerAddress VARCHAR(50),
customerCity VARCHAR(50),
customerState VARCHAR(50),
customerZip VARCHAR(50),
customerPhone VARCHAR(25),
customerBirthday DATE
);
	
CREATE TABLE CustomerAccounts(
accountID INT(100) AUTO_INCREMENT,
customerID INT(100),
accountType VARCHAR(20),
creationDate DATE,
amount DOUBLE,
FOREIGN KEY (customerid) REFERENCES customer(customerid),
PRIMARY KEY(accountid, customerid)
);

CREATE TABLE Employee(
employeeID INT(100) PRIMARY KEY AUTO_INCREMENT,
employeeFname VARCHAR(20),
employeeLname VARCHAR(20),
employeeUsername VARCHAR(20),
employeePassword CHAR(60)
);

CREATE TABLE Transactions(
transactionID INT(100) AUTO_INCREMENT PRIMARY KEY,
senderAccountID INT,
receiverAccountID INT,
transactiondate DATE,
transactionAmount DOUBLE,
FOREIGN KEY (senderAccountID) REFERENCES customeraccounts(accountid),
FOREIGN KEY (receiverAccountID) REFERENCES customeraccounts(accountid)
);

CREATE TABLE depositewithdraw(
dwID INT(100) AUTO_INCREMENT PRIMARY KEY,
AccountID INT,
actiontype VARCHAR(1),
dwDate DATE,
amount INT
);

Delimiter //
CREATE TRIGGER updateCustomerAccountAmount
AFTER INSERT ON transactions FOR EACH ROW 
BEGIN
	update customeraccounts
	SET amount = amount + NEW.transactionAmount
	WHERE accountid = NEW.receiverAccountID;
	UPDATE customeraccounts
	SET amount = amount - NEW.transactionAmount
	WHERE accountid = NEW.senderAccountID;
	
END;
//
delimiter ;

Delimiter //
CREATE TRIGGER updateCustomerAccountAmountAfterDorW
AFTER INSERT ON depositewithdraw FOR EACH ROW 
BEGIN
	update customeraccounts
	SET amount = amount + NEW.amount
	WHERE accountid = NEW.AccountID;
	
END;
//
delimiter ;



INSERT INTO Customer (customerFname, customerLname, customerAddress, customerCity, customerState, customerZip, customerPhone, customerBirthday) VALUES('Zach', 'Bray', '123 Main Street','Irvine', 'Kentucky', '40336', '555-555-5555', STR_TO_DATE('2/25/1993', '%m/%d/%Y'));
INSERT INTO Customer (customerFname, customerLname, customerAddress, customerCity, customerState, customerZip, customerPhone, customerBirthday) VALUES('Bereket', 'Demessie', '123 Hollywood', 'Richmond', 'Kentucky', '40475', '555-555-5555', STR_TO_DATE('1/23/1998', '%m/%d/%Y'));
INSERT INTO Customer (customerFname, customerLname, customerAddress, customerCity, customerState, customerZip, customerPhone, customerBirthday) VALUES('Will', 'Abernathy', '123 Middleofnowhere', 'Irvine', 'Kentucky', '40336', '555-555-5555', STR_TO_DATE('2/25/1993', '%m/%d/%Y'));
INSERT INTO Customer (customerFname, customerLname, customerAddress, customerCity, customerState, customerZip, customerPhone, customerBirthday) VALUES('Matt', 'Hudson', '123 Somewhere', 'Lexington', 'Kentucky', '40444', '555-555-5555', STR_TO_DATE('3/21/1970', '%m/%d/%Y'));
INSERT INTO Customer (customerFname, customerLname, customerAddress, customerCity, customerState, customerZip, customerPhone, customerBirthday) VALUES('RIP', 'FL4K', '123 Pandora', 'Birmingham', 'Alabama', '12345', '555-555-5555', STR_TO_DATE('4/14/1950', '%m/%d/%Y'));

INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('1', 'C', STR_TO_DATE('4/14/1950', '%m/%d/%Y'), 1000.00);
INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('1', 'S', STR_TO_DATE('12/25/1850', '%m/%d/%Y'), 1000000.00);
INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('2', 'S', STR_TO_DATE('5/6/1960', '%m/%d/%Y'), 10000.00);
INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('3', 'C', STR_TO_DATE('6/26/1990', '%m/%d/%Y'), 15000.00);
INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('4', 'C', STR_TO_DATE('7/18/1920', '%m/%d/%Y'), 5000.00);
INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('5', 'L', STR_TO_DATE('8/31/1900', '%m/%d/%Y'), 100.00);

INSERT INTO employee (employeeFname, employeeLname, employeeUsername, employeePassword) VALUES ('fname', 'lname', 'username', PASSWORD('password'));
INSERT INTO employee (employeeFname, employeeLname, employeeUsername, employeePassword) VALUES ('zach', 'brey', 'Zbrey', PASSWORD('flawed'));
INSERT INTO employee (employeeFname, employeeLname, employeeUsername, employeePassword) VALUES ('zach', 'bray', 'Zbray', PASSWORD('flawless'));

INSERT INTO transactions (senderAccountID,receiverAccountID,transactiondate,transactionAmount) VALUES(2, 1, STR_TO_DATE('8/31/1900', '%m/%d/%Y'), 100.0);

INSERT INTO depositewithdraw(accountid, actiontype, dwDate, amount) VALUES(3, 'd', STR_TO_DATE('8/31/1900', '%m/%d/%Y'), 1000.0);
INSERT INTO depositewithdraw(accountid, actiontype, dwDate, amount) VALUES(3, 'd', STR_TO_DATE('8/31/1900', '%m/%d/%Y'), -500.0);




