package monActivite;

public class activite {
private int id;
private String nom;
private String categorie;

public  activite() {
}

public activite(int id, String nom, String categorie) {
	this.id = id;
	this.nom = nom;
	this.categorie = categorie;
	}

//Getters
public int getId() {
	return id;
}

public String getNom() {
	return nom;
}

public String getCategorie() {
	return categorie;
}
//Setters 
public void setId(int id) {
	this.id = id;
}

public void setNom(String nom) {
	this.nom = nom;
}

public void setCategorie(String categorie) {
	this.categorie = categorie;
}
}


