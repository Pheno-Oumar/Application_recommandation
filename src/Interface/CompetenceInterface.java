package Interface;

import java.util.List;

import Model.Competence;

public interface CompetenceInterface {
	
    void creerCompetence(Competence comp);

    void modifierCompetence(Competence comp);

    void supprimerCompetence(int id);

    List<Competence> trouverTousCompetences();

    Competence trouverCompetenceParId(int id);
	

}
