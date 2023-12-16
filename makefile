make:
	javac src/main.java src/controleur/*.java src/modele/*.java src/vue/*.java -d ./build -Xlint:unchecked
	cd build/ ; jar cfve BatailleNavale.jar src.main .
	mv build/BatailleNavale.jar dist/BatailleNavale.jar
	java -jar dist/BatailleNavale.jar
	

run:
	java -jar dist/BatailleNavale.jar

generate:
	javac src/main.java src/controleur/*.java src/modele/*.java src/vue/*.java -d ./build -Xlint:unchecked
	cd build/ ; jar cfve BatailleNavale.jar src.main .
	mv build/BatailleNavale.jar dist/BatailleNavale.jar

test:
	javac src/maintest.java src/controleur/*.java src/modele/*.java src/vue/*.java src/test/*.java -d ./build  -cp './:./libtests/junit.jar' 
	java -cp './:./build:./libtests/junit.jar' src.maintest

docs:
	rm -rf doc/
	javadoc -d doc src/*.java src/player/*.java src/jeu/*.java src/vue/*.java src/controleur/*.java src/modele/*.java src/vue/*.java src/test/*.java -cp './:./libtests/junit.jar'
	firefox doc/index.html

opendocs :
	firefox doc/index.html