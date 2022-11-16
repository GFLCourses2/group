package executor.service.service;

import executor.service.model.ProxyConfigHolder;

import java.io.IOException;

public interface ProxySourcesClient {
    ProxyConfigHolder[] getProxy() throws IOException;
}
