create table payment (
    id uuid not null unique,
    type varchar(255),
    primary key (id)
);

create table payment_bluecode (
    barcode varchar(255) not null,
    branch_ext_id varchar(255) not null,
    currency varchar(255) not null,
    discount_amount int4 not null,
    end_to_end_id varchar(255),
    entry_mode varchar(255),
    purchase_amount int4 not null,
    requested_amount int4 not null,
    scheme varchar(255),
    slip varchar(255),
    slip_date_time date,
    slip_note varchar(255),
    source varchar(255),
    state varchar(255) not null,
    terminal varchar(255),
    tip_amount int4 not null
) INHERITS (payment);