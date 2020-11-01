mkdir -p bin
kotlinc -include-runtime src/ -d bin/AAAapp.jar -cp ./lib/kotlinx-cli-0.2.1.jar