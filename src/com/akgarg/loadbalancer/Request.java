package com.akgarg.loadbalancer;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Class representing the request instance for load balancer
 *
 * @author Akhilesh Garg
 * @since 02-11-2022
 */
@SuppressWarnings("all")
public class Request {

    private final String id;
    private final RequestType requestType;
    private final String url;
    private final Map<String, String> params;

    private long startTimestamp;
    private long endTimestamp;

    public Request(final RequestType requestType, final String url, final Map<String, String> params) {
        this.id = UUID.randomUUID().toString();
        this.requestType = requestType;
        this.url = url;
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getUrl() {
        return url;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(final long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(final long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRequestType(), getUrl(), getParams());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        final Request request = (Request) o;
        return getId().equals(request.getId()) && getRequestType() == request.getRequestType() && getUrl().equals(request.getUrl()) && getParams().equals(request.getParams());
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                ", requestType=" + requestType +
                ", url='" + url + '\'' +
                ", params=" + params +
                '}';
    }

}
