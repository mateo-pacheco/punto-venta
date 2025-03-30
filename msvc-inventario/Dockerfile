FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

ADD ./target/msvc-inventario-0.0.1-SNAPSHOT.jar msvc-inventario.jar

ENTRYPOINT [ "java", "-jar", "msvc-inventario.jar"]