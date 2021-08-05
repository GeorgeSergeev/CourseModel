:: shellcheck disable=SC2164
set IMAGE_NAME="coursemodelback/back:latest"

:: Remove previous image if it exist
  docker image rm -f %IMAGE_NAME%

:: Checks file integrity. And uses Dockerfile from the root of the project to create/replace image
  docker build -t %IMAGE_NAME% .