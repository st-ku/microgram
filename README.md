# Microgram app.
## Description
This is a clone of the popular social media app Instagram. It allows users to post pictures, follow other users, like and comment on their posts.
Whole idea of this project was to learn spring boot microservices and how to use them in real life projects.

## Technologies used
- Java 17, 8 (for gateway module)
- Spring Boot
- RabbitMQ
- PostgreSQL

## Modules description
- **gateway** - gateway module that handles all requests from the client and redirects them to the appropriate microservice.
- **user-service** - microservice that handles all user related requests.
- **post-service** - microservice that handles all post related requests.
- **feed-service** - microservice that handles all feed related requests.

## How to run
1. Clone the repository
2. Install postgresql and rabbitmq
3. Create database user with username: **sa** and password: **sa** which has access to database with name: **postgres**
You can execute following commands in psql shell:
```sql
psql -U postgres
CREATE USER sa WITH PASSWORD 'sa';
GRANT ALL PRIVILEGES ON DATABASE postgres TO sa;
```
4. Run rabbitmq server
5. Create rabbitmq queue with name: **users.auth**
6. Run all modules

## Example requests
By default, following users are created for testing purposes:
- username: **user1**, password: **password1**
- username: **user2**, password: **password2**
- username: **admin**, password: **admin**

1. Create post request, where **admin** is creating a post
```http curl request
curl --location 'http://localhost:8000/post' \
--form 'files=@"/C:/Users/StanislavKurstak/Downloads/New folder/stock-photo-142984111-1500x1000.jpg"' \
--form 'title="Sample Title 3"' \
--form 'body="This is the body of the post."' \
--form 'name="admin"' \
--form 'password="admin"'
```
Using this request, you can create a post. You need to specify the image file, title and body of the post. You also need to specify the username and password of the user that is creating the post. You can use any user that is created by default.

2. Get all posts request:
```http curl request
curl --location 'http://localhost:8000/post'
```
Using this request, you can get all posts that are created by all users.

3. Create new follow request, where **admin** is following **user1**:
```http curl request
curl --location 'http://localhost:8000/follow/admin' \
--form 'name="user1"' \
--form 'password="password1"'
```
Using this request, you can create a new follow. You need to specify the username and password of the user that is following. You also need to specify the username of the user that is being followed. You can use any user that is created by default.

4. Get a feed for a **user1** request:
```http curl request
curl --location --request GET 'http://localhost:8000/feed' \
--form 'name="user1"' \
--form 'password="password1"'
```