package executor.service.service.execution.proxy;

import executor.service.model.ProxyConfigHolder;

import java.util.Optional;

public interface ProxySourcesClient {
    Optional<ProxyConfigHolder> getProxy();
    void addProxy(ProxyConfigHolder proxyConfigHolder);
}
