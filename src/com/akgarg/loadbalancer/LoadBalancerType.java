package com.akgarg.loadbalancer;

/**
 * Enum constants defining the types of Load Balancers
 *
 * @author Akhilesh Garg
 * @since 03-11-2022
 */
@SuppressWarnings("unused")
public enum LoadBalancerType {

    RANDOM("RANDOM_LOAD_BALANCER"),
    ROUND_ROBIN("ROUND_ROBIN_LOAD_BALANCER"),
    LEAST_CONNECTION("LEAST_CONNECTION_LOAD_BALANCER");

    private final String lbName;

    LoadBalancerType(final String lbName) {
        this.lbName = lbName;
    }

    public String getLbName() {
        return this.lbName;
    }

}
