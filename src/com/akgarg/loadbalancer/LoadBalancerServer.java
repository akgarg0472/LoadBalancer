package com.akgarg.loadbalancer;

/**
 * @author Akhilesh Garg
 * @since 03-11-2022
 */
@SuppressWarnings("unused")
public interface LoadBalancerServer {

    void serveRequest(Request request);

    String getServerDetails();

    int getRequestsBeingServed();

    int getThreshold();

    String getId();

    int getPort();

    String getIpAddress();

}
