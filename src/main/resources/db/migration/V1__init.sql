CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE tasks (

    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(255) NOT NULL,

    description TEXT,

    due_date DATE,

    status VARCHAR(50) NOT NULL DEFAULT 'todo',

    created_at TIMESTAMP NOT NULL,

    owner_id BIGINT NOT NULL,

    CONSTRAINT fk_task_user
        FOREIGN KEY(owner_id)
        REFERENCES users(id)

);