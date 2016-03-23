#!/bin/bash
if [ "$#" -ne 1 ]
then
  echo "Need to supply the name of the Gemfire Service in the target space. ie: getGFLogs.sh gemfire-object-cache"
  exit 1
fi
rm -fr logs
mkdir logs
cf export-gemfire $1 -l ./logs/logs.zip