package com.develtrex.servidor.configuracion;

import co.elastic.apm.attach.ElasticApmAttacher;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jonathan.torres
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "elastic.apm")
@ConditionalOnProperty(value = "elastic.apm.enabled", havingValue = "true")
public class ElasticApmConfig {

    private static final String SERVER_URL_KEY = "server_urls";
    private String serverUrls;

    private static final String SERVICE_NAME_KEY = "service_name";
    private String serviceName;

    private static final String SECRET_TOKEN_KEY = "secret_token";
    private String secretToken;

    private static final String ENVIRONMENT_KEY = "environment";
    private String environment;

    private static final String APPLICATION_PACKAGES_KEY = "application_packages";
    private String applicationPackages;

    private static final String LOG_LEVEL_KEY = "log_level";
    private String logLevel;
    
    private static final String LOG_FILE_KEY = "log_file";
    private String logFile;
    
    private static final String LOG_ECS_REFORMATTING = "log_ecs_reformatting";
    private String logEcsReformatting;
    
    private static final String ENABLE_LOG_CORRELATION = "enable_log_correlation";
    private String enableLogCorrelation;
    
    private static final String DISABLE_BOOTSTRAP_CHECKS = "disable_bootstrap_checks";
    private String disableBootstrapChecks;

    @PostConstruct
    public void init() {
        Map<String, String> apmProps = new HashMap<>(6);
        apmProps.put(SERVER_URL_KEY, serverUrls);
        apmProps.put(SERVICE_NAME_KEY, serviceName);
        apmProps.put(SECRET_TOKEN_KEY, secretToken);
        apmProps.put(ENVIRONMENT_KEY, environment);
        apmProps.put(APPLICATION_PACKAGES_KEY, applicationPackages);
        apmProps.put(LOG_LEVEL_KEY, logLevel);
        apmProps.put(LOG_FILE_KEY, logFile);
        apmProps.put(LOG_ECS_REFORMATTING, logEcsReformatting);
        apmProps.put(ENABLE_LOG_CORRELATION, enableLogCorrelation);
        apmProps.put(DISABLE_BOOTSTRAP_CHECKS, disableBootstrapChecks);
        ElasticApmAttacher.attach(apmProps);
    }
}
