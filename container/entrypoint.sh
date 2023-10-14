#!/bin/sh

. /main.sh

check_inputs $1 $2
set_env $1 $2 $3 $4

echo $CHAT_ID
echo $SLACK_TOKEN
echo $MSG_TITLE
echo $JOB_URL
echo $USER_AVATAR
