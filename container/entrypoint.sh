#!/bin/sh

. /main.sh

check_inputs "$1" "$2"

set_env "$1" "$2" "$3" "$4" "$5" "$6" "$7" "$8"

java -jar /app.jar