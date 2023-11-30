drop table if exists reservation;
drop table if exists reservation_seq;

create table reservation (
                             id bigint not null,
                             check_in datetime(6),
                             check_out datetime(6),
                             contact_number decimal(38,0),
                             first_name varchar(255),
                             id_number decimal(38,0),
                             last_name varchar(255),
                             room_number integer not null,
                             primary key (id)
);

ALTER TABLE reservation
ADD CONSTRAINT room_constraint UNIQUE (room_number);

create table hibernate_sequence (
    next_val bigint
);

insert into hibernate_sequence values ( 1 )