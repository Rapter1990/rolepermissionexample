package com.security.rolepermissionexample.auth.utils;

import lombok.experimental.UtilityClass;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.IOException;
import java.io.StringReader;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Utility class named {@link KeyConverter} for converting PEM formatted keys to Java PublicKey and PrivateKey objects.
 */
@UtilityClass
public class KeyConverter {

    /**
     * Converts a PEM formatted public key string to a Java PublicKey object.
     *
     * @param publicPemKey The PEM formatted public key string.
     * @return The converted PublicKey object.
     * @throws RuntimeException If an IOException occurs during key conversion.
     */
    public PublicKey convertPublicKey(final String publicPemKey) {

        final StringReader keyReader = new StringReader(publicPemKey);
        try {
            SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo
                    .getInstance(new PEMParser(keyReader).readObject());
            return new JcaPEMKeyConverter().getPublicKey(publicKeyInfo);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    /**
     * Converts a PEM formatted private key string to a Java PrivateKey object.
     *
     * @param privatePemKey The PEM formatted private key string.
     * @return The converted PrivateKey object.
     * @throws RuntimeException If an IOException occurs during key conversion.
     */
    public PrivateKey convertPrivateKey(final String privatePemKey) {

        StringReader keyReader = new StringReader(privatePemKey);
        try {
            PrivateKeyInfo privateKeyInfo = PrivateKeyInfo
                    .getInstance(new PEMParser(keyReader).readObject());
            return new JcaPEMKeyConverter().getPrivateKey(privateKeyInfo);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

}
