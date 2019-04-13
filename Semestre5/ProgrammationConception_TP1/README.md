# ProgrammationConception_TP1
TP n°1 de Programmation et Conception de la Licence 3 Informatique (2018-2019) - Université Aix-Marseille (AMU).

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">

<h2>Mise en place </h2>

<h3> En utilisant IntelliJ IDEA </h3>
<p> Lancez IntelliJ IDEA. Cliquer sur <code> Check out from version control </code> puis selectionner <code>GitHub</code> ou <code>Git</code> sinon. Cliquer sur <code>login GitHub</code> et rentrer vos identifiants. On vous propose maintenant de cloner un dépot, choisissez dans la première barre qui est vide le dépot Git que vous avez créé pour le TP et clonez ce dépot. Accepter d'ourvir le <code> pom.xml </code>. Attendre qu'il charge et vous pouvez commencer. Il est possible qu'il vous demande de passer en projet maven via une petite notification verte il faut accepter. IL est aussi possible que la SDK ne soit pas spécifiée, il faut mettre la version 10.</p>


<h3> En utilisant Eclipse</h3>
<p> Lancer Eclipse. Ajouter une fenêtre Git en passant par <code>Window->Show view->other->git->Git repository</code>. Cela va ajouter une fenêtre pour gérer les dépôts Git en bas à droite. Cliquer sur la petite icone dans cette fenêtre avec une flèche verte <code> Clone a git repository ...</code>. Cela va vous demander une URL qui est celle de votre dépôt que vous (cliquer sur le bouton vert clone pour avoir cette URL). Vous rentrez également vos identifiants gitHub et vous choisissez l'endroit où va être cloné le dépôt. Une fois le dépot cloné on peut créer un projet à partir de son dossier <code>File->Open project from file system ...</code> en choisissant le répertoire dans lequel vous avez cloné votre dépot. Votre projet est créé avec la bonne configuration.</p>

<h2>Version courte </h2>

<p> Pour chacune des classes vue en TD vous devez écrire des tests pour chacune des méthodes et ensuite vous implémenterez ces méthodes. Vous avez un exemple de squelette pour la classe <code>Vector</code>.</p>

<h2>Objectif</h2>

<p>L'objectif de ce TP est de mettre en place des pratiques de programmation permettant
    de produire un code correct et lisible. Ce premier TP est assez long à réaliser. Il est impératif de vous concentrer
    sur votre TP et de travailler entre les TP pour le terminer.</p>

<p>La démarche que vous allez suivre pour implémenter les exercices du TD est la suivante. Vous allez faire dans l'ordre
    les trois points suivants :
<ul>
    <li>Écriture de commentaires pour spécifier <b>précisément</b> le comportement des classes et méthodes.</li>
    <li>Écriture de tests permettant de vérifier la <b>totalité</b> de la spécification.</li>
    <li>Écriture du code des méthodes de façon <b>la plus lisible possible</b>.</li>
</ul>
</p>

<h2>Vecteur d'entiers</h2>

<h3>Écriture des spécifications</h3>

Complétez les commentaires en vous inspirant des commentaires déjà fournis dans la classe <code>Vector</code>.
Le but de ces commentaires est de spécifier le comportement de chacune des méthodes de la classe.
Il est donc nécessaire de faire en sorte qu'il n'y ait <b>aucune ambiguïté</b>. Les commentaires
respectent le format de la <code>Javadoc</code> : ils commencent par <code>/**</code> et utilisent des tags comme
<code>@return</code> ou <code>@param</code>. <b>Vous n'avez pas à implémenter les méthodes pour le moment.</b>

<code>

    /**
    * La classe Vector implémente un tableau d'entiers
    * de taille dynamique. Les éléments du vecteur sont stockés dans un tableau.
    * La taille de ce tableau est au minimum doublée à chaque fois qu'il est
    * nécessaire de le faire grossir.
    */
    public class Vector {

    /**
    * Tableau permettant de stocker les éléments du vecteur.
    * Seuls les size premiers éléments font partie du vecteur.
    * La taille de ce tableau est égale à la capacité du vecteur, c'est-à-dire,
    * au nombre d'éléments maximum que le vecteur peut contenir sans
    * avoir besoin d'allouer un nouveau tableau.
    */
    private int[] elements;

    /**
    * Nombre d'éléments présents dans le vecteur.
    */
    private int size;

    /**
    * Construit un vecteur de capacité initiale initialCapacity.
    *
    * @param initialCapacity Capacité initiale du vecteur
    */
    public Vector(int initialCapacity) {
    elements = new int[initialCapacity];
    size = 0;
    }

    public Vector() {
    this(10);
    }

    /**
    * Augmente la capacité du vecteur si nécessaire de façon
    * à permettre le stockage d'au moins <code>minCapacity</code>
    * éléments. S'il est nécessaire d'augmenter la capacité du vecteur,
    * elle est au minimum doublée.
    *
    * @param minCapacity Capacité minimale à assurer
    */
    public void ensureCapacity(int minCapacity) {
    int oldCapacity = elements.length;
    if (oldCapacity >= minCapacity) return;
    int newCapacity = Math.max(oldCapacity * 2, minCapacity);
    elements = Arrays.copyOf(elements, newCapacity);
    }

    public void resize(int newSize) {
    ensureCapacity(newSize);
    this.size = newSize;
    }

    /**
    * Retourne la capacité du vecteur.
    *
    * @return Capacité du vecteur.
    */
    public int capacity() {
    return elements.length;
    }

    public int size() { return 0; }
    public boolean isEmpty() { return false; }
    public void add(int element) { }
    public void set(int index, int element) {  }
    public int get(int index) { return 0; }
    }
</code>

<h3>Écriture des tests</h3>

<p>Les tests permettent de vérifier à chaque étape du développement que le code écrit est correct, c'est-à-dire,
    qu'il vérifie les spécifications. Les tests vont être écrits sous la forme de petites méthodes qui vérifient
    un des comportements attendus. </p>

<p>La classe de test est déjà créée mais si vous aviez besoin de le faire voici la procédure sur Eclipse. Faites un clic droit sur la classe <code>Vector</code> et créez un nouveau <code>JUnit Test Case</code>.
    Sélectionnez <code>New JUnit 4 test</code>, le répertoire <code>src/test/java</code> et nommez
    la classe <code>VectorTest</code>. Normalement, la nouvelle classe contient un code qui ressemble à celui-ci : <p>

    <code>
        public class VectorTest {
        @Test
        public void test() {
        fail("Not yet implemented");
        }
        }
    </code>

<p>Placez vous sur la classe et <code>VectorTest</code> et exécutez son contenu à l'aide de la flèche
    verte d'<code>Eclipse</code>. Normalement, vous devriez voir apparaître le résultat de l'exécution
    avec un échec rencontré sur la méthode <code>void test()</code>. En effet, la méthode
    <code>test()</code> est annotée par <code>@Test</code>. Par conséquent, elle est exécutée comme
    un test par <code>JUnit</code>. La ligne <code>fail("Not yet implemented");</code> génère l'échec
    obtenu par l'exécution des tests.</p>

<p>Vous allez écrire des méthodes dans la classe <code>VectorTest</code> afin de tester les différentes
    fonctionnalités de la classe <code>Vector</code>. Pour cela, vous allez remplacer le code de la classe
    <code>VectorTest</code> par le code suivant et le compléter en vous inspirant des tests déjà écrits.
    Les deux premières lignes commençant par <code>import static</code> permettent d'avoir accès
    directement aux méthodes statiques des classes <code>MatcherAssert</code> et <code>Matchers</code>
    de <code>hamcrest</code> : cela permet par exemple d'utiliser la méthode <code>equalTo</code>
    sans avoir à écrire <code>org.hamcrest.Matchers.equalTo</code>. Un tutoriel de la librairie
    <code>Hamcrest</code> est donné <a href="https://code.google.com/archive/p/hamcrest/wikis/Tutorial.wiki">ici</a>.</p>

<code>

    import static org.hamcrest.MatcherAssert.assertThat;
    import static org.hamcrest.Matchers.*;

    import org.junit.Test;

    public class VectorTest {

    @Test
    public void testVectorInt() {
    Vector vector = new Vector(123);
    assertThat(vector.capacity(), equalTo(123));
    assertThat(vector.size(), equalTo(0));
    }

    @Test
    public void testVector() {
    Vector vector = new Vector();
    assertThat(vector.capacity(), equalTo(10));
    assertThat(vector.size(), equalTo(0));
    }

    @Test
    public void testEnsureCapacity_CapacityDoubled() {
    Vector vector = new Vector(23);
    vector.ensureCapacity(24);
    assertThat(vector.capacity(), greaterThanOrEqualTo(23*2));
    }

    @Test
    public void testEnsureCapacity_CapacitySatified() {
    Vector vector = new Vector(23);
    vector.ensureCapacity(120);
    /* TODO */
    fail("not yet implemented");
    }

    @Test
    public void testEnsureCapacity_CapacityAlwaysIncreased() {
    Vector vector = new Vector(120);
    vector.ensureCapacity(10);
    assertThat(vector.capacity(), equalTo(120));
    }

    @Test
    public void testResize() {
    Vector vector = new Vector();
    vector.resize(120);
    /* TODO */
    fail("not yet implemented");
    }

    @Test
    public void testResize_Zeros() {
    Vector vector = new Vector(1);
    vector.add(2);
    vector.resize(0);
    vector.resize(1);
    assertThat(vector.get(0), equalTo(0));
    }

    @Test
    public void testResize_CapacityIncreased() {
    /* TODO */
    fail("not yet implemented");
    }

    @Test
    public void testResize_CapacityAlwaysIncreased() {
    /* TODO */
    fail("not yet implemented");
    }

    @Test
    public void testIsEmpty() {
    Vector vector = new Vector();
    assertThat(vector.isEmpty(), equalTo(true));
    vector.resize(12);
    /* TODO */
    fail("not yet implemented");
    }

    @Test
    public void testAdd() {
    Vector vector = new Vector();
    vector.add(12);
    vector.add(13);
    vector.add(10);
    assertThat(vector.size(), equalTo(3));
    assertThat(vector.get(0), equalTo(12));
    assertThat(vector.get(1), equalTo(13));
    assertThat(vector.get(2), equalTo(10));
    vector.resize(1);
    vector.add(2);
    /* TODO */
    fail("not yet implemented");
    }

    @Test
    public void testSet() {
    Vector vector = new Vector();
    vector.add(12);
    vector.add(13);
    vector.add(10);
    vector.set(0, 2);
    vector.set(2, 4);
    vector.set(3, 123);
    /* TODO */
    fail("not yet implemented");
    }

    @Test
    public void testGet() {
    Vector vector = new Vector();
    vector.add(12);
    vector.add(13);
    /* TODO : tester également le fait d'être en dehors des bornes. */
    fail("not yet implemented");
    }

    }
</code>

<h3>Implémentation de la classe Vector</h3>

<p>Vous pouvez maintenant implémenter la classe <code>Vector</code> en vérifiant
    à chaque étape de son implémentation que de nouveaux tests passent au vert.
    N'hésitez pas à utiliser le débogueur d'Eclipse si vous avez des problèmes
    pour trouver vos erreurs. Évidemment, les erreurs peuvent se trouver dans
    les tests ou dans le code de la classe Vector. Dans tous les cas, vous ne
    devez modifier les tests uniquement s'ils sont en désaccords avec les
    spécifications de la classe.</p>

<h2>Pile d'entiers</h2>

<p>Effectuez les mêmes étapes que pour l'exercice précédent afin d'ajouter
    à votre projet la nouvelle classe <code>Stack</code> qui utilise
    la classe <code>Vector</code> afin d'implémenter une pile. Vous devez
    donc écrire les spécifications de la classe, écrire une classe
    de tests, puis implémenter la classe <code>Stack</code>. Cette classe
    peut être placée dans un package <code>fr.licinfo.structure</code>. </p>

<h2>Filtres de chaînes de caractères</h2>

<p>Nous allons programmer les classes de l'exercice 3 du TD en utilisant la même démarche de "qualité".
    Créez un nouveau projet Maven de nom <code>stringfilter</code>, configurez-le afin de pouvoir utiliser
    <code>JUnit 4</code> et <code>Hamcrest</code>.</p>

<p>Définissez ensuite l'interface <code>StringFilter</code> et commentez-là. Créez ensuite les différentes classes
    qui implémentent l'interface <code>StringFilter</code>. Écrivez les commentaires précisant leurs spécifications
    <b>sans les implémenter</b>.</p>

<p>Ajoutez une classe de tests <code>StringFilterTest</code>. Dans cette classe, ajoutez une méthode testant
    chacune des implémentations de <code>StringFilter</code>. Par exemple, vous pouvez ajouter la méthode suivante
    pour tester la classe <code>UpperCaseStringFilter</code> : </p>

<code>

	@Test
	public void upperCaseStringFilter() {
		String input = "toto";
		StringFilter filter = new UpperCaseStringFilter();
		String output = filter.filter(input);
		assertThat(output, equalTo("TOTO"));
	}
</code>

<p>Implémentez ensuite les différents filtres en vérifiant que votre code est correct à l'aide des tests.</p>
</div>
<ul id="slide-out" class="right side-nav"></ul>
</div>

<footer class="page-footer teal lighten-2">
    <div class="footer-copyright">
        <div class="center-align">
            &copy; 2018 Bertrand Estellon
        </div>
    </div>
</footer>

<h3>Si vous n'aviez pas eu le dépôt Git voilà comment vous auriez pu créer votre projet</h3>

<p>Afin de simplifier l'utilisation de librairies, nous allons utiliser l'outil <code>Maven</code> : il permet d'automatiser
    la construction de logiciels Java (comme <code>Make</code>) et la gestion des dépendances. </p>

<p>Voici les étapes à suivre de la création d'un projet Maven :</p>

<ul>
    <li>Lancez <code>Eclipse</code></li>
    <li>Créez un <code>Maven Project</code> via <code>File -> New -> Other</code>.</li>
    <li>Choisissez le répertoire de travail (<code>workspace</code>) où le projet sera créé et validez le premier écran.</li>
    <li>Sélectionnez ensuite l'archetype <code>maven-archetype-quickstart</code> : un archetype est une configuration de départ pour le projet.</li>
    <li>Remplissez ensuite l'écran suivant avec les informations suivantes :
        <ul>
            <li><code>Group Id</code>: <code>fr.licinfo</code> (créateur du projet)</li>
            <li><code>Artifact Id</code>: <code>structure</code> (nom du projet)</li>
            <li><code>Version</code>: <code>1.0</code></li>
            <li><code>Package</code>: <code>fr.licinfo.structure</code></li>
        </ul>
    </li>
    <li>Validez le dernier écran pour finaliser la création du projet.</li>
</ul>

<p>À la racine d'un projet <code>Maven</code>, vous trouvez un fichier <code>pom.xml</code> pour <code>Project Object Model</code>. Il contient les informations nécessaires à la construction du projet (équivalent du <code>makefile</code>). Normalement,
    ce fichier doit contenir les informations suivantes :</p>

<code>

    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>fr.licence3.info</groupId>
        <artifactId>tp1</artifactId>
        <version>1.0</version>
        <packaging>jar</packaging>

        <name>tp1</name>
        <url>http://maven.apache.org</url>

        <properties>
            <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        </properties>

        <dependencies>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>3.8.1</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
    </project>
</code>


Nous souhaitons utiliser les librairies <code>JUnit 4</code> et <code>Hamcrest</code> pour écrire des tests.
Modifiez le fichier <code>pom.xml</code> afin d'ajouter ces dépendances. Pour cela, remplacez le contenu
de la balise <code>dependencies</code> comme indiqué ci-dessous (les références des librairies utilisables
avec Maven peuvent être trouvées <a href="https://mvnrepository.com">ici</a>). Une fois ces lignes ajoutées,
les fichiers <code>jar</code> contenant les classes des librairies sont téléchargés automatiquement
et peuvent être utilisés.

<code>

    <project ...>
        <!-- ...  -->
        <build>
            <!-- ...  -->
        </build>
        <dependencies>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.12</version>
            </dependency>
            <dependency>
                <groupId>org.hamcrest</groupId>
                <artifactId>hamcrest-all</artifactId>
                <version>1.3</version>
            </dependency>
        </dependencies>
    </project>
</code>

<p>Vous avez deux répertoires importants à la racine d'un projet <code>Maven</code> :
<ul>
    <li>le répertoire <code>src/main/java</code> contenant les sources de votre projet ;</li>
    <li>le répertoire <code>test/main/java</code> contenant les tests ;</li>
</ul>

<p>Supprimez les deux classes <code>App</code> et <code>AppTest</code>
    que <code>Maven</code> a créé afin de partir avec un projet vierge.</p>


</body>

</html>
