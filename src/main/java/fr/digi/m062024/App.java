package fr.digi.m062024;


import fr.digi.m062024.entites.Commune;
import fr.digi.m062024.entites.Region;
import fr.digi.m062024.utils.PersistenceManager;
import fr.digi.m062024.utils.TraitementCommunes;
import fr.digi.m062024.utils.TraitementRegions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws IOException {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Set<Region> regions = TraitementRegions.getRegion();
        Set<Commune> communes = TraitementCommunes.getCommunes();


//      On boucle dans le set de regions et pour chaque region on l'envoi en base de données
        for (Region region : regions) {
            em.persist(region);
        }

//      On boucle dans le set de regions et pour chaque region on l'envoi en base de données
        for (Commune commune : communes) {
            em.persist(commune);
        }

        em.getTransaction().commit();
        em.close();
        PersistenceManager.closeEntityManagerFactory();
    }
}