package application;


public class NoteStudent {
  private int ID;
  private String Prenom,Nom,Matiere;
  private int TP,Examen,Generale;
  
public NoteStudent(int id,String nom ,String prenom, Integer tp, Integer examen , Integer generale,String matiere ) {
	
	ID=id;
	Nom = nom;
	Prenom=prenom;
	TP=tp;
	Examen=examen;
	Generale=generale;
	Matiere=matiere;
	}
public final int getID() {
	return this.ID;
}
public final void setID(int id) {
	this.ID=id;
}
public final String getNom() {
	return Nom;
}
public final void setNom(String nom) {
	this.Nom=nom;
}
public final String getPrenom() {
	return Prenom;	
}

public final void setPrenom(String prenom) {
	this.Prenom=prenom;	
}

public final int getTP() {
	return TP;
}
public final void setTP(int tp) {
	this.TP=tp;
}
public final int getExamen() {
	return Examen;
}
public final void setExam(int examen) {
	this.Examen=examen;
}
public final int getGenerale() {
	return Generale;
}
public final void setGlobale(int generale) {
	this.Generale=generale;
}
public final String getMatiere() {
	return Matiere;
}
public final void setMatiere(String matiere) {
	this.Matiere=matiere;
}
}