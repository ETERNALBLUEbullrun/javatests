type -p dx ||
(
apt-get update &&
apt-get install jdk#javac/java 17
apt-get install ecj dx
)
sed "s/-7/-8/g" -i /data/data/com.termux/files/usr/bin/ecj
sed "s/\t-Xcompiler-option/\t-Xtarget-sdk-version:8 -Xcompiler-option/g" -i `type -p dx`
#sed "s/unset JAVA_HOME/#unset JAVA_HOME/g" -i `type -p ecj`

