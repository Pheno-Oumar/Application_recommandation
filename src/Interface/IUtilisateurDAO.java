package Interface;

import java.util.List;

import Model.Utilisateur;

public interface IUtilisateurDAO {
   void creer(Utilisateur utilisateur) throws Exception;
   Utilisateur trouverParId(int id) throws Exception;
   Utilisateur trouverParTelephone(String telephone) throws Exception;
   void miseAjour(Utilisateur utilisateur) throws Exception;
   void supprimer(int id) throws Exception;
   List<Utilisateur> trouveTous() throws Exception;
   
}
