package fr.digi.m062024.utils;

import fr.digi.m062024.entites.Departement;
import fr.digi.m062024.entites.Region;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TraitementDepartements {


    //      On définit le chemin du fichier source communes.csv que l'on va utiliser
    private static Path path = Paths.get("src/main/resources/Departements.csv");
    private static Path pathCommunes = Paths.get("src/main/resources/Communes.csv");

    //      On lit toutes les lignes du fichier communes.csv que l'on stock dans la liste lignes
    private static List<String> lignes;
    private static List<String> lignesCommunes;

    static {
        try {
            lignes = Files.readAllLines(path);
            lignesCommunes = Files.readAllLines(pathCommunes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //      On initialise un set de departements à vide
    private static Set<Departement> departements = new HashSet<Departement>();

    //      On importe les régions
    private static Set<Region> regions = TraitementRegions.getRegion();


    public TraitementDepartements() {
    }


    public static Set<Departement> getDepartements() {

        for (int i = 1; i < lignesCommunes.size(); ++i) {

            String[] parts = lignesCommunes.get(i).split(";");

            Integer codeRegion = Integer.valueOf(parts[0]);


            for (int j = 1; j < lignes.size(); ++j) {

                String[] partsTwo = lignes.get(j).split(";");

                String code = partsTwo[0];
                String nom = partsTwo[1];

                Departement departement = new Departement(code, nom);


                for (Region region : regions) {

                    if (region.getCode().equals(codeRegion)) {
                        departement.setRegion(region);
                        System.out.println("Assignation de la région " + region.getNom() + " au département " + departement.getNom());
                        break;
                    }

                }

                if (!departements.contains(departement)) {
                    departements.add(departement);
                }

            }


        }
        return departements;
    }


}

