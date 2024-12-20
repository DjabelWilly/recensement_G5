package fr.digi.m062024.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class PersistenceManager {

    private static EntityManagerFactory emf;

    private PersistenceManager() {

    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("traitement-fichier");
        }
        return emf;
    }

    public static void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
    }

}
