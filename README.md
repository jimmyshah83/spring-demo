# Spring demo

This project contains example implementation to expose bond information persisted in an in-memory h2 database and exposed via a REST API with spring security.
This application will be deployed to docker container as a spring boot application and  we will also try and deploy this application onto a new AWS EC2 instance.

### Deploy to Docker

1. Create a Dockerfile
Docker can build images by reading instructions from a Dockerfile

2. docker build -t spring-boot-demo .
We will create a new docker image called spring-boot-demo

3. docker system prune
In case of any errors while running command 2, docker prune would clean up all unused images

4. docker run -p 8000:8080 spring-boot-demo
We will now create an instance of the image and start the container by running the docker run command on port 8000

If you need to define multiple docker containers and their relationships, you can use the docker-compose.yml
You can also push the newly created docker image to docker hub as well.