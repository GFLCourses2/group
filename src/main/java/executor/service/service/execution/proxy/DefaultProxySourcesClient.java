package executor.service.service.execution.proxy;

import executor.service.model.ProxyConfigHolder;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


@Service
public class DefaultProxySourcesClient implements ProxySourcesClient {
    private final Queue<ProxyConfigHolder> proxyConfigHoldersQueue = new LinkedBlockingQueue<>();

    @Override
    public Optional<ProxyConfigHolder> getProxy() {
        if(proxyConfigHoldersQueue.isEmpty())
            return Optional.empty();
        return Optional.of(proxyConfigHoldersQueue.poll());
    }

    @Override
    public void addProxy(ProxyConfigHolder proxyConfigHolder) {
        proxyConfigHoldersQueue.add(proxyConfigHolder);
    }


}
