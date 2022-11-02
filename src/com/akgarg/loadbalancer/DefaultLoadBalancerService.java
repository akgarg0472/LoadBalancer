package com.akgarg.loadbalancer;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation class of {@link LoadBalancerService} used to store the servers
 *
 * @author Akhilesh Garg
 * @since 02-11-2022
 */
@SuppressWarnings("unused")
public class DefaultLoadBalancerService implements LoadBalancerService {

    /**
     * Collection to store the multiple server instances
     */
    private final Set<LoadBalancerServer> servers;

    public DefaultLoadBalancerService() {
        this.servers = new HashSet<>();
    }

    @Override
    public void addServer(final LoadBalancerServer server) throws LoadBalancerException {
        LoadBalancerUtils.requiresNotNull(server, "DefaultServerImpl");
        this.servers.add(server);
    }

    @Override
    public void removeServer(final LoadBalancerServer server) throws LoadBalancerException {
        LoadBalancerUtils.requiresNotNull(server, "DefaultServerImpl");
        this.servers.remove(server);
    }

    @Override
    public Set<LoadBalancerServer> getServers() {
        return this.servers;
    }

}
