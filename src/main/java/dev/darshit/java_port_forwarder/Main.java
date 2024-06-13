package dev.darshit.java_port_forwarder;

import dev.darshit.java_port_forwarder.ssh.ForwardDetails;
import dev.darshit.java_port_forwarder.ssh.UserDetails;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        UserDetails userDetails = new UserDetails("username", "password", "sshHost", 22);
        ForwardDetails forwardDetails = new ForwardDetails(8090, "remoteHost", 8080);
        MultiPortForwarder multiPortForwarder = new MultiPortForwarder(userDetails, Set.of(forwardDetails));
        multiPortForwarder.forward();
    }
}
