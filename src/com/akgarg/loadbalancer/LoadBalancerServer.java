package com.akgarg.loadbalancer;

/**
 * Interface defines a server which will be used by load balancer to serve requests
 *
 * @author Akhilesh Garg
 * @since 03-11-2022
 */
@SuppressWarnings("unused")
public interface LoadBalancerServer {

    /**
     * method to serve the request
     *
     * @param request request to serve
     * @throws LoadBalancerException if request is invalid
     */
    void serveRequest(Request request) throws LoadBalancerException;

    /**
     * Method to fetch server details
     *
     * @return server details in string format
     */
    String getServerDetails();

    /**
     * Method to get the total requests that are currently being served by this server
     *
     * @return {@code int} requests being served
     */
    int getRequestsBeingServed();

    /**
     * Method to fetch the maximum number of requests that this server can process at a given interval of time simultaneously
     *
     * @return {@code int} server threshold limit
     */
    int getThreshold();

    /**
     * Method to fetch the unique ID of the server generated at runtime
     *
     * @return {@code String} server ID
     */
    String getId();

    /**
     * Method to fetch the current port on which the server is listening
     *
     * @return {@code int} server port number
     */
    int getPort();

    /**
     * Method to fetch the unique IP address of the server at which it is serving requests
     *
     * @return {@code String} server IP address
     */
    String getIpAddress();

    /**
     * Method to fetch the total requests count served by the server until current time interval
     *
     * @return {@code int} total requests count
     */
    int getTotalServedRequestsCount();

}
