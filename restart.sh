#!/bin/bash

HEROKU_APP_NAME="maventest"

# Restart the Heroku app
heroku restart --app $HEROKU_APP_NAME

echo "Heroku app $HEROKU_APP_NAME restarted at $(date)"