package com.travel.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Collections;

@ApplicationScoped
public class TravelIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {

        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential user = UsernamePasswordCredential.class.cast(credential);
            if (user.compareTo("use", "test")) {
                return new CredentialValidationResult("user", Collections.singleton("USE"));
            } else if (user.compareTo("airline", "test")) {
                return new CredentialValidationResult("airline", Collections.singleton("AIRLINE"));
            }  else if (user.compareTo("hotel", "test")) {
                return new CredentialValidationResult("hotel", Collections.singleton("HOTEL"));
            }
        }
        return CredentialValidationResult.INVALID_RESULT;
    }
}
