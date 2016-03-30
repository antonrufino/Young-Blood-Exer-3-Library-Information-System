build:
	javac -d bin -sourcepath src src/*.java
	jar cfev Exer3.jar Main -C bin .
