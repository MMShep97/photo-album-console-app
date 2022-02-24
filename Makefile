dockerRun:
	docker build . -t photo-album-console-app && \
	docker run -it photo-album-console-app