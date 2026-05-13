import ConnectionDB.MySQL;
import DAO.CompetenceDAOImpl;
import Database.MySQLDatabase;
import Interface.CompetenceInterface;
import Interface.Database;
import Model.Competence;
import Service.CompetenceService;
import Service.CompetenceServiceImpl;

public class Main {

    public static void main(String[] args) {

        // Création de la connexion BD
        Database db = new MySQL();

        // Création du DAO
        CompetenceInterface dao =
                new CompetenceDAOImpl(db);

        // Création du Service
        CompetenceService service =
                new CompetenceService(dao);

         

        Competence comp = new Competence();

        comp.setNom("Java");
        comp.setDescription("Programmation orientée objet");

        service.creerCompetence(comp);

         
        // TEST AFFICHAGE
        
        System.out.println("\nListe des compétences :");

        service.obtenirToutesCompetences()
                .forEach(System.out::println);

         
        // TEST RECHERCHE PAR ID
        

        System.out.println("\nRecherche compétence ID 1 :");

        service.obtenirCompetenceParId(1)
                .ifPresent(System.out::println);

        
        // TEST MODIFICATION
        

        Competence compUpdate = new Competence();

        compUpdate.setId(1);
        compUpdate.setNom("Java Avancé");
        compUpdate.setDescription("Spring Boot et JDBC");

        service.modifierCompetence(compUpdate);

        
        // TEST SUPPRESSION
       

        service.supprimerCompetence(1);

    }
}