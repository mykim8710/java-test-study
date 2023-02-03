package me.mykim.javatest.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    /**
     * 1초 이상 걸리는 메서드에 대해 메세지가 출력
     */
    private static final String START_TIME_KEY = "START_TIME";
    //private static final long THRESHOLD = 1000; // 1s

    private long THRESHOLD;

    public FindSlowTestExtension(long THRESHOLD) {
        this.THRESHOLD = THRESHOLD;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = getStore(context);
        store.put(START_TIME_KEY, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        SlowTest slowTestAnnotation = context.getRequiredTestMethod().getAnnotation(SlowTest.class);

        String testMethodName = context.getRequiredTestMethod().getName();
        ExtensionContext.Store store = getStore(context);

        long startTime = store.remove(START_TIME_KEY, long.class);
        long duration = System.currentTimeMillis() - startTime;

        if(duration > THRESHOLD && slowTestAnnotation == null) {
            log.warn("This Test Method execute Time = {}ms, Please consider mark at method [{}] with @SlowTest Annotation", (duration/1000), testMethodName);
        }
    }

    private static ExtensionContext.Store getStore(ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
        return store;
    }
}
