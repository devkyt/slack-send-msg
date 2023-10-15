FROM openjdk:21

COPY entrypoint.sh /entrypoint.sh
COPY main.sh /main.sh
COPY ../target/slack-msg-delivery-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

RUN chmod +x entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]