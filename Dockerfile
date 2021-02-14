FROM registry.bss.ural.mts.ru/vision/maven:3.6.3-openjdk-11-slim AS build
#COPY application /app/application
#COPY code-coverage /app/code-coverage
#COPY core /app/core
#COPY query-builder /app/query-builder
USER root
COPY src /app/src
COPY pom.xml /app/pom.xml

WORKDIR /app/

#RUN --mount=type=cache,mode=0777,target=$MAVEN_CONFIG mvn dependency:go-offline -Duser.home=/home/jenkins
RUN mvn clean -B && mvn package -DskipTests -B

FROM registry.bss.ural.mts.ru/vision/openjdk:11.0.9.1-jre-buster
WORKDIR /app/
COPY --from=build /app/target/*.jar ./
RUN mv *.jar bi-system.jar

# Define JVM options
#ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
ENV JVM_MEMORY_OPTIONS="-Xms128m -Xmx1024m -Xss512k -XX:MetaspaceSize=100m" \
    # JVM_REMOTE_DEBUG="-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000" \
    JVM_BASE_OPTIONS="-XX:+UseContainerSupport -XX:+HeapDumpOnOutOfMemoryError -XX:+ExitOnOutOfMemoryError"
    #JVM_JMX_OPTIONS="-Dcom.sun.management.jmxremote.access.file=/var/bi-system/conf/jmxremote.access -Dcom.sun.management.jmxremote.password.file=/var/bi-system/conf/jmxremote.password -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.rmi.port=5670 -Dcom.sun.management.jmxremote.port=5670" \
    #RUN_ARGS="--spring.config.location=./application.yml,classpath:application.yml"

ENTRYPOINT java "-agentlib:jdwp=transport=dt_socket,address=*:8000,server=y,suspend=n" -jar /app/bi-system.jar

EXPOSE 8080
EXPOSE 8000