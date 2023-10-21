# Slack Send Message

GitHub Action to send a message with your pretty face and commit info to Slack channel. 

**Note**: this action uses [Slack App](https://api.slack.com/start/apps) and [Web API](https://api.slack.com/web) instead of the webhooks.

## Preview
-------


## Usage
-------


#https://api.slack.com/messaging/sending

## Inputs
-------

| Input          | Required | Default       | Description                                                 |
|----------------|----------|---------------|-------------------------------------------------------------|
| chat_id        | true     | -             | An id of chat where to send message                         |
| slack_token    | true     | -             | App Oauth token with permission chat:write                  |
| message_title  | false    | Workflow name | Title for the message. Workflow name is used by default     |
| custom_message | false    | -             | Custom message. If specified default one will be ignored    |
| job_status     | false    | Success       | Status of a current job. Color of the message depends on it |
| environment    | false    | -             | Environment in which job is running                         |
| app_name       | false    | Workflow      | Will be shown as app username in chat                       |
| app_image      | false    | -             | Will be shown as app avatar in chat                         |