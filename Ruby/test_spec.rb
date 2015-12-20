require_relative 'spec'
gem 'minitest'

require 'minitest/autorun'
require 'minitest/spec'

$LOAD_PATH.unshift('.', 'lib', 'spec')

#must_equal
#must_raise

describe DSL do

    #let(:p2) { OpenRecord.new( nom: "David", prenom: "Anne-Marie" ) }
    #let(:p3) { OpenRecord.new( nom: "Durocher", prenom: "Nellie" ) }
	describe "#test general" do
		
		before do
			@rencontre_normale = DSL.CreerRencontre :RENCONTRE do 
				sport "HOCKEY" # ou FOOT, TENNIS 
				equipeOttawa_Senators "Erik Karlsson", "Craig Anderson" 
				equipeMontreal_Canadiens "Carey Price", "Brendan Gallagher" # ajout des équipes et de leurs joueurs
				match "Stade Olympique", "12-30-2015", "190" # paramètes de la rencontre
				score "0", "3"
				vainqueur "Montreal_Canadiens"
				fin
			end
		end
		
		it "lors du remplissage de la rencontre, doit retourner tous les champs" do
			@rencontre_normale.nomRencontre.must_equal :RENCONTRE
			@rencontre_normale.nomSport.must_equal "HOCKEY"
			#Verification des equipes
			@rencontre_normale.equipe.length.must_equal 2
			list_nom_equipe = @rencontre_normale.equipe.keys
			list_nom_equipe.at(0).must_equal "Ottawa_Senators"
			list_nom_equipe.at(1).must_equal "Montreal_Canadiens"
			#Verification des joueurs
			@rencontre_normale.equipe["Ottawa_Senators"].at(0).must_equal "Erik Karlsson"
			@rencontre_normale.equipe["Ottawa_Senators"].at(1).must_equal "Craig Anderson"
			@rencontre_normale.equipe["Montreal_Canadiens"].at(0).must_equal "Carey Price"
			@rencontre_normale.equipe["Montreal_Canadiens"].at(1).must_equal "Brendan Gallagher"
			#Autre verifications
			@rencontre_normale.score1.must_equal "0"
			@rencontre_normale.score2.must_equal "3"
			@rencontre_normale.lieu.must_equal "Stade Olympique"
			@rencontre_normale.date.must_equal "12-30-2015"
			@rencontre_normale.temps.must_equal "190"	
		end
	end
	
	describe "#test vainqueur" do
		it "lors du remplissage du vainqueur, doit etre deja connu" do
			assert_raises RuntimeError do 
				@rencontre_normale = DSL.CreerRencontre :RENCONTRE do 
					sport "HOCKEY" # ou FOOT, TENNIS 
					equipeOttawa_Senators "Erik Karlsson", "Craig Anderson" 
					equipeMontreal_Canadiens "Carey Price", "Brendan Gallagher" # ajout des équipes et de leurs joueurs
					match "Stade Olymphique", "12-30-2015", "190" # paramètes de la rencontre
					score "0", "3"
					vainqueur "Une autre equipe"
					fin
				end	
			 end
		end
	end
	
end
