
public class Tache {
	private int identifiant;
	private int dernierId;
	private String titre;
	private String etat;
	 private int date;
	 
	public Tache(int identifiant, int dernierId, String titre, String etat, int date) {
		super();
		this.identifiant = identifiant;
		this.dernierId = dernierId;
		this.titre = titre;
		this.etat = etat;
		this.date = date;
	}
	public Tache( int dernierId, String titre, String etat, int date) {
		super();
		this.dernierId = dernierId;
		this.titre = titre;
		this.etat = etat;
		this.date = date;
	}
	
	public Tache(  String titre, String etat, int date) {
		super();
		this.titre = titre;
		this.etat = etat;
		this.date = date;
	}
	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	public int getDernierId() {
		return dernierId;
	}
	public void setDernierId(int dernierId) {
		this.dernierId = dernierId;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Tache [identifiant=" + identifiant + ", dernierId=" + dernierId + ", titre=" + titre + ", etat=" + etat
				+ ", date=" + date + "]";
	}
	
	 

}
