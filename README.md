# quarkus-poc project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Steps

1. Check the last version of the `quarkus-maven-plugin` on https://search.maven.org/artifact/io.quarkus/quarkus-maven-plugin 
2. Create the project using the plugin:

```bash
mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create \
  -DprojectGroupId=com.github.thibaudledent \
  -DprojectArtifactId=quarkus-poc \
  -DclassName="com.github.thibaudledent.quarkus.poc.HelloResource" \
  -Dpath="/foo"
```

3. klhkl

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