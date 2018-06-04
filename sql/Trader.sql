/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/6/4 21:49:46                            */
/*==============================================================*/


drop table if exists Trader;

/*==============================================================*/
/* Table: Trader                                                */
/*==============================================================*/
create table Trader
(
   id                   int not null,
   name                 varchar(20) not null,
   password             varchar(20) not null,
   primary key (id)
);

