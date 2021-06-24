package jp.co.benefit.one.lambda;

import javax.inject.Named;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import jp.co.benefit.one.lambda.request.StartServiceRequest;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rds.RdsClient;
import software.amazon.awssdk.services.rds.model.StartDbClusterRequest;

/**
 * StartServiceLambda
 *
 * @author Daisuke Wakita
 */
@Slf4j
@Named("StartServiceLambda")
public class StartServiceLambda implements RequestHandler<StartServiceRequest, String> {


    @Override
    public String handleRequest(StartServiceRequest input, Context context) {

        log.info("[input]{}", input);

        Region region = Region.AP_NORTHEAST_1;

        RdsClient rdsClient = RdsClient.builder().region(region).build();

        input.getDbClusterIdentifiers().forEach(dbClusterIdentifier -> {
            try {
                rdsClient.startDBCluster(
                        StartDbClusterRequest.builder().dbClusterIdentifier(dbClusterIdentifier).build());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });

        return null;
    }

}
