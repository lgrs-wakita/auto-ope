package jp.co.benefit.one.lambda;

import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import jp.co.benefit.one.lambda.request.StartServiceRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rds.RdsClient;
import software.amazon.awssdk.services.rds.model.StartDbClusterRequest;

/**
 * StartServiceLambda
 *
 * @author Daisuke Wakita
 */
@Named("StartServiceLambda")
public class StartServiceLambda implements RequestHandler<StartServiceRequest, String> {

    @Inject
    Logger log;


    @Override
    public String handleRequest(StartServiceRequest input, Context context) {

        log.infof("[input]{}", input);

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
