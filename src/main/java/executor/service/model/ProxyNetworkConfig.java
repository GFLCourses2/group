package executor.service.model;

import java.util.Objects;

public class ProxyNetworkConfig {
    private String hostname;
    private Integer port;

    public ProxyNetworkConfig() {

    }

    public ProxyNetworkConfig(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostName() {
        return hostname;
    }

    public void setHostName(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProxyNetworkConfig proxyNetWorkConfig = (ProxyNetworkConfig) o;
        return Objects.equals(hostname, proxyNetWorkConfig.hostname) && Objects.equals(port, proxyNetWorkConfig.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostname, port);
    }

    @Override
    public String toString() {
        return "{" + "\"hostname\":\"" + hostname + "\"," + "\"port\":\"" + port + "\"}";
    }
}