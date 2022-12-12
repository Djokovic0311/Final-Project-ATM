create database Bank;
use Bank;

create table Person (
	ID int,
    userName varchar(255),
    userPassword int
);

create table SavingAccount (
	ID int,
    customerID int,
    balanceUSD double,
    balanceEUR double,
    balanceCNY double,
    lastDateRedeam DATE # YYYY-MM-DD
);

# Treat back account as a checking account
create table CheckingAccount (
	ID int,
    customerID int,
    balanceUSD double,
    balanceEUR double,
    balanceCNY double
);

create table SecurityAccount (
	ID int,
    customerID int,
    balanceUSD double,
    realizePprofit double,
    unrealizeProfit double
);

CREATE TABLE LOANS (
	ID int,
    customerID int,
    balance double,
    loanDate DATE # YYYY-MM-DD
);

CREATE TABLE Transactionas (
	ID int,
    customerID int,
    AccountID1 int,
    AccountID2 int,
    currencyType varchar(255),
    Balance double,
    Transactiontype varchar(255),
    trasactionDate DATE # YYYY-MM-DD
);

CREATE TABLE StockTransactionas (
	ID int,
    customerID int,
    StockID int,
    numStock int,
    Balance double,
    Transactiontype varchar(255),
    trasactionDate DATE # YYYY-MM-DD
);

CREATE TABLE CustomerHoldStocks (
	stockID int,
    customerID int,
    stockNumber int,
    priceBought double,
    dateBought DATE # YYYY-MM-DD
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
