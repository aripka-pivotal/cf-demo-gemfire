#!/bin/bash
if [ -e cluster.zip ]
then
	rm -fr cluster.zip
fi

if [ -e cluster ]
then 
	rm -rf cluster
fi

mkdir cluster
mkdir cluster/lib

cp target/*.jar cluster/lib
zip -r cluster.zip cluster
