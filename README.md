# Slack Send Message

GitHub Action to send a message with your pretty face and commit info to Slack channel. 

**Note**: this action uses [Slack App](https://api.slack.com/start/apps) and [Web API](https://api.slack.com/web) instead of the webhooks.

## Preview


## Usage




## Inputs

| Input          | Required | Description                                                 |
|----------------|----------|-------------------------------------------------------------|
| chat_id        | true     | An id of chat where to send message                         |
| slack_token    | true     | Oauth token for app with permission chat:write              |
| message_title  | false    | Title for the message. Workflow name is used by default     |
| custom_message | false    | If specified default one will be ignored                    |
| job_status     | false    | Status of a current job. Color of the message depends on it |
| environment    | false    | Environment in which job is running                         |
| app_name       | false    | Will be shown as app username in chat                       |
| app_image      | false    | Will be shown as app avatar in chat                         |