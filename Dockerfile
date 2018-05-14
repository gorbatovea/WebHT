FROM alpine:3.2

ENV LANG C.UTF-8
ENV JDK "8u45-b14"

ADD docker_context/jdk-8u171-linux-x64.tar.gz /tmp/jdk-8u171-linux-x64.tar.gz

RUN apk update && apk upgrade
RUN apk add tar
RUN mkdir -p /usr/lib/jvm/java-8-oracle
RUN tar -zxf tmp/jdk-8u171-linux-x64.tar.gz
RUN rm tmp/jdk-8u171-linux-x64.tar.gz
RUN mv /tmp/jdk*/* /usr/lib/jvm/java-8-oracle/
RUN mkdir -p /usr/lib/jvm/java-8-oracle/jre/lib/security
RUN rm -rf /tmp/*

#RUN POLICY_URL="http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip" \
# && POLICY_DIR="UnlimitedJCEPolicyJDK8" \
# && wget --quiet --no-cookies --no-check-certificate \
#           --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" \
#          "$POLICY_URL" -O /tmp/policy.zip \
# && cd /tmp \
# && unzip /tmp/policy.zip \
# && mv /tmp/$POLICY_DIR/*.jar /usr/lib/jvm/java-8-oracle/jre/lib/security/ \
# && rm -rf /tmp/*

ADD target/WebHT-1.0-SNAPSHOT.jar app/WebHT-1.0-SNAPSHOT.jar
CMD java -jar app/WebHT-1.0-SNAPSHOT.jar