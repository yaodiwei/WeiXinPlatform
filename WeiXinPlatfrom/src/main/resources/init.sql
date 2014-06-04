/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   Openid            	varchar not null,
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
   MsgId		        int not null,
   Content      		varchar not null,
   CreateTime           int not null,
   UserId             	varchar not null,
   primary key (MsgId)
);




/*==============================================================*/
/* Table: Operator                                              */
/*==============================================================*/
create table Operator
(
   OperatorCode         varchar(50) not null,
   Password             varchar(50) not null,
   primary key (OperatorCode)
);

alter table Operator comment '操作员表';

/*==============================================================*/
/* Table: Subscription                                          */
/*==============================================================*/
create table Subscription
(
   UserCode             varchar(50) not null,
   CompetitionId        int not null,
   SeasonIds            varchar(500) not null,
   SeasonNames          varchar(500) not null,
   primary key (UserCode, CompetitionId)
);

alter table Subscription comment '订阅表';



/*==============================================================*/
/* Table: Website                                               */
/*==============================================================*/
create table Website
(
   WebsiteId            int not null,
   WebsiteURL           varchar(256) not null,
   Package              longblob not null,
   primary key (WebsiteId)
);

alter table Website comment '权限网址';

alter table Subscription add constraint FK_Reference_2 foreign key (CompetitionId)
      references Competition (CompetitionId) on delete restrict on update restrict;

alter table Subscription add constraint FK_Reference_3 foreign key (UserCode)
      references User (UserCode) on delete restrict on update restrict;

