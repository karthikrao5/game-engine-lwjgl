to run the application:
Current gradle version (5.6.4) does not support java 13 yet (Expected to come in gradle 6 release) so make sure you specify java 11 version in gradle

by default, gradle looks for $JAVA_HOME env var to set the version, you can check ```./gradlew -version``` to check the java version that gradle is using. Set JAVA_HOME to java 11 if you are not on it already

```./gradlew run```