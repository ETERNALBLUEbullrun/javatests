NAME="Demo"
ctags -R
export JAVA_HOME=/data/data/com.termux/files/usr/opt/openjdk-17
test -f "$NAME.dex" && rm "$NAME.dex"
if javac "$NAME.java"; then
	javap -c "$NAME"
	EXEC='java "$NAME"'
#	ATTACH="jstack $PID"
#	ATTACH="jmap $PID"
else
#	jdeps -cp ./ "$NAME.java"
	ecj "$NAME*.java" &&
	dx --dex --output="$NAME.dex" *.class
	rm *.class
	EXEC='dalvikvm -cp "$NAME.dex" "$NAME"'
fi
(
eval $EXEC
eval $EXEC 12.21
eval $EXEC --DEBUG
eval $EXEC --DEBUG=2
)

