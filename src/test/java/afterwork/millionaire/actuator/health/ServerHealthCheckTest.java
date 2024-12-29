package afterwork.millionaire.actuator.health;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;

import static org.junit.jupiter.api.Assertions.*;

class ServerHealthCheckTest {

    private ServerHealthCheck serverHealthCheck;

    @BeforeEach
    public void setUp() {
        serverHealthCheck = new ServerHealthCheck();
    }

    @Test
    public void testHealthWhenServerIsUp() {
        // 서버가 정상일 때 상태 확인
        Health health = serverHealthCheck.health();
        assertEquals("UP", health.getStatus().toString());
    }

    @Test
    public void testHealthWhenServerIsDown() {
        // 서버를 비정상 상태로 설정
        serverHealthCheck.setServerAvailable(false);

        Health health = serverHealthCheck.health();
        assertEquals("DOWN", health.getStatus().toString());
    }
}