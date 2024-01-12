FROM openjdk:17 as build
COPY . /home/users
WORKDIR /home/users
RUN microdnf install findutils
RUN ./gradlew build

FROM openjdk:17 as run
COPY --from=build /home/users/build/libs/users-0.0.1-SNAPSHOT.jar /home/users/users.jar
WORKDIR /home/users
ENTRYPOINT ["java","-jar","users.jar"]