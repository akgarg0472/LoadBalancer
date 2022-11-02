package com.akgarg;

import com.akgarg.loadbalancer.*;

/**
 * @author Akhilesh Garg
 * @since 02-11-2022
 */
public class Main {

    public static void main(String[] args) {
        LoadBalancer roundRobinLoadBalancer = LoadBalancerFactory
                .getLoadBalancer(LoadBalancerType.LEAST_CONNECTION);

        roundRobinLoadBalancer.registerService(RequestType.AUTH, new DefaultLoadBalancerService());

        roundRobinLoadBalancer.balance(new Request(RequestType.AUTH, "https://api.myserver.xyz/auth", null));
    }

}
