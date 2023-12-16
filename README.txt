Projet de Bataille Navale réalisé par Clément Bartolone, Mathieu Goudal, Brice Andrieux et Loïc Laloux.

- Jouer :
1) Générer le .jar : Si le fichier "BatailleNavale.jar" n'est pas présent dans le répertoire "dist", vous pouvez depuis un terminal situé dans le répertoire "livraison" lancer la commande "make generate", qui va vous créer le fichier "BatailleNavale.jar".

2) Configuration de base : Avec le fichier "BatailleNavale.jar" généré, vous pouvez lancer une partie depuis le répertoire "dist" avec la commande "java -jar BatailleNavale.jar" . Cette partie se passe sur l'interface, et oppose un Humain (l'utilisateur) contre un Ordinateur. Vous pouvez aussi depuis le répertoire "livraison" faire la commande "make run".

Depuis le répertoire "livraison", la commande "make" génère "BatailleNavale.jar" et le lance.

3) Configuration globale : Avec le fichier "BatailleNavale.jar" généré, vous pouvez lancer une partie depuis le répertoire "dist" avec la commande "java -jar bataillenavale.jar [OPTIONS]". Les options possibles sont :
	-L'affichage : cela peut être 
		"interface", crée la partie dans l'interface ; c'est la valeur par défaut.
		"terminal", crée la partie dans le terminal.
		"double", crée la partie avec une vue interface et terminal en même temps, et un contrôleur interface.
	
	-Les joueurs : cela peut être
		"HvsH" : Permet une partie entre 2 Humains.
		"HvsR" : Permet une partie entre un humain et un Ordinateur; c'est la valeur par défaut.
		"RvsR" : Permet une partie entre 2 Ordinateurs.

Vous pouvez donc, par exemple, lancer une partie entre 2 Ordinateurs sur le terminal avec la commande "java -jar BatailleNavale.jar terminal RvsR".

Il est possible de ne pas mettre d'options (configuration de base), ne mettre que l'affichage, ou l'affichage puis les joueurs.


- Tests : Vous pouvez générer et lancer les tests depuis le répertoire "livraison" avec la commande "make test".

- Javadoc : Si le répertoire "doc" n'est pas présent dans le répertoire "livraison", vous pouvez depuis un terminal situé dans le répertoire "livraison" lancer la commande "make docs", qui va vous créer la documentation. Vous pouvez ensuite l'ouvrir avec "make opendocs".

- Clarification sur les commits:
Des commits ont été effectués avec comme nom d'utilisateur "Prénom Nom" la plupart du temps, mais dû à certaines configurations, certains commit ont été effectués avec d'autres noms : les commits de "Utilisateur" proviennent de Loïc Laloux, et ceux de "clemdemort" proviennent de Clément Bartolone.
