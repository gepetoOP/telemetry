CREATE TABLE IF NOT EXISTS telemetry_transactions (
    id SERIAL PRIMARY KEY,
    amount NUMERIC(10, 2) NOT NULL,
    operation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    customer_uuid uuid NOT NULL
);

commit;