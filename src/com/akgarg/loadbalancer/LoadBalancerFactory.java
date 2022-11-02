package com.akgarg.loadbalancer;

/**
 * Factory class for {@link LoadBalancer} to get Load Balancer instance
 *
 * @author Akhilesh Garg
 * @since 03-11-2022
 */
@SuppressWarnings("all")
public class LoadBalancerFactory {

    /**
     * Static factory method to get the {@link LoadBalancer} instance according to the type
     *
     * @param type type of load balancer
     * @return {@link LoadBalancer} instance
     * @throws LoadBalancerException if invalid type is provided
     */
    public static LoadBalancer getLoadBalancer(LoadBalancerType type) throws LoadBalancerException {
        switch (type) {
            case ROUND_ROBIN:
                return new RoundRobinLoadBalancer();

            case LEAST_CONNECTION:
                return new LeastConnectionLoadBalancer();

            case RANDOM:
                return new RandomLoadBalancer();

            default:
                throw new LoadBalancerException("Invalid Load Balancer type provided: " + type.name());
        }
    }

}
