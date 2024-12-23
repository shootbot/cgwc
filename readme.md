# Codingame Winter Challenge 2024 - Sprawl
This is a fork of the original referee, adding the ability to run it through commandline.
## Usage

### Build and use the jar
Building the JAR with a functional viewer has some requirements:
- JDK 17
- Maven
- a recent Node version (successfully tested with Node >= v20.14)
Steps:
- First build the viewer:
  ```bash
  cd src/main/resources
  npm install
  npm run build
  ```
- Then build the Jar:
  ```bash
  mvn package
  ```
You can then run the built Jar (located in `target/winter-2024-sprawl-1.0-SNAPSHOT.jar`) with the command `java -jar winter-2024-sprawl-1.0-SNAPSHOT.jar`.