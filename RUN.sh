if [[ "$OSTYPE" == "msys" ]]; then
  params="./bin/AAAapp.jar;./lib/kotlinx-cli-0.2.1.jar"
else
  params="./bin/AAAapp.jar:./lib/kotlinx-cli-0.2.1.jar"
fi

java -cp $params MainKt $@
