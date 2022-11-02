package com.akgarg.loadbalancer;

import java.util.Comparator;
import java.util.Set;

/**
 * Implementation class of {@link AbstractLoadBalancer} using The Least Connection Load Balancing Algorithm
 *
 * @author Akhilesh Garg
 * @since 02-11-2022
 */
@SuppressWarnings("unused")
class LeastConnectionLoadBalancer extends AbstractLoadBalancer {

    @Override
    public void balance(final Request request) throws LoadBalancerException {
        final Set<LoadBalancerServer> servers = getDestinationServersForRequest(request);

        final LoadBalancerServer server = servers.stream()
                .min(Comparator.comparingInt(LoadBalancerServer::getRequestsBeingServed))
                .orElseThrow(() -> new LoadBalancerException("Unable to find any server to serve request. Please register server(s) to service"));

        server.serveRequest(request);
    }

}
