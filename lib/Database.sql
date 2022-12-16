drop database Bank;
create database Bank;
use Bank;

create table Person (
	ID int,
    userName varchar(255),
    userPassword varchar(255),
    userType varchar(255)
);

create table SavingAccount (
	accountID int,
    customerID int,
    balanceUSD double,
    balanceEUR double,
    balanceCNY double,
    lastTimeRedeem double
);

# Treat back account as a checking account
create table CheckingAccount (
	accountID int,
    customerID int,
    balanceUSD double,
    balanceEUR double,
    balanceCNY double
);

create table SecurityAccount (
	accountID int,
    customerID int,
    currentBalance double,
    realizedProfit double,
    unrealizedProfit double
);

CREATE TABLE Loans (
	loanID int,
    customerID int,
    currencyType varchar(255),
    amount double,
    loanTime double
);

CREATE TABLE Transactions (
	transactionID int,
    customerID int,
    accountID1 int,
    accountID2 int,
    currencyType varchar(255),
    balance double,
    transactionType varchar(255),
    transactionTime double
);

CREATE TABLE CustomerHoldStocks (
	recordID int,
	stockID int,
    customerID int,
    quantity int,
    priceBought double,
    timeBought double
);

CREATE TABLE StockMarket (
	stockID int,
    price double
);

INSERT INTO Person VALUES (20221216, 'banker', '123456', 'Manager');
INSERT INTO Person VALUES (3424432, 'owen', 'cs611', 'Customer');
INSERT INTO CheckingAccount VALUES (1415926, 20221216, 10000.00, 100000.0, 100000.0);
INSERT INTO SavingAccount VALUES (1245678, 3424432, 10000.00, 0.0, 10000.00, 1600033901960);
INSERT INTO stockMarket VALUES (611, 45.0);
INSERT INTO stockMarket VALUES (630, 24.0);
INSERT INTO stockMarket VALUES (655, 85.0);
