DROP DATABASE IF EXISTS BankManagementSystem;
CREATE DATABASE BankManagementSystem;

USE BankManagementSystem;

DROP TABLE if EXISTS Customer;
DROP TABLE if EXISTS CustomerAccounts;
DROP TABLE if EXISTS Employee;
DROP TABLE if EXISTS Transactions;

CREATE TABLE Customer(
customerID VARCHAR(20) PRIMARY KEY,
customerFname VARCHAR(20),
customerLname VARCHAR(20),
customerAddress VARCHAR(50),
customerPhone VARCHAR(15),
customerBirthday DATE
);
	
CREATE TABLE CustomerAccounts(
accountID VARCHAR(20),
customerID VARCHAR(20),
accountType VARCHAR(20),
creationDate DATE,
amount DOUBLE,
FOREIGN KEY (customerid) REFERENCES customer(customerid),
PRIMARY KEY(accountid, customerid)
);

CREATE TABLE Employee(
employeeID VARCHAR(20) PRIMARY KEY,
employeeFname VARCHAR(20),
employeeLname VARCHAR(20),
employeeUsername VARCHAR(20),
employeePassword CHAR(60)
);

CREATE TABLE Transactions(
transactionID VARCHAR(20),
accountID VARCHAR(20),
customerID VARCHAR(20),
transactiondate DATE,
transactionAmount DOUBLE,
FOREIGN KEY (customerid) REFERENCES customer(customerid),
FOREIGN KEY (accountid) REFERENCES customeraccounts(accountid),
PRIMARY KEY (transactionid, accountid, customerid)
);

Delimiter //
CREATE TRIGGER updateCustomerAccountAmount
AFTER INSERT ON transactions FOR EACH ROW 
BEGIN
	update customeraccounts
	SET amount = amount + NEW.transactionAmount
	WHERE accountid = NEW.accountid AND customerid = NEW.customerid;
	
END;
//
delimiter ;

INSERT INTO Customer VALUES('1234', 'zach', 'bray', '123 main street', '555-555-5555', STR_TO_DATE('2/25/1993', '%m/%d/%Y'));
INSERT INTO Customer VALUES('4321', 'bereket', 'd', '123 hollywood', '555-555-5555', STR_TO_DATE('1/23/1998', '%m/%d/%Y'));
INSERT INTO Customer VALUES('5678', 'will', 'aber', '123 middleofnowhere', '555-555-5555', STR_TO_DATE('2/25/1993', '%m/%d/%Y'));
INSERT INTO Customer VALUES('8765', 'matt', 'hudson', '123 somewhere', '555-555-5555', STR_TO_DATE('3/21/1970', '%m/%d/%Y'));
INSERT INTO Customer VALUES('9999', 'rip', 'fl4k', '123 pandora', '555-555-5555', STR_TO_DATE('4/14/1950', '%m/%d/%Y'));

INSERT INTO Customeraccounts VALUES('1234', '4321', 'C', STR_TO_DATE('4/14/1950', '%m/%d/%Y'), 1000.00);
INSERT INTO Customeraccounts VALUES('7654', '4321', 'S', STR_TO_DATE('12/25/1850', '%m/%d/%Y'), 1000000.00);
INSERT INTO Customeraccounts VALUES('7533', '1234', 'S', STR_TO_DATE('5/6/1960', '%m/%d/%Y'), 10000.00);
INSERT INTO Customeraccounts VALUES('9784', '9999', 'C', STR_TO_DATE('6/26/1990', '%m/%d/%Y'), 15000.00);
INSERT INTO Customeraccounts VALUES('9876', '8765', 'CA', STR_TO_DATE('7/18/1920', '%m/%d/%Y'), 5000.00);
INSERT INTO Customeraccounts VALUES('2345', '5678', 'M', STR_TO_DATE('8/31/1900', '%m/%d/%Y'), 100.00);

INSERT INTO employee VALUES ('123', 'fname', 'lname', 'username', PASSWORD('password'));
INSERT INTO employee VALUES ('321', 'zach', 'brey', 'Zbrey', PASSWORD('flawed'));
INSERT INTO employee VALUES ('567', 'zach', 'bray', 'Zbray', PASSWORD('flawless'));

INSERT INTO transactions VALUES('1234567', '9784', '9999', STR_TO_DATE('8/31/1900', '%m/%d/%Y'), 1500.0);


SELECT amount FROM customeraccounts WHERE accountid = '9784' AND customerid = '9999';


