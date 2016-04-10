#!/bin/bash

dict="dictionary.txt"

if ! [ -e "$dict" ] && ! [ -s "$dict" ]
then
  echo "DOWNLOADING DICTIONARY"
  curl http://download.openwall.net/pub/wordlists/all.gz |  gunzip > passwordCheck.txt
  iconv -f utf-8 -t utf-8 -c temp.txt | sed '/^#!comment:/d' > "$dict"
  rm temp.txt
  echo "DICTIONARY DOWNLOADED"
fi

java classify