package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Interface.Database;
import Interface.IUtilisateurDAO;
import Model.Utilisateur;

public class UtilisateurDAO implements IUtilisateurDAO {
	
	//private final Database db;
	

	
	
	

	//public UtilisateurDAO(Database db) {
		//this.db = db;
	//}
	
	private final Database db;
	
	public UtilisateurDAO(Database db) {
		this.db= db;
	}

	@Override
	public void creer(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO utilisateur(nom, prenom, telephone, mdp, roleId) values(?, ?, ?, ?, ?)";
		try (Connection conn = db.connexion();
				PreparedStatement pr =conn.prepareStatement(sql) ){
			pr.setString(1, utilisateur.getNom());
			pr.setString(2, utilisateur.getPrenom());
			pr.setString(3, utilisateur.getTelephone());
			pr.setString(4, utilisateur.getMdp());
			pr.setInt(5, utilisateur.getRole().getId());
			
		  int rows =pr.executeUpdate();
		if(rows == 0) {
			System.out.println("Aucun utilisateur n'a été créé.");
		}
		else {
			System.out.println("Utilisateur créé avec succès !");
		}
			
		} catch (SQLException e) {
			System.err.println("Erreur lors de la création de l'utilisateur : " + e.getMessage());
		}
	}
	
	

	@Override
	public Utilisateur trouverParId(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM utilisateur WHERE id = ?";

        try (Connection conn = this.db.connexion();
             PreparedStatement pr = conn.prepareStatement(sql)) {
        	pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                return mapResultSetToUtilisateur(rs);
            } else {
                System.out.println("Utilisateur avec l'ID " + id + " non trouvé.");
                
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche par ID : " + e.getMessage());
       
        }
        return null;
            	
             }
	
	private Utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
	    Utilisateur utilisateur = new Utilisateur();

	    utilisateur.setId(rs.getInt("id"));
	    utilisateur.setNom(rs.getString("nom"));
	    utilisateur.setPrenom(rs.getString("prenom"));
	    utilisateur.setTelephone(rs.getString("telephone"));
	    utilisateur.setMdp(rs.getString("mdp"));

	    return utilisateur;
	}
	

           
	@Override
	public void modifier(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		String sql = "UPDATE utilisateur SET nom = ?, prenom= ?, telephone= ?, mdp = ?, roleId = ? WHERE id = ?";
		try(Connection conn = this.db.connexion();
			PreparedStatement pr = conn.prepareStatement(sql);){
			
			pr.setString(1, utilisateur.getNom());
			pr.setString(2, utilisateur.getPrenom());
			pr.setString(3, utilisateur.getTelephone());
			pr.setString(4, utilisateur.getMdp());
			pr.setInt(5, utilisateur.getRole().getId());
			pr.setInt(6, utilisateur.getId());
			
		int rows = pr.executeUpdate();
		if(rows > 0) {
			System.out.println("Utilisateur mis à jour avec succès.");
		}else {
			System.out.println("Aucun utilisateur mis à jour.");
		}
			
		} catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour : " + e.getMessage());
       
        }
		
	}

	@Override
	public void supprimer(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE utilisateur WHERE id = ?";
		try (Connection conn = this.db.connexion();
			PreparedStatement pr = conn.prepareStatement(sql);){
			
			pr.setInt(1, id);
			int rows = pr.executeUpdate();
			if(rows > 0) {
				System.out.println("Utilsateur supprime avec succès");
			}else {
				System.out.println("Aucun utilisateur supprimé.");
			}
			
		} catch (SQLException e) {
			 System.err.println("Erreur lors de la suppression : " + e.getMessage());
			
		}
		
	}

	@Override
	public List<Utilisateur> trouveTous() {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		
		String sql = "SELECT * FROM utilsateur ORDER BY nom";
		try(Connection conn = this.db.connexion();
				PreparedStatement pr = conn.prepareStatement(sql);) {
			ResultSet rs = pr.executeQuery();
			
		while(rs.next()) {
			list.add(mapResultSetToUtilisateur(rs));
		}
			
		} catch (SQLException e) {

			System.out.println("erreur"+e.getMessage());
		}
		
		return list;
	}
	
	

	        
	    }
	
	





