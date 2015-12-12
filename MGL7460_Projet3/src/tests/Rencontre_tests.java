package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dsl_rencontre.Rencontre;


public class Rencontre_tests {
	@Test
	public void test_rencontre_standards() {
		Rencontre rencontre =	Rencontre.getInstance();
		try {
			rencontre.CreerRencontre("nom de la rencontre")
			.Sport("HOCKEY")								// ou FOOT, TENNIS
			.AvecEquipe("Canadiens de Montréal")
			.AvecJoueur("Bob Martin")				// Ajoute un joueur à l'équipe citée précédemment
			.AvecJoueur("Jack Bower")
			.AvecEquipe("Karibou de Toronto")
			.AvecJoueur("Franck Newman")
			.AvecJoueur("Cal Lightman")
			.TempsMatch(190)
			.Lieu("Stade Olympique")
			.Date("12-30-2015")
			.ScoreVainqueur(3)
			.ScorePerdant(0)
			.Vainqueur("Canadiens de Montréal")
			.Fin();
		} catch (Exception e) {
			System.out.println("Erreur test_rencontre_standards");
			e.printStackTrace();
		}
		
		assertEquals(rencontre.getNom(),"nom de la rencontre");
		assertEquals(rencontre.getSport(),"HOCKEY");
		assertEquals(rencontre.getEquipes().size(),2);
		assertEquals(rencontre.getJoueurs().get(0),rencontre.getEquipes().get(0)+ " - Bob Martin");
		assertEquals(rencontre.getJoueurs().get(1),rencontre.getEquipes().get(0)+ " - Jack Bower");
		assertEquals(rencontre.getJoueurs().get(2),rencontre.getEquipes().get(1)+ " - Franck Newman");
		assertEquals(rencontre.getJoueurs().get(3),rencontre.getEquipes().get(1)+ " - Cal Lightman");
		assertEquals(rencontre.getTemps(),190);
		assertEquals(rencontre.getLieu(),"Stade Olympique");
		assertEquals(rencontre.getDate(),"12-30-2015");
		assertEquals(rencontre.getScoreP(),0);
		assertEquals(rencontre.getScoreV(),3);
		assertEquals(rencontre.getVainqueur(),rencontre.getEquipes().get(0));
	}
	
	@Test()
	public void test_joueurs_avant_equipe() {
		
		Rencontre rencontre =	Rencontre.getInstance();
		try {
			rencontre.CreerRencontre("nom de la rencontre")
			.AvecJoueur("Bob Martin")
			.AvecJoueur("Cal Lightman")
			.Fin();
		} catch (Exception e) {
		    assert(e.getMessage().contains("equipe"));
		}
	}
	
	@Test()
	public void test_pas_de_nom_de_rencontre() {
		
		Rencontre rencontre =	Rencontre.getInstance();
		try {
			rencontre
			.Sport("HOCKEY")								// ou FOOT, TENNIS
			.AvecEquipe("Canadiens de Montréal")
			.AvecJoueur("Bob Martin")				// Ajoute un joueur à l'équipe citée précédemment
			.AvecJoueur("Jack Bower")
			.AvecEquipe("Karibou de Toronto")
			.AvecJoueur("Franck Newman")
			.AvecJoueur("Cal Lightman")
			.TempsMatch(190)
			.Lieu("Stade Olympique")
			.Date("12-30-2015")
			.ScoreVainqueur(3)
			.Fin();
		} catch (Exception e) {
		    assert(e.getMessage().contains("nom de la rencontre"));
		}
	}
}