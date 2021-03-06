package com.piotrglazar.jmooolelo.providers;

import com.google.common.base.Throwables;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class BasicInterfaceProvider implements InterfaceProvider {

    private static final Pattern IP_PATTERN = Pattern.compile("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");

    private final String interfaceName;
    private final String ip;
    private final String hostname;

    public BasicInterfaceProvider(String interfaceName) {
        this.interfaceName = interfaceName;
        this.ip = findIpAddress();
        this.hostname = findHostname();
    }

    public BasicInterfaceProvider() {
        this("eth0");
    }

    private String findHostname() {
        return run(InetAddress::getLocalHost).getHostName();
    }

    private String findIpAddress() {
        Enumeration<InetAddress> inetAddresses = run(() -> NetworkInterface.getByName(interfaceName)).getInetAddresses();
        Optional<String> ipOpt = Collections.list(inetAddresses).stream()
                .map(InetAddress::getHostAddress)
                .filter(s -> IP_PATTERN.matcher(s).find())
                .findAny();
        return ipOpt.orElse("unknown");
    }

    @Override
    public String ip() {
        return ip;
    }

    @Override
    public String hostname() {
        return hostname;
    }

    private <T> T run(Callable<T> action) {
        try {
            return action.call();
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }
}
