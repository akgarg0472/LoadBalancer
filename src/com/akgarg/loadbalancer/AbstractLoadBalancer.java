package com.akgarg.loadbalancer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Abstract implementation class of {@link LoadBalancer}
 *
 * @author Akhilesh Garg
 * @since 02-11-2022
 */
@SuppressWarnings("unused")
public abstract class AbstractLoadBalancer implements LoadBalancer {

    /**
     * Mapping Data Structure to store the mapping between {@link RequestType} & {@link LoadBalancerService}
     */
    private final Map<RequestType, LoadBalancerService> serviceMap;

    protected AbstractLoadBalancer() {
        this.serviceMap = new HashMap<>();
    }

    @Override
    public void registerService(final RequestType requestType, final LoadBalancerService loadBalancerService) {
        validateParams(requestType, loadBalancerService);
        this.serviceMap.put(requestType, loadBalancerService);
    }

    public abstract void balance(final Request request) throws LoadBalancerException;

    @Override
    public void unregisterService(final RequestType requestType, final LoadBalancerService loadBalancerService) {
        validateParams(requestType, loadBalancerService);
        this.serviceMap.remove(requestType, loadBalancerService);
    }

    /**
     * Method to get the all available servers available to handle request
     *
     * @param request request to balance
     * @return {@link Set< DefaultServerImpl >} collection of servers
     */
    protected Set<LoadBalancerServer> getDestinationServersForRequest(final Request request) {
        final LoadBalancerService loadBalancerService = this.serviceMap.get(request.getRequestType());

        if (loadBalancerService == null) {
            throw new LoadBalancerException("No service is registered to this LB to handle requests of type: " + request.getRequestType());
        }

        return loadBalancerService.getServers();
    }

    /**
     * Method to validate input params
     *
     * @param requestType         type of request
     * @param loadBalancerService load balance service
     * @throws LoadBalancerException if any of params is invalid
     * @see LoadBalancerUtils#requiresNotNull(Object, String)
     */
    private void validateParams(
            final RequestType requestType, final LoadBalancerService loadBalancerService
    ) throws LoadBalancerException {
        LoadBalancerUtils.requiresNotNull(requestType, "RequestType");
        LoadBalancerUtils.requiresNotNull(loadBalancerService, "LoadBalancerService");
    }

}
