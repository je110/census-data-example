DROP TABLE IF EXISTS census;

CREATE TABLE census  (
   id INT AUTO_INCREMENT PRIMARY KEY,
   zip VARCHAR(20),
   population INT,
   medianAge FLOAT,
   totalMales INT,
   totalFemales INT,
   totalHouseholds INT,
   averageHousehold FLOAT,
   INDEX (population),
   INDEX (medianAge),
   INDEX (totalMales, totalFemales)
);