# kata-client
Kata client for accessing kata service.

Prerequisites:
Intall these prerequisites to run the client

    Java 8  
    Maven 3.2+
   
Procedure to run the client.

1) Checkout the repository. 
2) Run `mvn clean install`
3) Make sure in target folder `client-0.0.1-jar-with-dependencies.jar` is created
4) `java -jar target/client-0.0.1-jar-with-dependencies.jar /home/sankara/Carfax/CARFAX/vehicles.csv  http://localhost:8080/`
5) Parameter 1 is the path for the input csv file. In this case `/home/sankara/Carfax/CARFAX/vehicles.csv`
6) Parameter 2 is the url on which REST service is running. In this case `http://localhost:8080/`

Check the server logs for results.
