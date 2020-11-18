if [[ "$OSTYPE" == "msys" ]]; then
  params="./lib/log4j-api-2.14.0.jar;./lib/log4j-core-2.14.0.jar;./lib/kotlinx-cli-0.2.1.jar;./lib/h2-1.4.200.jar;./lib/flyway-core-7.2.0.jar;."
else
  params="./lib/log4j-api-2.14.0.jar:./lib/log4j-core-2.14.0.jar:./lib/kotlinx-cli-0.2.1.jar:../lib/h2-1.4.200.jar:./lib/flyway-core-7.2.0.jar:."
fi
mkdir -p bin
kotlinc-jvm -include-runtime -d bin/AAAapp.jar -cp $params src/
jar uf bin/AAAapp.jar -C src log4j2.xml