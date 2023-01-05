package executor.service.service.holder.proxy;

import executor.service.model.ProxyConfigHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


@Service
public class DefaultProxyHandler implements ProxyHandler {
    private final Queue<ProxyConfigHolder> proxyConfigHoldersQueue = new LinkedBlockingQueue<>();

    @Override
    public Optional<ProxyConfigHolder> getProxy() {
        if (proxyConfigHoldersQueue.isEmpty())
            return Optional.empty();
        return Optional.of(proxyConfigHoldersQueue.poll());
    }

    @Override
    public void addProxy(ProxyConfigHolder proxyConfigHolder) {
        proxyConfigHoldersQueue.add(proxyConfigHolder);
    }


}
