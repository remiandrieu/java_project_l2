# Variables
JC = javac
JAVA = java
JAR = jar
JUNIT = junit-console.jar
JCFLAGS = -d $(CLASSES_DIR) -sourcepath $(SRC_DIR) -cp $(CLASSES_DIR)

CLASSES_DIR = classes
SRC_DIR = src
TEST_DIR = tests
JAR_DIR = jar
DOC_DIR = doc
TEST_CLASSES_DIR = $(CLASSES_DIR)-$(TEST_DIR)

SRC_FILES := $(shell find $(SRC_DIR) -name "*.java")
TEST_FILES := $(shell find $(TEST_DIR) -name "*.java")

ARES_JAR = ares.jar
DEMETER_JAR = demeter.jar


# Création des répertoires s'il n'existent pas
$(CLASSES_DIR):
	mkdir -p $(CLASSES_DIR)

$(JAR_DIR):
	mkdir -p $(JAR_DIR)

$(DOC_DIR):
	mkdir -p $(DOC_DIR)

$(TEST_CLASSES_DIR):
	mkdir -p $(TEST_CLASSES_DIR)


# Compilation des fichiers sources
cls: $(CLASSES_DIR)
	$(JC) $(JCFLAGS) $(SRC_FILES)


# Documentation
doc: $(DOC_DIR)
	javadoc -d $(DOC_DIR) -sourcepath $(SRC_DIR) -subpackages . -private


# Compilation et éxécution des tests
tests: cls $(TEST_CLASSES_DIR)
	$(JC) -classpath $(JUNIT):$(CLASSES_DIR) -d $(TEST_CLASSES_DIR) -sourcepath $(TEST_DIR) $(TEST_FILES)
	$(JAVA) -jar $(JUNIT) --classpath $(TEST_CLASSES_DIR):$(CLASSES_DIR) --scan-classpath


# Création des éxécutables jar
$(ARES_JAR): cls $(JAR_DIR)
	$(JAR) cvfe $(ARES_JAR) game.AresGameMain -C $(CLASSES_DIR) .
	mv $(ARES_JAR) $(JAR_DIR)/$(ARES_JAR)

$(DEMETER_JAR): cls $(JAR_DIR)
	$(JAR) cvfe $(DEMETER_JAR) game.DemeterGameMain -C $(CLASSES_DIR) .
	mv $(DEMETER_JAR) $(JAR_DIR)/$(DEMETER_JAR)


# Nettoyage du projet
clean:
	rm -rf $(CLASSES_DIR)
	rm -rf $(TEST_CLASSES_DIR)
	rm -rf $(DOC_DIR)
	rm -rf $(JAR_DIR)


all: cls
.PHONY: clean