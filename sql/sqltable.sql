CREATE TABLE board (
  boardid bigint(30) unsigned not null auto_increment primary key ,
  nickname varchar(20) not null,
  title varchar(50) not null ,
  content text,
  regdate datetime,
  password varchar(15)
);

CREATE TABLE comment(
  commentid bigint(30) unsigned not null auto_increment primary key,
  nickname varchar(20) not null,
  content text not null,
  regdate datetime not null,
  password varchar(15) not null ,
  boardid bigint(30) unsigned not null,
  FOREIGN KEY (boardid) REFERENCES board (boardid)
);

alter table board modify regdate timestamp DEFAULT CURRENT_TIMESTAMP;
alter table comment modify regdate timestamp DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE board ADD view int NOT NULL DEFAULT 0;

alter table comment add constraint comment_ibfk_1 foreign key (boardid) references board (boardid) on delete cascade on update  cascade;