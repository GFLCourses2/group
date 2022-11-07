package executor.service.model;

import java.util.Objects;

public class WebDriverConfig {
    private String webDriverExecutable;
    private String userAgent;
    private Long pageLoadTimeout;
    private Long implicitlyWait;

    public void setWebDriverExecutable(String webDriverExecutable) {
        this.webDriverExecutable = webDriverExecutable;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setPageLoadTimeout(Long pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public void setImplicitlyWait(Long implicitlyWait) {
        this.implicitlyWait = implicitlyWait;
    }

    public String getWebDriverExecutable() {
        return webDriverExecutable;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Long getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public Long getImplicitlyWait() {
        return implicitlyWait;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        if (this == o) return true;

        WebDriverConfig wdc = (WebDriverConfig) o;
        return webDriverExecutable.equals(wdc.getWebDriverExecutable()) &&
                userAgent.equals(wdc.getUserAgent()) &&
                pageLoadTimeout.equals(wdc.getPageLoadTimeout()) &&
                implicitlyWait.equals(wdc.getImplicitlyWait());
    }

    @Override
    public int hashCode() {
        return Objects.hash(webDriverExecutable, userAgent, pageLoadTimeout, implicitlyWait);
    }

    @Override
    public String toString() {
        return "{" +
                "\"webDriverExecutable\":\"" + webDriverExecutable + "\"" +
                ",\"userAgent\":\"" + userAgent + "\"" +
                ",\"pageLoadTimeout\":" + pageLoadTimeout +
                ",\"implicitlyWait\":" + implicitlyWait +
                "}";
    }
}
