#!/usr/bin/env bash

echo "\nWhat do you want to do?"
echo "1. Remove docker containers"
echo "2. Start docker containers"
echo "3. Compile"
echo "4. Start the server"
echo "5. Compile and start the server"
echo "6. Execute Sonarqube report"
echo "0. Exit"

PROJECT_PATH=[YOUR PROJECT PATH]

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
			docker rm arc-mysql-test -f
			docker rm arc-sonarqube -f
			break
			;;
		2)
			echo "Starting docker containers..."
			cd $PROJECT_PATH/docker && docker-compose up -d
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
		6)
			echo "Executing Sonarqube report..."
			cd $PROJECT_PATH && ./gradlew check && ./gradlew jacocoTestReport && ./gradlew sonarqube
			break
			;;
		*)
			echo "Select a correct option"
			;;    
  esac
done