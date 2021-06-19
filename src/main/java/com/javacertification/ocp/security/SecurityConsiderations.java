package com.javacertification.ocp.security;

import java.io.FilePermission;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.function.Function;

public class SecurityConsiderations {

    /**
     * Runs a runnable instance by checking the permissions in the system.
     * The security manager is a way to lock down risky permission at the JVM level. By default, security manager is off
     *
     *
     * This is only enforced when a JVM is
     * running with the Security Manager on and when that policy file has been specified:
     * file my.policy
     * <code>
     *  grant codeBase "file:/myProgram.jar" {
     *      permission java.io.FilerPermission "<<ALL_FILES>>", "read,write";
     *  }
     * </code>
     *
     * <code>
     *  java -Djava.security.manager
     *       -Djava.security.policy=my.policy
     * <code/>
     *
     * @param runnable the action to applied in the read file
     * @throws AccessControlException if the read permission is denied of if the file does not exist.
     */
    public static void executeWithReadPermission(Runnable runnable) throws AccessControlException, IOException, URISyntaxException {
        var file = Paths.get(ClassLoader.getSystemResource("foo.txt").toURI());
        Permission permission = new FilePermission(file.toAbsolutePath().toString(), "read");
        PermissionCollection permissionCollection = permission.newPermissionCollection();

        Files.readAllLines(file).forEach(System.out::println);

        // This throws an AccessControlException of foo.txt is not readable or not found
        AccessController.checkPermission(permission);

        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            runnable.run();
            return null;
        }, new AccessControlContext(new ProtectionDomain[]{new ProtectionDomain(null, permissionCollection)}));
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        executeWithReadPermission(() -> System.out.println("reading the file"));
        Function<Integer, String> x = Integer::toHexString;
    }
}
