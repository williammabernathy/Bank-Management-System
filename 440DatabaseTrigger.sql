DROP DATABASE IF EXISTS BankManagementSystem;
CREATE DATABASE BankManagementSystem;

USE BankManagementSystem;

DROP TABLE if EXISTS Customer;
DROP TABLE if EXISTS CustomerAccounts;
DROP TABLE if EXISTS Employee;
DROP TABLE if EXISTS Transactions;

CREATE TABLE Customer(
customerID INT(100) PRIMARY KEY AUTO_INCREMENT,
customerFname VARCHAR(20),
customerLname VARCHAR(20),
customerAddress VARCHAR(50),
customerPhone VARCHAR(15),
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

INSERT INTO Customer (customerFname, customerLname, customerAddress, customerPhone, customerBirthday) VALUES('zach', 'bray', '123 main street', '555-555-5555', STR_TO_DATE('2/25/1993', '%m/%d/%Y'));
INSERT INTO Customer (customerFname, customerLname, customerAddress, customerPhone, customerBirthday) VALUES('bereket', 'd', '123 hollywood', '555-555-5555', STR_TO_DATE('1/23/1998', '%m/%d/%Y'));
INSERT INTO Customer (customerFname, customerLname, customerAddress, customerPhone, customerBirthday) VALUES('will', 'aber', '123 middleofnowhere', '555-555-5555', STR_TO_DATE('2/25/1993', '%m/%d/%Y'));
INSERT INTO Customer (customerFname, customerLname, customerAddress, customerPhone, customerBirthday) VALUES('matt', 'hudson', '123 somewhere', '555-555-5555', STR_TO_DATE('3/21/1970', '%m/%d/%Y'));
INSERT INTO Customer (customerFname, customerLname, customerAddress, customerPhone, customerBirthday) VALUES('rip', 'fl4k', '123 pandora', '555-555-5555', STR_TO_DATE('4/14/1950', '%m/%d/%Y'));

INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('1', 'C', STR_TO_DATE('4/14/1950', '%m/%d/%Y'), 1000.00);
INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('1', 'S', STR_TO_DATE('12/25/1850', '%m/%d/%Y'), 1000000.00);
INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('2', 'S', STR_TO_DATE('5/6/1960', '%m/%d/%Y'), 10000.00);
INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('3', 'C', STR_TO_DATE('6/26/1990', '%m/%d/%Y'), 15000.00);
INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('4', 'C', STR_TO_DATE('7/18/1920', '%m/%d/%Y'), 5000.00);
INSERT INTO Customeraccounts (customerID, accountType, creationDate, amount) VALUES('5', 'L', STR_TO_DATE('8/31/1900', '%m/%d/%Y'), 100.00);

INSERT INTO employee (employeeFname, employeeLname, employeeUsername, employeePassword) VALUES ('fname', 'lname', 'username', PASSWORD('password'));
INSERT INTO employee (employeeFname, employeeLname, employeeUsername, employeePassword) VALUES ('zach', 'brey', 'Zbrey', PASSWORD('flawed'));
INSERT INTO employee (employeeFname, employeeLname, employeeUsername, employeePassword) VALUES ('zach', 'bray', 'Zbray', PASSWORD('flawless'));

INSERT INTO transactions (accountID, customerID, transactiondate, transactionAmount) VALUES('1', '1', STR_TO_DATE('8/31/1900', '%m/%d/%Y'), 1500.0);



