package fr.digi.m062024.utils;

import fr.digi.m062024.entites.Region;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TraitementRegions {

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

    //      On initialise un set de regions à vide
    private static Set<Region> regions = new HashSet<Region>();


    public TraitementRegions() {


    }

    public static Set<Region> getRegion() {

        //      On fait une boucle for qu'on initialise à 1 pour skip l'en-tête
        for (int i = 1; i < lignes.size(); i++) {
//          sur chaque ligne on créér un tableau de string qui va contenir les infos extraite de chaque ligne en splitant sur ;
            String[] parts = lignes.get(i).split(";");

//          On prend et on stock les infos qui nous intéressent
            String nomRegion = parts[1];
            Integer codeRegion = Integer.parseInt(parts[0]);


//          On ajoute au Set de regions chacune des regions
            regions.add(new Region(nomRegion, codeRegion));

        }

        return regions;
    }
}
