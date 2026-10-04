CREATE TABLE users (
                       id        BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name      VARCHAR(255) NOT NULL,
                       email     VARCHAR(255) NOT NULL UNIQUE,
                       password  VARCHAR(255) NOT NULL,
                       created   TIMESTAMP NOT NULL,
                       modified  TIMESTAMP NOT NULL,
                       last_login TIMESTAMP NOT NULL,
                       token     VARCHAR(255) NOT NULL,
                       active    BOOLEAN NOT NULL
);

CREATE TABLE phones (
                        id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                        number      VARCHAR(30),
                        citycode    VARCHAR(10),
                        contrycode  VARCHAR(10),
                        user_id     BIGINT NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);