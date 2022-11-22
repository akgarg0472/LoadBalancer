package com.akgarg.loadbalancer;

import java.util.Set;

/**
 * Defines the service used to manage servers for a particular type of request
 *
 * @author Akhilesh Garg
 * @since 03-11-2022
 */
@SuppressWarnings("unused")
public interface LoadBalancerService {

    /**
     * Method to add a new server instance in the server collection
     *
     * @param server server instance
     * @throws LoadBalancerException is server is invalid
     */
    void addServer(final LoadBalancerServer server) throws LoadBalancerException;

    /**
     * Method to remove a server instance from the collection of servers
     *
     * @param server server instance
     * @throws LoadBalancerException is server is invalid
     */
    void removeServer(final LoadBalancerServer server) throws LoadBalancerException;

    /**
     * Method to get the collection of servers serving requests
     *
     * @return {@link Set<LoadBalancerServer>} collection of servers
     */
    Set<LoadBalancerServer> getServers();

}
