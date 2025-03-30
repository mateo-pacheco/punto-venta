FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

ADD ./target/msvc-punto-venta-0.0.1-SNAPSHOT.jar msvc-punto-venta.jar

ENTRYPOINT [ "java", "-jar", "msvc-punto-venta.jar" ]