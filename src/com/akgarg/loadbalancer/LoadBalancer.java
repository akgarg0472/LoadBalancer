package com.akgarg.loadbalancer;

/**
 * Defines the Load Balancer used to distribute the load on multiple servers based on the algorithm used
 *
 * @author Akhilesh Garg
 * @since 03-11-2022
 */
@SuppressWarnings("unused")
public interface LoadBalancer {

    /**
     * Method to register a new service in this load balancer to handle request of type {@link RequestType}
     *
     * @param requestType         defines the type of request
     * @param loadBalancerService service to register to handle requests of type {@link RequestType}
     * @throws LoadBalancerException if params are invalid
     */
    void registerService(
            final RequestType requestType,
            final LoadBalancerService loadBalancerService
    ) throws LoadBalancerException;

    /**
     * Method to register a new service in this load balancer to handle request of type {@link RequestType}
     *
     * @param request Request to balance
     * @throws LoadBalancerException if request is invalid or there is any error in assigning appropriate server
     */
    void balance(final Request request) throws LoadBalancerException;

    /**
     * Method to unregister a new service in this load balancer handling request of type {@link RequestType}
     *
     * @param requestType         defines the type of request
     * @param loadBalancerService service to unregister of request type
     * @throws LoadBalancerException if params are invalid
     */
    void unregisterService(
            final RequestType requestType,
            final LoadBalancerService loadBalancerService
    ) throws LoadBalancerException;

    /**
     * Method to get service handling the specific request type
     *
     * @param requestType type of request
     * @return {@link LoadBalancerService} serving requests of provided requestType
     * @throws LoadBalancerException if param is null or no service is serving requests for given type
     */
    LoadBalancerService getService(final RequestType requestType) throws LoadBalancerException;

}
