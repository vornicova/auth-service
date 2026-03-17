CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    full_name VARCHAR(40)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone    VARCHAR(50) NOT NULL,
    role     VARCHAR(50)  NOT NULL
);

ALTER TABLE users
    ADD CONSTRAINT uk_users_username UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT chk_users_role
        CHECK (role IN ('USER', 'ADMIN'));
