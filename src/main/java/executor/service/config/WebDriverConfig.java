package executor.service.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static executor.utils.PropertiesHelper.getPropertyLongValue;
import static executor.utils.PropertiesHelper.readProperties;

@Component(WebDriverConfig.BEAN_NAME)
@Data
public class WebDriverConfig {
    public static final String BEAN_NAME = "properties_WebDriverConfig";

    private static final String CONFIG_PATH = "src/main/java/resources/webDriverConfig.properties";

    private static final String EXECUTABLE_KEY = "executorservice.common.webDriverExecutable";
    private static final String USER_AGENT_KEY = "executorservice.common.userAgent";
    private static final String IMPLICITLY_WAIT_KEY = "executorservice.common.implicitlyWait";
    private static final String PAGE_LOAD_TIMEOUT_KEY = "executorservice.common.pageLoadTimeout";

    private static final String EXECUTABLE_DEF_VALUE = "src/main/java/resources/chromedriver.exe";
    private static final String USER_AGENT_DEF_VALUE = "browser";
    private static final long IMPLICITLY_WAIT_DEF_VALUE = 3L;
    private static final long PAGE_LOAD_TIMEOUT_DEF_VALUE = 30000L;

    private String webDriverExecutable;
    private String userAgent;
    private Long implicitlyWait;
    private Long pageLoadTimeout;

    @PostConstruct
    private void init() {
        Properties properties = readProperties(CONFIG_PATH);

        this.webDriverExecutable = properties.getProperty(EXECUTABLE_KEY, EXECUTABLE_DEF_VALUE);
        this.userAgent = properties.getProperty(USER_AGENT_KEY, USER_AGENT_DEF_VALUE);
        this.implicitlyWait = getPropertyLongValue(properties, IMPLICITLY_WAIT_KEY, IMPLICITLY_WAIT_DEF_VALUE);
        this.pageLoadTimeout = getPropertyLongValue(properties, PAGE_LOAD_TIMEOUT_KEY, PAGE_LOAD_TIMEOUT_DEF_VALUE);
    }
}
