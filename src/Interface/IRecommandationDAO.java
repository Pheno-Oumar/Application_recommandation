package Interface;

import java.util.List;
import Model.Recommandation;

public interface IRecommandationDAO {

    List<Recommandation> afficher();

    void ajouter(Recommandation r);

    void supprimer(int id);

    void modifier(Recommandation r);
}