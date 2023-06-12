# Proof Of Concept 
Add JAVA SDK for GraphAr with FastFFI

Require cmake 3.1, LLVM 11 and JDK 8.

1. Build and install fastFFI

    ```sh
    git clone https://github.com/alibaba/fastffi
    export LLVM11_HOME=<path-to-LLVM11-home>    # Ubuntu default: /usr/lib/llvm-11
    cd fastffi
    mvn clean install
    ```

2. Download and build graphar

    ```sh
    bash build-graphar.sh
    ```

3. Build source code

    ```sh
    export LLVM11_HOME=<path-to-LLVM11-home>
    mvn clean package
    ```
    
4. How to run test?
