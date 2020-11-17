if [[ "$OSTYPE" == "msys" ]]; then
  params="./bin/AAAapp.jar;./lib/log4j-api-2.14.0;./lib/log4j-core-2.14.0;./lib/kotlinx-cli-0.2.1.jar"
else
  params="./bin/AAAapp.jar:./lib/log4j-api-2.14.0:./lib/log4j-core-2.14.0:./lib/kotlinx-cli-0.2.1.jar"
fi

java -cp $params MainKt $@
