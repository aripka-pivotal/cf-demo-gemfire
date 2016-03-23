#!/bin/bash
if [ "$#" -ne 1 ]
then
  echo "Need to supply the name of the Gemfire Service in the target space. ie: restartCluster.sh gemfire-object-cache"
  exit 1
fi
echo "This will remove all configurations from the server. Are you sure?"
echo "Type 'yes' to confirm 'C' to cancel"
read input
if [ "$input" == "yes" ]; then
	cf restart-gemfire $1 --reset-defaults
fi
