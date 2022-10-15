# sw-service
## Requirement
Consume the information from SWAPI Extend the data model and implement functionality 
to keep track of the number of units for starships and vehicles.
This can be achieved by adding the count property.

### Feature
- allow to get the total number of units for
a specific starship or vehicle
Example: get how many Death Stars
are in the inventory of starships
- allow to set the total number of units for
a specific starship or vehicle
Example: set the number Death Stars
in the inventory of starships
- allow to increment the total number of
units for a specific starship or vehicle
Example: increment by x units the
number Death Stars in the inventory for
starships
- allow to decrement the total number of
units for a specific starship or vehicle
Example: decrement by x units the
number Death Stars in the inventory for
starships

### Tecnologies
- Java
- JDK 17
- Spring Boot
- Sring Data JPA
- Lombok
- Junit
- H2 Database
- Mysql
- Docker


### Create docker image
- The command must be run in the folder where the dockerfile is
docker build -t sw-servicoe:1.0 .


### Create container 
- To test the application you can create a container after creating the image
- By default it will start from an in-memory database, but if you want to use MYSQL you will need to set the database login 
and password in application-prod and change the environment variable in the dockerfile to prod.
- The container is using the network=host because it is already pre-defined so it is easy if you want to access your machine's MYSQL.
#### Command to create the container.
docker run -p 8100:8100 --network=host --name sw-service sw-service:1.0

### Testing API
After the application is running. Inside the project, there is a collections-postman folder to facilitate testing.
You just need to import in postman and you will be able to do the tests.

![image](https://user-images.githubusercontent.com/17939912/196011058-b7c77259-fdf2-4da5-9316-8f565800a171.png)
![image](https://user-images.githubusercontent.com/17939912/196011066-0a3d01fd-96a4-4a48-ac26-8fa60844e3ee.png)


