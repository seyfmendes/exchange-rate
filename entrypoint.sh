#!/bin/sh
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -jar "${HOME}/app.jar" "$@"
