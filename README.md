# Slack Send Message

GitHub Action to send a message with your pretty face and commit info to a Slack channel. 

This action uses [Slack App](https://api.slack.com/start/apps) and [Web API](https://api.slack.com/web) instead of the webhooks.

<br>

## Preview
![alt text](https://github.com/s8ckerpunch/slack-send-msg/blob/main/images/examples/base.png)

More examples [here](https://github.com/s8ckerpunch/slack-send-msg/tree/main/images/examples)

<br>

## Usage
```yaml
  - name: Send Message to Slack
    uses: s8ckerpunch/slack-send-msg@v0.1
    with:
      slack_token: ${{ secrets.SLACK_TOKEN }}
      chat_id: ${{ secrets.SLACK_CHAT_ID }}
```

<br>

## Inputs

| Input          | Required |                                              Default                                              | Description                                                                                                       |
|----------------|:--------:|:-------------------------------------------------------------------------------------------------:|-------------------------------------------------------------------------------------------------------------------|
| chat_id        |   true   |                                                 -                                                 | An id of a chat where to send a message                                                                           |
| slack_token    |   true   |                                                 -                                                 | Oauth token for an app with chat:write scope                                                                      |
| message_title  |  false   |                                           Workflow name                                           | Title for the message                                                                                             |
| custom_message |  false   |                                                 -                                                 | Custom message to replace default one                                                                             |
| job_status     |  false   |                                              Success                                              | Status of a current job. Message color depends on it: green for success, red for failure, and yellow for canceled |
| environment    |  false   |                                                 -                                                 | Environment in which job is running                                                                               |
| app_name       |  false   |                                             Workflows                                             | App username in the chat                                                                                          |
| app_image      |  false   | [Octocat](https://github.com/s8ckerpunch/slack-send-msg/blob/main/images/app-avatar.png?raw=true) | App avatar in the chat                                                                                            |
<br>

## Some notes to know

- You can create your own Slack app and receive a token for it by following this [guide](https://api.slack.com/start/quickstart)
- Add chat:write.customize scope to your token if you want to customize the app name and avatar
- Set a custom message for action when you run workflow manually (via workflow_dispatch event).
In another case, main branch will be used as a source for message info.
