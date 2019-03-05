drop table VOLUNTEER cascade constraints;
drop table SERVE_MONTH_HOUR cascade constraints;
drop table SERVE cascade constraints;
drop table REPORT cascade constraints;
drop table PERSON_DONATION_CHECK cascade constraints;
drop table PERSON_DONATION_CARD cascade constraints;
drop table ORGANIZATION_DONATION_CREDIT cascade constraints;
drop table NEED cascade constraints;
drop table LEAD cascade constraints;
drop table HAS_INSURANCE cascade constraints;
drop table EXPENSE cascade constraints;
drop table EMPLOYEE cascade constraints;
drop table DONOR cascade constraints;
drop table CLIENT cascade constraints;
drop table CARE cascade constraints;
drop table AFFILIATE cascade constraints;
drop table Person cascade constraints;
drop table SPONSOR cascade constraints;
drop table ORGANIZATION_DONATION_CHECK cascade constraints;
drop table ORGANIZATION cascade constraints;
drop table INSURANCE_POLICY cascade constraints;
drop table EMERGENCY_CONTACT cascade constraints;
drop table CHURCHU cascade constraints;
drop table BUSSINESS cascade constraints;
drop table TEAM cascade constraints;
drop table ORGANIZATION_DONATION_CARD cascade constraints;

create table person(
    SSN varchar2(9),
    name varchar2(20),
    birth_date varchar2(20),
    race varchar2(10),
    gender varchar2(5),
    profession varchar2(15),
    mailing_address varchar2(30),
    email_address varchar2(20),
    home varchar2(20),
    work varchar2(20),
    cell_phone varchar2(20),
    is_on_mailing_list varchar2(3),
    primary key(SSN)
);
create table emergency_contact(
    SSN     varchar2 (9),
    name    varchar2(20),
    contact_information varchar2(20),
    relationship    varchar2(10),
    primary key(SSN,name,contact_information,relationship),
    foreign key(SSN) references person
);
create table volunteer(
    SSN     varchar2(9),
    date_joined varchar2(10),
    date_of_training varchar2(10),
    location varchar2(20),
    primary key(SSN),
    foreign key(SSN)references person
);
create table team(
    name varchar2(20),
    type varchar2(10),
    date_formed varchar2(10),
    primary key(name)
);
create table lead(
    SSN varchar2(9),
    team_name varchar2(20),
    primary key(SSN),
    foreign key(SSN)references volunteer,
    foreign key(team_name)references team
);

create table client(
    SSN varchar2(9),
    doctor_name varchar2(20),
    doctor_phone_number varchar2(15),
    attorney_name varchar2(20),
    attorney_phone_number varchar2(15),
    data_first_assigned varchar2(10),
    primary key (SSN),
    foreign key(SSN)references person

);
create table need(
    SSN varchar(9),
    type varchar(20),
    importance number(2,0),
    primary key(SSN),
    foreign key(SSN)references client on delete cascade
);
create table insurance_policy(
    policy_id varchar2(20),
    provider_id varchar2(20),
    provider_address varchar2(20),
    type varchar2(10),
    primary key(policy_id)

);
create table has_insurance(
    SSN varchar2(10),
    policy_id varchar2(20),
    primary key(SSN,policy_id),
    foreign key(SSN) references client on delete cascade,
    foreign key(policy_id) references insurance_policy
	

);
create table care(
    team_name, 
    SSN,
    is_active varchar2(5),
    primary key(team_name,SSN),
    foreign key(SSN)references client on delete cascade,
    foreign key(team_name)references team



);
create table serve(
    team_name,
    SSN,
    is_active varchar2(5),
    primary key(team_name,SSN),
    foreign key(team_name)references team,
    foreign key(SSN)references volunteer


);
create table serve_month_hour(
    SSN,
    team_name,
    month varchar2(10),
    hour number(5,2),
    primary key(SSN,team_name,month),
    foreign key(SSN,team_name)references serve(SSN,team_name)
 
);

create table organization(
    name varchar2(20),
    mailing_address varchar2(20),
    phone_number varchar2(15),
    contact_person varchar2(20),
    is_anonymous varchar2(3),
    primary key(name)
);
create table affiliate(
    SSN,
    organization_name,
    primary key(SSN),
    foreign key(SSN)references person,
    foreign key(organization_name)references organization


);
create table churchu(
    name,
    religious_affiliation varchar2(10),
    primary key(name),
    foreign key(name)references organization

);
create table bussiness(
    name,
    type varchar2(10),
    company_size varchar2(10),
    primary key(name),
    foreign key(name) references organization

);
create table sponsor(
    team_name,
    organization_name,
    primary key(team_name,organization_name),
    foreign key(team_name)references team,
    foreign key(organization_name)references organization



);

create table employee(
    SSN,
    salary number(10,2),
    marital_status varchar2(10),
    hire_date varchar2(10),
    primary key(SSN),
    foreign key (SSN)references person

);
create table expense(
    SSN,
    "date" varchar2(10),
    amount number(10,2),
    description varchar2(30),
    primary key(SSN,"date",amount,description),
    foreign key(SSN)references employee

);
create table report(
    team_name,
    SSN,
    "date" varchar2(10),
    description varchar2(20),
    primary key(team_name,SSN),
    foreign key(SSN)references employee,
    foreign key(team_name)references team

);
create table donor(
    SSN,
    is_anonymous varchar2(5),
    primary key(SSN),
    foreign key(SSN)references person


);
create table person_donation_check(
    SSN,
   "date" varchar2(10),
    amount number(10,2),
    type varchar2(10),
    name_of_rasing_campaign varchar2(20),
    check_number varchar2(20),
    primary key(SSN,"date",amount,type,name_of_rasing_campaign,check_number),
    foreign key(SSN)references donor


);
create table person_donation_card(
     SSN,
   "date" varchar2(10),
    amount number(10,2),
    type varchar2(10),
    name_of_rasing_campaign varchar2(20),
    card_number varchar2(20),
    card_type   varchar2(10),
    expiration_date varchar2(10),
     primary key(SSN,"date",amount,type,name_of_rasing_campaign,card_number),
     foreign key(SSN)references donor


);
create table organization_donation_check(
    name,
   "date" varchar2(10),
    amount number(10,2),
    type varchar2(10),
    name_of_rasing_campaign varchar2(20),
    check_number varchar2(20),
    primary key(name,"date",amount,type,name_of_rasing_campaign,check_number),
    foreign key(name)references organization

);
create table organization_donation_card(
    name, "date" varchar2(10),
    amount number(10,2),
    type varchar2(10),
    name_of_rasing_campaign varchar2(20),
    card_number varchar2(20),
    card_type   varchar2(10),
    expiration_date varchar2(10),
     primary key(name,"date",amount,type,name_of_rasing_campaign,card_number),
     foreign key(name)references organization
);
     
-- select 'drop table '||table_name||' cascade constraints;' from user_tables;
-- drop table TEAM cascade constraints;


--insert into team values('cao','ni','da');
--insert into person values('cao','ni','da','sa','sa','sde','ds','dssad','sda',1);
--insert into person values('sd','da','daa','sad','weaw','we','wqe','fdf','sdawe',1);
--insert into person values('cp','jl','jhk','hkj','iu','gjh','dhk','dyti','shkj',1);
--select name, mailing_address from person where if_on_mailing_list = 1;

--Clients
insert into person values('1', 'Zhao', '1000-01-01', 'Han', 'male', 'C1', 'M1', 'EM1', 'H1', 'W1', 'C1', '1');
insert into person values('2', 'Qian', '1000-01-01', '1002-02-02', 'Han', 'male', 'CS2', 'M2', 'EM2', 'H2', 'W2', 'C2', '2');
insert into person values('13', 'XiaoBai', '1000-01-01', 'Han', 'male', 'C1', 'M1', 'EM1', 'H1', 'W1', 'C1', '1');
insert into person values('14', 'XiaoQiang', '1002-02-02', 'Han', 'male', 'CS2', 'M2', 'EM2', 'H2', 'W2', 'C2', '2');
--
--insert into client values('1', 'Dr.Zhao', '1', 'At.Zhao', '11', '111');
--insert into client values('2', 'Dr.Qian', '2', 'At.Qian', '2', '222');
--insert into client values('13', 'Dr.Zhao', '1', 'At.Zhao', '11', '111');
--insert into client values('14', 'Dr.Qian', '2', 'At.Qian', '2', '222');
--
----set insurance policy
insert into insurance_policy values('1', '1', 'address', 'health');
insert into insurance_policy values('2', '1', 'address', 'travel');
--Client 1 has health insurance(No need to delete)
insert into has_insurance values('1', '1');
insert into need values('1', 'transportation', '2');
--Client 2 has no health insurance, but tranportataion is 6, no need to delete
insert into has_insurance values('2', '2');
insert into need values('2', 'transportation', '6');
--Client 13 has health insurance, no need to delete
insert into has_insurance values('13', '1');
insert into need values('13', 'transportation', '3');
--Client 14 has health insurance, need to delete
insert into has_insurance values('14', '2');
insert into need values('14', 'transportation', '3');
--
--
--
----Employee
--insert into person values('3', '1003-03-03', 'Sun', 'Han', 'male', 'CS3', 'M3', 'EM3', 'H3', 'W3', 'C3', '3');
--insert into person values('4', '1004-04-04', 'Li', 'Han', 'male', 'CS4', 'M4', 'EM4', 'H4', 'W4', 'C4', '4');
--insert into employee values('3', '300', '1', '1995-03-03');
--insert into employee values('4', '400', '0', '1995-03-04');
--
----Volunteers
--insert into person values('5', '1005-05-05', 'Zhou', 'Han', 'male', 'CS5', 'M5', 'EM5', 'H5', 'W5', 'C5', '5');
--insert into person values('6', '1006-06-06', 'Wu', 'Han', 'male', 'CS6', 'M6', 'EM6', 'H6', 'W6', 'C6', '6');
--insert into volunteer values('5', '300', '1', '1995-03-03');
--insert into volunteer values('6', '400', '0', '1995-03-04');
--
----Donors also Employee
--insert into person values('7', '1007-07-07', 'Sun', 'Han', 'male', 'CS5', 'M5', 'EM5', 'H5', 'W5', 'C7', '8');
--insert into person values('8', '1008-08-08', 'Li', 'Han', 'male', 'CS6', 'M6', 'EM6', 'H6', 'W6', 'C8', '8');
--insert into donor values('7', '1');
--insert into donor values('8', '1');
--insert into employee values('7', '300', '1', '1995-03-03');
--insert into employee values('8', '400', '0', '1995-03-04');
--insert into person_donation_card values('7', '1000-0-0', '10000', 'Type_1', 'A', 'A', 'A', 'A');
--insert into person_donation_check values('7', '1000-0-0', '10000', 'Type_1', 'A', 'A');
--
----Teams
--insert into team values('Team_1', 'Type1', '1995-03-03');
--insert into team values('Team_2', 'Type2', '1995-04-04');
--
----Team1 cares Clients 5,6  
--insert into serve values('Team_1', '5', '1');
--insert into serve values('Team_1', '6', '1');
--
--insert into care values('Team_1', '1', '1');
--insert into care values('Team_1', '2', '1');
--
--insert into expense values('3', '1994-04-04', '300', 'Alipay');
--insert into expense values('3', '1994-04-05', '303', 'Wechat');
--
--insert into expense values('4', '1994-04-04', '400', 'Alipay');
--insert into expense values('4', '1994-04-04', '404', 'Wechat');
--
--insert into organization values('chang', 'jiba', '404', 'shabi',1);
--insert into organization values('liang', 'jiji', '504', 'shadiao',1);
--
--insert into sponsor values('Team_1', 'liang');
--insert into sponsor values('Team_1', 'chang');
--
----10
----select doctor_name, doctor_phone_number from client where ssn = '2';
----11
--select SSN, sum(amount) from expense where expense_date between '0' and '1995' group by SSN order by sum(amount); 
----12
--
----select SSN from serve where team_name in(select team_name from care where SSN = '1');
----13
----Select Name, Mailing_Address, Email_Address, Home, Cell_Phone from Person Where SSN in (Select SSN from care Where Team_name In(Select team_name From Sponsor Where Organization_Name between 'b' and 'k~'));
----14
----select name, sum_amount from person join (select SSN, sum(amount) as sum_amount from (select SSN, amount from person_donation_card where SSN in(select SSN from employee) union all select SSN, amount from person_donation_check where SSN in(select SSN from employee)) group by SSN) using(SSN);
----15
----16 Employee 11, 12 employees report to team_1
--insert into person values('11', '1000-01-01', 'Shangguan', 'Han', 'male', 'C1', 'M1', 'EM1', 'H1', 'W1', 'C1', '1');
--insert into person values('12', '1002-02-02', 'Ximen', 'Han', 'male', 'CS2', 'M2', 'EM2', 'H2', 'W2', 'C2', '2');
--insert into employee values('11', '1000', '1', '1995-03-03');
--insert into employee values('12', '1000', '1', '1995-03-04');
--insert into report values('11', 'Team_1', '1995-03-03', 'A');
--insert into report values('11', 'Team_2', '1995-03-03', 'A');
----update employee set salary = salary*1.1 where SSN in (select SSN from report group by ssn having count(*) > 1);
--
----17 13 client has no health insurance, 14 client has importance transportation < 5, 13, 14 should be delete from client
--
--
--
----select policy_id from insurance_policy where type = 'health';
----Delete from Client Where (SSN not in (select SSN from Has_Insurance) Or SSN in (Select SSN from Need Where Importance < 5));
----select SSN from client where SSN not in (select SSN from has_insurance where policy_id in);
----select SSN from client where SSN in(select need where type = 'transportaion' and importance < 5);
----delete from need where (SSN not in(select SSN from has_insurance where policy_id in(select policy_id from insurance_policy where type = 'health')) and (SSN in (select SSN from need where type = 'transportation' and importance < 5);
----delete from has_insurance where (SSN not in(select SSN from has_insurance where policy_id in(select policy_id from insurance_policy where type = 'health')) and SSN in (select SSN from need where type = 'transportation' and importance < 5));
----delete from CARE where (SSN not in(select SSN from has_insurance where policy_id in(select policy_id from insurance_policy where type = 'health')) and SSN in (select SSN from need where type = 'transportation' and importance < 5));
----delete from client where (SSN not in(select SSN from has_insurance where policy_id in(select policy_id from insurance_policy where type = 'health')) and SSN in(select SSN from need where type = 'transportation' and importance < 5));
--
----has health insurance
--
--select SSN from has_insurance
--minus
--(select distinct SSN from has_insurance, insurance_policy where has_insurance.policy_id = insurance_policy.policy_id and type = 'health')
--INTERSECT
--select distinct SSN from need where type = 'transportation' and importance < 5;
--
--
----delete from need where SSN in(
----select SSN from has_insurance
----minus
----(select distinct SSN from has_insurance, insurance_policy where has_insurance.policy_id = insurance_policy.policy_id and type = 'health')
----INTERSECT
----select distinct SSN from need where type = 'transportation' and importance < 5 );
--
--delete from has_insurance where SSN in(
--select SSN from has_insurance
--minus
--(select distinct SSN from has_insurance, insurance_policy where has_insurance.policy_id = insurance_policy.policy_id and type = 'health')
--INTERSECT
--select distinct SSN from need where type = 'transportation' and importance < 5 );
--
----delete from care where SSN in(
----select SSN from has_insurance
----minus
----(select distinct SSN from has_insurance, insurance_policy where has_insurance.policy_id = insurance_policy.policy_id and type = 'health')
----INTERSECT
----select distinct SSN from need where type = 'transportation' and importance < 5);
----
----delete from client where SSN in(
----select SSN from has_insurance
----minus
----(select distinct SSN from has_insurance, insurance_policy where has_insurance.policy_id = insurance_policy.policy_id and type = 'health')
----INTERSECT
----select distinct SSN from need where type = 'transportation' and importance < 5);
--
----select ssn from person where ssn= '1';
--insert into clients values('444444', 'a', 'a', 'a', '5','6');
select SSN from employee;
 union all select SSN, amount from person_donation_check
insert into serve_month_hour values('2', 'TM1', 22, 9);
select SSN, sum(amount) as sum_amount from ()) group by SSN;
select SSN, sum(amount) from expense where "date" between '1' and '9' group by SSN order by sum(amount);
select name, sum_amount from person join () using(SSN);

select name,mailing_address,email_address,home,person.work,cell_phone from person where ssn in(
select ssn1 from
(select ssn1, hour1,team_name1
from (select ssn as ssn1,sum(hour)as hour1,team_name as team_name1
from serve_month_hour
where month between 3 and 6
group by ssn,team_name)
,
(select team_name as team_name2,max(T)as hour2
from (select ssn,sum(hour)as T,team_name
from serve_month_hour
where month between 3 and 6
group by ssn,team_name)
group by team_name)
where team_name1=team_name2 and hour1=hour2)group by ssn1);