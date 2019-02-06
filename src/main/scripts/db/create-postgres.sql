-- Assumes the database already exists. The spring app uses the database name `appservspringbank`,
-- create such a database before running this script. Run the script with the command
-- mysql DB_IV1201 < src/scripts/db/create-postgres.sql -p


DROP TABLE IF EXISTS `CONVERSION3`;

--
-- Create for table `CONVERSION`
--
CREATE TABLE `CONVERSION3`
( `CON_FROMTO` varchar(50) NOT NULL,
  `CON_RATE`   double(50) NOT NULL,
  `CON_COUNT` int (10) NOT NULL,
  PRIMARY KEY(`CON_FROMTO`));