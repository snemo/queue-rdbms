package com.nuxplanet.queuerdbms.config;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.boot.autoconfigure.jooq.JooqExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JooqConfig {

    @Bean
    public JooqExceptionTranslator exceptionTransformer() {
        return new JooqExceptionTranslator();
    }

    @Bean
    public DefaultConfiguration configuration(DataSource dataSource,
                                              DataSourceConnectionProvider dataSourceConnectionProvider) {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(dataSourceConnectionProvider);
        jooqConfiguration.set(new DefaultExecuteListenerProvider(exceptionTransformer()));

        SQLDialect dialect = SQLDialect.POSTGRES;
        jooqConfiguration.set(dialect);

        return jooqConfiguration;
    }

    @Bean
    public DefaultDSLContext dsl(DataSource dataSource,
                                 DataSourceConnectionProvider dataSourceConnectionProvider) {
        return new DefaultDSLContext(configuration(dataSource, dataSourceConnectionProvider));
    }
}
