@ECHO OFF
SET INIT_SQL_DIR="src/main/resources/db/migration"
SET CUR_DIR=%cd%
SET IMAGE_NAME=course-db
SET CONTAINER_NAME=course-db

:: Checks file integrity. And uses Dockerfile from the root of the project to create/replase image
IF EXIST %INIT_SQL_DIR% (
  cd /D %INIT_SQL_DIR%
  call docker image rm %IMAGE_NAME%:latest >NUL  2>NUL
  call docker build -t %IMAGE_NAME%:latest .
) ELSE (
  ECHO "build docker image is impossible, directory $INIT_SQL_DIR with configuration files is missing"
)

:: Drop previous container if it's run
call docker rm -v -f %CONTAINER_NAME% >NUL  2>NUL

:: Checking if port 5432 is free for use
call netstat -an | findstr /RC:":5432 .*LISTENING" 1>nul 2>nul && (ECHO port 5432 already in use. Please clean it and try again && pause && EXIT 1)

:: Checking if docker image of db is exist. If true create container
FOR /F "Tokens=*" %%I IN ('docker images -q %IMAGE_NAME%') DO SET IMAGE_EXIST=%%I

IF "%IMAGE_EXIST%" NEQ "" (
  call docker run --name=%CONTAINER_NAME% -d -p 5432:5432 %IMAGE_NAME%
  ECHO "container %CONTAINER_NAME% was created"
) ELSE (
ECHO "Error! Docker image does not exist."
)
cd /D %CUR_DIR%