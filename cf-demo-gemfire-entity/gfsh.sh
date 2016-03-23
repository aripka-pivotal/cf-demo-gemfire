#!/bin/bash
	export WORKING_DIRECTORY=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
	export GEMFIRE=$WORKING_DIRECTORY/Pivotal_GemFire_820_b17919_Linux
	export PATH=$PATH:$JAVA_HOME/bin:$GEMFIRE/bin
	export CLASSPATH=$CLASSPATH:$WORKING_DIRECTORY/cluster.zip
	echo "Gemfire Shell (gfsh) is command-line interface to launch, manage and monitor Gemfire processes"
	echo "Type connect to connect to the grid if its running"
gfsh