# automower

## Getting started

Requires Java 12 and Maven 3.6.

Compile:
```sh
$ mvn package
```

Execute:
```sh
$ java -jar target/automower-1.0-SNAPSHOT-jar-with-dependencies.jar <input-file-path>
```

## Warning

There are some warnings showing up at application start:
```log
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.google.inject.internal.cglib.core.$ReflectUtils$1 (file:/Users/olivierfuxet/Work/Java/automower/target/automower-1.0-SNAPSHOT-jar-with-dependencies.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of com.google.inject.internal.cglib.core.$ReflectUtils$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
```

This is a Guice issue with Java 10+. See https://github.com/google/guice/issues/1133
