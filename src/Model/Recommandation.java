package Model;

import java.util.Date;

public class Recommandation {
	
	private Activite activite;
	private Profil profil;
	private Date dateAjout;
	
	public Recommandation() {
		// TODO Auto-generated constructor stub
	}

	public Activite getActivite() {
		return activite;
	}

	public void setActivite(Activite activite) {
		this.activite = activite;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public Date getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	

}
