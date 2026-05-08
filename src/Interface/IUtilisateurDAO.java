package Interface;

import java.util.List;

import Model.Utilisateur;

public interface IUtilisateurDAO {
   void creer(Utilisateur utilisateur) ;
   Utilisateur trouverParId(int id) ;
   void modifier(Utilisateur utilisateur) ;
   void supprimer(int id) ;
   List<Utilisateur> trouveTous();
   
}
