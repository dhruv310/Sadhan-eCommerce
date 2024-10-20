package com.spring_boot.ecommerce.config;

import com.spring_boot.ecommerce.entity.Country;
import com.spring_boot.ecommerce.entity.Product;
import com.spring_boot.ecommerce.entity.ProductCategory;
import com.spring_boot.ecommerce.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        HttpMethod[]  theunsupportedActions ={HttpMethod.DELETE , HttpMethod.PUT , HttpMethod.POST};;


        disableHttpMethods(Country.class, config , theunsupportedActions);;
        disableHttpMethods(Product.class , config , theunsupportedActions);
        disableHttpMethods(State.class , config , theunsupportedActions);
        disableHttpMethods( ProductCategory.class,config, theunsupportedActions);

        //call and internal helper method
        exposeIds(config);

    }

    private static void disableHttpMethods( Class theClass, RepositoryRestConfiguration config, HttpMethod[] theunsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theunsupportedActions)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theunsupportedActions)));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        //expose entity ids

        //get a list of all entity classes from the entity manager
        Set<EntityType<?>> entites = entityManager.getMetamodel().getEntities();


        //create an array of the entity types
        List<Class> entityClass = new ArrayList<>();

        //get the entityt types for the entity types
        for(EntityType tempEntityType : entites){
            entityClass.add(tempEntityType.getJavaType());
        }

        //expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClass.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
