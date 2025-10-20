# Java Game Project 

## FR

### Présentation

Ce projet a été réalisé avec trois autres étudiants dans le cadre du cours "Projet" de la 2e année de licence Informatique de l'Université de Lille.  
Le sujet du projet peut être consulté dans le fichier [sujet.pdf](sujet.pdf).  
Le fichier [projet.md](projet.md) retrace semaine par semaine l'avancée du projet.  
À l'issue de celui-ci, une soutenance a été tenue durant laquelle nous avons présenté les choix de conception que nous avions effectués et les difficultés que nous avions rencontrez. (les diapositives peuvent être trouvées [ici](diapo_soutenance.pdf).)

### Utilisation

Prérequis :
- Linux (testé sur Ubuntu 24.04.3)
- GNU Make ≥ 4.3
- JDK ≥ 11

Compilation et éxécution:
```shell
git clone https://github.com/remiandrieu/java_project_l2.git
cd java_project_l2
make game.jar
java -jar jar/game.jar      # Sans interface graphique
java -jar jar/game.jar gui  # Avec interface graphique
```

## EN

### Overview

This project was realized with three other students as part of the "Project" course in the second year of the Computer Science bachelor's degree program at the University of Lille.  
The subject of this project can be found in the file [sujet.pdf](sujet.pdf) (in french).  
The [projet.md](projet.md) file traces the progress of the project week by week.  
At the end of the project, a presentation was held where we explained the design choices we had made and the difficulties we had encountered. (the slides can be found [here](diapo_soutenance.pdf).)

### How to use

Requirements :
- Linux (tested on Ubuntu 24.04.3)
- GNU Make ≥ 4.3
- JDK ≥ 11

Compilation and execution:
```shell
git clone https://github.com/remiandrieu/java_project_l2.git
cd java_project_l2  
make game.jar
java -jar jar/game.jar      # Without GUI
java -jar jar/game.jar gui  # With GUI
```