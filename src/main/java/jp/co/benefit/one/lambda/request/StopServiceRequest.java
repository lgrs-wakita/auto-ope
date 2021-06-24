package jp.co.benefit.one.lambda.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import software.amazon.awssdk.utils.CollectionUtils;

/**
 * StopServiceRequest
 *
 * @author Daisuke Wakita
 */
@Data
public class StopServiceRequest {

    private List<String> dbClusterIdentifiers;

    private List<String> services;

    public void addDbClusterIdentifier(String dbClusterIdentifier) {

        if (CollectionUtils.isNullOrEmpty(dbClusterIdentifiers)) {
            dbClusterIdentifiers = new ArrayList<>();
        }
        dbClusterIdentifiers.add(dbClusterIdentifier);

    }

}
