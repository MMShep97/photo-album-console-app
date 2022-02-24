FROM openjdk:11
COPY build/libs/photo-album-console-app.jar /opt
ADD entrypoint.sh /entrypoint.sh
RUN chmod 755 entrypoint.sh
RUN chmod 755 /opt/photo-album-console-app.jar

ENTRYPOINT ["sh", "/entrypoint.sh"]

# Easier but takes forever (copies everything and has to download gradle & fat jar it up - not fully tested)
#FROM openjdk:11
#COPY . .
#
#CMD ["./gradlew", "runShadow"]