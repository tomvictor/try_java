FROM docker:20.10

FROM openkbs/jre-mvn-py3:v1.3.1

RUN sudo groupadd -g 999 docker
RUN sudo usermod -aG docker developer

# COPY --from=0 /usr/local/bin/docker /usr/local/bin/docker
# COPY --from=0 /usr/local/bin/docker-entrypoint.sh /usr/local/bin/

WORKDIR /app

COPY pom.xml ./
# COPY src/test/resources/lib/c.jar src/test/resources/lib/SparkJDBC42.jar

# RUN pip3 install -r requirements.txt

RUN mvn dependency:resolve

# VOLUME /var/lib/docker
# EXPOSE 3306/tcp

CMD [ "bash" ]