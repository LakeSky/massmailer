
insert  into USERS (username,password,first_name,last_name) 
values ('pepa','pepajede','Josef', 'Procházka');
insert  into users (username,password,first_name,last_name) 
values ('oliva','olivajede','Olivie', 'Fišerová');

insert  into DATA_SOURCE (NAME,FILE_NAME) 
values ('TEST','filename1');

insert  into DATA_SOURCE (NAME,FILE_NAME) 
values ('TEST1','filename2');

insert  into CAMPAIN (CAMPAIN_NAME,DATASOURCE_ID) 
values ('TEST1','dasourcessdad');

insert  into CAMPAIN (CAMPAIN_NAME,DATASOURCE_ID,STATUS,RECORDS_COUNT) 
values ('TESTSSA','dasourcesasdassdad','READY', 10 );

insert  into CAMPAIN (CAMPAIN_NAME,DATASOURCE_ID,STATUS,RECORDS_COUNT) 
values ('TESTSSAAA','dasourcesasdassdad','SENDING', 123 );

insert  into CAMPAIN (CAMPAIN_NAME,DATASOURCE_ID,STATUS,RECORDS_COUNT) 
values ('TESXXAAAA','dasouzsrcesasdassdad','EDIT',0 );