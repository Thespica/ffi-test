# Proof Of Concept 
Add JAVA SDK for GraphAr with FastFFI: 

Map C++ `InfoVersion` to Java:

- static InfoVersion create(int version)
- int version()
- boolean checkType(@CXXReference StdString typeStr)
- @CXXReference StdVector<StdString> userDefineTypes()

---

Require cmake 3.1, LLVM 11 and JDK 8.

1. Build and install fastFFI

    ```sh
    git clone https://github.com/alibaba/fastffi
    export LLVM11_HOME=<path-to-LLVM11-home>    # Ubuntu default: /usr/lib/llvm-11
    cd fastffi
    mvn clean install
    ```

3. Install gar library as GraphAr/cpp/README.rst teaches.

3. Clone this repository and test

    ```shell
    git clone https://github.com/Thespica/ffi-test.git
    cd ffi-test
    export LLVM11_HOME=<path-to-LLVM11-home>
    mvn clean test
    ```

    test successes info:

    ```shell
    [INFO] --- maven-surefire-plugin:3.0.0-M5:test (default-test) @ ffi-test ---
    [INFO] 
    [INFO] -------------------------------------------------------
    [INFO]  T E S T S
    [INFO] -------------------------------------------------------
    [INFO] Running com.alibaba.graphar.utils.InfoVersionTest
    [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.329 s - in com.alibaba.graphar.utils.InfoVers
    ionTest
    [INFO]
    [INFO] Results:
    [INFO]
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  16.396 s
    [INFO] Finished at: 2023-06-18T12:16:28+08:00
    [INFO] ------------------------------------------------------------------------
    ```

    

