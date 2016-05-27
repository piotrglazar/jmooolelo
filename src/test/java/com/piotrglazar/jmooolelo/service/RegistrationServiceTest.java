package com.piotrglazar.jmooolelo.service;

import com.piotrglazar.jmooolelo.gateway.MoooleloGateway;
import com.piotrglazar.jmooolelo.testutil.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest implements TestUtils {

    @Mock
    private MoooleloGateway gateway;

    private TestConcurrencyConfig concurrencyConfig = new TestConcurrencyConfig();

    private RegistrationService service;

    @Before
    public void create() {
        service = new RegistrationService(gateway, dummyDataProvider(), dummyClientConfig(), dummyServiceConfig(), concurrencyConfig);
    }

    @Test
    public void shouldSendRegistrationRequestOnStart() {
        // given
        service.register();

        // when
        concurrencyConfig.intervalScheduler().advanceTimeBy(50, TimeUnit.MILLISECONDS);

        // then
        verify(gateway).register(any());
    }

    @Test
    public void shouldSendRegistrationRequestAfterInterval() {
        // given
        service.register();

        // when
        concurrencyConfig.intervalScheduler().advanceTimeBy(1500, TimeUnit.MILLISECONDS);

        // then
        verify(gateway, times(2)).register(any());
    }
}
