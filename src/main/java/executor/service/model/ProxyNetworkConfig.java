package executor.service.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ProxyNetworkConfig {
    @JsonProperty("hostName")
    @JsonAlias("hostname")
    private String hostName;

    private Integer port;

    public ProxyNetworkConfig() {

    }

    public ProxyNetworkConfig(String hostName, Integer port) {
        this.hostName = hostName;
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostname) {
        this.hostName = hostname;
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
        return Objects.equals(hostName, proxyNetWorkConfig.hostName) && Objects.equals(port, proxyNetWorkConfig.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostName, port);
    }

    @Override
    public String toString() {
        return "{" + "\"hostname\":\"" + hostName + "\"," + "\"port\":" + port + "}";
    }
}