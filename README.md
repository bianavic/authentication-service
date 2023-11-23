# Spring Security Project
Implement Authentication on a Web API

- java 17
- gradle
- postgresql
- OpenAPI v3.0
- JSON Web Token
- Spring Security v6.2.0


| API ROUTE		          | ACCESS STATUS		             | DESCRIPTION        |
|:---------------------|:------------------------------|:-------------------|
| [POST] /auth/signup	 | Unprotected       | Register a new user|
| [POST] /auth/login   | Unprotected        | Authenticate a user                |
| [GET] /users/me      | Protected        | Retrieve the current authenticated user                |
| [GET] /users	        | Protected       | Retrieve all the users                |


### Swagger
http://localhost:8282/swagger-ui/index.html

#### register user
```bash
curl --location 'http://localhost:8282/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "fullName": "full name test 1",
    "email": "test1@email.com",
    "password": 1234
}'
```
###### 200 OK
``` json
{
    "fullName": "full name test 1",
    "email": "test1@email.com",
    "password": "$2a$10$ArH01RPUn.P9X5kDralMNO4rxahDXjID4EtWhsM11AyqlY6vBB/jG",
    "createdAt": "2023-11-23T14:54:40.121+00:00",
    "updatedAt": "2023-11-23T14:54:40.121+00:00",
    "enabled": true,
    "accountNonLocked": true,
    "authorities": [],
    "username": "test1@email.com",
    "accountNonExpired": true,
    "credentialsNonExpired": true
}
```
#### login user
```bash
curl --location 'http://localhost:8282/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "test1@email.com",
    "password": 1234
}'
```
###### 200 OK
``` json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBlbWFpbC5jb20iLCJpYXQiOjE3MDA3NTEyODYsImV4cCI6MTcwMDc1NDg4Nn0.KIOQpQj_Uf8Oa6lSDDAkFgPR3XCFm2WHqMKGS-bJl4c",
    "expiresIn": 3600000
}
```
#### GET me
```bash
curl --location 'http://localhost:8282/users/me' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBlbWFpbC5jb20iLCJpYXQiOjE3MDA3NTEyODYsImV4cCI6MTcwMDc1NDg4Nn0.KIOQpQj_Uf8Oa6lSDDAkFgPR3XCFm2WHqMKGS-bJl4c'
```
###### 200 OK
``` json
{
    "fullName": "full name test 1",
    "email": "test1@email.com",
    "password": "$2a$10$ArH01RPUn.P9X5kDralMNO4rxahDXjID4EtWhsM11AyqlY6vBB/jG",
    "createdAt": "2023-11-23T14:54:40.121+00:00",
    "updatedAt": "2023-11-23T14:54:40.121+00:00",
    "enabled": true,
    "accountNonLocked": true,
    "authorities": [],
    "username": "test1@email.com",
    "accountNonExpired": true,
    "credentialsNonExpired": true
}
```
#### GET all users
```bash
curl --location 'http://localhost:8282/users/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUBlbWFpbC5jb20iLCJpYXQiOjE3MDA3NTEyODYsImV4cCI6MTcwMDc1NDg4Nn0.KIOQpQj_Uf8Oa6lSDDAkFgPR3XCFm2WHqMKGS-bJl4c'
```
###### 200 OK
``` json
[
  {
    "fullName": "Full Name 1",
    "email": "test1@email.com",
    "password": "$2a$10$lASGo/FW7dFyREYNuzG.1ubmf4V0DzFKIjxXyd.2ziglsO4/qFcJi",
    "createdAt": "2023-11-23T16:10:09.582+00:00",
    "updatedAt": "2023-11-23T16:10:09.582+00:00",
    "enabled": true,
    "authorities": [],
    "username": "test1@email.com",
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
  },
  {
    "fullName": "Full Name 2",
    "email": "test2@email.com",
    "password": "$2a$10$OPqBKqdJXnZd20BMEcqeXOkV1YLVCJDC28Mp8trISA0F17N3vw5m.",
    "createdAt": "2023-11-23T16:11:20.758+00:00",
    "updatedAt": "2023-11-23T16:11:20.758+00:00",
    "enabled": true,
    "authorities": [],
    "username": "test2@email.com",
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
  }
]
```

| AUTHENTICATION ERROR	               | EXCEPTION THROWN	             | HTTP STATUS CODE |
|:------------------------------------|:------------------------------|:----------------|
| Bad login credentials	              | BadCredentialsException       | 401           |
| Account locked	                     | AccountStatusException        | 403            |
| Not authorized to access a resource | AccessDeniedException        | 403            |
| Invalid JWT		                       | SignatureException       | 401           |
| JWT has expired	                       | ExpiredJwtException       | 401           |
