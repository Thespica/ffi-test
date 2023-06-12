#! /bin/bash

DIR=$(pushd $(dirname $BASH_SOURCE[0]) > /dev/null && pwd && popd > /dev/null)

JAVA=$(which java)

if [[ -d "$JAVA_HOME" ]]; then
    JAVA=$JAVA_HOME/bin/java
fi

if [[ ! -x "$JAVA" ]]; then
    echo "\$JAVA_HOME/bin/java points to $JAVA, which is not an executable"
    exit 1
fi

CP=${DIR}/target/benchmarks.jar

VM_OPTS="$VM_OPTS -XX:CompileCommandFile=${DIR}/compile-commands.txt"
VM_OPTS="$VM_OPTS -Xms8G -Xmx8G"
#VM_OPTS="$VM_OPTS -XX:+UseParallelGC"

MAIN_CLASS=${com.alibaba.graphar.utils.InfoVersionTest}

CP=${DIR}/target/llvm4jni-output:$CP
$JAVA $VM_OPTS -cp $CP $MAIN_CLASS -gc true -rff ${DIR}/ffi.csv -bm avgt -tu ns $@
