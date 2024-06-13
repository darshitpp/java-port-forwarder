package dev.darshit.java_port_forwarder;

import com.jcraft.jsch.JSchException;
import dev.darshit.java_port_forwarder.ssh.ForwardDetails;
import dev.darshit.java_port_forwarder.ssh.UserDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class MultiPortForwarder {

    private final UserDetails userDetails;

    private final Set<ForwardDetails> forwardDetailsSet;

    private final List<Forwarder> forwarderSessions;

    public MultiPortForwarder(UserDetails userDetails, Set<ForwardDetails> forwardDetailsSet) {
        this.userDetails = userDetails;
        this.forwardDetailsSet = forwardDetailsSet;
        this.forwarderSessions = new ArrayList<>(forwardDetailsSet.size());
    }

    public void forward() {
        try {
            for (ForwardDetails forwardDetails : forwardDetailsSet) {
                forwarderSessions.add(new Forwarder(userDetails, forwardDetails).forward());
            }
            System.out.println("Press Enter to terminate the tunnels...");
            System.in.read();
        } catch (JSchException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            forwarderSessions.forEach(Forwarder::disconnect);
        }
    }
}
