# Proof Of Concept 
Add JAVA SDK for GraphAr with FastFFI: If we do it successfully, 
we can consider that it proof the concept:

- wright `InfoVersion` class by FastFFI, including two method:
   - `InfoVersion Factory.creat(int version)`
   - `int version()`
- getcall
its right number by calling `version()` method.

---

Require cmake 3.1, LLVM 11 and JDK 8.

1. Build and install fastFFI

    ```sh
    git clone https://github.com/alibaba/fastffi
    export LLVM11_HOME=<path-to-LLVM11-home>    # Ubuntu default: /usr/lib/llvm-11
    cd fastffi
    mvn clean install
    ```

3. Build source code

    ```sh
    export LLVM11_HOME=<path-to-LLVM11-home>
    mvn clean package
    ```
   If remove all test, `mvn clean package` will success. But it will meet error with test:
```shell
[INFO] Running com.alibaba.graphar.utils.InfoVersionTest
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.045 s <<< FAILURE! - in com.alibaba.graphar.
utils.InfoVersionTest
[ERROR] com.alibaba.graphar.utils.InfoVersionTest.testInfoVersion  Time elapsed: 0.017 s  <<< ERROR!
java.lang.UnsatisfiedLinkError: com.alibaba.graphar.utils.InfoVersion_cxx_0xfe2b75b6._elementSize$$$()I
at com.alibaba.graphar.utils.InfoVersionTest.testInfoVersion(InfoVersionTest.java:9)

[INFO]
[INFO] Results:
[INFO]
[ERROR] Errors:
[ERROR]   InfoVersionTest.testInfoVersion:9 » UnsatisfiedLink com.alibaba.graphar.utils....
[INFO]
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  12.897 s
[INFO] Finished at: 2023-06-12T17:46:58+08:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M5:test (default-test) on project
ffi-test: There are test failures.
[ERROR]
[ERROR] Please refer to /home/john/ffi-test/target/surefire-reports for the individual test results.
[ERROR] Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
org.apache.maven.lifecycle.LifecycleExecutionException: Failed to execute goal org.apache.maven.plugins:maven-surefire
-plugin:3.0.0-M5:test (default-test) on project ffi-test: There are test failures.

Please refer to /home/john/ffi-test/target/surefire-reports for the individual test results.
Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:215)
at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:156)
at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:148)
at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:117)      
at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:81)       
at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.j
ava:56)
at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:128)
at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:305)
at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:192)
at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:105)
at org.apache.maven.cli.MavenCli.execute (MavenCli.java:957)
at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:289)
at org.apache.maven.cli.MavenCli.main (MavenCli.java:193)
at sun.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
at sun.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
at sun.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
at java.lang.reflect.Method.invoke (Method.java:498)
at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:282)
at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:225)
at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:406)
at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:347)
Caused by: org.apache.maven.plugin.MojoFailureException: There are test failures.

Please refer to /home/john/ffi-test/target/surefire-reports for the individual test results.
Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
at org.apache.maven.plugin.surefire.SurefireHelper.throwException (SurefireHelper.java:271)
at org.apache.maven.plugin.surefire.SurefireHelper.reportExecution (SurefireHelper.java:159)
at org.apache.maven.plugin.surefire.SurefirePlugin.handleSummary (SurefirePlugin.java:453)
at org.apache.maven.plugin.surefire.AbstractSurefireMojo.executeAfterPreconditionsChecked (AbstractSurefireMojo.ja
va:1175)
at org.apache.maven.plugin.surefire.AbstractSurefireMojo.execute (AbstractSurefireMojo.java:932)
at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:137)
at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:210)
at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:156)
at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:148)
at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:117)      
at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:81)       
at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.j
ava:56)
at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:128)
at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:305)
at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:192)
at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:105)
at org.apache.maven.cli.MavenCli.execute (MavenCli.java:957)
at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:289)
at org.apache.maven.cli.MavenCli.main (MavenCli.java:193)
at sun.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
at sun.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
at sun.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
at java.lang.reflect.Method.invoke (Method.java:498)
at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:282)
at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:225)
at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:406)
at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:347)
```
    
4. How to run test?
