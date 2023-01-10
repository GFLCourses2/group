package executor.service.service.execution.executionservice;

import executor.service.service.execution.executionservice.worker.Worker;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class DefaultParallelFlowExecutorService extends ParallelFlowExecutorService {
    private final Worker worker;

    public DefaultParallelFlowExecutorService(ThreadPoolExecutor threadPoolExecutor,
                                              CountDownLatch countDownLatch,
                                              Worker worker) {
        super(threadPoolExecutor, countDownLatch);
        this.worker = worker;
    }

    @Override
    protected Worker getWorker() {
        return worker;
    }
}
