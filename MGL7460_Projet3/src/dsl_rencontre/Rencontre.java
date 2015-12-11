package dsl_rencontre;

import java.awt.List;
import java.util.ArrayList;

public class Rencontre {
	private String nom, sport, lieu, date, vainqueur;
	private ArrayList<String> equipes, joueurs;
	private int temps, scoreV, scoreP;
	private Rencontre() {
		nom = "";
		sport = "";
		lieu = "";
		date = "";
		vainqueur = "";
		equipes = new ArrayList<String>();
		joueurs = new ArrayList<String>();
		temps = 0;
		scoreV = 0;
		scoreP = 0;
	}

	private static Rencontre INSTANCE = null;

	public static Rencontre getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Rencontre();
		}
		return INSTANCE;
	}

	public Rencontre CreerRencontre(String nom) {
		this.nom = nom;
		return this;
	}

	public Rencontre Sport(String sport) {
		this.sport = sport;
		return this;
	}

	public Rencontre AvecEquipe(String nomE) {
		this.equipes.add(nomE);
		return this;
	}

	public Rencontre AvecJoueur(String nomJ) {
		this.joueurs.add(this.equipes.get(this.equipes.size()-1) + " - " + nomJ);
		return this;
	}

	public Rencontre TempsMatch(int temps) {
		this.temps = temps;
		return this;
	}

	public Rencontre Lieu(String lieu) {
		this.lieu = lieu;
		return this;
	}

	public Rencontre Date(String date) {
		this.date = date;
		return this;
	}

	public Rencontre ScoreVainqueur(int scoreV) {
		this.scoreV = scoreV;
		return this;
	}

	public Rencontre ScorePerdant(int scoreP) {
		this.scoreP = scoreP;
		return this;
	}

	public Rencontre Vainqueur(String vainqueur) {
		this.vainqueur = vainqueur;
		return this;
	}

	public void Fin() {
		System.out.println("Nom de la renconre : " + this.nom);
		System.out.println("Sport : " + this.sport);
		System.out.println("Equipes : " + this.equipes.get(0) + " VS " + this.equipes.get(1));
		System.out.println("Joueurs : ");
		for (String joueur : this.joueurs) {
		    System.out.print(joueur + ", " );
		}
		System.out.println("\nTemps de jeu : " + this.temps);
		System.out.println("Lieu : " + this.lieu);
		System.out.println("Date : " + this.date);
		System.out.println("Vainqueur : " + this.vainqueur);
		System.out.println("Score du vainqueur : " + this.scoreV);
		System.out.println("Score du perdant : " + this.scoreP);
	}
}
