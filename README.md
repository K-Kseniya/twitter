# Simple Social Network Example

###1. Technologies
* Spring 4.3.1.RELEASE
* Maven 3.3.3

###2. To Run this project locally
```shell
$ git clone https://github.com/K-Kseniya/twitter.git
$ mvn tomcat7:run
```
Access ```http://localhost:8080/springrest/twitter/```

###3. Usage examples:
curl -H "Content-Type: application/json" -X POST -d '''
{"email": "email1@gmail.com", "message": "My first message"}
''' localhost:8080/springrest/twitter/

curl -H "Content-Type: application/json" -X POST -d '''
{"email": "email2@gmail.com", "message": "My second message"}
''' localhost:8080/springrest/twitter/

curl -H "Content-Type: application/json" -X POST -d '''
{"email": "email1@gmail.com", "message": "My first message 2"}
''' localhost:8080/springrest/twitter/

curl -H "Accept: application/json" http://localhost:8080/springrest/twitter/wall/email1%40gmail.com/
curl -H "Accept: application/json" http://localhost:8080/springrest/twitter/wall/email2%40gmail.com/

curl -H "Content-Type: application/json" -X POST -d '''
{"userEmail": "email@gmail.com", "friendEmail": "email1@gmail.com"}
''' localhost:8080/springrest/twitter/follow

curl -H "Content-Type: application/json" -X POST -d '''
{"userEmail": "email@gmail.com", "friendEmail": "email2@gmail.com"}
''' localhost:8080/springrest/twitter/follow

curl -H "Accept: application/json" http://localhost:8080/springrest/twitter/timeline/email%40gmail.com/

###4. Documentation can be found via http://localhost:8080/springrest/twitter/swagger-ui.html

