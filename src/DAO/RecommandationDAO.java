package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Model.Recommandation;

public class RecommandationDAO {

    private Connection connection;

    // CONSTRUCTEUR
    public RecommandationDAO(Connection connection) {
        this.connection = connection;
    }

    // METHODE AJOUT
    public void ajouter(Recommandation r) {

        try {

            String sql = "INSERT INTO recommandation(activiteId, profilId, dateAjout) VALUES (?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, r.getActivite().getId());
            ps.setInt(2, r.getProfil().getId());

            ps.setDate(
                3,
                new java.sql.Date(r.getDateAjout().getTime())
            );

            ps.executeUpdate();

            System.out.println("Ajout réussi ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}