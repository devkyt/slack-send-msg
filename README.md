# Slack Send Message

GitHub Action to send a message with your pretty face and commit info to a Slack channel. 

This action uses [Slack App](https://api.slack.com/start/apps) and [Web API](https://api.slack.com/web) instead of the webhooks.

## Preview
![](/Users/kirilltikhanskiy/Desktop/Screenshot 2023-10-24 at 19.16.52.png)

More examples here

## Usage
```yaml
  - name: Send Message to Slack
    uses: s8ckerpunch/slack-send-msg@v0.1
    with:
      slack_token: ${{ secrets.SLACK_TOKEN }}
      chat_id: ${{ secrets.SLACK_CHAT_ID }}
```

## Inputs

| Input          | Required |    Default    | Description                                                                                                       |
|----------------|:--------:|:-------------:|-------------------------------------------------------------------------------------------------------------------|
| chat_id        |   true   |       -       | An id of a chat where to send message                                                                             |
| slack_token    |   true   |       -       | Oauth token for an app with <span style="background-color:#818589">chat:write</span> scope                        |
| message_title  |  false   | Workflow name | Title for the message.                                                                                            |
| custom_message |  false   |       -       | Custom message provided by user to replace default one                                                            |
| job_status     |  false   |    Success    | Status of a current job. Message color depends on it: green for success, red for failure and yellow for cancelled |
| environment    |  false   |       -       | Environment in which job is running                                                                               |
| app_name       |  false   |   Workflows   | App username in the chat                                                                                          |
| app_image      |  false   |    Octocat    | App avatar in the chat                                                                                            |


## Some notes to know

- You can create your own Slack app and receive token for it by following this [guide](https://api.slack.com/start/quickstart)
- If you want to customize app name and avatar, your Oauth token must have <span style="background-color:#818589">chat:write.customize</span> scope
- If you run your workflow manually (via workflow_dispatch event) you should set custom message for action.
In other case main branch will be used as a source for message info. 
