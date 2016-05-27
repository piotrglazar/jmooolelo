package com.piotrglazar.jmooolelo.api;

import com.google.common.collect.ImmutableList;
import com.piotrglazar.jmooolelo.extensions.PimpedGson;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JvmSettingsTest {

    @Test
    public void shouldMapAsArrayList() {
        // given
        JvmSettings jvmSettings = new JvmSettings(ImmutableList.of("a", "b", "c"));

        // when
        String json = PimpedGson.GSON.toJson(jvmSettings);

        // then
        assertThat(json).isEqualTo("[\"a\",\"b\",\"c\"]");
    }
}
