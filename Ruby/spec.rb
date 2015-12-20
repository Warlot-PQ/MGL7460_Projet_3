class DSL
	
	attr_reader :nomSport, :equipe, :score1, :score2, :lieu, :date, :temps, :nomVainquer, :nomRencontre
	
	def initialize(nomRencontre)
		@nomRencontre = nomRencontre
		@sport
		@equipe = Hash.new				# { nomEquipe => [Joueur1, Joueur2]}
		@lieu
		@date
		@temps
		@score1 = "nil"
		@score2 = "nil"
		@vainqueur = "nil"
		@fini = false					# au moins un nom de rencontre, un sport, 2 equipe, un lieu, une date, une durée de match
	end

	def self.CreerRencontre(nomRencontre, &block)
		dsl = DSL.new nomRencontre
		dsl.instance_eval &block
		dsl
	end

	def sport(nomSport)
		@nomSport = nomSport
		self
	end

	def method_missing(m, *args, &block)					# ajoute une equipe et des joueurs si commence par "equipe" 
		if m[0, 6] == "equipe"
			nomEquipe = m[6..-1]
			@equipe[nomEquipe] = []
			args.each do |nomJoueur|
				@equipe[nomEquipe].push nomJoueur
			end
		else
			puts "Aucune methode nommee #{m} ici."
		end
		self
	end

	def match(lieu, date, temps)
		@lieu = lieu
		@date = date
		@temps = temps
		self
	end

	def score(score1, score2)
		@score1 = score1
		@score2 = score2
		self
	end

	def vainqueur(nomVainquer)
		if !@equipe.has_key?(nomVainquer)
			raise "vainqueur : cette équipe n'exsite pas"
		end
		@nomVainquer = nomVainquer
		self
	end

	def fin()
		@fini = true
	end

	def afficher()
		puts "Nom de la rencontre : " << @nomRencontre.to_s
		puts "Sport : " << @nomSport.to_s
		@equipe.each do |nomEquipe, tableauJoueur|
			tableauJoueur.each do |nomJoueur|
				puts "nom du joueur de l'équipe " << nomEquipe << " : " << nomJoueur
			end
		end
		puts "Lieu : " << @lieu
		puts "Date : " << @date
		puts "Temps de match : " << @temps

		if @score1 != "nil" and @score2 != "nil" and @nomVainquer != "nil" 
			maximumScore = @score1 > @score2 ? @score1 : @score2
			minimumScore = @score1 < @score2 ? @score1 : @score2
			puts @nomVainquer << " a gagne le match " << maximumScore << " a " << minimumScore
		end
		self
	end
end

dsl = DSL.CreerRencontre :RENCONTRE do 
	sport "HOCKEY" # ou FOOT, TENNIS 
	equipeOttawa_Senators "Erik Karlsson", "Craig Anderson" 
	equipeMontreal_Canadiens "Carey Price", "Brendan Gallagher" # ajout des équipes et de leurs joueurs
	match "Stade Olymphique", "12-30-2015", "190" # paramètes de la rencontre
	score "0", "3"
	vainqueur "Montreal_Canadiens"
	fin
end

dsl.afficher()