package com.vote.module;


import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceFilter;
import com.vote.HerenCorsFilter;
import com.vote.api.AdminResource;
import com.vote.api.BackgroundResource;
import com.vote.api.ProjectResource;
import com.vote.api.RecordResource;
import com.vote.domain.entity.Background;
import com.vote.domain.entity.BackgroundOld;
import com.vote.domain.entity.Record;
import com.vote.domain.facade.AdminFacade;
import com.vote.domain.facade.BackgroundFacade;
import com.vote.domain.facade.ProjectFacade;
import com.vote.domain.facade.RecordFacade;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

import static com.sun.jersey.api.core.PackagesResourceConfig.PROPERTY_PACKAGES;

public class ResourceModule extends JerseyServletModule {
    @Override
    protected void configureServlets() {

        bind(GuiceContainer.class);
        binder().requireExplicitBindings();
        bind(GuiceFilter.class);
        bind(InvalidRequestServlet.class);
        bind(ProjectFacade.class);
        bind(AdminFacade.class);
        bind(RecordFacade.class);
        bind(BackgroundFacade.class);
        install(new DbModule());
        bind(ProjectResource.class);
        bind(AdminResource.class);
        bind(RecordResource.class);
        bind(BackgroundResource.class);
        bind(HerenCorsFilter.class).in(Singleton.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put(PROPERTY_PACKAGES, "com.vote.api.*");
        filter("/api").through(HerenCorsFilter.class);
        serve("/api/*").with(GuiceContainer.class, params);
    }
}
