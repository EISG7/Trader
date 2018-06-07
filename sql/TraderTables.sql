/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/6/5 18:32:02                            */
/*==============================================================*/


drop table if exists "Order";

drop table if exists Product;

drop table if exists Trader;

/*==============================================================*/
/* Table: "Orders"                                               */
/*==============================================================*/
create table "Orders"
(
   id                   int not null,
   broker               varchar(20) not null,
   code                 varchar(8) not null,
   product              varchar(16) not null,
   period               varchar(16) not null,
   price                int not null,
   quantity             int not null,
   initiatorName        varchar(20) not null,
   initiatorComp        varchar(20) not null,
   initiatorSide        bool not null,
   completionName       varchar(20),
   completionComp       varchar(20),
   completionTime       timestamp,
   primary key (id)
);

/*==============================================================*/
/* Table: Product                                               */
/*==============================================================*/
create table Product
(
   id                   int not null,
   broker               varchar(20) not null,
   code                 varchar(8) not null,
   name                 varchar(16) not null,
   period               varchar(16) not null,
   measurement          varchar(8) not null,
   level                int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Trader                                                */
/*==============================================================*/
create table Trader
(
   id                   int not null,
   name                 varchar(20) not null,
   password             varchar(32) not null,
   primary key (id)
);

