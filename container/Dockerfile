FROM azul/zulu-openjdk-alpine:21-jre

LABEL app.name="slack-send-msg" \
      app.version="0.1"

RUN apk update \
    && apk add --no-cache jq

COPY entrypoint.sh main.sh app.jar ./

RUN chmod +x entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]