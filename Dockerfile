FROM openjdk:17-jdk-alpine3.12

WORKDIR /app

COPY target/MartialArtCMS-0.0.1-SNAPSHOT.jar MartialArtCMS-0.0.1-SNAPSHOT.jar

ENV SPRING_PROFILES_ACTIVE=dev
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/martial_arts_dev
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=1111
ENV REDIS_HOST=localhost
ENV REDIS_PORT=6379

ENTRYPOINT ["java", "-jar","db:3306","--", "target/MartialArtCMS-0.0.1-SNAPSHOT.jar"]