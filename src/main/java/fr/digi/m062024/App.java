package fr.digi.m062024;


import fr.digi.m062024.entites.Commune;
import fr.digi.m062024.entites.Departement;
import fr.digi.m062024.entites.Region;
import fr.digi.m062024.utils.PersistenceManager;
import fr.digi.m062024.utils.TraitementCommunes;
import fr.digi.m062024.utils.TraitementDepartements;
import fr.digi.m062024.utils.TraitementRegions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {

    //  COULEURS
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    //  On créé le Principale Menu  avec une méthode pour le réutilisé
    public static void afficherMenu() {
        System.out.println(YELLOW + "-----------------------MENU---------------------" + RESET);
        System.out.println(BLUE + "1. Affichage de la population d’une ville donnée" + RESET);
        System.out.println(BLUE + "2. Affichage de la population d’un département donné" + RESET);
        System.out.println(BLUE + "3. Affichage de la population d’une région donnée" + RESET);
        System.out.println(GREEN + "4. Affichage des N villes les plus peuplées d’un département" + RESET);
        System.out.println(GREEN + "5. Affichage des N villes les plus peuplées d’une région" + RESET);
        System.out.println(GREEN + "6. Affichage des N départements les plus peuplés de France" + RESET);
        System.out.println(RED + "7. Fin de l’application" + RESET);
    }


    public static void main(String[] args) throws IOException {

        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Set<Region> regions = TraitementRegions.getRegion();
        Set<Departement> departements = TraitementDepartements.getDepartements();
        Set<Commune> communes = TraitementCommunes.getCommunes();


        //      On boucle dans le set de regions et pour chaque region on l'envoi en base de données
        for (Region region : regions) {
            em.persist(region);

        }

        //      On boucle dans le set de départements et pour chaque département on l'envoi en base de données
        for (Departement departement : departements) {
            em.persist(departement);
        }

        //      On boucle dans le set de communes et pour chaque commune on l'envoi en base de données
        for (Commune commune : communes) {
            em.persist(commune);
        }


        em.getTransaction().commit();


        Scanner sc = new Scanner(System.in);

        Integer selection = 0;

        do {
            afficherMenu();

            selection = sc.nextInt();

            switch (selection) {

                case 1:
                    System.out.println("Saisir une ville :");
                    String input = sc.next();
                    TypedQuery<Commune> popCommune = em.createQuery("SELECT c FROM Commune c WHERE c.nom = :ville", Commune.class);
                    popCommune.setParameter("ville", input);

                    try {
                        Commune commune = popCommune.getSingleResult();
                        System.out.println("Population dans la ville : " + commune.getPopulation() + " habitants");
                    } catch (NoResultException e) {
                        System.out.println("Aucune commune trouvée avec le nom : " + input);
                    }
                    break;

                case 2:
                    System.out.println("Saisir un département :");
                    input = sc.next();
                    TypedQuery<Long> popDepQuery = em.createQuery("SELECT SUM(c.population) FROM Departement d LEFT JOIN d.communes c WHERE d.nom = :departement", Long.class);
                    popDepQuery.setParameter("departement", input);

                    try {
                        Long popDep = popDepQuery.getSingleResult();
                        System.out.println("Population dans le département : " + popDep + " habitants");
                    } catch (NoResultException e) {
                        System.out.println("Aucun département trouvée avec le nom : " + input);
                    }
                    break;

                case 3:
                    System.out.println("Saisir une region :");
                    input = sc.next();
                    TypedQuery<Long> popRegQuery = em.createQuery("SELECT SUM(c.population) FROM Commune c LEFT JOIN c.departement d LEFT JOIN d.region r WHERE r.nom = :region", Long.class);
                    popRegQuery.setParameter("region", input);

                    try {
                        Long popReg = popRegQuery.getSingleResult();
                        System.out.println("Population dans la region : " + popReg + " habitants");
                    } catch (NoResultException e) {
                        System.out.println("Aucune région trouvée avec le nom : " + input);
                    }
                    break;

                case 4:
                    System.out.println("Saisir un département :");
                    input = sc.next();
                    System.out.println("Saisir un nombres de ville :");
                    Integer num = sc.nextInt();
                    TypedQuery<Commune> nbsCommuneDepQuery = em.createQuery("SELECT c FROM Commune c LEFT JOIN c.departement d WHERE d.nom = :departement ORDER BY c.population DESC", Commune.class);
                    nbsCommuneDepQuery.setParameter("departement", input);

                    try {
                        List<Commune> nbsCommune = nbsCommuneDepQuery.setMaxResults(num).getResultList();
                        System.out.println(nbsCommune);
                    } catch (NoResultException e) {
                        System.out.println("Aucun département trouvée avec le nom : " + input);
                    }
                    break;

                case 5:
                    System.out.println("Saisir une région :");
                    input = sc.next();
                    System.out.println("Saisir un nombres de ville :");
                    num = sc.nextInt();
                    TypedQuery<Commune> nbsCommuneRegQuery = em.createQuery("SELECT c FROM Commune c LEFT JOIN c.departement d LEFT JOIN d.region r WHERE r.nom = :region ORDER BY c.population DESC", Commune.class);
                    nbsCommuneRegQuery.setParameter("region", input);

                    try {
                        List<Commune> nbsCommune = nbsCommuneRegQuery.setMaxResults(num).getResultList();
                        System.out.println(nbsCommune);
                    } catch (NoResultException e) {
                        System.out.println("Aucune région trouvée avec le nom : " + input);
                    }
                    break;

                case 6:
                    System.out.println("Saisir un nombres de département :");
                    num = sc.nextInt();
                    TypedQuery<Departement> nbsDepQuery = em.createQuery("SELECT d FROM Departement d LEFT JOIN d.communes c LEFT JOIN d.region r GROUP BY d.nom ORDER BY SUM(c.population) DESC", Departement.class);

                    try {
                        List<Departement> nbsDep = nbsDepQuery.setMaxResults(num).getResultList();
                        System.out.println(nbsDep);
                    } catch (NoResultException e) {
                        System.out.println("Aucun résultat trouvée...");
                    }
                    break;

            }

        } while (selection != 7);


        em.close();
        PersistenceManager.closeEntityManagerFactory();
    }


}