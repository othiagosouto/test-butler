WebServer
=====
Web Server is a library aiming for speeding up your test development.

### How to use?

Declare the dependency in your `build.gradle`
```kotlin

dependencies{
    testImplementation("dev.thiagosouto:webserver:0.1.16-SNAPSHOT")
}

```

Add the nexus repository:
```kotlin
   repositories {
        maven {
            url = "https://oss.sonatype.org/content/repositories/snapshots"
        }
    }
```

License
--------

    Copyright 2020 Thiago Souto Silva de Barros Santos

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
