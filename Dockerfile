FROM openjdk:8u181-jre-slim
COPY /build/libs/pc-news.jar pc-news.jar
ENTRYPOINT ["java",  "-jar","/pc-news.jar"]
