package executor.service.controller;

import executor.service.model.ProxyConfigHolder;
import executor.service.service.execution.proxy.ProxySourcesClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProxyController {
    private ProxySourcesClient proxySourcesClient;

    @PostMapping(value = "/proxy/add",consumes = "application/json")
    private ResponseEntity<?> addProxy(@ModelAttribute ProxyConfigHolder proxyConfigHolder){
        proxySourcesClient.addProxy(proxyConfigHolder);
        return ResponseEntity.ok("Added proxy successfully");
    }
}
