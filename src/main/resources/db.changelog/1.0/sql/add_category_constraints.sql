ALTER TABLE home_budget.category
    ADD CONSTRAINT category_limit_check CHECK (category_limit > 0);
