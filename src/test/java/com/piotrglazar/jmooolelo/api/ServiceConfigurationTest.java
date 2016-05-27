package com.piotrglazar.jmooolelo.api;

import com.google.common.collect.ImmutableMap;
import com.piotrglazar.jmooolelo.extensions.PimpedGson;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceConfigurationTest {

    @Test
    public void shouldMapAsJsonMap() {
        // given
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration(ImmutableMap.of("key", "value"));

        // when
        String json = PimpedGson.gson.toJson(serviceConfiguration);

        // then
        assertThat(json).isEqualTo("{\"key\":\"value\"}");
    }
}
