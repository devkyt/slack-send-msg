# Slack Send Message

GitHub Action to send a message with your pretty face and commit info to a Slack channel. 

**Note**: this action uses [Slack App](https://api.slack.com/start/apps) and [Web API](https://api.slack.com/web) instead of the webhooks.

## Preview


## Usage
```yaml
  - name: Send Message to Slack
    uses: s8ckerpunch/slack-send-msg@v0.1
    with:
      slack_token: ${{ secrets.SLACK_TOKEN }}
      chat_id: ${{ secrets.SLACK_CHAT_ID }}
```




## Inputs

| Input          | Required | Description                                             |
|----------------|----------|---------------------------------------------------------|
| chat_id        | true     | An id of a chat where to send message                   |
| slack_token    | true     | Oauth token for an app with permission chat:write       |
| message_title  | false    | Title for the message. Workflow name is used by default |
| custom_message | false    | Custom message provided by user to replace default one  |
| job_status     | false    | Status of a current job. Message color depends on it    |
| environment    | false    | Environment in which job is running                     |
| app_name       | false    | App username in the chat                                |
| app_image      | false    | App avatar in the chat                                  |

