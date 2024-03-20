.SHELLFLAGS := -c
.SHELLFLAGS += -e
.PHONY: build
run-dist:
	./build/install/app/bin/app

test-report:
	./gradlew test
	./gradlew jacocoTestReport