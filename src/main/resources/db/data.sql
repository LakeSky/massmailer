
insert  into USERS (username,password,first_name,last_name) 
values ('pepa','pepajede','Josef', 'Procházka');
insert  into users (username,password,first_name,last_name) 
values ('oliva','olivajede','Olivie', 'Fišerová');

insert  into DATA_SOURCE (NAME,FILE_NAME) 
values ('TEST','filename1');

insert  into DATA_SOURCE (NAME,FILE_NAME) 
values ('TEST1','filename2');

insert  into CAMPAIN (CAMPAIN_NAME,DATASOURCE_ID) 
values ('TEST1','52fcd88844aef19a6f3c74db');

insert  into CAMPAIN (CAMPAIN_NAME,DATASOURCE_ID,STATUS,RECORDS_COUNT) 
values ('TESTSSA','52fcd88844aef19a6f3c74db','READY', 10 );

insert  into CAMPAIN (CAMPAIN_NAME,DATASOURCE_ID,STATUS,RECORDS_COUNT) 
values ('TESTSSAAA','52fcd88844aef19a6f3c74db','SENDING', 123 );

insert  into CAMPAIN (CAMPAIN_NAME,DATASOURCE_ID,STATUS,RECORDS_COUNT) 
values ('TESXXAAAA','52fcd88844aef19a6f3c74db','EDIT',0 );