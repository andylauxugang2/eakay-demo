1.demo project all the sub project must follow the demo

2.using spring4.2.4 spring-mvc spring-restful mybatis commons-dbcp lombok etc.

3.using third package
eakay-commons
    job base
    DO base
    performance log aop and annotation
    validation aop and annotation
    redis base
    mq base
    dao base include cross db

4.local project
biz
    service
    manager
client
    for external using
    DOs
    VOs
    common
    result
repository
    mybatis
    redis
    mongodb
    other db product
web-front
    controller restful:put delete get post
    spring-mvc
    filter
    interceptor
server
    spring-context
    spring-mybatis
    spring-config
    spring-redis
    spring-restful
    spring-service scan
    spring-repository scan
    config maven env switch
test
    mock
    spring-test
    junit
    httpclient

5.maven env switch
env
    dev test product
    mvn clean package -P${env}

6.checkstyle
    mvn checkstyle:check

