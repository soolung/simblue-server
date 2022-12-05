FROM openjdk:11-jre-slim
EXPOSE 8080
ENV TZ=Asia/Seoul
COPY .env ./
RUN export $(cat .env | xargs)
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
