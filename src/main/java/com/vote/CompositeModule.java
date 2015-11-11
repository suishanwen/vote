package com.vote;

import com.heren.i0.config.Configuration;
import com.heren.i0.config.util.LogLevel;
import com.heren.i0.container.grizzly.EmbeddedGrizzly;
import com.heren.i0.container.grizzly.WebSocket;
import com.heren.i0.core.Application;
import com.heren.i0.core.ApplicationModule;
import com.heren.i0.core.GuiceModule;
import com.heren.i0.core.Servlet3;
import com.heren.i0.jersey.RestApi;
import com.heren.i0.jpa.JpaConfiguration;
import com.heren.i0.jpa.JpaPersist;
import com.heren.i0.jpa.config.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static com.heren.i0.config.Configuration.config;
import static com.heren.i0.config.Configuration.read;
import static com.heren.i0.jpa.DatabaseConfiguration.database;

@Application("vote")
@GuiceModule
@Servlet3
@RestApi
@JpaPersist(unit = "domain")
@EmbeddedGrizzly(assets =
        {
                @EmbeddedGrizzly.Asset(uri = "/app", resource = "app"),
                @EmbeddedGrizzly.Asset(uri = "/assets", resource = "assets"),
                @EmbeddedGrizzly.Asset(uri = "/css", resource = "css"),
                @EmbeddedGrizzly.Asset(uri = "/js", resource = "js")
        },
        mimeExtensions = {@EmbeddedGrizzly.MimeExtension(extension = "eot", mime = "application/vnd.ms-fontobject"),
                @EmbeddedGrizzly.MimeExtension(extension = "ttf", mime = "application/x-font-opentype"),
                @EmbeddedGrizzly.MimeExtension(extension = "otf", mime = "application/x-font-opentype"),
                @EmbeddedGrizzly.MimeExtension(extension = "woff", mime = "application/x-font-woff")}
)
/*@WebSocket*/
/*@DefaultTimeZone("Asia/Shanghai")*/
//@DefaultTimeZone("UTC")
/*@ExceptionHandling*/
public class CompositeModule extends ApplicationModule<JpaConfiguration> {

    private static final String DEFAULT_CONFIG_FOLDER = "./";
    private static final String DEFAULT_YML_FILENAME = "config.yml";
    private static Logger logger = LoggerFactory.getLogger(CompositeModule.class);
    private static JpaConfiguration jpaConfiguration;

    public CompositeModule() {
        super();
//        createDefaultConfiguration(null);
    }

    @Override
    protected JpaConfiguration createDefaultConfiguration(Configuration.ConfigurationBuilder config) {
        try {
            /*String herenFile = SystemProperty.getHerenFile(DEFAULT_YML_FILENAME);
            if (herenFile.length() > 0) {
                JpaConfiguration systemConfiguration = readConfigurationFromFile(herenFile);
                if (systemConfiguration != null) {
                    logger.info("Reading configuration from system environment");
                    jpaConfiguration = systemConfiguration;
                    return jpaConfiguration;
                }
            }*/
            URL configFile = this.getClass().getClassLoader().getResource(DEFAULT_CONFIG_FOLDER + DEFAULT_YML_FILENAME);
            if (configFile != null) {
                logger.info("Reading configuration from project config");
                jpaConfiguration = readConfigurationFromFile(configFile.getFile());
            } else {
                logger.warn("config file in project url is null");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (jpaConfiguration == null) {
            jpaConfiguration = defaultConfiguration();
        }
        return jpaConfiguration;
    }

    private JpaConfiguration readConfigurationFromFile(String path) {
        JpaConfiguration jpaConfiguration = null;
        try {
            File file = new File(path);
            if (logger.isInfoEnabled()) {
                logger.info("Reading configuration from file {}", file.getCanonicalPath());
            }
            if (file.exists()) {
                jpaConfiguration = read(new FileInputStream(file), JpaConfiguration.class);
            } else {
                logger.warn("config file {} not exist", file.getCanonicalPath());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return jpaConfiguration;
    }

    private static JpaConfiguration defaultConfiguration() {
        if (logger.isInfoEnabled())
            logger.info("No configuration found, will use default configuration.");

        return new JpaConfiguration(config().http().port(8888).end()
                .logging().level(LogLevel.INFO).console().end().end().build(),
                database().user("vote").password("vote").driver("oracle.jdbc.driver.OracleDriver")
                        .url("jdbc:oracle:thin:@localhost:1521:orcl")
                        .with(Hibernate.showSql, Hibernate.dialect("Oracle10g")).build());
    }
}
