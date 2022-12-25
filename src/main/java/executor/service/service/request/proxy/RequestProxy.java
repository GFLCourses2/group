package executor.service.service.request.proxy;

import executor.service.config.RequestConfig;
import executor.service.service.holder.proxy.ProxySourcesClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RequestProxy {
    private ProxySourcesClient proxySourcesClient;
    private RequestConfig requestConfig;

    public RequestProxy(ProxySourcesClient proxySourcesClient, RequestConfig requestConfig) {
        this.proxySourcesClient = proxySourcesClient;
        this.requestConfig = requestConfig;
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void sendRequest() {

    }
}
