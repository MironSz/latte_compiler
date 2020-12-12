#!/bin/bash
for f in ../lattests/bad/*.lat; do
  echo $f
  java -classpath "./java_cup.jar:./" latte/Main $f
done

for f in ../additional_tests/mrjp-tests/bad/semantic/*lat ; do
  echo $f
  java -classpath "./java_cup.jar:./" latte/Main $f
done

