# quarkus-poc project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/.

## How to...

### Create the Java project

1. Check the last version of the `quarkus-maven-plugin` on https://search.maven.org/artifact/io.quarkus/quarkus-maven-plugin 
2. Create the project using the plugin:

```bash
mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create \
  -DprojectGroupId=com.github.thibaudledent \
  -DprojectArtifactId=quarkus-poc \
  -DclassName="com.github.thibaudledent.quarkus.poc.HelloResource" \
  -Dpath="/quizz"
```

3. `mvn compile quarkus:dev`
4. Try it out `curl -X GET "localhost:8080/quizz"`

### Run a Containerized Native Image

In order to build a  native image, you don’t need to have GraalVM configured locally, Quarkus can use a dedicated containerized version of GraalVM for that:

1. `mvn package -Pnative -Dquarkus.native.container-build=true`
2. `docker build -f src/main/docker/Dockerfile.native -t thibaudledent/quarkus-poc .`

A Docker image is created, you can check its size with `docker images -a | head`:

``` docker images -a | head
REPOSITORY                  TAG     IMAGE ID       CREATED           SIZE
thibaudledent/quarkus-poc   latest  d0d0209d1325   13 seconds ago    155MB
```

3. `docker run thibaudledent/quarkus-poc`

Starting time is fast `time docker run thibaudledent/quarkus-poc`:

```bash
__  ____  __  _____   ___  __ ____  ______
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2020-03-27 17:23:10,191 INFO  [io.quarkus] (main) quarkus-poc 1.0-SNAPSHOT (powered by Quarkus 1.3.0.Final) started in 0.027s. Listening on: http://0.0.0.0:8080
2020-03-27 17:23:10,191 INFO  [io.quarkus] (main) Profile prod activated.
2020-03-27 17:23:10,191 INFO  [io.quarkus] (main) Installed features: [cdi, resteasy]
^C2020-03-27 17:23:11,081 INFO  [io.quarkus] (main) quarkus-poc stopped in 0.005s
docker run thibaudledent/quarkus-poc  0.03s user 0.02s system 3% cpu 1.669 total
```

3. Try it out `docker exec -it $(docker ps | grep thibaudledent | awk '{print $1}') /bin/bash -c "curl -X GET 'localhost:8080/quizz'"`

### Running a Standalone Jar

```bash
mvn package
java -jar target/quarkus-poc-1.0-SNAPSHOT-runner.jar
```
### Deploying the Standalone Jar on AWS Lambda

1. Create a function with Java runtime

<img src="screenshot_1.png" style="zoom:50%;" />

2. Upload the jar

Use `com.github.thibaudledent.quarkus.poc.HelloResource::hello `as handler

<img src="screenshot_2.png" style="zoom:50%;" />

3. Test it

<img src="screenshot_3.png" style="zoom:50%;" />

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `quarkus-poc-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/quarkus-poc-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/quarkus-poc-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide.