package executor.service.controller;

import executor.service.model.ProxyConfigHolder;
import executor.service.service.holder.proxy.ProxySourcesClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProxyController {
    private ProxySourcesClient proxySourcesClient;

    @PostMapping(value = "/proxy/add", consumes = "application/json", produces = "application/json")
    private ResponseEntity<?> addProxy(@RequestBody ProxyConfigHolder proxyConfigHolder) {
        proxySourcesClient.addProxy(proxyConfigHolder);
        return ResponseEntity.ok("Added proxy successfully");
    }
}
