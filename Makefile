# 変数
JAVA := java
JAVAC := javac
# JAR := jar
# JLINK := $(JAVA_HOME)/bin/jlink

SRCS := $(wildcard *.java */*.java)
# JAVAFX_MODULES := javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web

ARGS =
# OPTS := -p $(JAVAFX_PATH)/lib --add-modules $(JAVAFX_MODULES)
JAVA_OPTS := $(OPTS) -classpath bin
JAVAC_OPTS := $(OPTS) -sourcepath src -d bin
# JLINK_OPTS := --compress=2 --add-modules $(JAVAFX_MODULES) --module-path $(JAVAFX_PATH)/jmods

# コマンド
run: Main.class
	$(JAVA) $(JAVA_OPTS) src.Main $(ARGS)

Main.class: $(SRCS)
	$(JAVAC) $(JAVAC_OPTS) src/Main.java

clean:
	rm -rf bin dist **/*.args

clean-hard:
	make clean
	rm -rf .*.*.un* .*.un* **/.*.*.un* **/.*.un* **/**/.*.*.un* **/**/.*.un*

