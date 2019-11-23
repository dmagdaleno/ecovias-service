# EcoVIAS REST API

## Build

### Build project

./gradlew clean build

### Copy JAR to docker folder

cp build/libs/ecovias-service*.jar docker/ecovias-service.jar

### Build docker image

docker build -t ecovias-service:v0.0.1 -t ecovias-service:latest docker/

### Stop and Rerun service

docker-compose -f docker/docker-compose.yaml down

docker-compose -f docker/docker-compose.yaml up -d