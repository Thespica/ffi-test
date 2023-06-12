#! /bin/bash

DIR=$(pushd $(dirname $BASH_SOURCE[0]) > /dev/null && pwd && popd > /dev/null)

SRC_DIR=${DIR}/graphar-src


if [[ ! -d $SRC_DIR ]]; then
    git clone --depth 1 https://github.com/alibaba/GraphAr.git $SRC_DIR
fi
SRC_DIR=${SRC_DIR}/cpp

pushd $SRC_DIR

#rm -rf build
mkdir -p build
pushd build
cmake ..
make -j`nproc`

popd
popd
