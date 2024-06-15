package dev.darshit.java_ssh_tunnel.ssh;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class UserDetails {

    private final String userName;
    private final String sshHost;
    private final int sshPort;

    private final byte[] password;

    public UserDetails(String userName, String password, String sshHost) {
        this.userName = userName;
        this.sshHost = sshHost;
        this.password = password.getBytes(StandardCharsets.UTF_8);
        this.sshPort = 22; // default port
    }

    public UserDetails(String userName, String password, String sshHost, int sshPort) {
        this.userName = userName;
        this.sshHost = sshHost;
        this.password = password.getBytes(StandardCharsets.UTF_8);
        this.sshPort = sshPort;
    }

    public String getUserName() {
        return userName;
    }

    public String getSshHost() {
        return sshHost;
    }

    public int getSshPort() {
        return sshPort;
    }

    public byte[] getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return sshPort == that.sshPort && Objects.equals(userName, that.userName) && Objects.equals(sshHost, that.sshHost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, sshHost, sshPort);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userName='" + userName + '\'' +
                ", sshHost='" + sshHost + '\'' +
                ", sshPort=" + sshPort +
                ", password='<HIDDEN>'" +
                '}';
    }
}
