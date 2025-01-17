package afterwork.millionaire.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * ServerHealthCheck
 * 이 클래스는 서버의 상태를 확인하고 Spring Boot Actuator의 HealthIndicator 인터페이스를 구현하여
 * 서버의 상태를 헬스 체크 엔드포인트로 반환하는 기능을 제공합니다.
 * - 서버가 정상적으로 작동하면 "up" 상태를 반환하고,
 * - 서버가 사용 불가능하면 "down" 상태를 반환합니다.
 */
@Component
public class ServerHealthCheck implements HealthIndicator {

    // 서버 상태를 나타내는 필드 (기본값: true, 즉 서버는 기본적으로 정상으로 설정)
    private boolean serverAvailable = true;

    /**
     * 서버의 상태를 확인하여 Spring Boot Actuator 헬스 체크에 필요한 상태를 반환합니다.
     * 서버가 정상적으로 작동 중이면 "Health.up()"을 반환하고,
     * 서버가 다운되었으면 "Health.down()"을 반환합니다.
     * @return 서버의 상태를 나타내는 Health 객체
     */
    @Override
    public Health health() {
        // 서버 상태에 따라 Health 상태를 반환
        if (serverAvailable) {
            return Health.up().build();  // 서버가 정상일 때
        } else {
            return Health.down().build();  // 서버가 다운되었을 때
        }
    }

    /**
     * 서버 상태를 설정하는 메서드
     * 서버의 상태를 변경할 수 있습니다. 이 메서드를 호출하여 서버 상태를 "up" 또는 "down"으로 설정할 수 있습니다.
     * @param serverAvailable 서버의 상태 (true: 정상, false: 다운)
     */
    public void setServerAvailable(boolean serverAvailable) {
        this.serverAvailable = serverAvailable;
    }

    /**
     * 서버 상태를 확인하는 메서드
     * 서버가 정상적으로 작동 중인지 확인하는 내부 메서드입니다.
     * @return 서버 상태 (true: 정상, false: 다운)
     */
    private boolean isServerAvailable() {
        return serverAvailable;
    }
}
