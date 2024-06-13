package dev.darshit.java_port_forwarder;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import dev.darshit.java_port_forwarder.ssh.ForwardDetails;
import dev.darshit.java_port_forwarder.ssh.UserDetails;

public final class Forwarder {

    private final JSch jsch;
    private final Session session;

    private final ForwardDetails forwardDetails;

    public Forwarder(JSch jsch, Session session, ForwardDetails forwardDetails) throws JSchException {
        this.jsch = jsch;
        this.session = session;
        this.session.setConfig("StrictHostKeyChecking", "no"); // deliberate for only testing usage
        this.session.connect();
        this.forwardDetails = forwardDetails;
    }

    public Forwarder(UserDetails userDetails, ForwardDetails forwardDetails) throws JSchException {
        this.jsch = new JSch();
        this.session = this.jsch.getSession(userDetails.getUserName(), userDetails.getSshHost(), userDetails.getSshPort());
        this.session.setPassword(userDetails.getPassword());
        this.session.setConfig("StrictHostKeyChecking", "no"); // deliberate for only testing usage
        this.session.connect();
        this.forwardDetails = forwardDetails;
    }

    public Forwarder forward() throws JSchException {
        this.session.setPortForwardingL(forwardDetails.getLocalPort(), forwardDetails.getRemoteHost(), forwardDetails.getRemotePort());
        return this;
    }

    public void disconnect() {
        if (session.isConnected()) {
            this.session.disconnect();
        }
    }
}
