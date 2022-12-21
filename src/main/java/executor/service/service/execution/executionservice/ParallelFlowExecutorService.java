package executor.service.service.execution.executionservice;

import executor.service.annotation.Main;
import executor.service.service.execution.executionservice.worker.Worker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@AllArgsConstructor
public abstract class ParallelFlowExecutorService {

    private ThreadPoolExecutor threadPoolExecutor;

    private CountDownLatch countDownLatch;

    @Main
    public void run() {
        startWorkers(threadPoolExecutor);
        threadPoolExecutor.shutdown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void startWorkers(ThreadPoolExecutor threadPoolExecutor) {
        for (int i = 0; i < threadPoolExecutor.getCorePoolSize(); i++) {
            threadPoolExecutor.execute(getWorker());
        }
    }

    protected abstract Worker getWorker();
}