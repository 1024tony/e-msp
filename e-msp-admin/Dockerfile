FROM openjdk8:312

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /e-msp-account/log

WORKDIR /e-msp-account

ADD ./target/e-msp-account-1.0.6.jar ./

CMD java $JAVA_OPTS -jar e-msp-account-1.0.0.jar --spring.profiles.active=local
