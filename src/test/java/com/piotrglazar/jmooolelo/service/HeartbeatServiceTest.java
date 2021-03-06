package com.piotrglazar.jmooolelo.service;

import com.piotrglazar.jmooolelo.api.HeartbeatRequest;
import com.piotrglazar.jmooolelo.gateway.MoooleloGateway;
import com.piotrglazar.jmooolelo.testutil.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HeartbeatServiceTest implements TestUtils {

    @Mock
    private MoooleloGateway moooleloGateway;

    private TestConcurrencyConfig concurrencyConfig = new TestConcurrencyConfig();

    private HeartbeatService heartbeatService;

    @Before
    public void create() {
        heartbeatService = new HeartbeatService(moooleloGateway, dummyDataProvider(), dummyClientConfig(),
                dummyServiceConfig(), concurrencyConfig);
    }

    @Test
    public void shouldSendHeartbeatRequestAfterInterval() {
        // given
        heartbeatService.heartbeat();

        // when
        concurrencyConfig.intervalScheduler().advanceTimeBy(1500, TimeUnit.MILLISECONDS);

        // then
        verify(moooleloGateway).heartbeat(any(HeartbeatRequest.class));
    }
}
