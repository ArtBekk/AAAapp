if [[ "$OSTYPE" == "msys" ]]; then
  params="./lib/log4j-api-2.14.0;./lib/log4j-core-2.14.0;./lib/kotlinx-cli-0.2.1.jar"
else
  params="./lib/log4j-api-2.14.0:./lib/log4j-core-2.14.0:./lib/kotlinx-cli-0.2.1.jar"
fi
mkdir -p bin
kotlinc -include-runtime src/ -d bin/AAAapp.jar -cp $params
