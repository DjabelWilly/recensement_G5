package fr.digi.m062024.utils;

import fr.digi.m062024.entites.Commune;
import fr.digi.m062024.entites.Departement;
import fr.digi.m062024.entites.Region;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TraitementCommunes {

    //      On définit le chemin du fichier source communes.csv que l'on va utiliser
    private static Path path = Paths.get("src/main/resources/Communes.csv");

    //      On lit toutes les lignes du fichier communes.csv que l'on stock dans la liste lignes
    private static List<String> lignes;

    static {
        try {
            lignes = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //      On initialise un set de communes à vide
    private static Set<Commune> communes = new HashSet<Commune>();
    private static Set<Departement> departements = TraitementDepartements.getDepartements();

    public TraitementCommunes() {
    }

    public static Set<Commune> getCommunes() {

        //      On fait une boucle for qu'on initialise à 1 pour skip l'en-tête
        for (int i = 1; i < lignes.size(); i++) {

//          sur chaque ligne on créér un tableau de string qui va contenir les infos extraite de chaque ligne en splitant sur ;
            String[] parts = lignes.get(i).split(";");

//          On prend et on stock les infos qui nous intéressent
            String nom = parts[6];
            Integer codeCommune = Integer.parseInt(parts[5]);
            String codeDepartement = parts[2];
            Integer population = Integer.parseInt(parts[9].replace(" ", ""));


//          On ajoute au Set de communes chacune des communes
            Commune commune = new Commune(nom, codeCommune, population);

            for (Departement departement : departements) {
                if(departement.getCode().equals(codeDepartement)) {
                    commune.setDepartement(departement);
                }
            }


            communes.add(commune);


        }

        return communes;

    }
}
