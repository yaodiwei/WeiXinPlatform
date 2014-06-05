/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/6/5 18:43:13                            */
/*==============================================================*/


drop table if exists Operator;

drop table if exists Text;

drop table if exists User;

/*==============================================================*/
/* Table: Operator                                              */
/*==============================================================*/
create table Operator
(
   operatorid           varchar not null,
   loginname            varchar not null,
   password             varchar not null,
   primary key (operatorid)
);

/*==============================================================*/
/* Table: Text                                                  */
/*==============================================================*/
create table Text
(
   msgid                int not null,
   content              varchar not null,
   createtime           int not null,
   userid               varchar not null,
   openid               varchar,
   operatorid           varchar,
   primary key (msgid)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   openid               varchar not null,
   nikename             varchar not null,
   sex                  int not null,
   country              varchar not null,
   province             varchar not null,
   city                 varchar not null,
   headimgurl           varchar not null,
   subscribe_time       int not null,
   primary key (openid)
);

alter table Text add constraint FK_Reference_1 foreign key (openid)
      references User (openid) on delete restrict on update restrict;

alter table Text add constraint FK_Reference_2 foreign key (operatorid)
      references Operator (operatorid) on delete restrict on update restrict;

