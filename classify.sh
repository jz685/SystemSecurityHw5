#!/bin/bash

dict="dictionary.txt"

if ! [ -e "$dict" ] && ! [ -s "$dict" ]
then
  echo "DOWNLOADING DICTIONARY"
  curl http://download.openwall.net/pub/passwords/wordlists/passwords/password.gz |  gunzip > passwordCheck.txt
  curl http://download.openwall.net/pub/passwords/wordlists/languages/English/3-large/lower.gz |  gunzip >> passwordCheck.txt
  iconv -f utf-8 -t utf-8 -c temp.txt | sed '/^#!comment:/d' > "$dict"
  rm temp.txt
  echo "DICTIONARY DOWNLOADED"
fi

java classify