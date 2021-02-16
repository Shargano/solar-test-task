# RT Solar Interview Test Task

### Docs
To view API documentation run the application and follow to `http://{$host}:{$port}/docs`

### Building
In order to build this project, make sure the following are installed:

* [JDK 8+]              (https://jdk.java.net/java-se-ri/11)
* [SBT 1.3.8]           (https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Linux.html)

* **Compiling**
    * **SBT**:
        ```
         sbt compile
        ```
* **Packaging in fat jar**
  * **SBT**:
      ```
       sbt assembly
      ```    
      
### Running

*
    ```
    java -jar rt-solar-cats-effect-{$version}.jar
    ```
