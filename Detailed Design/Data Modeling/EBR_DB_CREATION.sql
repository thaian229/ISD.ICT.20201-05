CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

Create table dock (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
    name text NOT NULL,
	location text NOT NULL,
	capacity integer NOT NULL,
	bike_num integer NOT NULL,
    image_url text DEFAULT ''::text,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT dock_pk PRIMARY KEY (id)
);

CREATE TABLE bike (
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
    type INTEGER NOT NULL DEFAULT 1,
	barcode INTEGER NOT NULL,
	saddle_num INTEGER NOT NULL DEFAULT 1,
	pedal_num INTEGER NOT NULL DEFAULT 1,
	rear_seat_num INTEGER NOT NULL DEFAULT 1,
	value INTEGER NOT NULL,
	rental_fees INTEGER NOT NULL,
    image_url TEXT DEFAULT ''::text,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
	dock_id UUID NOT NULL,
    CONSTRAINT bike_pk PRIMARY KEY (id),
	CONSTRAINT bike_fk FOREIGN KEY (dock_id)
	REFERENCES dock (id) ON DELETE CASCADE ON UPDATE NO ACTION
);

create table e_bike (
	bike_id UUID NOT NULL,
	battery numeric(3,1) not null,
	time_remain int not null,
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT e_bike_pk PRIMARY KEY (bike_id),
	constraint e_bike_fk foreign key (bike_id)
	references bike (id) on delete cascade ON UPDATE NO ACTION
);

Create table card (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	card_num text not null,
    card_owner text not null,
	security_code text not null,
	exp_date text not null,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    constraint card_pk PRIMARY KEY (id)
);

create table payment_transaction (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
    session_id uuid not null,
	type text not null,
	amount numeric not null,
	method text not null,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    constraint payment_transaction_pk PRIMARY KEY (id)
	--constraint payment_transaction_fk foreign key(session_id) references session (id)
);

create table session (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
    card_id uuid NOT NULL,
	bike_id uuid not null,
	rent_transactionId uuid not null,
	return_transactionId uuid not null,
	start_time text not null,,
	end_time text not null,,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    constraint session_pk PRIMARY KEY (id),
	constraint session_fk1 foreign key(card_id) references Card (id), 
	constraint session_fk2 foreign key(bike_id) references Bike (id),
	constraint session_fk3 foreign key(rent_transactionId) references payment_transaction (id),
	constraint session_fk4 foreign key(return_transactionId) references payment_transaction (id)
);

Create table invoice (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
    session_id uuid not null,
	total_charge numeric not null,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    constraint invoice_pk PRIMARY KEY (id),
	constraint invoice_fk foreign key (session_id) references session (id)
);

ALTER TABLE payment_transaction
ADD CONSTRAINT payment_transaction_fk foreign key(session_id)
    references session (id) ON DELETE CASCADE ON UPDATE NO ACTION;