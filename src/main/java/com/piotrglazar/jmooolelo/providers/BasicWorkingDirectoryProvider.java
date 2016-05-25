package com.piotrglazar.jmooolelo.providers;

public class BasicWorkingDirectoryProvider implements WorkingDirectoryProvider {

    @Override
    public String get() {
        return System.getProperty("user.dir");
    }
}
