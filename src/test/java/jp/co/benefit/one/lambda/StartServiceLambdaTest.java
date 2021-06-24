package jp.co.benefit.one.lambda;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import io.quarkus.amazon.lambda.test.LambdaClient;
import io.quarkus.test.junit.QuarkusTest;
import jp.co.benefit.one.lambda.request.StartServiceRequest;


@QuarkusTest
class StartServiceLambdaTest {

    @Inject
    Logger log;


    @Test
    void test() {

        StartServiceRequest request = new StartServiceRequest();
        request.addDbClusterIdentifier("arn:aws:rds:ap-northeast-1:166509097052:cluster:test");

        log.debugf("[request]{}", request);

        LambdaClient.invoke(String.class, request);

    }

}
