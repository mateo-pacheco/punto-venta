FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

ADD ./target/msvc-registro-ventas-0.0.1-SNAPSHOT.jar msvc-registro-ventas.jar

ENTRYPOINT [ "java", "-jar", "msvc-registro-ventas.jar" ]