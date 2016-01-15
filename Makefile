test-data:
	git submodule update --init --recursive

deploy:
	lein deploy clojars
