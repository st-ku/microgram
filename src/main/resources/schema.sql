CREATE TABLE follow (
                        id bigint PRIMARY KEY,
                        user_id integer,
                        followed_user_id integer,
                        created_at timestamp
);

CREATE TABLE "users" (
                        id integer PRIMARY KEY,
                        username varchar(255),
                        password varchar(255),
                        email varchar(255),
                        created_at timestamp
);

CREATE TABLE post (
                      id integer PRIMARY KEY,
                      title varchar(255),
                      body text,
                      user_id integer,
                      status varchar(50),
                      created_at timestamp,
                      updated_at timestamp
);

CREATE TABLE content (
                         id integer,
                         post_id integer,
                         content_link varchar(255)
);

COMMENT ON COLUMN post.body IS 'Content of the post';

COMMENT ON COLUMN content.content_link IS 'Link to attached image file';

ALTER TABLE post ADD FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE follow ADD FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE follow ADD FOREIGN KEY (followed_user_id) REFERENCES "user" (id);

ALTER TABLE content ADD FOREIGN KEY (post_id) REFERENCES post (id);
