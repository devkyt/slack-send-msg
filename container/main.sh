#!/bin/sh

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

set_env() {
  export CHAT_ID="$1"
  export SLACK_TOKEN="$2"
  export MSG_TITLE=$(get_msg_title $3)
  export JOB_STATUS=$(get_job_status $4)
  export COMMIT_SHA=$(get_full_commit_sha $5)
  export SHORT_COMMIT_SHA=$(get_full_commit_sha $5)
  export JOB_URL="${GITHUB_SERVER_URL}/${GITHUB_REPOSITORY}/actions/runs/${GITHUB_RUN_ID}"
  export COMMIT_URL="${GITHUB_SERVER_URL}/${GITHUB_REPOSITORY}/commits/${COMMIT_SHA}"
  export USER_AVATAR="https://avatars.githubusercontent.com/u/${GITHUB_ACTOR_ID}"
}


get_msg_title() {
      if  [ -z "$1" ];
      then
          echo "$GITHUB_WORKFLOW"
      else
        echo "$1"
      fi
}

get_job_status() {
      JOB_STATUSES="success failure cancelled"

      if  [ -z "$1" ] || ! echo "$JOB_STATUSES" | grep -q "$1";
      then
          echo "success"
      else
          echo "$1"
      fi
}

get_full_commit_sha() {
  git log -n 1 $1 --pretty=format:"%H"
}

get_short_commit_sha() {
  git log -n 1 $1 --pretty=format:"%h"
}

