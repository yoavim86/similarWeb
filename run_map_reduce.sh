#!/bin/sh

function runMapReduce()
{
hadoop fs -mkdir -p webSimilarities/input
hadoop fs -mkdir -p webSimilarities/output
hadoop fs -mkdir -p out1
hadoop fs -mkdir -p out2

hadoop fs -copyFromLocal python_test/input.tsv webSimilarities/input
hadoop fs -rm -r out1/ 
hadoop fs -rm -r out2/
hadoop fs -rm -r webSimilarities/output/

mvn clean install
hadoop jar target/SimilarWeb-0.0.1-SNAPSHOT.jar SimilarWebJob webSimilarities/input webSimilarities/output

hadoop fs -cat webSimilarities/output/part-r-00000 > similarWebResults.txt
}

runMapReduce

