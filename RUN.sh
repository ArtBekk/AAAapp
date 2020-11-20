if [ -z "$URL" ]; then
    export URL=jdbc:h2:file:./db/aaa
fi
if [ -z "$LOGIN" ]; then
    export LOGIN=ArtBekk
fi
if [ -z "$PASS" ]; then
    export PASS=3678
fi

if [[ "$OSTYPE" == "msys" ]]; then
  params="./bin/AAAapp.jar;./lib/log4j-api-2.14.0.jar;./lib/log4j-core-2.14.0.jar;./lib/kotlinx-cli-0.2.1.jar;./lib/h2-1.4.200.jar;./lib/flyway-core-7.2.0.jar;."
else
  params="./bin/AAAapp.jar:./lib/log4j-api-2.14.0.jar:./lib/log4j-core-2.14.0.jar:./lib/kotlinx-cli-0.2.1.jar:./lib/h2-1.4.200.jar:./lib/flyway-core-7.2.0.jar:."
fi

java -cp $params MainKt $@
