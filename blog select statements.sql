USE blog;

select * from post;

select * from hashtag; 

SELECT h.* 
FROM hashtag h 
JOIN post_hashtag ph 
ON ph.hashtagId = h.id 
WHERE ph.postId = 1; 

SELECT p.*
FROM post p
JOIN post_hashtag ph 
ON ph.postId = p.id 
WHERE ph.hashtagId = 1; 