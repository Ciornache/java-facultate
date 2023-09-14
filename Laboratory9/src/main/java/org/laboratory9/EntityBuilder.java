package org.laboratory9;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityBuilder {
    private static EntityManagerFactory instance = null;
    private EntityBuilder() {
        super();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if(instance == null)
            instance = Persistence.createEntityManagerFactory("Laboratory9");
        return instance;
    }
}
