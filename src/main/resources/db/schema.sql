DROP TABLE USERS;


DROP TABLE EMAIL;
DROP TABLE EMAIL_CONTENT;
DROP TABLE EMAIL_FOLDER;

CREATE TABLE USERS (
  id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  username varchar(100) NOT NULL,
  password varchar(100) NOT NULL,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL);

DROP TABLE DATA_SOURCE;
DROP TABLE DATA_STRUCTURE_FIELDS;
DROP TABLE DATA_STRUCTURE;

CREATE TABLE DATA_STRUCTURE  ( 
    ID              	INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL,
    FIRST_ROW_CNAMES	SMALLINT,
    CONSTRAINT datastructure_px PRIMARY KEY(ID)
);

CREATE TABLE DATA_SOURCE  ( 
    ID           	INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL,
    NAME         	VARCHAR(255) NOT NULL,
    FILE_NAME    	VARCHAR(255),
    RECORDS_COUNT	INTEGER,
    DATA_STRUCTURE_ID	INTEGER,
    CONSTRAINT datasource_px PRIMARY KEY(ID),
    FOREIGN KEY("DATA_STRUCTURE_ID")
    REFERENCES "DATA_STRUCTURE"("ID")
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


DROP TABLE CAMPAIN;

CREATE TABLE "CAMPAIN"  ( 
	"ID"               	INTEGER  GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)  NOT NULL,
	"CAMPAIN_NAME"            	VARCHAR(255) ,
	"RECIPIENTS"            	VARCHAR(255) ,
	"CC_RECIPIENTS"            	VARCHAR(255) ,
	"BCC_RECIPIENTS"            	VARCHAR(255) ,
	"SUBJECT"            	VARCHAR(255) ,
	"EMAIL_TEXT"             	CLOB  ,
	"DATASOURCE_ID"            	VARCHAR(255) ,
	"CUSTOMIZE_EMAIL"            	SMALLINT,
	"STATUS"            	VARCHAR(255) ,
	"RECORDS_COUNT"          DECIMAL(10,5) ,
	"RECORDS_SENT"          DECIMAL(10,5) ,
	CONSTRAINT "campain_px" PRIMARY KEY("ID")
);





DROP TABLE EMAIL_STATUS;

CREATE TABLE "EMAIL_STATUS"  ( 
	"ID"               	INTEGER  GENERATED ALWAYS AS IDENTITY NOT NULL,
	"STATUS"            	VARCHAR(255),
	CONSTRAINT "emailst_px" PRIMARY KEY("ID")

);
DROP TABLE EMAIL_EMAIL_FOLDER;
CREATE TABLE "EMAIL_FOLDER"  ( 
	"ID"               	INTEGER  GENERATED ALWAYS AS IDENTITY NOT NULL,
	"NAME"            	VARCHAR(255) ,
         "PARENT_FOLDER"          INTEGER ,
	CONSTRAINT "emailfd_px" PRIMARY KEY("ID")
);


DROP TABLE CAMPAIN_ATTACHMENT;

CREATE TABLE "CAMPAIN_ATTACHMENT"  ( 
	"ID"               	INTEGER  GENERATED ALWAYS AS IDENTITY NOT NULL,
        "INDEX"               	INTEGER,
	"ATTACHMENT"            	BLOB ,
	"ATTACHMENT_NAME"            	VARCHAR(255),
        "ATTACHMENT_FS_NAME"            	VARCHAR(255),
	"ATTACHMENT_FILE_TYPE"            	VARCHAR(255),
	"ATTACHMENT_OUTPUT_NAME"            	VARCHAR(255),
	"ATTACHMENT_OUTPUT_TYPE"            	VARCHAR(255),
	"CUSTOMIZE_ATTACHMENT"            	SMALLINT,
	"SENT_ATTACHMENT_AS"            	VARCHAR(255) ,
	"CAMPAIN_ID"	INTEGER,
	CONSTRAINT "campainattachment_px" PRIMARY KEY("ID"),
        CONSTRAINT "attacampain_fk"
        FOREIGN KEY("CAMPAIN_ID")
        REFERENCES "CAMPAIN"("ID")
);



DROP TABLE EMAIL;

CREATE TABLE "EMAIL"  ( 
	"ID"               	INTEGER  GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)  NOT NULL,
	"MESSAGE_ID"            	VARCHAR(255) ,
	"FROM_EMAIL"            	VARCHAR(255) ,
	"RECIPIENTS"            	VARCHAR(255) ,
	"CC_RECIPIENTS"            	VARCHAR(255) ,
	"BCC_RECIPIENTS"            	VARCHAR(255) ,
	"SUBJECT"            	VARCHAR(255) ,
        "EMAIL_CONTENT"         CLOB,
        "CAMPAIN_ID"          INTEGER  ,
        "EMAIL_FOLDER"          INTEGER  ,
	"READY_TO_SENT"            	SMALLINT,
	"SENT_DATE"            	DATE ,
        "STATUS_DATE"            	DATE ,
        "EMAIL_STATUS"          VARCHAR(255)  ,
	CONSTRAINT "email_px" PRIMARY KEY("ID"),
        CONSTRAINT "emailfolder_fk"
	FOREIGN KEY("EMAIL_FOLDER")
	REFERENCES "EMAIL_FOLDER"("ID"),
        CONSTRAINT "emailcampain_fk"
	FOREIGN KEY("CAMPAIN_ID")
	REFERENCES "CAMPAIN"("ID")
 

);


DROP TABLE ATTACHMENT;

CREATE TABLE "ATTACHMENT"  ( 
	"ID"               	INTEGER  GENERATED ALWAYS AS IDENTITY NOT NULL,
	"ATTACHMENT"            	BLOB ,
	"ATTACHMENT_NAME"            	VARCHAR(255),
	"ATTACHMENT_FILE_TYPE"            	VARCHAR(255),
	"EMAIL_ID"	INTEGER NOT NULL,
	CONSTRAINT "attachment_px" PRIMARY KEY("ID"),
        CONSTRAINT "attachmentemail_fk"
        FOREIGN KEY("EMAIL_ID")
        REFERENCES "EMAIL"("ID")
);



DROP TABLE FILES;

CREATE TABLE "FILES"  ( 
	"ID"               	INTEGER  GENERATED ALWAYS AS IDENTITY NOT NULL,
	"NAME"            	VARCHAR(255),
	"TYPE"            	VARCHAR(255),
	"FILE"            BLOB ,
	CONSTRAINT "files_px" PRIMARY KEY("ID")

);
