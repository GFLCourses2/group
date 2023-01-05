package executor.service.service.holder.proxy;

import executor.service.model.ProxyConfigHolder;

import java.util.Optional;

public interface ProxyHandler {
    Optional<ProxyConfigHolder> getProxy();

    void addProxy(ProxyConfigHolder proxyConfigHolder);
}
