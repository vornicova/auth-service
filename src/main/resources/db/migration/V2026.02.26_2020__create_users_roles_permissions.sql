CREATE TABLE t_users
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE t_roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE t_permissions
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE t_user_roles
(
    user_id BIGINT REFERENCES t_users (id),
    role_id BIGINT REFERENCES t_roles (id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE t_role_permissions
(
    role_id       BIGINT REFERENCES t_roles (id),
    permission_id BIGINT REFERENCES t_permissions (id),
    PRIMARY KEY (role_id, permission_id)
);