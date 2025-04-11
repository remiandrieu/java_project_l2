package listchooser.util;

import java.util.Scanner;

/**
 * Une classe utilitaire pour la saisie de chaînes ou d'entiers sur l'entrée
 * standard.
 */

public class Input {
    private static Scanner scanner;

    // Scanner statique pour utiliser la même instance à chaque appel
    static {
        scanner = new Scanner(System.in);
    }

    public Input() {
    }
    
    /**
     * Réinitialise le scanner avec un nouveau flux d'entrée
     * À utiliser pour les tests
     */
    public static void setInputStream(java.io.InputStream inputStream) {
        // Fermer l'ancien scanner pour libérer toutes les ressources
        if (scanner != null) {
            scanner.close();
        }
        scanner = new Scanner(inputStream);
    }
    
    /**
     * permet la saisie d'une chaîne sur l'entrée standard
     * 
     * @return la chaîne saisie
     */
    public static String readString() {
        return scanner.next();
    }

    /**
     * permet la saisie d'un entier sur l'entrée standard
     * 
     * @return l'entier saisi
     */
    public static int readInt() throws java.io.IOException {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
            scanner.skip(".*");
            throw new java.io.IOException();
        }
    }
    
    // pour le test
    public static void main(String[] args) {
        try {
            System.out.print(" chaine : ? ");
            String chaineLue = Input.readString();
            System.out.println("lue  => " + chaineLue);
            System.out.print(" int : ? ");
            int intLu = Input.readInt();
            System.out.println("lue  => " + intLu);
        } catch (java.io.IOException e) {
        }
    }
} // Input