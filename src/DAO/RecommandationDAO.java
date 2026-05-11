package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Interface.Database;
import Model.Recommandation;

public class RecommandationDAO {

    private Database db;

    // Constructeur conseillé
    public RecommandationDAO(Database db) {
        this.db = db;
    }

    public List<Recommandation> afficher() {

        List<Recommandation> liste = new ArrayList<>();
        String sql = "SELECT * FROM recommandation";

        try (Connection conn = this.db.connexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Recommandation r = new Recommandation();

                r.setId(rs.getInt("id"));
                r.setDateAjout(rs.getDate("dateAjout"));

                liste.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return liste;
    }

    public void ajouter(Recommandation r) {

        String sql = "INSERT INTO recommandation(activiteId, profilId, dateAjout) VALUES (?, ?, ?)";

        try (Connection conn = this.db.connexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getActivite().getId());
            ps.setInt(2, r.getProfil().getId());
            ps.setDate(3, new java.sql.Date(r.getDateAjout().getTime()));

            ps.executeUpdate();

            System.out.println("Ajout réussi");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void supprimer(int id) {

        String sql = "DELETE FROM recommandation WHERE id = ?";

        try (Connection conn = this.db.connexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Suppression réussie");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifier(Recommandation r) {

        String sql =
            "UPDATE recommandation SET activiteId=?, profilId=?, dateAjout=? WHERE id=?";

        try (Connection conn = this.db.connexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getActivite().getId());
            ps.setInt(2, r.getProfil().getId());
            ps.setDate(3, new java.sql.Date(r.getDateAjout().getTime()));
            ps.setInt(4, r.getId());

            ps.executeUpdate();

            System.out.println("Modification réussie");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}