CREATE TABLE IF NOT EXISTS USERS
(
    id                               UUID          NOT NULL,
    name                             VARCHAR       NOT NULL,

    CONSTRAINT PK_USER PRIMARY KEY (id)
);