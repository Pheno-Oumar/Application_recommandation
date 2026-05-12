package Interface;

import java.util.List;

import Model.Utilisateur;

public interface IUtilisateurDAO {

    // créer un utilisateur
    void creer(Utilisateur utilisateur);

    // trouver un utilisateur par son id
    Utilisateur trouverParId(int id);

    // trouver un utilisateur par téléphone (utile pour connexion)
    Utilisateur trouverParTelephone(String telephone);

    // modifier un utilisateur
    void modifier(Utilisateur utilisateur);

    // supprimer un utilisateur
    void supprimer(int id);

    // récupérer tous les utilisateurs
    List<Utilisateur> trouveTous();
}