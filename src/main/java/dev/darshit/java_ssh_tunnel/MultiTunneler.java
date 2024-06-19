package dev.darshit.java_ssh_tunnel;

import com.jcraft.jsch.JSchException;
import dev.darshit.java_ssh_tunnel.ssh.TunnelDetails;
import dev.darshit.java_ssh_tunnel.ssh.UserDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class MultiTunneler {

    private final UserDetails userDetails;

    private final Set<TunnelDetails> tunnelDetailsSet;

    private final List<Tunneler> tunnels;

    public MultiTunneler(UserDetails userDetails, Set<TunnelDetails> tunnelDetailsSet) {
        this.userDetails = userDetails;
        this.tunnelDetailsSet = tunnelDetailsSet;
        this.tunnels = new ArrayList<>(tunnelDetailsSet.size());
    }

    public void forward() {
        try {
            System.out.println("Starting tunneling...");
            for (TunnelDetails tunnelDetails : tunnelDetailsSet) {
                startTunnel(tunnelDetails);
            }
            System.out.println("Press Enter to terminate the tunnels...");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            tunnels.forEach(Tunneler::disconnect);
        }
    }

    private void startTunnel(TunnelDetails tunnelDetails) {
        try {
            tunnels.add(new Tunneler(userDetails, tunnelDetails).tunnel());
            System.out.println(tunnelDetails);
        } catch (JSchException e) {
            System.err.println("Failed: " + tunnelDetails);
        }
    }
}
