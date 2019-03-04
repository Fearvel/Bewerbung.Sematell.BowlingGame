# Bewerbung.Sematell.BowlingGame
Das telefonisch besprochene Projekt.

Der eingesetzte Zeitaufwand war ca. 3,5 Stunden über den Tag verteilt.
Dieses Projekt wurde mittels IntelliJ erzeugt.


# Review
Hallo Andreas, ich nutze einfach mal die README.md, um dir noch ein paar Infos rund um das Review zu geben.

Hier ein paar Sachen, die mir während der Review deines Codes ausgefallen sind:

Hier ein paar Sachen, die mir während der Review deines Codes ausgefallen sind:

1. Du hast den .idea Ordner und die projektbezogene .iml Datei mit eingecheckt. Das ist kein Problem, solange du alleine an einem Projekt arbeitest. Sobald mehrere am selben Projekt arbeiten, führt das immer zu Problemen und überflüssigen Commits. Daher habe ich den .idea Ordner und die .iml Datei aus dem Repository entfernt. Ändere Entwickler verwenden auch andere IDEs. Neben Intellij wird oftmals auch noch Eclipse, VSCode usw. verwendet. Jeder Entwickler sollte in der Wahl seiner IDE frei sein.
2. Aus 1. ergibt sich dann sofort das nächste "Problem". Dein Programm ist ohne deine Intellij-Settings nicht lauffähig, da du wahrscheinlich die JUnit Dependency über Intellij verwaltet bzw. hinzugefügt hast. Deine Test-Klassen compilen jetzt nicht mehr und damit auch dein Prod-Code. Dieses Problem löst man, in dem man ein Build-Tool verwendet. Dieses Build-Tools verwaltet losgelöst von der verwendeten IDE die benötigten Dependencies. Wir setzen dafür Gradle ein. Gradle ist ein Standalone-Tool und daher nutzen wir Gradle-Wrapper (kurz gradlew). Ich habe dafür die Gradlew in deinem Projekt integriert. Ausgangspunkt ist die build.gradle … in dieser Datei werden z.B. alle für das Projekt benötigte Dependencies definiert. Gradlew wird mit ins Repository eingecheckt und für sämtliche Aufgaben (z.B. Testausführung, .jar bauen und Upload in ein Artifact-Repository) verwendet.
3. Deine Tests liegen neben dem Prod-Code … da gehören sie aber nicht hin. Testcode darf niemals mit ausgeliefert werden. Das finale jar enthält ansonsten nutzlosen Testcode und diese Tests verraten zu viel über den Prodcode. Dazu habe ich sogenannte Sourcesets angelegt und deinen Prod-Code von den Test-Code getrennt. Dafür habe ich src/main/jave (prod) und src/test/java (test) erstellt. Gradle erkennt diese Sourcesets automatisch und weiß, dass die Tests nicht in den das finale .jar gehören. Intellij bietet einen sehr guten Gradle-Support an. Entweder startest du die Tests dann über die eingebaute Gradle-View oder per Console „gradlew clean test“. Jetzt hast du ein Projekt, das du auf beliebigen Rechner auschecken und verwenden kannst. Der Rechner muss nur eine kompatible Java-SDK besitzen.
4. Jetzt kommen wir zum Code. Ich kenne deinen bisherigen Programmiererfahrungen nicht. In Java werden Packagenamen generellt lowercase geschrieben, damit verhindert man Konflikte mit Klassen.
Ich habe dir Kommentare direkt an die entsprechenden Code-Stellen geschrieben, damit du sie besser nachvollziehen kannst.
Ich habe relativ viele TODOs erstellt … nicht erschrecken. Einige Code-Stellen habe ich angepasst, andere nicht … du kannst den Code gern anpassen und mir nochmal zu einem Re-Review geben.

Allg:
Als Team ist es sehr wichtig, sich an gemeinsam definierte Code-Conventions zu halten. Ziel muss es sein, dass ich innerhalb kürzester Zeit Code von anderen Entwicklern lesen und verstehen kann. Die Unittests habe ich mir aus Zeitgründen noch nicht genau angeschaut … aber erstmal gut, dass du welche hast. Die würde ich mir separat nochmal anschauen.

Was hat mir gut gefallen:
* Exception-Handling
* Java-Docs
* Klassenaufteilung
* Benamung
* Das Unittests vorhanden sind, auch wenn sich nicht vollständig sind

