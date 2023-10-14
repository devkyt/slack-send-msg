FROM alpine:3.10

COPY entrypoint.sh /entrypoint.sh
COPY main.sh /main.sh

RUN chmod +x entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]