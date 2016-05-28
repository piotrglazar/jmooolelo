package com.piotrglazar.jmooolelo.example;

import com.piotrglazar.jmooolelo.DefaultMoooleloClient;
import com.piotrglazar.jmooolelo.MoooleloClient;
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

public class Example {

    private Example() {
        // not needed here
    }

    private static class ExampleClientConfig implements ClientConfig {

        @Override
        public ServerConfig serverConfig() {
            return new ServerConfig() {
                @Override
                public String host() {
                    return "http://localhost";
                }

                @Override
                public int port() {
                    return 8666;
                }
            };
        }

        @Override
        public Duration registrationInterval() {
            return new Duration(3, TimeUnit.SECONDS);
        }

        @Override
        public Duration heartbeatInterval() {
            return new Duration(1, TimeUnit.SECONDS);
        }
    }

    private static class ExampleServiceConfig implements ServiceConfig {

        @Override
        public int serviceId() {
            return 1;
        }

        @Override
        public String serviceType() {
            return "testType";
        }

        @Override
        public String serviceGroup() {
            return "testGroup";
        }

        @Override
        public String environment() {
            return "testEnvironment";
        }

        @Override
        public OptionalInt servicePort() {
            return OptionalInt.empty();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ClientConfig clientConfig = new ExampleClientConfig();
        ServiceConfig serviceConfig = new ExampleServiceConfig();
        DataProvider dataProvider = new DataProvider.DataProviderBuilder(() -> new HealthStatus(true, Optional.empty()),
                new BasicInterfaceProvider()).build();

        MoooleloClient client = new DefaultMoooleloClient(clientConfig, serviceConfig, dataProvider);

        client.start();

        Thread.sleep(10000L);
    }
}
