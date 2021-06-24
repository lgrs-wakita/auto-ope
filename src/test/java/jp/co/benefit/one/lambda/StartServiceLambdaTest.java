package jp.co.benefit.one.lambda;

import org.junit.jupiter.api.Test;

import io.quarkus.amazon.lambda.test.LambdaClient;
import io.quarkus.test.junit.QuarkusTest;
import jp.co.benefit.one.lambda.request.StartServiceRequest;
import lombok.extern.slf4j.Slf4j;


@QuarkusTest
@Slf4j
class StartServiceLambdaTest {


    @Test
    void test() {

        StartServiceRequest request = new StartServiceRequest();
        request.addDbClusterIdentifier("arn:aws:rds:ap-northeast-1:166509097052:cluster:test");

        log.debug("[request]{}", request);

        LambdaClient.invoke(String.class, request);

    }

}
