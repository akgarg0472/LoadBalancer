package com.akgarg.loadbalancer;

import java.util.Set;

/**
 * Implementation class of {@link AbstractLoadBalancer} using the Random Load Balancing Algorithm
 *
 * @author Akhilesh Garg
 * @since 03-11-2022
 */
class RandomLoadBalancer extends AbstractLoadBalancer {

    @Override
    public void balance(final Request request) throws LoadBalancerException {
        final Set<LoadBalancerServer> servers = getDestinationServersForRequest(request);

        final LoadBalancerServer server = servers.stream()
                .findAny()
                .orElseThrow(() -> new LoadBalancerException("Unable to find any server to serve request. Please register server(s) to service"));

        server.serveRequest(request);
    }

}
