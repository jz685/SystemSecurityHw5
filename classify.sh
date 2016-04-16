#!/bin/bash

dict="passwordCheck.txt"

if ! [ -e "$dict" ] && ! [ -s "$dict" ]
then
  echo "DOWNLOADING DICTIONARY"
  curl http://download.openwall.net/pub/passwords/wordlists/passwords/password.gz |  gunzip > temp.txt
  curl http://download.openwall.net/pub/wordlists/languages/English/3-large/lower.gz |  gunzip >> temp.txt
  iconv -f utf-8 -t utf-8 -c temp.txt | sed '/^#!comment:/d' > "$dict"
  rm temp.txt
  echo "DICTIONARY DOWNLOADED"
fi

javac classify.java
java classify
