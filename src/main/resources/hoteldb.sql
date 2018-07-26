create user hoteldb identified by hotelcalifornia;
grant connect, resource, create view, create session, create table to hoteldb;

-- Creating and defining the tables --

create table hotel_user (
    user_id number primary key,
    first_name varchar2(30) not null,
    last_name varchar2(30) not null,
    email varchar2(100) not null,
    user_role varchar2(10) not null,
    hotel_managed number  -- A host attribute only
)

create table user_account (
    username varchar2(50) primary key,
    user_password varchar2(200) not null,
    user_id number not null
)

create table user_role (
    role_name varchar2(10) primary key
)

create table issue (
    created_by number not null, -- Guest_id
    created_on timestamp default CURRENT_TIMESTAMP,
    message varchar2(400) not null,
    resolved_by number,         -- Host_id    
    resolved_on timestamp,      -- Whenever it is resolved
    is_resolved number(1) default 0,  -- 0 means 'false', 1 means 'true'
    constraint pk_issue primary key (created_by, created_on)
)

create table reservation (
    user_id number not null,
    hotel_room_id number not null,
    start_date Date not null,
    end_date Date not null,
    current_status varchar2(10) not null,
    constraint pk_reservation primary key (user_id, hotel_room_id, start_date, end_date)
)

create table status (
    status_name varchar2(10) primary key
)

create table hotel (
    hotel_id number primary key,
    hotel_name varchar2(50) not null
)

create table hotel_room (
    hotel_room_id number primary key,
    room_number number not null,
    hotel_id number not null,
    image_url varchar2(200),
    constraint uq_hotel_room unique (hotel_id, room_number)
)

-- Foreign Key Constraints
alter table hotel_user add constraint fk_hotel_user_user_role foreign key (user_role) references user_role(role_name) on delete cascade;

alter table user_account add constraint fk_user_account_user_id foreign key (user_id) references hotel_user(user_id) on delete cascade;

alter table issue add constraint fk_issue_created_by foreign key (created_by) references hotel_user(user_id) on delete cascade;

alter table issue add constraint fk_issue_resolved_by foreign key (resolved_by) references hotel_user(user_id) on delete cascade;

alter table reservation add constraint fk_reservation_user_id foreign key (user_id) references hotel_user(user_id) on delete cascade;

alter table reservation add constraint fk_reservation_hotel_room_id foreign key (hotel_room_id) references hotel_room(hotel_room_id) on delete cascade;

alter table reservation add constraint fk_reservation_status foreign key (current_status) references status(status_name);

alter table hotel_room add constraint fk_hotel_room_hotel_id foreign key (hotel_id) references hotel(hotel_id) on delete cascade;

-- Sequences
CREATE SEQUENCE SQ_HOTEL_USER_PK START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE SQ_HOTEL_PK START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE SQ_HOTEL_ROOM_PK START WITH 1 INCREMENT BY 1;

-- Trigger for auto-incrementing ID sequences
CREATE OR REPLACE TRIGGER TR_INSERT_HOTEL_USER
BEFORE INSERT ON HOTEL_USER
FOR EACH ROW
BEGIN
    SELECT SQ_HOTEL_USER_PK.NEXTVAL INTO :NEW.USER_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER TR_INSERT_HOTEL
BEFORE INSERT ON HOTEL
FOR EACH ROW
BEGIN
    SELECT SQ_HOTEL_PK.NEXTVAL INTO :NEW.HOTEL_ID FROM DUAL;
END;
/


CREATE OR REPLACE TRIGGER TR_INSERT_HOTEL_ROOM
BEFORE INSERT ON HOTEL_ROOM
FOR EACH ROW
BEGIN
    SELECT SQ_HOTEL_ROOM_PK.NEXTVAL INTO :NEW.HOTEL_ROOM_ID FROM DUAL;
END;
/

/* STORED PROCEDURES */
-- For inserting a hotel
create or replace procedure insert_hotel( hotel_name in varchar2) as
begin
    insert into hotel(hotel_name) values (hotel_name);
    commit;    
end;
/

-- For inserting a hotel room
create or replace procedure insert_hotel_room( room_number in number, hotel_id in number) as
begin
    insert into hotel_room(room_number, hotel_id) values (room_number, hotel_id);
    commit;    
end;
/
-- For inserting a guest hotel user
create or replace procedure insert_guest(first_name in varchar2, last_name in varchar2, email in varchar2) as
begin
    insert into hotel_user(first_name, last_name, email, user_role) values(first_name, last_name, email, 'GUEST');
    commit;    
end;
/

-- For inserting a hotel host 
create or replace procedure insert_host(first_name in varchar2, last_name in varchar2, email in varchar2, hotel_managed in number) as
begin
    insert into hotel_user(first_name, last_name, email, user_role, hotel_managed) values(first_name, last_name, email, 'HOST', hotel_managed);
    commit;    
end;
/
-- For inserting an issue
create or replace procedure insert_issue(created_by in number, message in varchar2) as
begin
    insert into issue(created_by, message) values (created_by, message);
    commit;    
end;
/
-- For inserting a reservation
create or replace procedure insert_reservation(user_id in number, hotel_room_id in number, start_date in date, end_date in date) as
begin
    insert into reservation(user_id, hotel_room_id, start_date, end_date, current_status) values(user_id, hotel_room_id, start_date, end_date, 'PENDING'); 
    commit;    
end;
/
-- For inserting a user account
create or replace procedure insert_user_account(username in varchar2, user_password in varchar2, user_id in number) as
begin
    insert into user_account(username, user_password, user_id) values (username, user_password, user_id);
    commit;    
end;
/


-- For resolving an issue (Updating the issue)

/* INSERTS */

-- Hotels
insert into hotel(hotel_name) values ('Hotel California');

-- Hotel Rooms
insert into hotel_room(room_number, hotel_id) values (101, 1);

-- User roles
insert into user_role values('GUEST');
insert into user_role values('HOST');

-- Reservation Status
insert into status values ('APPROVED');
insert into status values ('DENIED');
insert into status values ('PENDING');

-- Users
insert into hotel_user(first_name, last_name, email, user_role) values('Don', 'Felder', 'dfelder@gmail.com', 'GUEST');
insert into hotel_user(first_name, last_name, email, user_role, hotel_managed) values('Don', 'Henley', 'dhenley@gmail.com', 'HOST', 1);

-- User Accounts
insert into user_account(username, user_password, user_id) values ('dhenley', 'dhenley', 2);
insert into user_account(username, user_password, user_id) values ('dfelder', 'dfelder', 1);

-- Issue (May need to split table into 2)
insert into issue(created_by, message) values (1, 'Shower curtain is missing');

-- Reservations
insert into reservation(user_id, hotel_room_id, start_date, end_date, current_status) values(1, 1, to_date('12/23/1999','mm/dd/yyyy'), to_date('12/25/1999', 'mm/dd/yyyy'), 'PENDING'); 
insert into reservation(user_id, hotel_room_id, start_date, end_date, current_status) values(1, 1, to_date('12/23/1998','mm/dd/yyyy'), to_date('12/25/1998', 'mm/dd/yyyy'), 'APPROVED'); 


-- Select statements
select * from hotel;
select * from hotel_room;
select * from hotel_user;
select * from issue;
select * from reservation;
select * from status;
select * from user_account;
select * from user_role;

