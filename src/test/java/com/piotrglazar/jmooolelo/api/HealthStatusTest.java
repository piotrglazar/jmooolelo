package com.piotrglazar.jmooolelo.api;

import org.junit.Test;

import java.util.Optional;

import static com.piotrglazar.jmooolelo.extensions.PimpedGson.GSON;
import static org.assertj.core.api.Assertions.assertThat;

public class HealthStatusTest {

    @Test
    public void shouldSerializeOptionalWithValue() {
        // given
        HealthStatus healthStatus = new HealthStatus(true, Optional.of("test"));

        // when
        String json = GSON.toJson(healthStatus);

        // then
        assertThat(json).contains("isHealthy");
        assertThat(json).contains("reason");
    }

    @Test
    public void shouldNotSerializeEmptyOptional() {
        // given
        HealthStatus healthStatus = new HealthStatus(true, Optional.empty());

        // when
        String json = GSON.toJson(healthStatus);

        // then
        assertThat(json).contains("isHealthy");
        assertThat(json).doesNotContain("reason");
    }
}
