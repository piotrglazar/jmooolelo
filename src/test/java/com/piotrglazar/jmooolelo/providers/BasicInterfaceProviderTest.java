package com.piotrglazar.jmooolelo.providers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicInterfaceProviderTest {

    @Test
    public void shouldGetIpAddressAndHostname() {
        // given
        BasicInterfaceProvider provider = new BasicInterfaceProvider();

        // expect
        assertThat(provider.hostname()).isNotEmpty();
        assertThat(provider.ip()).doesNotContain("unknown");
    }
}
