package fr.digi.m062024.utils;

public class Menu {

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

}
