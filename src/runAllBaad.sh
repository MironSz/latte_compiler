#!/bin/bash
for f in ../lattests/bad/*.lat; do
  echo $f
  java -classpath "./java_cup.jar:./" latte/Main $f
done

