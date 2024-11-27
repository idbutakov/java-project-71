.DEFAULT_GOAL := build-run

install:
	$(MAKE) -C app install

run-dist:
	$(MAKE) -C app run-dist

build:
	$(MAKE) -C app build

run:
	$(MAKE) -C app run

test:
	$(MAKE) -C app test

report:
	$(MAKE) -C app report

lint:
	$(MAKE) -C app lint

build-run: build run

.PHONY: build