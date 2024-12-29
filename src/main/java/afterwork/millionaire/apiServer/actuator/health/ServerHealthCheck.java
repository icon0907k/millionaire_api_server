package afterwork.millionaire.apiServer.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ServerHealthCheck implements HealthIndicator {

    // 서버 상태를 저장하는 필드
    private boolean serverAvailable = true;

    @Override
    public Health health() {
        // 서버 상태를 확인하여 Health 상태 반환
        if (serverAvailable) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }

    // 서버 상태를 설정하는 메서드
    public void setServerAvailable(boolean serverAvailable) {
        this.serverAvailable = serverAvailable;
    }

    // 서버 상태를 확인하는 메서드
    private boolean isServerAvailable() {
        return serverAvailable;
    }
}
