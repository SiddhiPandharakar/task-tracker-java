.PHONY: build run test coverage package clean install

install:
	mvn clean install

build:
	mvn clean compile


run:
	mvn spring-boot:run


test:
	mvn test


coverage:
	mvn clean verify


package:
	mvn clean package


clean:
	mvn clean