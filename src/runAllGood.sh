#!/bin/bash
for f in ../lattests/good/*.lat; do
  echo $f
  java -classpath "./java_cup.jar:./" latte/Main $f
done
