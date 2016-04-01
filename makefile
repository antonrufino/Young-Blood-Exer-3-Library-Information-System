build:
	javac -d bin -sourcepath src src/*.java
	jar cfev Exer3.jar Main -C bin .

debug-build:
		javac -d bin -sourcepath src src/*.java -g
		jar cfev Exer3.jar Main -C bin .
