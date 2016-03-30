build:
	javac -d bin -sourcepath src src/*.java
	jar cfev bin/Exer3.jar Library -C bin .
