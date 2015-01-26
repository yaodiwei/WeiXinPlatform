/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/8/25 1:11:20                            */
/*==============================================================*/


drop table if exists Operator;

drop table if exists Passive;

drop table if exists Text;

drop table if exists User;

/*==============================================================*/
/* Table: Operator                                              */
/*==============================================================*/
create table Operator
(
   operatorid           varchar(50) not null,
   appid                varchar(50) not null,
   appsecret            varchar(50) not null,
   loginname            varchar(50) not null,
   password             varchar(50) not null,
   primary key (operatorid)
);

/*==============================================================*/
/* Table: Passive                                               */
/*==============================================================*/
create table Passive
(
   operatorid           varchar(50) not null,
   name                 varchar(255) not null,
   value                varchar(21537) not null,
   enable               int not null,
   primary key (name, operatorid)
);

/*==============================================================*/
/* Table: Text                                                  */
/*==============================================================*/
create table Text
(
   msgid                varchar(50) not null,
   content              varchar(21691) not null,
   createtime           int not null,
   openid               varchar(50),
   operatorid           varchar(50),
   primary key (msgid)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   openid               varchar(50) not null,
   nickname             varchar(50) not null,
   sex                  int not null,
   country              varchar(50) not null,
   province             varchar(50) not null,
   city                 varchar(50) not null,
   headimgurl           varchar(255) not null,
   subscribetime        int not null,
   primary key (openid)
);

alter table Passive add constraint FK_Reference_2 foreign key (operatorid)
      references Operator (operatorid) on delete restrict on update restrict;

alter table Text add constraint FK_Reference_1 foreign key (openid)
      references User (openid) on delete restrict on update restrict;

alter table Text add constraint FK_Reference_3 foreign key (operatorid)
      references Operator (operatorid) on delete restrict on update restrict;

 

insert into Operator values ('gh_e05a6414704b', 'wx67a9d38ff7e2a347', '8e9bfbf5623ceece8120b5ca0a9ff135', 'yao', '123');
alter table Passive modify column value varchar(21537) not null;
alter table Passive add column enable int not null;