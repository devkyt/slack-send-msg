#!/bin/bash

echo "$1"
echo "$GITHUB_SERVER_URL/$GITHUB_REPOSITORY/actions/runs/$GITHUB_RUN_ID"
echo "$GITHUB_SERVER_URL/$GITHUB_REPOSITORY/commits/$GITHUB_SHA"
echo "$GITHUB_ACTOR_ID"

