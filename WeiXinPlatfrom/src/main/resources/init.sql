/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   openid            	varchar not null,
   nickname             varchar not null,
   sex           		int not null,
   country           	varchar not null,
   province             varchar not null,
   city               	varchar not null,
   headimgurl           varchar not null,
   subscribe_time       int not null,
   primary key (Openid)
);



/*==============================================================*/
/* Table: Text                                           */
/*==============================================================*/
create table Text
(
   msgId		        int not null,
   content      		varchar not null,
   createTime           int not null,
   userId             	varchar not null,
   primary key (MsgId)
);




/*==============================================================*/
/* Table: Operator                                              */
/*==============================================================*/
create table Operator
(
   operatorid	        varchar(50) not null,
   password             varchar(50) not null,
   primary key (OperatorCode)
);

alter table Operator comment '操作员表';




alter table Website comment '权限网址';

alter table Subscription add constraint FK_Reference_2 foreign key (CompetitionId)
      references Competition (CompetitionId) on delete restrict on update restrict;

alter table Subscription add constraint FK_Reference_3 foreign key (UserCode)
      references User (UserCode) on delete restrict on update restrict;

