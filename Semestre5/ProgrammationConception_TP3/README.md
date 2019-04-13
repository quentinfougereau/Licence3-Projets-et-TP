# ProgrammationConception_TP3
TP n°3 de Programmation et Conception de la Licence 3 Informatique (2018-2019) - Université Aix-Marseille (AMU).

<h2>Itérateur sur un tableau</h2>

<p>Utilisez la méthodologie du premier TP afin d'implémenter
la classe <code>RangeArray</code> du TD. Vous implémenterez également un itérateur sur <code>RangeArray</code>. Vous devez évidemment écrire des tests pour vérifier
que l'itérateur fonctionne correctement.</p>

<h2>Itérateur sur une grille</h2>

<p>Utilisez la méthodologie du premier TP afin d'implémenter
les classes <code>Grid</code> et <code>GridIterator</code> du TD.</p>

<h2>Vecteur générique</h2>

<p>Modifiez le code du vecteur d'entiers du TP 1 afin d'obtenir un vecteur générique.</p>

<p>Certains tests écrits au TP 1 ne vont plus passer :
<ul>
<li>Vous devez alors modifier les spécifications des méthodes concernées
par ces tests si cela est nécessaire afin d'être certain de savoir ce que vous souhaitez faire;</li>
<li>Modifiez les tests en conséquence.</li>
</ul>

<p>Nous souhaitons faire en sorte que les méthodes <code>set</code> et <code>get</code>
retournent des exceptions <code>IndexOutOfBoundsException</code> lorsque l'utilisateur
de la méthode donne un index à l'extérieur du vecteur :</p>

<ul>
<li>Modifiez la documentation des méthodes <code>get</code> et <code>set</code> afin
de signaler qu'une exception peut être lancée. Vous pouvez vous inspirer de la
<a href="https://docs.oracle.com/javase/7/docs/api/java/util/Vector.html#set(int,%20E)">documentation</a>
de la méthode <code>set</code> de la classe <code>Vector</code> de Java. Le tag <code>@throws</code>
permet d'ajouter la section <code>Throws</code> dans la documentation. Par exemple, vous pouvez
ajouter :

<code>

	/**
 	* ...
 	*
 	* @throws ArrayIndexOutOfBoundsException si l'index est en dehors des bornes, c'est à dire, index < 0 || index >= size<
	.
 	*/
</code>
</li>

<li>Ajoutez des tests pour vérifier que l'exception est lancée si on accède à une case en dehors
du vecteur. Par exemple, vous pouvez ajouter le test suivant pour tester qu'un index négatif
déclanche l'exception. Bien évidemment, d'autres tests sont nécessaires.

<code>

	@Test(expected=IndexOutOfBoundsException.class)
	public void testSet_NegativeIndex() {
		Vector vector = new Vector();
		vector.set(-1, 3);
	}
</code></li>

<li>Modifiez ensuite les méthodes <code>get</code> et <code>set</code> afin de faire passer
les tests</code>. Pour lancer une exception, il suffit de faire :

<code>

	throw new IndexOutOfBoundsException("Index "+index+" is out of range [0, "+(size-1)+"].");
</code>

Le fait de lancer une exception arrête l'exécution de la méthode. Nous verrons en cours
pourquoi il n'est pas nécessaire de modifier la signature des méthodes <code>get</code>
et <code>set</code> pour signaler que ces méthodes peuvent lancer des exceptions
de la classe <code>IndexOutOfBoundsException</code>.
</li>
</ul>

<p>Modifiez également la méthode <code>resize</code> de votre classe <code>Vector</code>
de façon à lancer une exception si l'utilisateur demande une taille strictement négative.
Vous pouvez choisir de lancer une exception <code>ArrayIndexOutOfBoundsException</code>
comme la classe <code>Vector</code> de Java. N'oubliez pas de modifier la documentation
et de créer un test.</p>
