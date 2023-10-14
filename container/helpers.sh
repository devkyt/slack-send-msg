#!/bin/sh

set_env() {
  export CHAT_ID="$1"
  export SLACK_TOKEN="$2"
  export MSG_TITLE=$(get_msg_title $3)
  export JOB_URL="${GITHUB_SERVER_URL}/${GITHUB_REPOSITORY}/actions/runs/${GITHUB_RUN_ID}"
  export COMMIT_URL="${GITHUB_SERVER_URL}/${GITHUB_REPOSITORY}/commits/${GITHUB_SHA}"
  export USER_AVATAR="https://avatars.githubusercontent.com/u/${GITHUB_ACTOR_ID}"
}

check_inputs() {
      if  [ -z "$1" ];
      then
          echo "Error: chat id must be provided"
          exit 1
      fi

      if  [ -z "$2" ];
      then
            echo "Error: token must be provided"
            exit 1
      fi
}

get_msg_title() {
      if  [ -z "$1" ];
      then
          echo "$GITHUB_WORKFLOW"
      else
        echo "$1"
      fi
}