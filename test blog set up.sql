DROP DATABASE IF EXISTS blogTest;
CREATE DATABASE blogTest;

Use blogTest;
CREATE TABLE post(
    id INT PRIMARY KEY AUTO_INCREMENT,
    timePosted datetime,
    title VARCHAR(75) NOT NULL,
    blogBody text NOT NULL,
    isApproved boolean NOT NULL
);

INSERT INTO post(timePosted, title, blogBody, isApproved)
VALUES('2022-12-12 13:00:00', "Hello World", "<p>testing</p>", true),
('2022-12-13 13:00:00', "Lorem Ipsum", "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>", true),
('2022-12-14 13:00:00', "Album of the Year?", "<p>Ants From Up There by Black Country, New Roads stole my heart completely this year</p>", true),
('2022-12-15 13:00:00', "Blink twice if you need help", "<p>😐 😑 😐 😑</p>", false),
('2022-12-16 13:00:00', "Blog Post 2", "<p><strong>h</strong>ow i lov<strong>e</strong> to make Posts on my B<strong>l</strong>og on the Internet thumbs u<strong>p</strong> :)</p>", true),
('2022-12-17 13:00:00', "Hello Cruel World", "<p>i am going to make it through this year if it kills me</p>", true); 

CREATE TABLE hashtag(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL
);
INSERT INTO hashtag(name)
VALUES("hello"),
("world"),
("lorem"),
("ipsum"),
("music"),
("endofyear"),
("blogging"); 

CREATE TABLE post_hashtag(
	postId INT NOT NULL,
    hashtagId INT NOT NULL,
    PRIMARY KEY(postId, hashtagId),
    FOREIGN KEY (postId) REFERENCES post(id),
    FOREIGN KEY (hashtagId) REFERENCES hashtag(id)
);
INSERT INTO post_hashtag(postId, hashtagId)
VALUES(1,1),
(1,2),
(2,3),
(2,4),
(3,5),
(3,6),
(4,3),
(4,4),
(5,7),
(6,1),
(6,2);