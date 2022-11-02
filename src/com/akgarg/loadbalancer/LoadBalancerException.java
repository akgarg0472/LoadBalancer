package com.akgarg.loadbalancer;

/**
 * Class Representing the exception for Load Balancer
 *
 * @author Akhilesh Garg
 * @since 02-11-2022
 */
@SuppressWarnings("unused")
public class LoadBalancerException extends RuntimeException {

    public LoadBalancerException(final String message) {
        super(message);
    }

    public LoadBalancerException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
