package dev.darshit.java_port_forwarder.ssh;

import java.util.Objects;

public class ForwardDetails {

    private final int localPort;
    private final String remoteHost;

    private final int remotePort;

    public ForwardDetails(int localPort, String remoteHost, int remotePort) {
        this.localPort = localPort;
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    public int getLocalPort() {
        return localPort;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public int getRemotePort() {
        return remotePort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForwardDetails that = (ForwardDetails) o;
        return localPort == that.localPort && remotePort == that.remotePort && Objects.equals(remoteHost, that.remoteHost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localPort, remoteHost, remotePort);
    }
}
