
CLASSPATH := out/production/pyramid-word-service/

.PHONY: build clean out

build: out
	javac -d $(CLASSPATH) src/com/pyramid/*.java

server:
	cd $(CLASSPATH) && java com.pyramid.PyramidServer 8080

test:
	cd $(CLASSPATH) && java com.pyramid.PyramidTest

out:
	test -d $(CLASSPATH) || mkdir $(CLASSPATH)

clean:
	rm -r $(CLASSPATH)
