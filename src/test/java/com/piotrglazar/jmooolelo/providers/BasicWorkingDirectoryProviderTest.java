package com.piotrglazar.jmooolelo.providers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicWorkingDirectoryProviderTest {

    @Test
    public void shouldFindWorkingDirectory() {
        // given
        BasicWorkingDirectoryProvider provider = new BasicWorkingDirectoryProvider();

        // expect
        assertThat(provider.get()).contains("jmooolelo");
    }
}