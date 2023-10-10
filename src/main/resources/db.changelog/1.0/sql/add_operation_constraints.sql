ALTER TABLE home_budget.operation
    ADD CONSTRAINT operation_amount_check CHECK (amount > 0);
