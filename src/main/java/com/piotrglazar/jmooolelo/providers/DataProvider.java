package com.piotrglazar.jmooolelo.providers;

import java.util.Optional;

public class DataProvider {

    private final HealthProvider healthProvider;
    private final InterfaceProvider interfaceProvider;
    private final Optional<VersionProvider> versionProvider;
    private final Optional<StartupSettingsProvider> startupSettingsProvider;
    private final Optional<WorkingDirectoryProvider> workingDirectoryProvider;
    private final Optional<ConfigurationProvider> configurationProvider;

    private DataProvider(HealthProvider healthProvider,
                         InterfaceProvider interfaceProvider,
                         Optional<VersionProvider> versionProvider,
                         Optional<StartupSettingsProvider> startupSettingsProvider,
                         Optional<WorkingDirectoryProvider> workingDirectoryProvider,
                         Optional<ConfigurationProvider> configurationProvider) {
        this.healthProvider = healthProvider;
        this.interfaceProvider = interfaceProvider;
        this.versionProvider = versionProvider;
        this.startupSettingsProvider = startupSettingsProvider;
        this.workingDirectoryProvider = workingDirectoryProvider;
        this.configurationProvider = configurationProvider;
    }

    public HealthProvider getHealthProvider() {
        return healthProvider;
    }

    public InterfaceProvider getInterfaceProvider() {
        return interfaceProvider;
    }

    public Optional<VersionProvider> getVersionProvider() {
        return versionProvider;
    }

    public Optional<StartupSettingsProvider> getStartupSettingsProvider() {
        return startupSettingsProvider;
    }

    public Optional<WorkingDirectoryProvider> getWorkingDirectoryProvider() {
        return workingDirectoryProvider;
    }

    public Optional<ConfigurationProvider> getConfigurationProvider() {
        return configurationProvider;
    }

    public static class DataProviderBuilder {
        private final HealthProvider healthProvider;
        private final InterfaceProvider interfaceProvider;
        private Optional<VersionProvider> versionProvider = Optional.empty();
        private Optional<StartupSettingsProvider> startupSettingsProvider = Optional.empty();
        private Optional<WorkingDirectoryProvider> workingDirectoryProvider = Optional.empty();
        private Optional<ConfigurationProvider> configurationProvider = Optional.empty();

        public DataProviderBuilder(HealthProvider healthProvider, InterfaceProvider interfaceProvider) {
            this.healthProvider = healthProvider;
            this.interfaceProvider = interfaceProvider;
        }

        public DataProviderBuilder versionProvider(VersionProvider versionProvider) {
            this.versionProvider = Optional.ofNullable(versionProvider);
            return this;
        }

        public DataProviderBuilder startupSettingsProvider(StartupSettingsProvider startupSettingsProvider) {
            this.startupSettingsProvider = Optional.ofNullable(startupSettingsProvider);
            return this;
        }

        public DataProviderBuilder workingDirectoryProvider(WorkingDirectoryProvider workingDirectoryProvider) {
            this.workingDirectoryProvider = Optional.ofNullable(workingDirectoryProvider);
            return this;
        }

        public DataProviderBuilder configurationProvider(ConfigurationProvider configurationProvider) {
            this.configurationProvider = Optional.ofNullable(configurationProvider);
            return this;
        }

        public DataProvider build() {
            return new DataProvider(healthProvider, interfaceProvider, versionProvider, startupSettingsProvider,
                    workingDirectoryProvider, configurationProvider);
        }
    }
}
