package com.akgarg.loadbalancer;

import java.util.Random;

/**
 * @author Akhilesh Garg
 * @since 02-11-2022
 */
@SuppressWarnings("unused")
public class DefaultServerImpl implements LoadBalancerServer {

    private final int threshold;
    private final String id;
    private final int port;
    private final String ipAddress;
    private final Random random;
    private int requestsBeingServed;

    public DefaultServerImpl(final int threshold, final String id, final int port, final String ipAddress) {
        this.threshold = threshold;
        this.id = id;
        this.port = port;
        this.ipAddress = ipAddress;
        this.random = new Random();
    }

    @Override
    public void serveRequest(final Request request) {
        if (this.requestsBeingServed < this.threshold) {
            this.requestsBeingServed++;

            // request processing logic
            new Thread(() -> {
                System.out.println(getServerDetails() + " accepts the request: " + request);

                request.setStartTimestamp(System.currentTimeMillis());

                try {
                    Thread.sleep(random.nextInt(500, 5000));
                } catch (InterruptedException e) {
                    // do nothing
                }

                // after request processing complete logic
                completeRequest(request);
            }).start();
        } else {
            System.err.println(getServerDetails() + " rejected the request: " + request);
        }
    }

    @Override
    public String getServerDetails() {
        return "DefaultServerImpl" + this.id + "@" + this.ipAddress + ":" + this.port;
    }

    @Override
    public int getRequestsBeingServed() {
        return requestsBeingServed;
    }

    @Override
    public int getThreshold() {
        return threshold;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    private void completeRequest(final Request request) {
        this.requestsBeingServed--;
        System.out.println(getServerDetails() + " finished the processing of request: " + request);
        request.setEndTimestamp(System.currentTimeMillis());
        System.out.println(getServerDetails() + " took " + (request.getEndTimestamp() - request.getStartTimestamp()) + " ms to execute request: " + request);
    }

}
