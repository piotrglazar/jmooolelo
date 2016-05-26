package com.piotrglazar.jmooolelo.providers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JvmStartupSettingsProviderTest {

    @Test
    public void shouldFindStartupArguments() {
        // given
        JvmStartupSettingsProvider provider = new JvmStartupSettingsProvider();

        // expect
        assertThat(provider.get().getSettings()).isNotEmpty();
    }
}