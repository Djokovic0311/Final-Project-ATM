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
	ID int,
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

CREATE TABLE StockTransactions (
	transactionID int,
    customerID int,
    stockID int,
    quantity int,
    totalPrice double,
    transactionType varchar(255),
    transactionTime double
);

CREATE TABLE CustomerHoldStocks (
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

CREATE TABLE Settings (
	openAccountFee double,
    closeAccountFee double,
    savingInterest double,
    loanInterest double,
    transactionFee double # this is the percentage charged during traction
);
