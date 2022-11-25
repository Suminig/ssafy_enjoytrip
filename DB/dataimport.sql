LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/spotdata.csv'
INTO TABLE spot
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/do.csv'
INTO TABLE sido
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/gugun.csv'
INTO TABLE gugun
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

create table temp(
	descid int,
    contentid int, 
    descr mediumtext
);

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/description.csv'
INTO TABLE temp
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

create view dup as select b.contentid from spot as a right join temp as b on a.contentid = b.contentid where a.contentid is null;

select * from temp where contentid not in(select * from dup);

insert into description select * from (select * from temp where contentid not in(select * from dup))as A;

drop table temp;
drop view dup;

select * from spot; 
select * from sido;
select * from gugun;
select *from description;