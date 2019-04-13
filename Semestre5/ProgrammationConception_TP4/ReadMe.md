# ProgrammationConception_TP4
TP n°4 de Programmation et Conception de la Licence 3 Informatique (2018-2019) - Université Aix-Marseille (AMU).

<h2> Painter </h2>


<p>Implémentez la classe <code>Truck</code> et <code>FrenchPainter</code> afin de faire
fonctionner l'application (sans définir pour le moment l'interface <code>Painter</code>). Les méthodes pour dessiner des rectangles et des cercles sont respectivement <code>strokeRect</code> et <code>strokeOval</code></p>
<p> Extraire l'interface <code>Painter</code> de <code>FrenchPainter</code> grâce à votre IDE.

<p>Implémentez l'adaptateur de façon à utiliser la classe <code>EnglishPainter</code> du
paquet <code>org.info.painter</code>.

 <h2>Stratégies de filtrage</h2>

<p>En suivant la méthodologie TDD (<code>test-driven development</code>) introduite dans
les premiers TP, développez les versions définitives de la classe <code>Filter</code>,
de l'interface <code>Predicate</code> et des filtres sur les entiers de l'exercice
2 du TD.</p>

<h2>Stratégies d'affichage</h2>
<p> Implémentez les stratégies d'affichage de manière à passer les tests</p>

<p>Vous noterez que les tests sont plus techniques à écrire car la classe <code>ItemList</code>
écrit sur la sortie standard. Pour écrire les tests, il est donc nécessaire de rediriger
la sortie standard vers un objet de la classe <code>ByteArrayOutputStream</code> avant
l'exécution de chaque test : cela est réalisé par la méthode <code>setUpStream</code>
et l'annotation <code>@Before</code>. On supprime ensuite la redirection
à la fin de chaque test dans la méthode <code>cleanUpStream</code> annotée par <code>@After</code>.</p>

<p>Modifiez ensuite le code de la classe <code>ItemList</code> et les tests de façon
  à appliquer le patron <code> strategy </code>. Normalement, les tests vont vous permettre
d'être certain de ne pas changer le comportement de votre programme. N'oubliez pas
d'ajouter de la documentation à votre code.</p>
