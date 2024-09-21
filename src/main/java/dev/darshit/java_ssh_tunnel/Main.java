package dev.darshit.java_ssh_tunnel;

import dev.darshit.java_ssh_tunnel.ssh.TunnelDetails;
import dev.darshit.java_ssh_tunnel.ssh.UserDetails;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        UserDetails userDetails = new UserDetails("username", "password", "sshHost", 22);
        TunnelDetails tunnelDetails = new TunnelDetails(8090, "remoteHost", 8080);
        // Can create Multiple tunnelDetails and pass it into the Set
        // Can also read a resources file to create tunnelDetail objects as required
        MultiTunneler multiTunneler = new MultiTunneler(userDetails, Set.of(tunnelDetails/*, tunnelDetails2, ... */));
        multiTunneler.forward();
    }
}
