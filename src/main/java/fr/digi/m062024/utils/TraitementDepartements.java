package fr.digi.m062024.utils;

import fr.digi.m062024.entites.Departement;
import fr.digi.m062024.entites.Region;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TraitementDepartements {

    private static Path pathDepartements = Paths.get("src/main/resources/Departements.csv");
    private static Path pathCommunes = Paths.get("src/main/resources/Communes.csv");

    private static List<String> lignesDepartements;
    private static List<String> lignesCommunes;

    // On charge les lignes des fichiers
    static {
        try {
            lignesDepartements = Files.readAllLines(pathDepartements);
            lignesCommunes = Files.readAllLines(pathCommunes);
        } catch (IOException e) {
            throw new RuntimeException("Erreur de lecture des fichiers");
        }
    }

    public static Set<Departement> getDepartements() {

        Set<Departement> departements = new HashSet<>();

        Map<String, Departement> mapDepartements = new HashMap<>();
        Map<Integer, Region> mapRegions = new HashMap<>();


        for (Region region : TraitementRegions.getRegion()) {
            mapRegions.put(region.getCode(), region);
        }

        for (int i = 1; i < lignesDepartements.size(); i++) {

            String[] parts = lignesDepartements.get(i).split(";");

            String code = parts[0];
            String nom = parts[1];

            mapDepartements.put(code, new Departement(code, nom));
        }

        for (int i = 1; i < lignesCommunes.size(); i++) {

            String[] parts = lignesCommunes.get(i).split(";");

            Integer codeRegion = Integer.parseInt(parts[0]);
            String codeDepartement = parts[2];

            Departement departement = mapDepartements.get(codeDepartement);
            Region region = mapRegions.get(codeRegion);

            if (departement != null && region != null) {
                departement.setRegion(region);
                System.out.println("Département " + departement.getNom() + " associé à la région " + region.getNom());
            }
        }

        departements.addAll(mapDepartements.values());
        return departements;
    }
}
