
package Main;


//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

import ConnectionDB.MySQL;
//import DAO.ActiviteCompetenceDAOImpl;
//import DAO.ImplementationProfilDAO;

import DAO.UtilisateurDAO;
//import Enumeration.TypeZone;
import Interface.Database;
//import Model.Competence;
//import Model.Profil;
import Model.Utilisateur;
//import Service.CompetenceService;
import Service.IUtilisateurService;


import Service.UtilisateurService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Connexion DB
        Database db = new MySQL();

        // DAO
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(db);
        //ProfilDAO profilDAO = new ProfilDAO(db);
        //CompetenceDAO competenceDAO = new CompetenceDAO(db);
       // ProfilCompetenceDAO profilCompetenceDAO = new ProfilCompetenceDAO(db);

        // Services
        IUtilisateurService utilisateurService =
                new UtilisateurService(utilisateurDAO);

        /*ICompetenceService competenceService =
                new CompetenceService(competenceDAO);

        IProfilService profilService =
                new ProfilService(profilDAO, profilCompetenceDAO);

        */
        
        Utilisateur utilisateurConnecte = null;

        while (true) {

            // ==========================
            // MENU AUTHENTIFICATION
            // ==========================
            while (utilisateurConnecte == null) {

                System.out.println("\n===== AUTHENTIFICATION =====");
                System.out.println("1. Inscription");
                System.out.println("2. Connexion");
                System.out.println("0. Quitter");

                System.out.print("Votre choix : ");
                int choixAuth = sc.nextInt();
                sc.nextLine();

                switch (choixAuth) {

                    case 1:

                        // Validation nom
                        String nom;
                        do {
                            System.out.print("Nom : ");
                            nom = sc.nextLine().trim();

                            if (nom.isEmpty()) {
                                System.out.println("Nom obligatoire.");
                            }

                        } while (nom.isEmpty());

                        // Validation prénom
                        String prenom;
                        do {
                            System.out.print("Prénom : ");
                            prenom = sc.nextLine().trim();

                            if (prenom.isEmpty()) {
                                System.out.println("Prénom obligatoire.");
                            }

                        } while (prenom.isEmpty());

                        // Validation téléphone
                        String tel;
                        do {
                            System.out.print("Téléphone : ");
                            tel = sc.nextLine().trim();

                            if (tel.isEmpty()) {
                                System.out.println("Téléphone obligatoire.");
                            }

                        } while (tel.isEmpty());

                        // Validation mot de passe
                        String mdp;
                        do {
                            System.out.print("Mot de passe : ");
                            mdp = sc.nextLine().trim();

                            if (mdp.isEmpty()) {
                                System.out.println("Mot de passe obligatoire.");
                            }

                            else if (mdp.length() < 4) {
                                System.out.println(
                                        "Mot de passe trop court (minimum 4 caractères)"
                                );
                                mdp = "";
                            }

                        } while (mdp.isEmpty());

                        Utilisateur nouvelUtilisateur =
                                new Utilisateur(
                                        nom,
                                        prenom,
                                        tel,
                                        mdp
                                );

                        utilisateurService.inscription(
                                nouvelUtilisateur
                        );

                        break;

                    case 2:

                        System.out.print("Téléphone : ");
                        String phone = sc.nextLine();

                        System.out.print("Mot de passe : ");
                        String pass = sc.nextLine();

                        utilisateurConnecte =
                                utilisateurService.connexion(
                                        phone,
                                        pass
                                );

                        break;

                    case 0:
                        System.out.println("Au revoir !");
                        sc.close();
                        return;

                    default:
                        System.out.println("Choix invalide.");
                }
            }

            // ==========================
            // MENU UTILISATEUR CONNECTÉ
            // ==========================
            int choixUser;

            do {
                System.out.println("\n===== MENU UTILISATEUR =====");
                System.out.println("1. Compléter mon profil");
                System.out.println("2. Voir mon profil");
                System.out.println("0. Déconnexion");

                System.out.print("Votre choix : ");
                choixUser = sc.nextInt();
                sc.nextLine();

                /*switch (choixUser) {

                    case 1:

                        Profil profil = new Profil();

                        System.out.print("Disponibilité : ");
                        profil.setDisponibilite(
                                sc.nextDouble()
                        );

                        System.out.print("Capital : ");
                        profil.setCapital(
                                sc.nextDouble()
                        );

                        System.out.print(
                                "Accès internet (true/false): "
                        );
                        profil.setAccessInternet(
                                sc.nextBoolean()
                        );

                        sc.nextLine();

                        System.out.println(
                                "Choisissez votre zone :"
                        );
                        System.out.println("1. Village");
                        System.out.println("2. Ville");

                        int zoneChoix = sc.nextInt();
                        sc.nextLine();

                        if (zoneChoix == 1) {
                            profil.setZone(
                                    TypeZone.VILLAGE
                            );
                        } else {
                            profil.setZone(
                                    TypeZone.VILLE
                            );
                        }

                        // afficher compétences
                        List<Competence> competences =
                                competenceService
                                        .afficherToutesCompetences();

                        if (competences.isEmpty()) {
                            System.out.println(
                                    "Aucune compétence disponible."
                            );
                            break;
                        }

                        System.out.println(
                                "Liste des compétences disponibles :"
                        );

                        for (Competence comp : competences) {
                            System.out.println(
                                    comp.getId()
                                            + " - "
                                            + comp.getNom()
                            );
                        }

                        System.out.println(
                                "Entrez les IDs séparés par virgule (ex: 1,2)"
                        );

                        String ids = sc.nextLine();

                        String[] splitIds =
                                ids.split(",");

                        List<Integer> competenceIds =
                                new ArrayList<>();

                        for (String id : splitIds) {
                            competenceIds.add(
                                    Integer.parseInt(
                                            id.trim()
                                    )
                            );
                        }

                        profilService.completerProfil(
                                utilisateurConnecte.getId(),
                                profil,
                                competenceIds
                        );

                        break;

                    case 2:

                        Profil monProfil =
                                profilService.voirMonProfil(
                                        utilisateurConnecte.getId()
                                );

                        if (monProfil != null) {

                            System.out.println(
                                    "\n===== MON PROFIL ====="
                            );

                            System.out.println(
                                    "Disponibilité : "
                                            + monProfil.getDisponibilite()
                            );

                            System.out.println(
                                    "Capital : "
                                            + monProfil.getCapital()
                            );

                            System.out.println(
                                    "Zone : "
                                            + monProfil.getZone()
                            );

                            System.out.println(
                                    "Compétences : "
                            );

                            if (monProfil.getCompetences() != null
                                    && !monProfil.getCompetences().isEmpty()) {

                                for (Competence comp :
                                        monProfil.getCompetences()) {

                                    System.out.println(
                                            "- " + comp.getNom()
                                    );
                                }

                            } else {
                                System.out.println(
                                        "Aucune compétence associée."
                                );
                            }
                        }

                        break;

                    case 0:
                        utilisateurConnecte = null;
                        System.out.println(
                                "Déconnexion réussie."
                        );
                        break;

                    default:
                        System.out.println(
                                "Choix invalide."
                        );
                }
*/
            } while (choixUser != 0);
        }
    }
}