# l2s4-projet-2025

Vous devez *forker* ce projet dans votre espace de travail Gitlab (bouton `Fork`) et vidéo sur le [portail](https://www.fil.univ-lille.fr/portail/index.php?dipl=L&sem=S4&ue=Projet&label=Documents)
Un unique fork doit être réalisé par équipe.

Une fois cela réalisé, supprimer ces premières lignes et remplissez les noms des membres de votre équipe.
N'oubliez pas d'ajouter les autres membres de votre équipe aux membres du projet, ainsi que votre enseignant·e (statut Maintainer).

# Equipe

- Rémi ANDRIEU
- Samuel DEMON
- Tom TAFFIN
- Andreï CHARONOV

# Sujet

[Le sujet 2025](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2025.pdf)

# Livrables

Les paragraphes concernant les livrables doivent être rempli avant la date de rendu du livrable. A chaque fois on décrira l'état du projet par rapport aux objectifs du livrable. Il est attendu un texte de plusieurs lignes qui explique la modélisation choisie, et/ou les algorithmes choisis et/ou les modifications apportées à la modélisation du livrable précédent.

Un lien vers une image de l'UML doit être fourni (une photo d'un diagramme UML fait à la main est suffisant).

## Livrable 1

### Atteinte des objectifs

La génération du plateau se fait selon la methode suivante. Tout d'abord les dimensions du plateau sont choisies à sa construction. Puis une grille remplie de tuile mer est généré. Ensuite on détermine aléatoirement le nombre de tuile terrain à génèrer entre 1/4 et 1/3 pour respecter la condition d'au moins 2/3 de mer. Ensuite tant qu'il reste des tuiles terrain à poser, on se positionne sur une tuile aléatoirement et si c'est une tuile mer, on la change en terrain aléatoirement. Puis si elle est entouré que de mer, on génère un autre terrain à coté aléatoirement. Puis si il reste des tuiles à placer, on a une probabilité p de poursuivre la génération à partir de l'ancienne tuile génèré. Sinon recommence le processus. Cette probabilité p peut être mofifié afin de générer plus ou moins d'îles de taille plus ou moins grande. La génération du plateau est instantané pour un plateau de 1000 par 1000.

____

#### UML:

![Board](images/livrable1_uml_board.png)

![Building](images/livrable1_uml_building.png)

![Player](images/livrable1_uml_player.png)

![Action](images/livrable1_uml_action.png)

![Objective](images/livrable1_uml_objective.png)

____

#### commandes

Pour générer la documentation :  
```javadoc -sourcepath ./src -d ./docs -subpackages game game.board game.board.util```

Pour consulter la documentation, ouvrez `index.html` situé dans le dossier `docs`

Pour compiler :
```javac -sourcepath src src/game/board/*.java -d classes```
```javac -sourcepath src src/game/board/util/*.java -d classes```


Pour créer un jar exécutable `livrable1.jar` :
```jar cvfe livrable1.jar game.board.Livrable1 -C classes game```

Pour exécuter `livrable1.jar` :
```java -jar livrable1.jar```


Pour compiler et exécuter les tests :  
```javac -classpath junit-console.jar:classes -sourcepath test test/game/board/*.java && java -jar junit-console.jar -classpath test:classes -scan-classpath```




### Difficultés restant à résoudre

Nous n'avons pas trouvé de moyen pour que si un nouveau type de terrain est ajouté, le choix du terrain inclu ce nouveau terrain. La methode getRandomTypeOfLand devra donc être modifié. Le choix d'un voisin mer aléatoirement n'est pas trés efficace, nous reviendrons peut être sur son implémentation.

## Livrable 2

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 3

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 4

### Atteinte des objectifs

### Difficultés restant à résoudre

# Journal de bord

Le journal de bord doit être rempli à la fin de chaque séance encadrée, et avant de quitter la salle. 

Pour chaque semaine on y trouvera :
- ce qui a été réalisé, les difficultés rencontrées et comment elles ont été surmontées (on attend du contenu, pas uniquement une phrase du type "tous les objectifs ont été atteints")
- la liste des objectifs à réaliser d'ici à la prochaine séance encadrée

## Semaine 1

### Ce qui a été réalisé

Réflexion à propos de la conception des premières classes concernant le plateau. Nous avons schématisé cela sous forme de diagramme UML.  
Nous avons réfléchi à la génération aléatoire du tableau avec différentes approches.

![image](images/plateauUML.png)

### Difficultés rencontrées

Nous voulions créer une classe Mer mais ne savions pas quelles méthodes y intégrer du fait de sa nature.  
Nous avions également rencontré des difficultés algorithmiques concernant la gestion de la génération d'îles dans le plateau.  
Nous ne savons pas encore quelle approche choisir pour la génération du plateau.  
Enfin, nous nous posions des questions sur la collaboration pour le projet avec Git.

### Objectifs pour la semaine

Se rassembler pour mieux prendre en main les outils Git (branch, merge, etc...).
Décider de l'approche que nous allons suivre pour la conception du plateau. 

## Semaine 2

### Ce qui a été réalisé

Modification de l'uml du plateau.

![image](images/BoardUML2.png)

Reflexion sur les classes *Building*, *Player*, *Action* et *Game*. Les classes *Building* et *Action* sont abstraites pour qu'une implémentation de nouvelles actions ou batîments soit plus simple.

On s'est finalement mis d'accord sur la génération aléatoire du plateau. Tout d'abord les dimensions du plateau sont choisies à sa construction. Puis une grille remplie de tuile mer est généré. Ensuite on détermine aléatoirement le nombre de tuile terrain à génèrer entre 1/4 et 1/3 pour respecté la condition d'au moins 2/3 de mer. Ensuite tant qu'il reste plus d'une tuile à poser, on se positionne sur une tuile au hasard et si c'est une tuile mer, on la change en terrain au hazard. Puis si elle est entouré de mer, on génère un autre terrain à coté. Puis si il reste des tuiles à placer, on a une probabilité de 0.5 de poursuivre la génération à partir de l'ancienne tuile génèré. Sinon recommence le processus.

Création de l'ensemble des classes pour les tuiles ,de l'enum ressource ainsi que la classe plateau qui n'est pas terminée.

![image](images/buildingUML.png)


![image](images/PlayerGameActionUML.png)

### Difficultés rencontrées

Quelques désagrèments à propos du *Building* et du fonctionnement des ressources, notamment avec la méthode **produce()**.
Doutes concernant la création des constructeurs dans les classes abstraites.

Pour la génération aléatoire du plateau, nous avons rencontré des difficultés sur le choix aléatoire d'un terrain qui puissent comporter les terrains créé postérieurement. Nous avons eu aussi des doutes sur la meilleur façon de vérifier ou de choisir une tuile voisine qui est une mer. Ces deux dernières difficultés n'ont pas encore était résolue.

### Objectifs pour la semaine

Rémi, Tom : finir la creation du plateau avec les tests

Andreï, Samuel : Poursuite de la reflexion sur les classes à venir et les umls, ainsi que la création de premières classes dont l'uml à déja été fait.

## Semaine 3

### Ce qui a été réalisé

#### Building:

![image](images/week3_uml_building.png)

Changement au niveau de la classe **building**, création d'une classe abstraite **LandBuilding**, représantant les bâtiments sur terre, qui ne sont donc pas des ports et peuvent collecter des ressources.

Cette classe est divisée en plusieurs classes représentant les bâtiments des différents jeux.

__________________

#### Player and Game:

![image](images/week3_uml_player_and_game.png)

Pour les classes **Player** et **Game**, on a également crée des classes filles pour chaque jeu différent, permettant ainsi de représenter les attributs uniques de chaque jeu.

_______

#### Action:

![image](images/week3_uml_actions.png)

On ajoute un attribut *label* à la classe **action**. Nous permettant plus tard de pouvoir représenter cette action plus facilement.
On ajoute également des classes abstraite pour chaque jeu, mais on a des doutes concernant l'implémentation des actions communes aux 2 jeux. Peut-être avec une interface? Pour l'instant on laisse ça en suspens...

_____

#### Objective:

![image](images/week3_uml_Objective.png)

On crée une interface **Objective** pour représenter les différents objectifs, comme le jeu *Démeter* n'a qu'un seul objectif, on ne crée pas de classes intermédiaires pour les différents jeux. Cependant, cette implémentation n'est peut-être pas la meilleure et va probablement changer.

______

On rajoute également des méthodes `toString()` aux classes **Building** et **Tile** pour permettre une représentation visuelle plus simple.

_____

Continuation du codage de la classe **Board**, dernière ligne droite.

### Difficultés rencontrées

La classe **Action** qui souffre encore de quelques problèmes, comme explicité ci-dessus. Et l'interface **Objective** n'est pas non plus très convaincante.

Boucle infinie dans la classe **Board**, méthode `createGrid()`

### Objectifs pour la semaine

Andreï, Samuel: Trouver une meilleure implémentation des classes **Action** et **Objective**.

Rémi, Tom: Régler les derniers problèmes du **Board**, faire les derniers tests.

## Semaine 4

### Ce qui a été réalisé

Trace de la génération du plateau 

![image](images/traceGenerateboard.png)
![image](images/traceGenerateboard1.png)
![image](images/traceGenerateboard2.png)
![image](images/traceGenerateboard3.png)

____

#### UML:

On remplace les attributs *private* par des *protected* dans les classes qu'il faut.

#### Actions:

On a trouvé une façon efficace d'implémenter les **Actions**. On a une classe **Action** abstraite qui *extend* vers des classes abstraites **DemeterActions**, **AresActions** et **CommonActions**, qui représentent, respectivement, les actions possibles des deux jeux, et les actions communes aux 2 jeux.

![image](images/week4_uml_actions.png)

#### Objectifs:

On a finalement décidé de faire une implémentation similaire aux actions. La classe **joueur** aura un attribut objectif pour représenter les objectifs que chaque joueur doit atteindre.

![image](images/week4_uml_objective.png)


### Difficultés rencontrées
Doute sur les parties du code de createGrid à encapsuler dans des methodes. Test des methodes avec du random. Nous n'avons pas trouvé de moyen pour que si un nouveau type de terrain est ajouté, le choix du terrain inclu ce nouveau terrain. La methode getRandomTypeOfLand devra donc être modifié. Enfin le choix d'un voisin mer aléatoirement n'est pas trés efficace, nous reviendrons peut être sur son implémentation.

### Objectifs pour la semaine

Andreï, Samuel: Continuer l'implémentation des batiments.

Rémi, Tom: Rendre le livrable 1. Réflexion sur l'uml de objectif et implémentation des actions.

## Semaine 5

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 6

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 7

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 8

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 9

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 10

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 11

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine

## Semaine 12

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour finaliser le projet