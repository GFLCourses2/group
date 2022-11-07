package executor.service.model;

import java.lang.Integer;
import java.lang.String;
import java.util.Objects;


class ThreadPoolConfig {
	private Integer corePoolSize;
	private Long keepAliveTime;

	public ThreadPoolConfig() {}

	public ThreadPoolConfig (Integer corePoolSize, Long keepAliveTime) {
		this.corePoolSize = corePoolSize;
		this.keepAliveTime = keepAliveTime;
	}

	public Integer getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(Integer corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public Long getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTime(Long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

    @Override
	public String toString() {
		return "{\"corePoolSize\": " + corePoolSize + ", \"keepAliveTime\": " + keepAliveTime + '}';
	}

    @Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof ThreadPoolConfig)) return false;
		ThreadPoolConfig other = (ThreadPoolConfig) o;

		return Objects.equals(this.corePoolSize, other.corePoolSize) && Objects.equals(this.keepAliveTime, other.keepAliveTime);
	}

    @Override
	public int hashCode() {
		return Objects.hash(corePoolSize, keepAliveTime);
	}
}