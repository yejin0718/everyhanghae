CREATE TABLE everyhanghae.users
(
    user_id   BIGINT AUTO_INCREMENT,
    email     VARCHAR(50) NOT NULL UNIQUE,
    nickname  VARCHAR(20) NOT NULL,
    password  VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE everyhanghae.board
(
    board_id    BIGINT AUTO_INCREMENT,
    created_at  TIMESTAMP,
    modified_at TIMESTAMP,
    category    VARCHAR(10),
    content     VARCHAR(255)  NOT NULL,
    like_count  INT DEFAULT 0 NOT NULL,
    title       VARCHAR(50)   NOT NULL,
    user_id     BIGINT,
    writer      VARCHAR(20)   NOT NULL,
    PRIMARY KEY (board_id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE everyhanghae.board_like
(
    like_id  BIGINT AUTO_INCREMENT,
    user_id  BIGINT,
    board_id BIGINT,
    PRIMARY KEY (like_id),
    FOREIGN KEY (board_id) REFERENCES everyhanghae.board (board_id)
);

CREATE TABLE everyhanghae.comment
(
    comment_id  BIGINT AUTO_INCREMENT,
    created_at  TIMESTAMP,
    modified_at TIMESTAMP,
    content     VARCHAR(255) NOT NULL,
    user_id     BIGINT,
    writer      VARCHAR(20)  NOT NULL,
    board_id    BIGINT,
    primary key (comment_id),
    FOREIGN KEY (board_id) REFERENCES everyhanghae.board (board_id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;