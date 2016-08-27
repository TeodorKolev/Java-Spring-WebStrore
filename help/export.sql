--------------------------------------------------------
--  File created - Събота-Април-09-2016   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence STORE_CUSTOMER_ID
--------------------------------------------------------

   CREATE SEQUENCE  "STORE_CUSTOMER_ID"  MINVALUE 1 MAXVALUE 1000 INCREMENT BY 1 START WITH 19 NOCACHE  NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence STORE_ORDER_ID
--------------------------------------------------------

   CREATE SEQUENCE  "STORE_ORDER_ID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence STORE_PRODUCT_ID
--------------------------------------------------------

   CREATE SEQUENCE  "STORE_PRODUCT_ID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 101 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence STORE_USER_ID
--------------------------------------------------------

   CREATE SEQUENCE  "STORE_USER_ID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Table STORE_CUSTOMER
--------------------------------------------------------

  CREATE TABLE "STORE_CUSTOMER" ("CUSTOMER_ID" NUMBER, "NAME" VARCHAR2(50), "PERSONAL_ID" NUMBER, "ADDRESS" VARCHAR2(100), "BIRTH_DATE" DATE, "ID_USER" NUMBER, "ID_STATUS" NUMBER)
--------------------------------------------------------
--  DDL for Table STORE_CUSTOMER_STATUS
--------------------------------------------------------

  CREATE TABLE "STORE_CUSTOMER_STATUS" ("C_STATUS_ID" NUMBER, "NAME" VARCHAR2(20))
--------------------------------------------------------
--  DDL for Table STORE_ORDER
--------------------------------------------------------

  CREATE TABLE "STORE_ORDER" ("ORDER_ID" NUMBER, "ID_PRODUCT" NUMBER, "QUANTITY" NUMBER, "ID_CUSTOMER" NUMBER, "ID_USER" NUMBER, "PURCHASE_DATE" DATE, "ID_STATUS" NUMBER)
--------------------------------------------------------
--  DDL for Table STORE_ORDER_STATUS
--------------------------------------------------------

  CREATE TABLE "STORE_ORDER_STATUS" ("O_STATUS_ID" NUMBER, "NAME" VARCHAR2(20))
--------------------------------------------------------
--  DDL for Table STORE_PRODUCT
--------------------------------------------------------

  CREATE TABLE "STORE_PRODUCT" ("PRODUCT_ID" NUMBER, "NAME" VARCHAR2(50), "TYPE" VARCHAR2(20), "PRICE" NUMBER, "QUANTITY" NUMBER(10,2))
--------------------------------------------------------
--  DDL for Table STORE_USER
--------------------------------------------------------

  CREATE TABLE "STORE_USER" ("USER_ID" NUMBER, "NAME" VARCHAR2(50), "PASSWORD" VARCHAR2(80), "ID_STATUS" NUMBER, "ID_ROLE" NUMBER, "USERNAME" VARCHAR2(20))
--------------------------------------------------------
--  DDL for Table STORE_USER_ROLE
--------------------------------------------------------

  CREATE TABLE "STORE_USER_ROLE" ("U_ROLE_ID" NUMBER, "NAME" VARCHAR2(20))
--------------------------------------------------------
--  DDL for Table STORE_USER_STATUS
--------------------------------------------------------

  CREATE TABLE "STORE_USER_STATUS" ("U_STATUS_ID" NUMBER, "NAME" VARCHAR2(20))
REM INSERTING into STORE_CUSTOMER
SET DEFINE OFF;
Insert into STORE_CUSTOMER (CUSTOMER_ID,NAME,PERSONAL_ID,ADDRESS,BIRTH_DATE,ID_USER,ID_STATUS) values (2,'Andrea G. Cortez',59483744,'136 Malhom Str. Western Virginia',to_date('1956-04-22','RRRR-MM-DD'),1,1);
Insert into STORE_CUSTOMER (CUSTOMER_ID,NAME,PERSONAL_ID,ADDRESS,BIRTH_DATE,ID_USER,ID_STATUS) values (1,'John N. Miller',56555555,'Str. Christ, Chicago',to_date('1934-04-15','RRRR-MM-DD'),1,1);
Insert into STORE_CUSTOMER (CUSTOMER_ID,NAME,PERSONAL_ID,ADDRESS,BIRTH_DATE,ID_USER,ID_STATUS) values (3,'Marta C. Hall',28322456,'Queens NY 100001',to_date('1978-04-14','RRRR-MM-DD'),1,1);
Insert into STORE_CUSTOMER (CUSTOMER_ID,NAME,PERSONAL_ID,ADDRESS,BIRTH_DATE,ID_USER,ID_STATUS) values (18,'Christena Scoville',56567754,'List Avenu 134 Ltg. NY',to_date('1974-04-08','RRRR-MM-DD'),1,1);
Insert into STORE_CUSTOMER (CUSTOMER_ID,NAME,PERSONAL_ID,ADDRESS,BIRTH_DATE,ID_USER,ID_STATUS) values (17,'Donald K. Tewksbury',155654789,'3546 Hillside Street',to_date('1956-04-16','RRRR-MM-DD'),1,1);
Insert into STORE_CUSTOMER (CUSTOMER_ID,NAME,PERSONAL_ID,ADDRESS,BIRTH_DATE,ID_USER,ID_STATUS) values (16,'Nancy C. Crawford',12424252,'3286 Virginia Street',to_date('1982-04-05','RRRR-MM-DD'),1,2);
REM INSERTING into STORE_CUSTOMER_STATUS
SET DEFINE OFF;
Insert into STORE_CUSTOMER_STATUS (C_STATUS_ID,NAME) values (1,'ACTIVE');
Insert into STORE_CUSTOMER_STATUS (C_STATUS_ID,NAME) values (2,'INACTIVE');
REM INSERTING into STORE_ORDER
SET DEFINE OFF;
Insert into STORE_ORDER (ORDER_ID,ID_PRODUCT,QUANTITY,ID_CUSTOMER,ID_USER,PURCHASE_DATE,ID_STATUS) values (44,48,1,2,1,to_date('2016-04-09','RRRR-MM-DD'),1);
Insert into STORE_ORDER (ORDER_ID,ID_PRODUCT,QUANTITY,ID_CUSTOMER,ID_USER,PURCHASE_DATE,ID_STATUS) values (45,48,12,16,7,to_date('2016-04-09','RRRR-MM-DD'),1);
Insert into STORE_ORDER (ORDER_ID,ID_PRODUCT,QUANTITY,ID_CUSTOMER,ID_USER,PURCHASE_DATE,ID_STATUS) values (43,61,2,16,1,to_date('2016-04-09','RRRR-MM-DD'),1);
Insert into STORE_ORDER (ORDER_ID,ID_PRODUCT,QUANTITY,ID_CUSTOMER,ID_USER,PURCHASE_DATE,ID_STATUS) values (46,48,6,17,1,to_date('2016-04-09','RRRR-MM-DD'),2);
Insert into STORE_ORDER (ORDER_ID,ID_PRODUCT,QUANTITY,ID_CUSTOMER,ID_USER,PURCHASE_DATE,ID_STATUS) values (41,48,1,1,1,to_date('2016-04-09','RRRR-MM-DD'),2);
Insert into STORE_ORDER (ORDER_ID,ID_PRODUCT,QUANTITY,ID_CUSTOMER,ID_USER,PURCHASE_DATE,ID_STATUS) values (42,81,2,3,1,to_date('2016-04-09','RRRR-MM-DD'),1);
REM INSERTING into STORE_ORDER_STATUS
SET DEFINE OFF;
Insert into STORE_ORDER_STATUS (O_STATUS_ID,NAME) values (1,'ACTIVE');
Insert into STORE_ORDER_STATUS (O_STATUS_ID,NAME) values (2,'REFUNDED');
REM INSERTING into STORE_PRODUCT
SET DEFINE OFF;
Insert into STORE_PRODUCT (PRODUCT_ID,NAME,TYPE,PRICE,QUANTITY) values (48,'MelodySusie?® High Quality Hair Wig','Hair Wig',4.5,546);
Insert into STORE_PRODUCT (PRODUCT_ID,NAME,TYPE,PRICE,QUANTITY) values (61,'Estes 2274 Recovery Wadding','Powder',13.12,295);
Insert into STORE_PRODUCT (PRODUCT_ID,NAME,TYPE,PRICE,QUANTITY) values (45,'The Bullpen Gospels','Book',16.56,456);
Insert into STORE_PRODUCT (PRODUCT_ID,NAME,TYPE,PRICE,QUANTITY) values (81,'Hyperblue: Sesso E Violenza / Attitude Problem','Music Album',14.65,20);
REM INSERTING into STORE_USER
SET DEFINE OFF;
Insert into STORE_USER (USER_ID,NAME,PASSWORD,ID_STATUS,ID_ROLE,USERNAME) values (1,'Dale Seaman','457391c9c82bfdcbb4947278c0401e41',1,1,'admin');
Insert into STORE_USER (USER_ID,NAME,PASSWORD,ID_STATUS,ID_ROLE,USERNAME) values (7,'Tiffanie Belee','457391c9c82bfdcbb4947278c0401e41',1,2,'user');
Insert into STORE_USER (USER_ID,NAME,PASSWORD,ID_STATUS,ID_ROLE,USERNAME) values (8,'Laverne Francisco','asata',1,2,'eight');
Insert into STORE_USER (USER_ID,NAME,PASSWORD,ID_STATUS,ID_ROLE,USERNAME) values (42,'Giovanni Galeano','457391c9c82bfdcbb4947278c0401e41',2,2,'qqqqq');
Insert into STORE_USER (USER_ID,NAME,PASSWORD,ID_STATUS,ID_ROLE,USERNAME) values (61,'Felisha Villescas','457391c9c82bfdcbb4947278c0401e41',1,2,'felichita09');
Insert into STORE_USER (USER_ID,NAME,PASSWORD,ID_STATUS,ID_ROLE,USERNAME) values (5,'Cody Soni','457391c9c82bfdcbb4947278c0401e41',2,2,'coding');
Insert into STORE_USER (USER_ID,NAME,PASSWORD,ID_STATUS,ID_ROLE,USERNAME) values (41,'Irving Latson','b2ca678b4c936f905fb82f2733f5297f',2,1,'laty');
Insert into STORE_USER (USER_ID,NAME,PASSWORD,ID_STATUS,ID_ROLE,USERNAME) values (2,'Adina Danielson','457391c9c82bfdcbb4947278c0401e41',1,2,'andy123');
REM INSERTING into STORE_USER_ROLE
SET DEFINE OFF;
Insert into STORE_USER_ROLE (U_ROLE_ID,NAME) values (1,'ROLE_ADMIN');
Insert into STORE_USER_ROLE (U_ROLE_ID,NAME) values (2,'ROLE_USER');
REM INSERTING into STORE_USER_STATUS
SET DEFINE OFF;
Insert into STORE_USER_STATUS (U_STATUS_ID,NAME) values (1,'ACTIVE');
Insert into STORE_USER_STATUS (U_STATUS_ID,NAME) values (2,'INACTIVE');
--------------------------------------------------------
--  DDL for Index STORE_CUSTOMER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "STORE_CUSTOMER_PK" ON "STORE_CUSTOMER" ("CUSTOMER_ID")
--------------------------------------------------------
--  DDL for Index STORE_CUSTOMER_STATUS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "STORE_CUSTOMER_STATUS_PK" ON "STORE_CUSTOMER_STATUS" ("C_STATUS_ID")
--------------------------------------------------------
--  DDL for Index STORE_ORDER_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "STORE_ORDER_PK1" ON "STORE_ORDER" ("ORDER_ID")
--------------------------------------------------------
--  DDL for Index STORE_ORDER_STATUS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "STORE_ORDER_STATUS_PK" ON "STORE_ORDER_STATUS" ("O_STATUS_ID")
--------------------------------------------------------
--  DDL for Index STORE_PRODUCT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "STORE_PRODUCT_PK" ON "STORE_PRODUCT" ("PRODUCT_ID")
--------------------------------------------------------
--  DDL for Index STORE_USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "STORE_USER_PK" ON "STORE_USER" ("USER_ID")
--------------------------------------------------------
--  DDL for Index STORE_USER_ROLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "STORE_USER_ROLE_PK" ON "STORE_USER_ROLE" ("U_ROLE_ID")
--------------------------------------------------------
--  DDL for Index STORE_USER_STATUS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "STORE_USER_STATUS_PK" ON "STORE_USER_STATUS" ("U_STATUS_ID")
--------------------------------------------------------
--  Constraints for Table STORE_CUSTOMER
--------------------------------------------------------

  ALTER TABLE "STORE_CUSTOMER" MODIFY ("ADDRESS" NOT NULL ENABLE)
  ALTER TABLE "STORE_CUSTOMER" MODIFY ("ID_STATUS" NOT NULL ENABLE)
  ALTER TABLE "STORE_CUSTOMER" MODIFY ("ID_USER" NOT NULL ENABLE)
  ALTER TABLE "STORE_CUSTOMER" ADD CONSTRAINT "STORE_CUSTOMER_ID" PRIMARY KEY ("CUSTOMER_ID") ENABLE
  ALTER TABLE "STORE_CUSTOMER" MODIFY ("PERSONAL_ID" NOT NULL ENABLE)
  ALTER TABLE "STORE_CUSTOMER" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "STORE_CUSTOMER" MODIFY ("CUSTOMER_ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table STORE_CUSTOMER_STATUS
--------------------------------------------------------

  ALTER TABLE "STORE_CUSTOMER_STATUS" ADD CONSTRAINT "STORE_CUSTOMER_STATUS_PK" PRIMARY KEY ("C_STATUS_ID") ENABLE
  ALTER TABLE "STORE_CUSTOMER_STATUS" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "STORE_CUSTOMER_STATUS" MODIFY ("C_STATUS_ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table STORE_ORDER
--------------------------------------------------------

  ALTER TABLE "STORE_ORDER" MODIFY ("ID_STATUS" NOT NULL ENABLE)
  ALTER TABLE "STORE_ORDER" ADD CONSTRAINT "STORE_ORDER_PK" PRIMARY KEY ("ORDER_ID") ENABLE
  ALTER TABLE "STORE_ORDER" MODIFY ("ID_USER" NOT NULL ENABLE)
  ALTER TABLE "STORE_ORDER" MODIFY ("ID_CUSTOMER" NOT NULL ENABLE)
  ALTER TABLE "STORE_ORDER" MODIFY ("QUANTITY" NOT NULL ENABLE)
  ALTER TABLE "STORE_ORDER" MODIFY ("ID_PRODUCT" NOT NULL ENABLE)
  ALTER TABLE "STORE_ORDER" MODIFY ("ORDER_ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table STORE_ORDER_STATUS
--------------------------------------------------------

  ALTER TABLE "STORE_ORDER_STATUS" ADD CONSTRAINT "STORE_ORDER_STATUS_PK" PRIMARY KEY ("O_STATUS_ID") ENABLE
  ALTER TABLE "STORE_ORDER_STATUS" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "STORE_ORDER_STATUS" MODIFY ("O_STATUS_ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table STORE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "STORE_PRODUCT" ADD CONSTRAINT "STORE_PRODUCT_PK" PRIMARY KEY ("PRODUCT_ID") ENABLE
  ALTER TABLE "STORE_PRODUCT" MODIFY ("QUANTITY" NOT NULL ENABLE)
  ALTER TABLE "STORE_PRODUCT" MODIFY ("PRICE" NOT NULL ENABLE)
  ALTER TABLE "STORE_PRODUCT" MODIFY ("TYPE" NOT NULL ENABLE)
  ALTER TABLE "STORE_PRODUCT" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "STORE_PRODUCT" MODIFY ("PRODUCT_ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table STORE_USER
--------------------------------------------------------

  ALTER TABLE "STORE_USER" MODIFY ("USERNAME" NOT NULL ENABLE)
  ALTER TABLE "STORE_USER" MODIFY ("ID_ROLE" NOT NULL ENABLE)
  ALTER TABLE "STORE_USER" ADD CONSTRAINT "STORE_USER_PK" PRIMARY KEY ("USER_ID") ENABLE
  ALTER TABLE "STORE_USER" MODIFY ("ID_STATUS" NOT NULL ENABLE)
  ALTER TABLE "STORE_USER" MODIFY ("PASSWORD" NOT NULL ENABLE)
  ALTER TABLE "STORE_USER" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "STORE_USER" MODIFY ("USER_ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table STORE_USER_ROLE
--------------------------------------------------------

  ALTER TABLE "STORE_USER_ROLE" ADD CONSTRAINT "STORE_USER_ROLE_PK" PRIMARY KEY ("U_ROLE_ID") ENABLE
  ALTER TABLE "STORE_USER_ROLE" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "STORE_USER_ROLE" MODIFY ("U_ROLE_ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table STORE_USER_STATUS
--------------------------------------------------------

  ALTER TABLE "STORE_USER_STATUS" ADD CONSTRAINT "STORE_USER_STATUS_PK" PRIMARY KEY ("U_STATUS_ID") ENABLE
  ALTER TABLE "STORE_USER_STATUS" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "STORE_USER_STATUS" MODIFY ("U_STATUS_ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Ref Constraints for Table STORE_CUSTOMER
--------------------------------------------------------

  ALTER TABLE "STORE_CUSTOMER" ADD CONSTRAINT "STORE_CUSTOMER_STATUS" FOREIGN KEY ("ID_STATUS") REFERENCES "STORE_CUSTOMER_STATUS" ("C_STATUS_ID") ENABLE
  ALTER TABLE "STORE_CUSTOMER" ADD CONSTRAINT "STORE_CUSTOMER_USER" FOREIGN KEY ("ID_USER") REFERENCES "STORE_USER" ("USER_ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table STORE_ORDER
--------------------------------------------------------

  ALTER TABLE "STORE_ORDER" ADD CONSTRAINT "STORE_ORDER_CUSTOMER_ID" FOREIGN KEY ("ID_CUSTOMER") REFERENCES "STORE_CUSTOMER" ("CUSTOMER_ID") ENABLE
  ALTER TABLE "STORE_ORDER" ADD CONSTRAINT "STORE_ORDER_PRODUCT_ID" FOREIGN KEY ("ID_PRODUCT") REFERENCES "STORE_PRODUCT" ("PRODUCT_ID") ENABLE
  ALTER TABLE "STORE_ORDER" ADD CONSTRAINT "STORE_ORDER_STATUS_ID" FOREIGN KEY ("ID_STATUS") REFERENCES "STORE_ORDER_STATUS" ("O_STATUS_ID") ENABLE
  ALTER TABLE "STORE_ORDER" ADD CONSTRAINT "STORE_ORDER_USER_ID" FOREIGN KEY ("ID_USER") REFERENCES "STORE_USER" ("USER_ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table STORE_USER
--------------------------------------------------------

  ALTER TABLE "STORE_USER" ADD CONSTRAINT "STORE_USER_ROLE" FOREIGN KEY ("ID_ROLE") REFERENCES "STORE_USER_ROLE" ("U_ROLE_ID") ENABLE
  ALTER TABLE "STORE_USER" ADD CONSTRAINT "STORE_USER_STATUS" FOREIGN KEY ("ID_STATUS") REFERENCES "STORE_USER_STATUS" ("U_STATUS_ID") ENABLE
