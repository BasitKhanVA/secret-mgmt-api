package gov.va.bip.secretmgmt.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SwaggerConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Stream.of(OpenApiResource.class).collect(Collectors.toSet());
    }

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Secret Management API")
                        .version("1.0")
                        .description("API for managing secrets using HashiCorp Vault"));
    }
}