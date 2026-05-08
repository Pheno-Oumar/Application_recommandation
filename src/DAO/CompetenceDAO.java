import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetenceDAO implements CompetenceDAO {

    @Override
    public void ajouterCompetence(Competence competence) {
        String sql = "INSERT INTO competence(nom, description) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, competence.getNom());
            ps.setString(2, competence.getDescription());

            ps.executeUpdate();
            System.out.println("Compétence ajoutée !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Competence getCompetenceById(int id) {
        String sql = "SELECT * FROM competence WHERE id = ?";
        Competence competence = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                competence = new Competence(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return competence;
    }

    @Override
    public List<Competence> getAllCompetences() {
        List<Competence> liste = new ArrayList<>();

        String sql = "SELECT * FROM competence";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                liste.add(new Competence(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    @Override
    public void updateCompetence(Competence competence) {
        String sql = "UPDATE competence SET nom=?, description=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, competence.getNom());
            ps.setString(2, competence.getDescription());
            ps.setInt(3, competence.getId());

            ps.executeUpdate();

            System.out.println("Compétence mise à jour !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCompetence(int id) {
        String sql = "DELETE FROM competence WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Compétence supprimée !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}