## Local Development

### Prerequisites

Ensure you have the following installed on your machine:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase/jdk22-archive-downloads.html) (version 22)
- [Maven](https://maven.apache.org/download.cgi)(version 3.8 or above)


To build the project, navigate to the root directory of the project and run:

```bash
mvn clean install
```
This command will compile the code, run tests, and package

### Running the Services

#### Be sure that port 8081 isn't already in use before running.

To run the project buy Maven run the command
```bash
mvn spring-boot:run 
```
or you can run a jar file. To run the jar file navigate to directory called "target" from root directory of the project and run:
```bash
java -jar CheapestTransferRoute-0.0.1-SNAPSHOT.jar
```

### Example CURL requests

```bash
curl -X GET "http://localhost:8081/api/route" \
  -H "Content-Type: application/json" \
  -H "Accept: */*" \
  -d '{
    "maxWeight": 15,
    "availableTransfers": [
      {
        "weight": 5,
        "cost": 10
      },
      {
        "weight": 10,
        "cost": 20
      },
      {
        "weight": 3,
        "cost": 5
      },
      {
        "weight": 8,
        "cost": 15
      }
    ]
  }'
```
and response will be:


```bash
{"totalWeight":15,"totalCost":30,"selectedTransfers":[{"weight":10,"cost":20},{"weight":5,"cost":10}]}
```