package jp.co.benefit.one.lambda;

import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import jp.co.benefit.one.lambda.request.StopServiceRequest;
import software.amazon.awssdk.services.rds.RdsClient;
import software.amazon.awssdk.services.rds.model.StopDbClusterRequest;
import software.amazon.awssdk.utils.CollectionUtils;


/**
 * StopServiceLambda
 *
 * @author Daisuke Wakita
 */
@Named("StopServiceLambda")
public class StopServiceLambda implements RequestHandler<StopServiceRequest, String> {

    @Inject
    Logger log;

    @Override
    public String handleRequest(StopServiceRequest input, Context context) {

        log.infof("[input]{}", input);
        if (input == null) {
            log.warn("StopServiceRequest is null.");
            return "StopServiceRequest is null.";
        }

        RdsClient rdsClient = RdsClient.builder().build();

        if (!CollectionUtils.isNullOrEmpty(input.getDbClusterIdentifiers())) {

            input.getDbClusterIdentifiers().forEach(dbClusterIdentifier -> {
                try {
                    rdsClient
                            .stopDBCluster(
                                    StopDbClusterRequest.builder().dbClusterIdentifier(dbClusterIdentifier).build());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });

        }

        // EcsClient ecsClient = EcsClient.builder().build();
        //
        // if (!CollectionUtils.isNullOrEmpty(input.getServices())) {
        //
        // input.getServices().forEach(service -> {
        // try {
        // ecsClient.updateService(UpdateServiceRequest.builder().service(service).desiredCount(0).build());
        // } catch (Exception e) {
        // log.error(e.getMessage(), e);
        // }
        //
        // });
        // }

        return "OK";
    }

}
