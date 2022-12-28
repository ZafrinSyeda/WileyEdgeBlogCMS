DROP DATABASE IF EXISTS blog;
CREATE DATABASE blog;

Use blog;
CREATE TABLE post(
    id INT PRIMARY KEY AUTO_INCREMENT,
    timePosted datetime,
    title VARCHAR(50) NOT NULL,
    blogBody text NOT NULL,
    isApproved boolean NOT NULL
);

INSERT INTO post(timePosted, title, blogBody, isApproved)
VALUES('2022-12-12 13:00:00', "Hello World", "testing", true); 

CREATE TABLE hashtag(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL
);
INSERT INTO hashtag(name)
VALUES("hello"),
("world"); 

CREATE TABLE course_student(
	postId INT NOT NULL,
    hashtagId INT NOT NULL,
    PRIMARY KEY(postId, hashtagId),
    FOREIGN KEY (postId) REFERENCES post(id),
    FOREIGN KEY (hashtagId) REFERENCES hashtag(id)
);
INSERT INTO course_student(postId, hashtagId)
VALUES(1,1),
(1,2); 