package com.piotrglazar.jmooolelo.testutil;

import com.piotrglazar.jmooolelo.api.HealthStatus;
import com.piotrglazar.jmooolelo.config.ClientConfig;
import com.piotrglazar.jmooolelo.config.Duration;
import com.piotrglazar.jmooolelo.config.ServerConfig;
import com.piotrglazar.jmooolelo.config.ServiceConfig;
import com.piotrglazar.jmooolelo.providers.BasicInterfaceProvider;
import com.piotrglazar.jmooolelo.providers.DataProvider;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;

public interface TestUtils {

    default ServerConfig dummyServerConfig() {
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

    default ClientConfig dummyClientConfig() {
        return new ClientConfig() {
            @Override
            public ServerConfig serverConfig() {
                return dummyServerConfig();
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
    }

    default ServiceConfig dummyServiceConfig() {
        return new ServiceConfig() {
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
    }

    default DataProvider dummyDataProvider() {
        return new DataProvider.DataProviderBuilder(() -> new HealthStatus(true, Optional.empty()), new BasicInterfaceProvider()).build();
    }
}
