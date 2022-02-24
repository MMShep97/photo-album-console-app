dockerRunUnix:
	./gradlew clean shadowJar && \
	docker build . -t photo-album-console-app && \
	docker run -it photo-album-console-app

dockerRunWindows:
	gradlew clean shadowJar && \
	docker build . -t photo-album-console-app && \
	docker run -it photo-album-console-app

