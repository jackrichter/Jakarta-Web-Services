package com.travel;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures Jakarta RESTful WebServices for the application.
 * @author Jack
 */
@BasicAuthenticationMechanismDefinition
@DeclareRoles({"AIRLINE", "HOTEL", "USER"})
@ApplicationScoped
@ApplicationPath("api")
public class TravelAppConfiguration extends Application {
}
