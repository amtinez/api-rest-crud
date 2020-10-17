# API Rest CRUD

API Rest CRUD with OAuth2

## Script
There is a script in scripts/___apiRestCrud.sh. to pick up the docker containers, compile and run the server from the terminal. 
- In order to make it work we will need the PROJECT_PATH variable with the PATH where the project is located.
```shell
#!/usr/bin/env bash

echo "\nWhat do you want to do?"
echo "1. Remove docker containers"
echo "2. Start docker containers"
echo "3. Compile"
echo "4. Start the server"
echo "5. Compile and start the server"
echo "0. Exit"

PROJECT_PATH=~/personal/api-rest-crud/
```