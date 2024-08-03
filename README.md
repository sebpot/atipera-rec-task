# atipera-rec-task
 This project implements a recruitment task for Atipera. Application allows to list 
 all github user repositories with its branches, given user login.

## Built With
- Java 21
- Spring Boot 3.3.2
- Maven 4.0.0
 
## How to Run
- Clone the repository
- You can build the project by running ```mvn package```
- Once built, you can run the service by one of these two methods:
```
   java -jar target/atipera-0.0.1-SNAPSHOT.jar
   
or

   mvn spring-boot:run
```
- To successfully run the service you need to add environment variable responsible for
personal github API token.
```
   API_TOKEN="personal github api token"
```
It can be set in run configuration or in build tools of maven. You can also run the app 
by giving the variable in command:
```
   java -jar  -DAPI_TOKEN="personal github api token" target/atipera-0.0.1-SNAPSHOT.jar
```

## Endpoints
Service offers only one endpoint, for listing github user repositories along with its branches
and last commit sha.
```
   GET /api/users/{user}/repos
   
   Example response:
   {
       "repos": [
        {
            "repositoryName": "spring-app",
            "ownerLogin": "user",
            "branches": [
                {
                    "branchName": "master",
                    "lastCommitSha": "gf6437fg8gf367g4367g"
                }
            ]
        },
        {
            "repositoryName": "simple-frontend",
            "ownerLogin": "user",
            "branches": [
                {
                    "branchName": "master",
                    "lastCommitSha": "hyf67g4389fg3698gf"
                },
                {
                    "branchName": "other branch",
                    "lastCommitSha": "f3g7f6g3489fg64689"
                }
            ]
        }]
   }
   
   RESPONSE: HTTP 200
```

## Author
Sebastian Potrykus - sebpot (github login)
<br/>seba05092001@gmail.com