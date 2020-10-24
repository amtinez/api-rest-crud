#!/usr/bin/env bash

echo "\nWhat do you want to do?"
echo "1. Remove docker containers"
echo "2. Start docker containers"
echo "3. Compile"
echo "4. Start the server"
echo "5. Compile and start the server"
echo "0. Exit"

PROJECT_PATH=~/personal/api-rest-crud

while :
do
	read OPTION
	echo "\nYou have selected:" $OPTION
	case $OPTION in
		0)
			echo "Disconnected\n"
			break
			;;
		1)
			echo "Removing docker containers..."
			docker rm arc-mysql -f
			break
			;;
		2)
			echo "Starting docker containers..."
			cd $PROJECT_PATH/docker/ && docker-compose up -d
			break
			;;
		3)
			echo "Compiling..."
			cd $PROJECT_PATH && ./gradlew classes && ./gradlew testClasses
			break
			;;
		4)
			echo "Starting the server..."
			cd $PROJECT_PATH && ./gradlew bootRun
			break
			;;
		5)
			echo "Compiling and starting the server..."
			cd $PROJECT_PATH && ./gradlew classes && ./gradlew testClasses && ./gradlew bootRun
			break
			;;
		*)
			echo "Select a correct option"
			;;    
  esac
done