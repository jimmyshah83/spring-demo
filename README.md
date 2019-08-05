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

### Deploy to Amazon EC2

1. Creating a new EC2 instance on the AWS management console
	I created an ubuntu 18.04 using one of the existing AMI's.

2. Connect to the newly created ec2 instance using the below commands:
	`chmod 400 spring-demo-ec2-ubuntu.pem`
	`ssh -i spring-demo-ec2-ubuntu.pem ubuntu@18.220.180.182`

3. Install the required java version on the ec2 instance. 
	I am using java 8 so I have done the below steps:
	
	`java -version` \n
	`sudo apt update`
	`sudo apt install default-jre`
	`sudo apt install openjdk-8-jdk`
	`sudo update-alternatives --config java`

4. Upload the jar file to Amazon S3

5. Download the uploaded jar using the below command:
	`wget spring.demo-1.0.0-SNAPSHOT.jar .`
	Note that you can download the jar anywhere on your new ubuntu ec2 instance. 
	
6. Run the application using the below command:
	`java -jar spring.demo-1.0.0-SNAPSHOT.jar &`

7. You can test it in your browswe using the below URL:
	ec2-18-220-180-182.us-east-2.compute.amazonaws.com:8080/bonds
	
	Note that this would not work for you as I have spring security enabled and it would ask for credentials.
