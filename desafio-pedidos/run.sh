#!/bin/bash

tag=$(git symbolic-ref HEAD 2>/dev/null | awk -F '/' '{print $NF}')
mvn clean package docker:build -DdockerImageTags=$tag
docker run -p :8080 -d desafio-bluesoft:$tag
