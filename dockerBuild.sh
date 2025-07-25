#Author Lorenzo Lince
#Shell Script automates the spring boot app docker build
#!/bin/bash
mvn clean install -DskipTests

echo "################# tigo-test-api ######################"
docker build --tag=tigo-test-ui --no-cache -f tigo-test-api/Dockerfile tigo-test-api/.
echo "################# tigo-test-api ######################"
docker build --tag=tigo-test-api -f TigoTest/Dockerfile TigoTest/.



