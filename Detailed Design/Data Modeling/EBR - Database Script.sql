--CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
Create table dock(
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
    name text NOT NULL,
	location text not null,
	capacity integer not null,
	bike_num integer not null,
    image_url text DEFAULT ''::text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    constraint dock_pkey PRIMARY KEY (id)
);
CREATE TABLE bike (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
    type text NOT NULL,
	barcode integer not null,
	saddle_num integer not null,
	pedal_num integer not null,
	rear_seat_num integer not null,
	value integer not null,
	rental_fees integer not null,
	deposit_fees integer not null,
    image_url text DEFAULT ''::text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    description text,
	dock_id uuid not null,
    CONSTRAINT bike_pkey PRIMARY KEY (id),
	constraint bike_fk foreign key (dock_id)
	references dock (id) on delete cascade  on update no action
	);
create table e_bike(
	bike_id uuid not null,
	battery numeric not null,
	time_remain int not null,
    image_url text DEFAULT ''::text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
	constraint e_bike_fk foreign key (bike_id)
	references bike (id) on delete cascade  
);
Create table card(
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
    card_owner text not null,
	card_num text not null,
	security_code text not null,
	exp_date text not null,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    constraint card_pkey PRIMARY KEY (id)
);
create table payment_transaction(
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
    session_id uuid not null,
	type text not null,
	amount numeric not null,
	method text not null,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    constraint payment_transaction_pkey PRIMARY KEY (id)
	--constraint payment_transaction_fk foreign key(session_id) references session (id)
);
create table session(
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
    card_id uuid NOT NULL,
	bike_id uuid not null,
	rent_transactionId uuid not null,
	return_transactionId uuid not null,
	start_time timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
	end_time timestamp with time zone,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    constraint session_pkey PRIMARY KEY (id),
	constraint session_fk1 foreign key(card_id) references Card (id), 
	constraint session_fk2 foreign key(bike_id) references Bike (id),
	constraint session_fk3 foreign key(rent_transactionId) references payment_transaction (id),
	constraint session_fk4 foreign key(return_transactionId) references payment_transaction (id)
);


Create table invoice(
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
    session_id uuid not null,
	total_charde numeric not null,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    constraint invoice_pkey PRIMARY KEY (id),
	constraint invoice_fk foreign key (session_id) references session (id)
);
