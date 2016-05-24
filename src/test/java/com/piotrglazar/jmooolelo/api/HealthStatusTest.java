package com.piotrglazar.jmooolelo.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.piotrglazar.jmooolelo.extensions.OptionalSerializer;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class HealthStatusTest {

    private static final Gson gson = new GsonBuilder().registerTypeAdapter(Optional.class, new OptionalSerializer()).create();

    @Test
    public void shouldSerializeOptionalWithValue() {
        // given
        HealthStatus healthStatus = new HealthStatus(true, Optional.of("test"));

        // when
        String json = gson.toJson(healthStatus);

        // then
        assertThat(json).contains("isHealthy");
        assertThat(json).contains("reason");
    }

    @Test
    public void shouldNotSerializeEmptyOptional() {
        // given
        HealthStatus healthStatus = new HealthStatus(true, Optional.empty());

        // when
        String json = gson.toJson(healthStatus);

        // then
        assertThat(json).contains("isHealthy");
        assertThat(json).doesNotContain("reason");
    }
}