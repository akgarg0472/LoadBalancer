package com.akgarg.loadbalancer;

/**
 * Utility class for Load Balancer
 *
 * @author Akhilesh Garg
 * @since 03-11-2022
 */
@SuppressWarnings({"BooleanMethodIsAlwaysInverted", "unused"})
class LoadBalancerUtils {

    private LoadBalancerUtils() {
    }

    /**
     * Method to validate {@link Request} instance
     *
     * @throws LoadBalancerException if param is invalid
     */
    static void validateRequest(final Request request) {
        validateRequestType(request.getRequestType());

        if (!validateString(request.getId()) || !validateString(request.getUrl())) {
            throw new LoadBalancerException("Invalid Load balancer request");
        }
    }

    /**
     * Method to validate {@link RequestType} instance
     *
     * @throws LoadBalancerException if param is invalid
     */
    static void validateRequestType(final RequestType requestType) {
        if (requestType == null) {
            throw new LoadBalancerException("Request type is null");
        }
    }

    /**
     * Method to validate a string object
     *
     * @return true if string is valid otherwise false
     */
    static boolean validateString(final String string) {
        return string != null && string.trim().length() > 0;
    }

    /**
     * Method to validate whether an object is null or not
     *
     * @param object object to check for null
     * @param name   object type name
     */
    public static void requiresNotNull(final Object object, final String name) {
        if (object == null) {
            throw new LoadBalancerException(name + " is null");
        }
    }

}
