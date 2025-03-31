CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS telemetry_transactions (
    id SERIAL PRIMARY KEY,
    amount NUMERIC(10, 2) NOT NULL,
    operation_date TIMESTAMP NOT NULL,
    customer_uuid uuid NOT NULL,
    transaction_uuid uuid NOT NULL
);

commit;