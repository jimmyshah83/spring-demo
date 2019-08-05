FROM openjdk:8
COPY ./target/spring.demo-1.0.0-SNAPSHOT.jar /Users/jiviteshshah/projects/docker/springDemo/
WORKDIR /Users/jiviteshshah/projects/docker/springDemo
EXPOSE 8080
CMD ["java", "-jar", "spring.demo-1.0.0-SNAPSHOT.jar"]