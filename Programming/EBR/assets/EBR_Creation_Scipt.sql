SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';

SET default_tablespace = '';

SET default_table_access_method = heap;

DROP TABLE IF EXISTS public.invoice;
DROP TABLE IF EXISTS public.session;
DROP TABLE IF EXISTS public.payment_transaction;
DROP TABLE IF EXISTS public.card;
DROP TABLE IF EXISTS public.e_bike;
DROP TABLE IF EXISTS public.bike;
DROP TABLE IF EXISTS public.dock;

CREATE TABLE public.bike (
                             id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
                             type integer DEFAULT 1 NOT NULL,
                             barcode integer NOT NULL,
                             saddle_num integer DEFAULT 1 NOT NULL,
                             pedal_num integer DEFAULT 1 NOT NULL,
                             rear_seat_num integer DEFAULT 1 NOT NULL,
                             value integer NOT NULL,
                             rental_fees integer NOT NULL,
                             image_url text DEFAULT ''::text,
                             created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
                             description text,
                             dock_id uuid
);

ALTER TABLE public.bike OWNER TO postgres;

CREATE TABLE public.card (
                             id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
                             card_num text NOT NULL UNIQUE,
                             card_owner text NOT NULL,
                             security_code text NOT NULL,
                             exp_date text NOT NULL,
                             created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE public.card OWNER TO postgres;

CREATE TABLE public.dock (
                             id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
                             name text NOT NULL,
                             location text NOT NULL,
                             capacity integer NOT NULL,
                             bike_num integer NOT NULL DEFAULT 0,
                             image_url text DEFAULT ''::text,
                             created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE public.dock OWNER TO postgres;

CREATE TABLE public.e_bike (
                               bike_id uuid NOT NULL,
                               battery numeric(3,1) NOT NULL,
                               time_remain integer NOT NULL,
                               created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE public.e_bike OWNER TO postgres;

CREATE TABLE public.invoice (
                                id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
                                session_id uuid NOT NULL,
                                total_charge numeric,
                                created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE public.invoice OWNER TO postgres;

CREATE TABLE public.payment_transaction (
                                            id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
                                            card_id uuid NOT NULL,
                                            type text NOT NULL,
                                            amount numeric NOT NULL,
                                            method text,
                                            created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE public.payment_transaction OWNER TO postgres;

CREATE TABLE public.session (
                                id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
                                card_id uuid NOT NULL,
                                bike_id uuid NOT NULL,
                                rent_transactionid uuid NOT NULL,
                                return_transactionid uuid,
                                last_rent_time_before_lock INT DEFAULT 0,
                                active boolean DEFAULT true,
                                start_time Text,
                                last_resume_time Text,
                                end_time Text,
                                created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE public.session OWNER TO postgres;

-- BIKE

INSERT INTO public.bike (id, type, barcode, saddle_num, pedal_num, rear_seat_num, value, rental_fees, image_url, created_at, description, dock_id) VALUES ('078a3525-4146-49ba-9c4c-9caf1678ddcd', 1, 467295, 1, 1, 1, 1000000, 25000, 'assets\images\bikes\StandardBike.png', '2020-12-03 21:53:34.242179+07', NULL, '012b8b33-32a3-4c15-ba0f-ba3204e119b0');
INSERT INTO public.bike (id, type, barcode, saddle_num, pedal_num, rear_seat_num, value, rental_fees, image_url, created_at, description, dock_id) VALUES ('1be8a4e1-5eb6-48f6-b9ff-5f67a6c45f85', 4, 452593, 2, 2, 1, 8000000, 50000, 'assets\images\bikes\TwinElectricalBike.png', '2020-12-03 21:58:17.775008+07', NULL, '31b2f5d7-dcbc-428a-8be5-3f49fa5a3573');
INSERT INTO public.bike (id, type, barcode, saddle_num, pedal_num, rear_seat_num, value, rental_fees, image_url, created_at, description, dock_id) VALUES ('31e1ac5c-a755-4946-aa1b-9cf8989ee87f', 1, 784318, 1, 1, 1, 1000000, 25000, 'assets\images\bikes\StandardBike.png', '2020-12-03 21:54:50.621477+07', NULL, '4a4e10eb-1de2-411f-a35b-fc5be0cc7393');
INSERT INTO public.bike (id, type, barcode, saddle_num, pedal_num, rear_seat_num, value, rental_fees, image_url, created_at, description, dock_id) VALUES ('524ccbd1-c1d5-47d9-9c28-827f88a9adcd', 3, 697316, 1, 1, 1, 6000000, 50000, 'assets\images\bikes\ElectricalBike.png', '2020-12-03 21:56:37.492976+07', NULL, '012b8b33-32a3-4c15-ba0f-ba3204e119b0');
INSERT INTO public.bike (id, type, barcode, saddle_num, pedal_num, rear_seat_num, value, rental_fees, image_url, created_at, description, dock_id) VALUES ('78270ffe-be03-4e83-b466-b9c2e274a976', 4, 228636, 2, 2, 1, 8000000, 50000, 'assets\images\bikes\TwinElectricalBike.png', '2020-12-03 21:57:52.695281+07', NULL, '012b8b33-32a3-4c15-ba0f-ba3204e119b0');
INSERT INTO public.bike (id, type, barcode, saddle_num, pedal_num, rear_seat_num, value, rental_fees, image_url, created_at, description, dock_id) VALUES ('9ef7ceb8-38c9-4d56-9fb6-1ddfd0364f46', 1, 216064, 1, 1, 1, 1000000, 25000, 'assets\images\bikes\StandardBike.png', '2020-12-03 21:54:09.924574+07', NULL, '31b2f5d7-dcbc-428a-8be5-3f49fa5a3573');
INSERT INTO public.bike (id, type, barcode, saddle_num, pedal_num, rear_seat_num, value, rental_fees, image_url, created_at, description, dock_id) VALUES ('b3af2957-1a4d-4c64-8cef-7b0b393625ce', 2, 527841, 2, 2, 1, 4000000, 50000, 'assets\images\bikes\TwinBike.png', '2020-12-03 21:55:52.16724+07', NULL, '31b2f5d7-dcbc-428a-8be5-3f49fa5a3573');
INSERT INTO public.bike (id, type, barcode, saddle_num, pedal_num, rear_seat_num, value, rental_fees, image_url, created_at, description, dock_id) VALUES ('cc2e033a-7505-4a66-a639-4d9cd35f9d6d', 2, 826086, 2, 2, 1, 4000000, 50000, 'assets\images\bikes\TwinBike.png', '2020-12-03 21:55:25.58271+07', NULL, '4a4e10eb-1de2-411f-a35b-fc5be0cc7393');
INSERT INTO public.bike (id, type, barcode, saddle_num, pedal_num, rear_seat_num, value, rental_fees, image_url, created_at, description, dock_id) VALUES ('d06ba58c-4759-419d-85c3-07b3f738f500', 1, 533503, 1, 1, 1, 1000000, 25000, 'assets\images\bikes\StandardBike.png', '2020-12-03 21:53:49.299133+07', NULL, '012b8b33-32a3-4c15-ba0f-ba3204e119b0');
INSERT INTO public.bike (id, type, barcode, saddle_num, pedal_num, rear_seat_num, value, rental_fees, image_url, created_at, description, dock_id) VALUES ('e50afbf2-e6f7-46c6-a9e3-82505901af21', 3, 188831, 1, 1, 1, 6000000, 50000, 'assets\images\bikes\ElectricalBike.png', '2020-12-03 21:56:55.635597+07', NULL, '012b8b33-32a3-4c15-ba0f-ba3204e119b0');

-- DOCK

INSERT INTO public.dock (id, name, location, capacity, bike_num, image_url, created_at) VALUES ('4a4e10eb-1de2-411f-a35b-fc5be0cc7393', 'ECO Bike Park 1', 'Location 1', 200, 0, '', '2020-12-03 21:52:19.458054+07');
INSERT INTO public.dock (id, name, location, capacity, bike_num, image_url, created_at) VALUES ('012b8b33-32a3-4c15-ba0f-ba3204e119b0', 'ECO Bike Park 2', 'Location 2', 200, 0, '', '2020-12-03 21:52:30.624056+07');
INSERT INTO public.dock (id, name, location, capacity, bike_num, image_url, created_at) VALUES ('31b2f5d7-dcbc-428a-8be5-3f49fa5a3573', 'ECO Bike Park 3', 'Location 3', 100, 0, '', '2020-12-03 21:52:36.709013+07');

-- E-BIKE

INSERT INTO public.e_bike (bike_id, battery, time_remain, created_at) VALUES ('1be8a4e1-5eb6-48f6-b9ff-5f67a6c45f85', 99.9, 360, '2020-12-11 13:51:02.69043+07');
INSERT INTO public.e_bike (bike_id, battery, time_remain, created_at) VALUES ('524ccbd1-c1d5-47d9-9c28-827f88a9adcd', 20.0, 72, '2020-12-11 13:52:01.710722+07');
INSERT INTO public.e_bike (bike_id, battery, time_remain, created_at) VALUES ('78270ffe-be03-4e83-b466-b9c2e274a976', 40.0, 144, '2020-12-11 13:52:01.710722+07');
INSERT INTO public.e_bike (bike_id, battery, time_remain, created_at) VALUES ('e50afbf2-e6f7-46c6-a9e3-82505901af21', 80.0, 288, '2020-12-11 13:52:01.710722+07');


ALTER TABLE ONLY public.bike
    ADD CONSTRAINT bike_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.card
    ADD CONSTRAINT card_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.dock
    ADD CONSTRAINT dock_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.e_bike
    ADD CONSTRAINT e_bike_pk PRIMARY KEY (bike_id);

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.payment_transaction
    ADD CONSTRAINT payment_transaction_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.payment_transaction
    ADD CONSTRAINT payment_transaction_fk FOREIGN KEY (card_id) REFERENCES public.card(id) ON DELETE CASCADE;

ALTER TABLE ONLY public.session
    ADD CONSTRAINT session_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.bike
    ADD CONSTRAINT bike_fk FOREIGN KEY (dock_id) REFERENCES public.dock(id) ON DELETE CASCADE;

ALTER TABLE ONLY public.e_bike
    ADD CONSTRAINT e_bike_fk FOREIGN KEY (bike_id) REFERENCES public.bike(id) ON DELETE CASCADE;

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_fk FOREIGN KEY (session_id) REFERENCES public.session(id);

ALTER TABLE ONLY public.session
    ADD CONSTRAINT session_fk1 FOREIGN KEY (card_id) REFERENCES public.card(id);

ALTER TABLE ONLY public.session
    ADD CONSTRAINT session_fk2 FOREIGN KEY (bike_id) REFERENCES public.bike(id);

ALTER TABLE ONLY public.session
    ADD CONSTRAINT session_fk3 FOREIGN KEY (rent_transactionid) REFERENCES public.payment_transaction(id);

ALTER TABLE ONLY public.session
    ADD CONSTRAINT session_fk4 FOREIGN KEY (return_transactionid) REFERENCES public.payment_transaction(id);

CREATE FUNCTION trigger_function()
    RETURNS TRIGGER
    LANGUAGE PLPGSQL
AS $$
BEGIN
   -- trigger logic
END;
$$