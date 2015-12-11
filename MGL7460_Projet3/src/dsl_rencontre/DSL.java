package dsl_rencontre;

public class DSL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rencontre rencontre =	Rencontre.getInstance();
		rencontre.CreerRencontre("nom de la rencontre")
		.Sport("HOCKEY")								// ou FOOT, TENNIS
		.AvecEquipe("Canadiens de Montr�al")
		.AvecJoueur("Bob Martin")				// Ajoute un joueur � l'�quipe cit�e pr�c�demment
		.AvecJoueur("Jack Bower")
		.AvecEquipe("Karibou de Toronto")
		.AvecJoueur("Franck Newman")
		.AvecJoueur("Cal Lightman")
		.TempsMatch(190)
		.Lieu("Stade Olympique")
		.Date("12-30-2015")
		.ScoreVainqueur(3)
		.ScorePerdant(0)
		.Vainqueur("Canadiens de Montr�al")
		.Fin();
	}

}
