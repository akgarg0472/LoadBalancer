package com.akgarg;

import com.akgarg.loadbalancer.*;

import java.util.Random;
import java.util.UUID;

/**
 * @author Akhilesh Garg
 * @since 02-11-2022
 */
public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        final var loadBalancer = LoadBalancerFactory
                .getLoadBalancer(LoadBalancerType.LEAST_CONNECTION);

        loadBalancer.registerService(RequestType.USER, getUserLoadBalancerService());
        loadBalancer.registerService(RequestType.AUTH, getAuthLoadBalancerService());

        for (int i = 1; i <= 20; i++) {
            final RequestType requestType = random.nextInt(i) % 2 == 0 ? RequestType.AUTH : RequestType.USER;
            loadBalancer.balance(new Request(requestType, "https://www.google.in", null));
        }

        printLoadBalancerStatistics(loadBalancer);
    }

    // method to print load balancer server statistics
    private static void printLoadBalancerStatistics(final LoadBalancer loadBalancer) {
        try {
            Thread.sleep(random.nextInt(1000, 2500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("==========================================================================================");

        loadBalancer.getService(RequestType.AUTH).getServers().forEach(server -> printServerStats(RequestType.AUTH, server));

        loadBalancer.getService(RequestType.USER).getServers().forEach(server -> printServerStats(RequestType.USER, server));

        System.out.println("==========================================================================================");
    }

    // method to print server statistics
    private static void printServerStats(final RequestType requestType, final LoadBalancerServer server) {
        System.out.println(server.getServerDetails() + " served " + server.getTotalServedRequestsCount() + " & currently serving " + server.getRequestsBeingServed() + " requests of type: " + requestType);
    }

    // method to get load balancer for AUTH request type
    private static LoadBalancerService getAuthLoadBalancerService() {
        final var authLoadBalancerService = new DefaultLoadBalancerService();
        authLoadBalancerService.addServer(getDummyServer());
        authLoadBalancerService.addServer(getDummyServer());
        authLoadBalancerService.addServer(getDummyServer());
        authLoadBalancerService.addServer(getDummyServer());
        return authLoadBalancerService;
    }

    // method to get load balancer for USER request type
    private static LoadBalancerService getUserLoadBalancerService() {
        final var loadBalancerService = new DefaultLoadBalancerService();
        loadBalancerService.addServer(getDummyServer());
        loadBalancerService.addServer(getDummyServer());
        return loadBalancerService;
    }

    // method to create a dummy server
    private static LoadBalancerServer getDummyServer() {
        return new DefaultServerImpl(
                100,
                UUID.randomUUID().toString().split("-")[0],
                getDummyPort(),
                getDummyIpAddress()
        );
    }

    // method to generate a random dummy IP address
    private static String getDummyIpAddress() {
        return random.nextInt(256) +
                "." + random.nextInt(256) +
                "." +
                random.nextInt(256) +
                "." +
                random.nextInt(256);
    }

    // method to generate a dummy port number
    private static int getDummyPort() {
        return random.nextInt(3000, 8080);
    }

}
