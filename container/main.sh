#!/bin/sh

DEFAULT_TITLE="$GITHUB_WORKFLOW"
DEFAULT_APP_NAME="GitHub Actions"
DEFAULT_APP_AVATAR="https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png"

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
    export MSG_TITLE=$(add_env_to_title "$(get_msg_title "$3")" "$6")
    export CUSTOM_MSG="$4"
    export JOB_STATUS=$(get_job_status "$5")
    export JOB_URL="${GITHUB_SERVER_URL}/${GITHUB_REPOSITORY}/actions/runs/${GITHUB_RUN_ID}"
    export BRANCH="${GITHUB_REF##*/}"
    export BRANCH_URL="${GITHUB_SERVER_URL}/${GITHUB_REPOSITORY}/tree/${BRANCH}"
    export COMMIT_SHA=$(get_short_commit_id)
    export COMMIT_URL="${GITHUB_SERVER_URL}/${GITHUB_REPOSITORY}/commits/${GITHUB_SHA}"
    export USERNAME="${GITHUB_ACTOR}"
    export USER_AVATAR="https://avatars.githubusercontent.com/u/${GITHUB_ACTOR_ID}"
    export APP_NAME=$(get_app_name "$7")
    export APP_AVATAR=$(get_app_avatar "$8")
}


add_env_to_title() {
      if [ -n "$2" ];
      then
          echo "$1: $2"
      else
          echo "$1"
      fi
}


get_msg_title() {
    if [ -z "$1" ];
    then
        echo "$DEFAULT_TITLE"
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


get_short_commit_id() {
   echo "${GITHUB_SHA}" | head -c7
}


get_app_name() {
    if  [ -z "$1" ];
    then
        echo "$DEFAULT_APP_NAME"
    else
        echo "$1"
    fi
}


get_app_avatar() {
    if  [ -z "$1" ];
    then
        echo "$DEFAULT_APP_AVATAR"
    else
        echo "$1"
    fi
}

