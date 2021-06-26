# VERX - Challenge project for selection process
#### This is a project for the [VERX](https://www.verx.com.br/) Selection process.


## API
As a requirement, 2 http endpoints were created:
```
GET - <host>/convert/{srcCurrency}/{targetCurrency}/{amount}/{idUser}
GET - <host>/transaction/{idUser}
```

The results must provide the following data structure in JSON format as a response
``` json
{
   "id":"60d7a9b2e3e923064eaed2db",
   "userId":"renato",
   "srcCurrency":"USD",
   "amount":13000.0,
   "targetCurrency":"BRL",
   "exchangeRate":4.934504570169485,
   "date":null,
   "exchangeAmount":64148.55941220331
}
```

## Must haves

* Internal logic shall be under unit test
* Functionality shall be under integration test
* Clear and "to the point" readme on how to comsume

# The Project

Developed with [Eclipse Oxygen](https://www.eclipse.org/oxygen/).

## Technologies

This project was built using [Spring Boot](https://spring.io/projects/spring-boot) because of its simplicity, easy setup and startup, almost intuitive configuration and wide range of comunities and forums.

The technologies behind it are:
* Spring web, using rest controllers to implement the Controller layer of the MVC design pattern;
 * Tomcat as the embedded web container;
 * Spring data, to implement the Model/Persistence layer:
   * Embedded MongoDB
 
 ### Hows and Whys
 
 As mentioned above, this project was written using spring boot. The reason is because it is currently the best alternative for fast and small projects, that provides a wide variety of tools, frameworks, configurations and ease of use.
 
 The database used to accomplish the requirement is the embedded mongodb. There were specific demands concerning the database type. The only demand is that the database must be embedded.
 
 # Running it
 
 Java JDK/JRE 1.8+ and Maven 3.0+ are required to be installed in the machine so everything works.
 
 To run the application, open the **project** folder.
### To simply start the application, in command line, run the following script:
#### Windows
```
$ mvn_run.cmd
```
#### Linux
```
$ mvn_run.sh
```


### To run the application test cases, in the command line, run the following:
#### Windows
```
$ mvn_tests.cmd
```
#### Linux
```
$ mvn_tests.sh
```

