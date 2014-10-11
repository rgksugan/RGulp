package in.iamsugan.rgulp.util;

/**
 * Created by sugan on 11/10/14.
 */
import java.security.PublicKey;

import net.schmizz.sshj.transport.verification.HostKeyVerifier;

public class NullHostKeyVerifier implements HostKeyVerifier {

    /*
     * This method is used to bypass HostKeyVerification.
     * It returns true for whatever the input is.
     *
     */
    @Override
    public boolean verify(String arg0, int arg1, PublicKey arg2) {
        return true;
    }
}
