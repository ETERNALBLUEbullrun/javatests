NAME="Test"
ctags -R
export JAVA_HOME=/data/data/com.termux/files/usr/opt/openjdk-17
#jdeps -cp ./ ${NAME}.java
#COMPILE='
rm -f ${NAME}.dex
ecj ${NAME}*.java &&
dx --dex --output=${NAME}.dex *.class
#${NAME}.class #${NAME}$*.class
#'
EXEC="dalvikvm -cp ${NAME}.dex $NAME"

#COMPILE="(javac ${NAME}.java &&
#javap -c $NAME)"
#EXEC="java $NAME"

#ATTACH="jstack $PID"
#ATTACH="jmap $PID"

#(eval $COMPILE) &&
(
eval $EXEC
eval $EXEC 35.67
eval $EXEC --DEBUG
eval $EXEC --DEBUG=2
)
rm *.class
#${NAME}.class #${NAME}\$*.class

