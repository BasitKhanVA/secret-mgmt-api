package gov.va.bip.secretmgmt.service;

import gov.va.bip.secretmgmt.model.Secret;
import java.util.Optional;

public interface SecretService {
    Optional<Secret> getSecret(String key);
    String getVaultSecretPath();
}