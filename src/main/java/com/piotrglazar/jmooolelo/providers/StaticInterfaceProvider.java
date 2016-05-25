package com.piotrglazar.jmooolelo.providers;

public class StaticInterfaceProvider implements InterfaceProvider {

    private final String ip;
    private final String hostname;

    public StaticInterfaceProvider(String ip, String hostname) {
        this.ip = ip;
        this.hostname = hostname;
    }

    @Override
    public String ip() {
        return ip;
    }

    @Override
    public String hostname() {
        return hostname;
    }
}
