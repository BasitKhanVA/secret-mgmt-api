package gov.va.bip.secretmgmt.service;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import gov.va.bip.secretmgmt.model.Secret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class SecretServiceImpl implements SecretService {
    private static final Logger logger = LoggerFactory.getLogger(SecretServiceImpl.class);
    private final Vault vault;
    private final String vaultSecretPath;

    public SecretServiceImpl() {
        String vaultAddr = System.getenv("VAULT_ADDR");
        String vaultToken = System.getenv("VAULT_TOKEN");
        this.vaultSecretPath = System.getenv("VAULT_SECRET_PATH");

        if (vaultAddr == null || vaultToken == null || vaultSecretPath == null) {
            throw new IllegalStateException("One or more required environment variables are not set");
        }

        try {
            VaultConfig config = new VaultConfig()
                    .address(vaultAddr)
                    .token(vaultToken)
                    .build();
            this.vault = new Vault(config);
        } catch (VaultException e) {
            logger.error("Failed to initialize Vault client", e);
            throw new RuntimeException("Failed to initialize Vault client", e);
        }
    }

    @Override
    public Optional<Secret> getSecret(String key) {
        try {
            String value = vault.logical()
                    .read(vaultSecretPath)
                    .getData()
                    .get(key);

            if (value != null) {
                return Optional.of(new Secret(key, value));
            } else {
                return Optional.empty();
            }
        } catch (VaultException e) {
            logger.error("Failed to retrieve secret from Vault", e);
            return Optional.empty();
        }
    }

    @Override
    public String getVaultSecretPath() {
        return vaultSecretPath;
    }
}