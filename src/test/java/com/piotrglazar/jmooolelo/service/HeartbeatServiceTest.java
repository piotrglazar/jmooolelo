package com.piotrglazar.jmooolelo.service;

import com.piotrglazar.jmooolelo.api.HealthStatus;
import com.piotrglazar.jmooolelo.api.HeartbeatRequest;
import com.piotrglazar.jmooolelo.config.ClientConfig;
import com.piotrglazar.jmooolelo.config.Duration;
import com.piotrglazar.jmooolelo.config.ServerConfig;
import com.piotrglazar.jmooolelo.config.ServiceConfig;
import com.piotrglazar.jmooolelo.gateway.MoooleloGateway;
import com.piotrglazar.jmooolelo.providers.BasicInterfaceProvider;
import com.piotrglazar.jmooolelo.providers.DataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class HeartbeatServiceTest {

    private TestConcurrencyConfig concurrencyConfig = new TestConcurrencyConfig();

    @Mock
    private MoooleloGateway moooleloGateway;

    private DataProvider dataProvider = new DataProvider.DataProviderBuilder(() -> new HealthStatus(true, Optional.empty()), new BasicInterfaceProvider()).build();

    private ClientConfig clientConfig = new ClientConfig() {
        @Override
        public ServerConfig serverConfig() {
            return new ServerConfig() {
                @Override
                public String host() {
                    return "";
                }

                @Override
                public int port() {
                    return 0;
                }
            };
        }

        @Override
        public Duration registrationInterval() {
            return new Duration(1, TimeUnit.SECONDS);
        }

        @Override
        public Duration heartbeatInterval() {
            return new Duration(1, TimeUnit.SECONDS);
        }
    };

    private ServiceConfig serviceConfig = new ServiceConfig() {
        @Override
        public int serviceId() {
            return 1;
        }

        @Override
        public String serviceType() {
            return "type";
        }

        @Override
        public String serviceGroup() {
            return "group";
        }

        @Override
        public String environment() {
            return "environment";
        }

        @Override
        public OptionalInt servicePort() {
            return OptionalInt.empty();
        }
    };

    private HeartbeatService heartbeatService;

    @Before
    public void create() {
        heartbeatService = new HeartbeatService(moooleloGateway, dataProvider, clientConfig,
                serviceConfig, concurrencyConfig);
    }

    @Test
    public void shouldSendHeartbeatRequestAfterInterval() {
        // given
        heartbeatService.heartbeat();

        // when
        concurrencyConfig.intervalScheduler().advanceTimeBy(1500, TimeUnit.MILLISECONDS);

        // then
        Mockito.verify(moooleloGateway).heartbeat(any(HeartbeatRequest.class));
    }
}
