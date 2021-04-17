#!/bin/sh
git pull origin master
now="$(date): update all by king"

git add -A README.md
git add -A "King/*"
git add -A update-King.sh

git commit -m "$now"
git push origin master