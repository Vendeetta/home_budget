ALTER TABLE home_budget.user
    ADD CONSTRAINT user_email_check CHECK (email ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$');
