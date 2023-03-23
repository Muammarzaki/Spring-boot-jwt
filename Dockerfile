FROM alpine

RUN apk add openjdk17

WORKDIR $HOME/app

COPY target/spring-boot-jwt-0.0.1-SNAPSHOT.jar $HOME/app/springbootjwt.jar

EXPOSE 8082

CMD ["java" ,"-jar","springbootjwt.jar"] 
