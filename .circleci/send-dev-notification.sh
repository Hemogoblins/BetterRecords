#!/bin/bash

COMMITS="$(git reflog dev --since='24 hours ago')"

if [[ -n "$COMMITS" ]]; then
    curl -H "Content-Type: application/json"\
     -X POST $DISCORD_DEV_URL\
     -d "
     {
       \"content\": \"\",
       \"embeds\": [{
           \"title\": \"A New Nightly Build is Available\",
           \"description\": \"$COMMITS\",
           \"url\": \"https://artifactory.feldman.tech/artifactory/webapp/#/artifacts/browse/tree/General/minecraft/com/codingforcookies/betterrecords/BetterRecords-forge/1.12.2-SNAPSHOT/maven-metadata.xml\",
           \"color\": 2068783
       }]
     }
     "
fi
