CREATE TABLE "follow" (
  "id" long PRIMARY KEY,
  "user_id" integer,
  "followed_user_id" integer,
  "created_at" timestamp
);

CREATE TABLE "userModel" (
  "id" integer PRIMARY KEY,
  "username" varchar,
  "password" varchar,
  "email" varchar,
  "created_at" timestamp
);

CREATE TABLE "post" (
  "id" integer PRIMARY KEY,
  "title" varchar,
  "body" text,
  "user_id" integer,
  "status" varchar,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "content" (
  "id" integer,
  "post_id" integer,
  "content_link" varchar
);

COMMENT ON COLUMN "post"."body" IS 'Content of the post';

COMMENT ON COLUMN "content"."content_link" IS 'Link to attached image file';

ALTER TABLE "post" ADD FOREIGN KEY ("user_id") REFERENCES "userModel" ("id");

ALTER TABLE "follow" ADD FOREIGN KEY ("user_id") REFERENCES "userModel" ("id");

ALTER TABLE "follow" ADD FOREIGN KEY ("followed_user_id") REFERENCES "userModel" ("id");

ALTER TABLE "content" ADD FOREIGN KEY ("post_id") REFERENCES "post" ("id");
