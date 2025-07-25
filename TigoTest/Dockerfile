FROM openjdk:18-jdk-alpine3.14
MAINTAINER Lorenzo Lince<lorenzolince@gmail.com>

RUN apk add --update netcat-openbsd && rm -rf /var/cache/apk/*
RUN apk add --no-cache cifs-utils
COPY target/*.jar /app.jar
RUN mkdir -p /app
RUN echo "/bin/sh -c " > entrypoint.sh
RUN echo "java \$API_JAVA_OPTION -jar /app.jar --server.port=80" >> entrypoint.sh
RUN adduser -s /bin/true -u 1000 -D -h /app app \
  && chown -R app:app /app \
  && chown -R app:app  /app \
  && chmod 700 /app \
  && chown -R app:app  /app.jar \
  && chmod 700 /app.jar \
  && chmod 755 entrypoint.sh
USER app
RUN cat entrypoint.sh

CMD /bin/sh ./entrypoint.sh