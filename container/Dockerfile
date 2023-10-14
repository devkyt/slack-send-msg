FROM alpine:3.10

COPY entrypoint.sh /entrypoint.sh
COPY helpers.sh /helpers.sh

RUN chmod +x entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]