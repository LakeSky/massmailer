DROP TABLE USERS;

CREATE TABLE USERS (
  id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  username varchar(100) NOT NULL,
  password varchar(100) NOT NULL,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL);
DROP TABLE DATA_SOURCE;

CREATE TABLE DATA_SOURCE  ( 
    ID           	INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL,
    NAME         	VARCHAR(255) NOT NULL,
    FILE_NAME    	VARCHAR(255),
    RECORDS_COUNT	INTEGER,
    CONSTRAINT datasource_px PRIMARY KEY(ID)
);
DROP TABLE DATA_STRUCTURE_FIELDS;
DROP TABLE DATA_STRUCTURE;

CREATE TABLE DATA_STRUCTURE  ( 
    ID              	INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL,
    FIRST_ROW_CNAMES	SMALLINT,
    CONSTRAINT datastructure_px PRIMARY KEY(ID)
);



CREATE TABLE "DATA_STRUCTURE_FIELDS"  ( 
	"ID"               	INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
	"FILE_FNAME"             	VARCHAR(255) NOT NULL,
	"DATA_SOURCE_FNAME"            	VARCHAR(255) NOT NULL,
	"IS_ID"            	SMALLINT,
	"DATA_STRUCTURE_ID"	INTEGER NOT NULL,
	"INDEX"             	INTEGER NOT NULL,
	"TYPE"             	VARCHAR(25),
	CONSTRAINT "datastructure_fields_px" PRIMARY KEY("ID"),
   CONSTRAINT "datastructure_fk"
	FOREIGN KEY("DATA_STRUCTURE_ID")
	REFERENCES "DATA_STRUCTURE"("ID")
);


