USE mocktpo;

DROP TABLE IF EXISTS MT_USER;
CREATE TABLE MT_USER (
  MT_ID       BIGINT PRIMARY KEY AUTO_INCREMENT,
  MT_EMAIL    VARCHAR(255) NOT NULL,
  MT_PASSCODE CHAR(33)     NOT NULL
);

DROP TABLE IF EXISTS MT_ORDER;
CREATE TABLE MT_ORDER (
  MT_ID              BIGINT PRIMARY KEY AUTO_INCREMENT,
  MT_ORDER_NUMBER    CHAR(17)     NOT NULL,
  MT_PID             INT          NOT NULL,
  MT_EMAIL           VARCHAR(255) NOT NULL,
  MT_PAYMENT_TYPE    INT          NOT NULL,
  MT_PRICE           DOUBLE       NOT NULL,
  MT_STATUS          INT          NOT NULL,
  MT_ACTIVATION_CODE CHAR(13),
  MT_HARDWARE        VARCHAR(37)
);

DROP TABLE IF EXISTS MT_TEST_INFO;
CREATE TABLE MT_TEST_INFO (
  MT_TID          CHAR(33) PRIMARY KEY,
  MT_TAG_ID       INT,
  MT_TITLE        VARCHAR(64)  NOT NULL,
  MT_STARS        INT          NOT NULL,
  MT_VERSION      DOUBLE       NOT NULL,
  MT_CREATOR      VARCHAR(255) NOT NULL,
  MT_CREATED_TIME BIGINT       NOT NULL,
  MT_UPDATED_TIME BIGINT       NOT NULL,
  MT_STATUS       INT          NOT NULL
);

DROP TABLE IF EXISTS MT_TEST_TAG;
CREATE TABLE MT_TEST_TAG (
  MT_TAG_ID INT PRIMARY KEY,
  MT_TITLE  VARCHAR(64) NOT NULL,
  MT_STATUS INT         NOT NULL
);
INSERT INTO MT_TEST_TAG (MT_TAG_ID, MT_TITLE, MT_STATUS) VALUES (0, 'Others', 2);
INSERT INTO MT_TEST_TAG (MT_TAG_ID, MT_TITLE, MT_STATUS) VALUES (1, 'TPO 20-01', 2);
INSERT INTO MT_TEST_TAG (MT_TAG_ID, MT_TITLE, MT_STATUS) VALUES (2, 'TPO 34-21', 2);
INSERT INTO MT_TEST_TAG (MT_TAG_ID, MT_TITLE, MT_STATUS) VALUES (3, 'TPO 53-38', 2);
