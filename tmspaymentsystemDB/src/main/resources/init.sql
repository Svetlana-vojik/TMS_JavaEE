--------------------------------------------------------
--  DDL for schema PAYMENT_SYSTEM_DB
--------------------------------------------------------
DROP SCHEMA IF EXISTS PAYMENT_SYSTEM_DB;
CREATE SCHEMA IF NOT EXISTS PAYMENT_SYSTEM_DB;

--------------------------------------------------------
--  DDL for Table bank_accounts
--------------------------------------------------------
DROP TABLE IF EXISTS PAYMENT_SYSTEM_DB.bank_accounts;
CREATE TABLE IF NOT EXISTS PAYMENT_SYSTEM_DB.bank_accounts (
                                                            id VARCHAR(60),
                                                            merchant_id VARCHAR(60),
                                                            status VARCHAR(10),
                                                            account_number VARCHAR(10),
                                                            created_at Timestamp);
--------------------------------------------------------
--  DDL for Table merchant
--------------------------------------------------------
DROP TABLE IF EXISTS PAYMENT_SYSTEM_DB.merchant;
CREATE TABLE IF NOT EXISTS PAYMENT_SYSTEM_DB.merchant (
                                                        id VARCHAR(60),
                                                        name VARCHAR(15),
                                                        created_at Timestamp);
--------------------------------------------------------
--  DML for Table bank_account, merchant
--------------------------------------------------------
INSERT INTO payment_system_db.bank_accounts(id, merchant_id, status, account_number, created_at) VALUES(?, ?, ?, ?, ?);
INSERT INTO payment_system_db.merchant(id, name, created_at) VALUES(?, ?, ?);