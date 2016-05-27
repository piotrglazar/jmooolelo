package com.piotrglazar.jmooolelo.api;

import com.piotrglazar.jmooolelo.extensions.PimpedGson;
import com.piotrglazar.jmooolelo.testutil.TestCreators;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationRequestTest implements TestCreators {

    @Test
    public void shouldContainAllRequiredFieldsAndNoOptionalFields() {
        // given
        RegistrationRequest registrationRequest = registrationRequest();

        // when
        String json = PimpedGson.gson.toJson(registrationRequest);

        // then
        assertThat(json).contains("id", "serviceType", "group", "environment", "host", "ip", "health");
        assertThat(json).doesNotContain("port");
        assertThat(json).doesNotContain("version");
        assertThat(json).doesNotContain("jvmSettings");
        assertThat(json).doesNotContain("config");
        assertThat(json).doesNotContain("workingDirectory");
    }

    @Test
    public void shouldContainAllFields() {
        // given
        RegistrationRequest registrationRequest = fullRegistrationRequest();

        // when
        String json = PimpedGson.gson.toJson(registrationRequest);

        // then
        assertThat(json).contains("id", "serviceType", "group", "environment", "host", "ip", "health", "port", "version",
                "jvmSettings", "config", "workingDirectory");
    }
}
