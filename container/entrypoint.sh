#!/bin/sh

. /main.sh

check_inputs "$1" "$2"
set_env "$1" "$2" "$3" "$4" "$5" "$6" "$7" "$8"

#echo $CHAT_ID
#echo $MSG_TITLE
#echo $JOB_STATUS
#echo $JOB_URL
#echo $USER_AVATAR
#echo $COMMIT_SHA
#echo $COMMIT_URL
#
#cat "$GITHUB_EVENT_PATH"

java -jar /app.jar