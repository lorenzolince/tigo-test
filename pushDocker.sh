#Author Lorenzo Lince
#Shell docker build
#!/bin/bash

echo "####################  tigo-test-ui ######################"
docker image tag tigo-test-ui lorenzolince/tigo-test-ui
docker push lorenzolince/tigo-test-ui
echo "####################  tigo-test-api ######################"
docker image tag tigo-test-api lorenzolince/tigo-test-api
docker push lorenzolince/tigo-test-api

