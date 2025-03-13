CREATE DATABASE tweeter;

CREATE TABLE tb_user (
    user_id SERIAL NOT NULL,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

CREATE TABLE tb_tweet (
    tweet_id BIGINT NOT NULL,
    user_id VARCHAR NOT NULL,
    content VARCHAR NOT NULL,
    creationTimestamp VARCHAR NOT NULL,
    CONSTRAINT pk_tweet PRIMARY KEY (tweet_id)
);

CREATE TABLE tb_role (
    role_id BIGINT NOT NULL,
    role VARCHAR(20) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (role_id)
);