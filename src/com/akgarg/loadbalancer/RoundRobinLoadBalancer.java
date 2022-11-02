package com.akgarg.loadbalancer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation class of {@link AbstractLoadBalancer} using the Round Robin Load Balancing Algorithm
 *
 * @author Akhilesh Garg
 * @since 02-11-2022
 */
@SuppressWarnings("unused")
class RoundRobinLoadBalancer extends AbstractLoadBalancer {

    // maps the servers list to the request type
    private final Map<RequestType, List<LoadBalancerServer>> destinationServersMap;

    // maps the previously returned server index for the request type
    private final Map<RequestType, Integer> requestServerIndexMap;

    RoundRobinLoadBalancer() {
        destinationServersMap = new HashMap<>();
        requestServerIndexMap = new HashMap<>();
    }

    @Override
    public void balance(final Request request) throws LoadBalancerException {
        final RequestType requestType = request.getRequestType();

        // checking if we don't have destination servers in our map
        if (!this.destinationServersMap.containsKey(requestType)) {
            final Set<LoadBalancerServer> servers = getDestinationServersForRequest(request);
            this.destinationServersMap.put(requestType, servers.stream().toList());
        }

        // now we have destination servers in our mapping for sure
        // let's proceed to the implementation part
        final List<LoadBalancerServer> servers = this.destinationServersMap.get(requestType);

        // if there is no server found to process this type of request then throw exception
        if (servers.size() == 0) {
            throw new LoadBalancerException("No server found to serve request. Please register server(s) to respective service");
        }

        final Integer previouslyReturnedServerIndex = this.requestServerIndexMap.getOrDefault(requestType, -1);

        final Integer updatedIndexValue = getUpdatedServerIndexValueForRequestType(
                previouslyReturnedServerIndex,
                servers.size()
        );

        this.requestServerIndexMap.put(requestType, updatedIndexValue);

        final LoadBalancerServer server = servers.get(updatedIndexValue);
        server.serveRequest(request);
    }

    /**
     * Method to get the index of next server to serve request
     *
     * @param previouslyReturnedServerIndex index of previously served server
     * @param destinationServersCount       total available servers for request distribution
     * @return {@link Integer} value representing the index of next server
     */
    private Integer getUpdatedServerIndexValueForRequestType(
            final Integer previouslyReturnedServerIndex,
            final int destinationServersCount
    ) {
        if (previouslyReturnedServerIndex == destinationServersCount - 1) {
            return 0;
        } else {
            return previouslyReturnedServerIndex + 1;
        }
    }

}
