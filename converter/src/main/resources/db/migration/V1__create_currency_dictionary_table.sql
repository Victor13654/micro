create table CURRENCY_DICTIONARY
(
    ID                           VARCHAR2(3) NOT NULL primary key,
    DESCRIPTION                  VARCHAR2(50) NOT NULL

);

COMMENT ON COLUMN CURRENCY_DICTIONARY.ID IS 'CURRENCY CODE';
COMMENT ON COLUMN CURRENCY_DICTIONARY.DESCRIPTION IS 'CURRENCY NAME';