# CourseModel

* Автор: Новосельцев Никита Евгеньевич
* Контакты: nick.novoseltsev@gmail.com, +79789052606

## Build the JARs and Docker images
To build application docker image run script from root directory:
* on Windows:
```
build-local-back.cmd
```
* on Linux:
```
build-local-back.sh
```
To run the application on dockers type from root directory:
```
docker-compose up -d
```
To run only database:
```
docker-compose up -d postgres
```
To run only backend:
```
docker-compose up -d postgres course-model-back
```
:warning: If it asks you smth - answer him ``yes``

To stop application:
```
docker-compose down
```
Required:
- docker;
- free ports 5432, 80, 8080;

