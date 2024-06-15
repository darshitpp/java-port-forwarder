package dev.darshit.java_ssh_tunnel;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import dev.darshit.java_ssh_tunnel.ssh.TunnelDetails;
import dev.darshit.java_ssh_tunnel.ssh.UserDetails;

public final class Tunneler {

    private final JSch jsch;
    private final Session session;

    private final TunnelDetails tunnelDetails;

    public Tunneler(JSch jsch, Session session, TunnelDetails tunnelDetails) throws JSchException {
        this.jsch = jsch;
        this.session = session;
        this.session.setConfig("StrictHostKeyChecking", "no"); // deliberate for only testing usage
        this.session.connect();
        this.tunnelDetails = tunnelDetails;
    }

    public Tunneler(UserDetails userDetails, TunnelDetails tunnelDetails) throws JSchException {
        this.jsch = new JSch();
        this.session = this.jsch.getSession(userDetails.getUserName(), userDetails.getSshHost(), userDetails.getSshPort());
        this.session.setPassword(userDetails.getPassword());
        this.session.setConfig("StrictHostKeyChecking", "no"); // deliberate for only testing usage
        this.session.connect();
        this.tunnelDetails = tunnelDetails;
    }

    public Tunneler tunnel() throws JSchException {
        this.session.setPortForwardingL(tunnelDetails.localPort(), tunnelDetails.remoteHost(), tunnelDetails.remotePort());
        return this;
    }

    public void disconnect() {
        if (session.isConnected()) {
            this.session.disconnect();
        }
    }
}
