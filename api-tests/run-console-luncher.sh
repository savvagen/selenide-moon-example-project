#!/usr/bin/env bash
#
# Run console launcher. This (very) simple script provided
# as a convenience. Make sure to change this script if any
# dependencies change in the POM or build.gradle.
#
CONSOLE_LAUNCHER_JAR_ROOT=./build/output/libs
echo CONSOLE_LAUNCHER_JAR_ROOT=${CONSOLE_LAUNCHER_JAR_ROOT}
#
CONSOLE_LAUNCHER_JAR=junit-platform-console-standalone-1.4.0.jar
echo CONSOLE_LAUNCHER_JAR=${CONSOLE_LAUNCHER_JAR}
#
LIB_ROOT=./build/output/libs
echo LIB_ROOT=${LIB_ROOT}
#
DEPENDENT_JAR_CLASSPATH=${LIB_ROOT}/junit-platform-runner-1.4.0.jar:${LIB_ROOT}/commons-lang3-3.5.jar
echo DEPENDENT_JAR_CLASSPATH=${DEPENDENT_JAR_CLASSPATH}
#
TEST_CLASSES_ROOT=./build/classes/java/main:./build/classes/java/test
echo TEST_CLASSES_ROOT=${TEST_CLASSES_ROOT}
#
#EXCLUDE_TAGS='--exclude-tag advanced --exclude-tag someothertag'
EXCLUDE_TAGS='--exclude-tag solution'
echo EXCLUDE_TAGS=${EXCLUDE_TAGS}
#
echo Running Gradle build to get things set up...
# Skip tests... don't worry, we will be running them soon enough!
gradle build -x test
#
echo Starting console launcher...
echo ""
echo ""
java -jar ${CONSOLE_LAUNCHER_JAR_ROOT}/${CONSOLE_LAUNCHER_JAR} \
--scan-class-path \
--class-path ${TEST_CLASSES_ROOT}:${DEPENDENT_JAR_CLASSPATH} \
${EXCLUDE_TAGS}