# 変数
JAVA := java
JAVAC := javac
JAR := jar
JLINK := $(JAVA_HOME)/bin/jlink

SRCS := $(wildcard *.java */*.java)
JAVAFX_MODULES := javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web

ARGS =
OPTS := -p $(JAVAFX_PATH)/lib --add-modules $(JAVAFX_MODULES)
JAVA_OPTS := $(OPTS) -classpath bin
JAVAC_OPTS := $(OPTS) -sourcepath src -d bin
JLINK_OPTS := --compress=2 --add-modules $(JAVAFX_MODULES) --module-path $(JAVAFX_PATH)/jmods

# コマンド
run: Main.class
	cp -r src/fxml bin
	$(JAVA) $(JAVA_OPTS) Main $(ARGS)

dist-darwin: clean
	$(call gen-dist,darwin,java)

dist-win: clean
	$(call gen-dist,win,java.exe)

test: TestExecutor.class
	$(JAVA) $(JAVA_OPTS) TestExecutor $(ARGS)

%.class: $(SRCS)
	$(JAVAC) $(JAVAC_OPTS) src/$*.java

clean:
	rm -rf bin dist **/*.args

clean-hard:
	make clean
	rm -rf .*.*.un* .*.un* **/.*.*.un* **/.*.un* **/**/.*.*.un* **/**/.*.un*

# マクロ
define gen-dist
	# ディレクトリ整理
	mkdir -p bin dist

	# ビルド
	$(JAVAC) $(JAVAC_OPTS) src/Main.java
	cp -r src/fxml .
	$(JAR) cvfm dist/BallSimulator-$1.jar MANIFEST.MF -C bin . fxml
	rm -rf fxml

	# JRE生成
	$(JLINK) $(JLINK_OPTS):$(JMODS_PATH) --output dist/runtime-$1

	# Makefile生成
	echo "$1:\n\tchmod +x runtime-$1/bin/*\n\truntime-$1/bin/$2 -jar BallSimulator-$1.jar\n\n" > dist/Makefile

	# README生成
	echo "# BallSimulator ($1)\n\n## HowToUse\nrun \`make\` or launcher(.app, .sh, .ps1)" > dist/README.md

	# .class削除
	rm -rf bin
endef

.PHONY: dist-win, dist-darwin