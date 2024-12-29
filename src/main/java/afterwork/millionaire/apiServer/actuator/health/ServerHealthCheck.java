package afterwork.millionaire.apiServer.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ServerHealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        boolean isHealthy = isServerAvailable();

        if (isHealthy) {
            return Health.up().withDetail("status", "Service is healthy").build();
        } else {
            return Health.down().withDetail("status", "Service is unhealthy").build();
        }
    }
    // 서버 상태 확인
    private boolean isServerAvailable() {
        return true;
    }
}
