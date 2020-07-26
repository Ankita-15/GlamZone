
drop view Delivery_Detail;
drop table On_Rent;
drop table Seller;
drop table Customer;
drop view Sign_In;
drop table Items;
drop table Contact_us;


create table Customer(c_name  varchar2(20),c_add varchar2(50),p_no number(10),email varchar2(20) primary key,pass varchar2(10));
insert into Customer values('mansi','jaipur,rajasthan',5894586475,'mansi@gmail.com','1212mM!!');
insert into Customer values('saroj','ranchi,jharkhand',1589466344,'saroj@gmail.com','1212mM!!');
insert into Customer values('alka','gorakhpur,UP',8459612368,'alka@gmail.com','1212mM!!');
insert into Customer values('pragya','kanpur,UP',6947516485,'pragya@gmail.com','1212mM!!');


create table Items(i_id varchar2(10)  primary key,i_name varchar2(255),i_color varchar2(100),i_size varchar2(25),fabric_description varchar2(10),ctn_money number(10),c_p_day number(10),alteration varchar(3),availabilty number(1));   
insert into Items values('im1001','kurta','black','m','cotton',1000,100,'yes',1);
insert into Items values('im2005','formal','blue','s','silk',1500,150,'no',0);
insert into Items values('iw1001','saree','black','free','polyester',2000,100,'yes',1);

create table On_Rent(email varchar2(20) primary key,i_id  varchar2(10),book_date date,ret_date date,durtn number(10));
insert into On_Rent values('mansi@gmail.com','im1001','10-oct-19','12-oct-19',3);
insert into On_Rent values('alka@gmail.com','iw1001','14-dec-19','19-dec-19',6);


create table Seller(email varchar2(200) primary key,s_name varchar2(100),i_name  varchar2(100),i_color  varchar2(100),i_size varchar2(100),cat varchar2(200),p_no number(10));
-- insert into Seller values('mm@gmail','ankita','saree','red n blue','free','w');
-- insert into Seller values('alka@gmail,'kurti','blue','l','m');

create table Contact_us(c_name varchar2(20),email varchar2(20),p_no number(10),message varchar2(500));
insert into Contact_us values('mansi','mansi@gmail.com',5894586475,'we would like if u provide kids wear also, otherwise its an awesome colection');



create view Delivery_Detail as select c.c_name,c.c_add,rnt.i_id,rnt.book_date,rnt.ret_date,(rnt.durtn*i.c_p_day) as act_cost,((rnt.durtn*i.c_p_day)+i.ctn_money) as due_amt,i.ctn_money as refund_amt from On_Rent rnt inner join Customer c on c.email=rnt.email inner join Items i on rnt.i_id=i.i_id;

create view Sign_In as select email, pass from Customer;

Select * from Delivery_Detail;
Select * from Sign_In;
