package com.piotrglazar.jmooolelo.providers;

import java.util.Map;
import java.util.function.Supplier;

public interface ConfigurationProvider extends Supplier<Map<String, String>> {
}
