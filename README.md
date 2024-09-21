# Java SSH Tunnel

### Structure
```bash
java-ssh-tunnel
└── src
    └── main
        ├── java
        │   └── dev
        │       └── darshit
        │           └── java_ssh_tunnel
        │               ├── Main.java
        │               ├── MultiTunneler.java
        │               ├── Tunneler.java
        │               └── ssh
        │                   ├── TunnelDetails.java
        │                   └── UserDetails.java
        └── resources


```

### Usage

1. Download the project
2. Load up in your IDE
3. Run `mvn clean install`
4. Change `Main.java` with required details like SSH `username`, SSH `password`, JumpHost `sshHost`
5. (Optional) Maybe load up `localPort`, `remoteHost`, and `remotePort` details from a file
6. Run `Main.java`

If all things go well, you'll see following output on your `stdout`
```
Starting tunneling...
<remoteHost>:<remotePort> is available on localhost:<localPort>
Press Enter to terminate the tunnels...
```

### Caveats

While the above is very convenient, ***DO NOT USE IT TO CONNECT TO PROD***

Read my blog post on the same: https://darshit.dev/posts/java-ssh-tunnel/

