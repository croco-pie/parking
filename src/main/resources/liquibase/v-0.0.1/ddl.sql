--liquibase formatted sql

--changeset crocopie:add_parking_slot_status_enum
CREATE TYPE parking_slot_status AS ENUM('AVAILABLE', 'PARTIALLY_AVAILABLE', 'OCCUPIED');

--changeset crocopie:add_size_enum
CREATE TYPE "size" AS ENUM('S', 'M', 'L');

--changeset crocopie:add_car_type_table
CREATE TABLE IF NOT EXISTS car_type(
    id                  INT                 PRIMARY KEY,
    "type"              VARCHAR(10)         NOT NULL,
    "size"              SIZE                NOT NULL
);

--changeset crocopie:add_pricing_table
CREATE TABLE IF NOT EXISTS pricing(
    id                  INT                 PRIMARY KEY,
    car_type_id         INT                 NOT NULL,
    period_in_min       INT                 NOT NULL,
    period_sequence_num INT                 NOT NULL,
    price               NUMERIC             NOT NULL,
    CONSTRAINT fk_pricing_car_type FOREIGN KEY(car_type_id) REFERENCES car_type(id)
);

--changeset crocopie:add_parking_slot_table
CREATE TABLE IF NOT EXISTS parking_slot(
    id                  INT                 PRIMARY KEY,
    "size"              SIZE                NOT NULL,
    status              PARKING_SLOT_STATUS NOT NULL DEFAULT 'AVAILABLE'
);

--changeset crocopie:add_parked_car_table
CREATE TABLE IF NOT EXISTS parked_car(
    id                  UUID                PRIMARY KEY,
    parking_slot_id     INT                 NOT NULL,
    car_reg_number      VARCHAR(20)         NOT NULL,
    car_type_id         INT                 NOT NULL,
    begin_time          TIMESTAMP           NOT NULL,
    end_time            TIMESTAMP,
    total_price         NUMERIC,
    service_done        BOOLEAN             NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_parked_car_parking_slot FOREIGN KEY(parking_slot_id) REFERENCES parking_slot(id),
    CONSTRAINT fk_parked_car_car_type_id FOREIGN KEY(car_type_id) REFERENCES car_type(id)
);
