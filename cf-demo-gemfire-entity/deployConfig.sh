#!/bin/bash
if [ "$#" -ne 1 ]
then
  echo "Need to supply the name of the Gemfire Service in the targer space. ie: deployConfig.sh gemfire-object-cache"
  exit 1
fi
echo "Running cf restart-gemfire $1 --cluster-config ./cluster.zip --spring-xml /cache-config.xml"
cf restart-gemfire $1 --cluster-config ./cluster.zip --spring-xml /cache-config.xml
