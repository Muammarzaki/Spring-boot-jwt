package com.belajarjwt.security.jwt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;

public class JWTKeyPropeties {
    private RSAKey accessKeyPair;
    private RSAKey refrestKeyPair;
    private RSAPublicKey publicAccessKey;
    private RSAPrivateKey privateAccessKey;
    private RSAPublicKey publicRefrestKey;
    private RSAPrivateKey privateRefrestKey;

    /**
     * @throws JOSEException
     * 
     */
    public JWTKeyPropeties() throws JOSEException {
        RSAKeyGenerator rsaAccessKeyGenerator = new RSAKeyGenerator(2084);
        RSAKeyGenerator rsaRefrestKeyGenerator = new RSAKeyGenerator(2084);
        accessKeyPair = rsaAccessKeyGenerator.generate();
        refrestKeyPair = rsaRefrestKeyGenerator.generate();
        publicAccessKey = accessKeyPair.toRSAPublicKey();
        publicRefrestKey = refrestKeyPair.toRSAPublicKey();
        privateAccessKey = accessKeyPair.toRSAPrivateKey();
        privateRefrestKey = refrestKeyPair.toRSAPrivateKey();
    }

    /**
     * @return the publicAccessKey
     */
    public RSAPublicKey getPublicAccessKey() {
        return publicAccessKey;
    }

    /**
     * @return the privateAccessKey
     */
    public RSAPrivateKey getPrivateAccessKey() {
        return privateAccessKey;
    }

    /**
     * @return the publicRefrestKey
     */
    public RSAPublicKey getPublicRefrestKey() {
        return publicRefrestKey;
    }

    /**
     * @return the privateRefrestKey
     */
    public RSAPrivateKey getPrivateRefrestKey() {
        return privateRefrestKey;
    }

    /**
     * @return the accessKeyPair
     */
    public RSAKey getAccessKeyPair() {
        return accessKeyPair;
    }

    /**
     * @return the refrestKeyPair
     */
    public RSAKey getRefrestKeyPair() {
        return refrestKeyPair;
    }

}
