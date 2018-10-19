Spark QrCode Example
-------------------
Demonstrates some of the capabilities of the Spark Framework (microservices) through a small, simple example.
After reviewing this example, you should have a good understanding of what Spark Framework can do and get a feel for how easy it is to use.
It has the same features as its big brother which is build using SpringBoot: https://github.com/raonigabriel/spring-qrcode-example but it is smaller, cleaner and faster.

#Features:

1. Based on Spark Framework (http://sparkjava.com/)
2. Microservice to generate qrcode images  
  1. Produces binary Content-Type (image/png)
  2. Uses the Google zxing library (https://github.com/zxing/zxing)
  3. HTTP header manipulation (Cache-Control)
  4. Java Exception translation to HTTP status code 500
  5. Uses GET, so it is safe to use with img elements 
3. Backend caching using caffeine memory-based cache (https://github.com/ben-manes/caffeine)
4. Logging with sl4j (http://www.slf4j.org/)
5. Only 2 classes, about 100 lines of code!!! 
6. Small. Final JAR (shaded) includes everything, it self-contained and it is only 2,9 MB

To get the code:
-------------------
Clone the repository:

    $ git clone https://github.com/raonigabriel/spark-qrcode-example.git

If this is your first time using Github, review http://help.github.com to learn the basics.

To run the application:
-------------------	
From the command line with Linux or Windows:

    $ cd spark-qrcode-example
    $ mvn clean package
    $ cd target/
    $ java -jar spark-qrcode-example-1.0.1.jar

From the command line with Mac:

    $ echo 'Do yourself a favor and get LinuxMint!'

Access the deployed web application [http://localhost:8080](http://localhost:8080)


To create docker image (requires docker tools):
-------------------	

    $ mvn clean package dockerfile:build
    
To run the image in foreground:

    $docker run --rm -p 8080:8080 -it raonigabriel/spark-qrcode-example


To run the image in background, add  "-d" flag and don use the "--rm" option
    
    $docker run -p 8080:8080 -it -d raonigabriel/spark-qrcode-example

To work on the code:
-------------------	
In your preferred IDE such as Eclipse or IDEA:

* Import spark-qrcode-example as a Maven Project

## License

Released under the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html)
