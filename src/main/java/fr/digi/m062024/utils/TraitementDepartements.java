package fr.digi.m062024.utils;

import fr.digi.m062024.entites.Departement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TraitementDepartements {
    public TraitementDepartements() {
    }

    public static Set<Departement> getDepartements() throws IOException {
        Path path = Paths.get("D:\\06 - Java\\recensement_G5\\src\\main\\resources\\Departements.csv");
        List<String> lignes = Files.readAllLines(path);
        Set<Departement> departements = new HashSet();

        for(int i = 1; i < lignes.size(); ++i) {
            String[] parts = ((String)lignes.get(i)).split(";");
            String code = parts[0];
            String nom = parts[1];
            departements.add(new Departement(code, nom));
        }

        return departements;
    }
}
