package eni.calendrier;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class Calendrier {

    public static void main(String[] args) {
        int year = new GregorianCalendar().get(Calendar.YEAR);
        int month = new GregorianCalendar().get(Calendar.MONTH);
        displayCalendar(year, month);
    }

    /**
     * Affiche le calendrier pour une année et un mois définie via les paramètres ainsi que les différentes options pour celui-ci.
     *
     * @param year >> Année affichée.
     * @param month >> Mois affiché.
     */
    public static void displayCalendar(int year, int month) {
        clearScreen();
        displayMonth(year, month);
        displayOptions();
        updateCalendar(year, month);
    }

    /**
     * Affiche le calendrier pour une année et un mois définie via les paramètres.
     *
     * @param year >> Année affichée.
     * @param month >> Mois affiché.
     */
    public static void displayMonth(int year, int month) {
        GregorianCalendar calendar = new GregorianCalendar(year, month, 1);
        int dayOfTheWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK) == 1 ? 7 : calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;
        int lastOFTheMonth = calendar.getMaximum(GregorianCalendar.DAY_OF_MONTH);
        // Affichage:
        System.out.println(
                "# " +
                new String(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE).toUpperCase()) +
                " " + year);
        System.out.println("L  Ma Me J  V  S  D ");
        // Affichage à vide des jours avant le premier du mois:
        for (int days = 1; days < dayOfTheWeek; days ++) {
            System.out.print("   ");
        }
        // Affiche des jours:
        for (int daysInMonth = 1; daysInMonth < lastOFTheMonth + 1; daysInMonth ++) {
            System.out.print(daysInMonth < 10 ? "0" + daysInMonth + " " : + daysInMonth + " ");
            // Si le jour est plus grand que dimanche, celui-ci est réinitialisé à lundi et on revient à la ligne:
            if (++ dayOfTheWeek > 7) {
                dayOfTheWeek = 1;
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * Permet d'afficher le menu de commande.
     */
    public static void displayOptions() {
        System.out.println("(-) Précédent");
        System.out.println("        | Suivant (+)");
        System.out.println("(?) Choisir une date");
        System.out.println("(Q) Quitter");
    }

    /**
     * Permet de modifier l'année et/ou le mois entrés en paramètres afin d'actualiser le calendrier.
     *
     * @param year >> Année à modifier.
     * @param month >> Mois à modifier.
     */
    public static void updateCalendar(int year, int month) {
        Scanner console = new Scanner(System.in);
        while (true) {
            String selection = console.nextLine().toUpperCase();
            // Cette version du "switch' requiert Java 12 ou supérieur~
            switch (selection) {
                case "+" -> displayCalendar(year, ++month);
                case "-" -> displayCalendar(year, --month);
                case "?" -> {
                    System.out.println("Veuillez choisir une année:");
                    year = console.nextInt();
                    System.out.println("Veuillez choisir une mois (1-12):");
                    month = console.nextInt() - 1;
                    displayCalendar(year, month);
                }
                case "Q" -> System.exit(0);
                default -> System.out.println("Saisie invalide.");
            }
        }
    }

    /**
     * Permet de remonter le contenu de la console, pour la vider.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
