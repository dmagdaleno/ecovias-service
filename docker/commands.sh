./gradlew clean build

cp build/libs/ecovias-service*.jar docker/ecovias-service.jar

docker build -t ecovias-service:v0.0.1 -t ecovias-service:latest docker/

docker-compose -f docker/docker-compose.yaml up -d

docker-compose -f docker/docker-compose.yaml down
