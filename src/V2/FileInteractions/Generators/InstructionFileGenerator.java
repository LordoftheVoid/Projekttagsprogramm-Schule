package V2.FileInteractions.Generators;

import V2.cMain;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Aaron on 27.02.2018.
 */
public class InstructionFileGenerator {

    private String baseURL;

    public InstructionFileGenerator(String baseURL) {
        this.baseURL = baseURL;
    }

    public void generateHelpFile() {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(this.baseURL + "\\Hilfe.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.print("In dieser Datei finden sie Handlungsratschläge bei Fehlfunktion des Programms." + System.lineSeparator() +
                "" + System.lineSeparator() +
                "   Zunächst sei darauf hingewiesen, dass sich trotz bester Bemühungen nicht alle Fehlerursachen abfangen lassen," + System.lineSeparator() +
                "   Software hat allein der Komplexität wegen Fehler." + System.lineSeparator() +
                "   Dementsprechend ist es wichtig, einen kühlen Kopf zu bewahren, sowie konkrete Schritte einzuleiten um wahlweise fortzufahren oder " + System.lineSeparator() +
                "   dem Entwickler bestmöglich bei der Behebung des Problems zu helfen." + System.lineSeparator() +
                "" + System.lineSeparator() +
                "   Konkrete Schritte in diesem Fall sind zunächst einmal die Kontrolle der Ausgabe: Es gibt viele Fälle, " + System.lineSeparator() +
                "   in dem das Programm in der Lage ist, Fehler abzufangen. Entsprechende Ausgaben gilt es zu beachten, beispielsweise falsche Eingaben " + System.lineSeparator() +
                "   oder fehlende Programmteile. " +
                "" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "   Sollte es weiterhin zu unerwartetem Verhalten kommen, so ist ein Neustart des Programms meistens zu empfehlen," + System.lineSeparator() +
                "   auf diese Weise kann die Programmlogik wieder von null beginnen. " + System.lineSeparator() +
                "" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Dieser Abschnitt beschäftigt sich mit wiedeholt auftretendem, vermutlich nicht geplantem und wiedersprüchlichem Verhalten, beispielsweise: " + System.lineSeparator() +
                "       -Abstürze." + System.lineSeparator() +
                "       -Keine Reaktion auf Eingaben." + System.lineSeparator() +
                "       -Falsche Verarbeitung der Eingabe." + System.lineSeparator() +
                "       -Falsche Ausgabe bzw die Ergebnisse werden nach manueller Probe als falsch befunden. " + System.lineSeparator() +
                "" + System.lineSeparator() +
                "   In solchen Fällen hilft es, dem Entwckickler hilfreiche Informationen bereitzustellen, damit dieser weiß wonach er zu suchen hat. " + System.lineSeparator() +
                "       Solche Informationen können beispielsweise umfassen: " + System.lineSeparator() +
                "           -Wo/ Wann trat das Problem auf ? " + System.lineSeparator() +
                "           -Was war das erwartete Verhalten ? Was geschah stattdessen ? " + System.lineSeparator() +
                "           -Ist das Fehlverhalten eventuell sogar reproduzierbar ? Wenn ja, wie ? " + System.lineSeparator() +
                "           -Die entsprechenden Dateien mitschicken, in diesem Fall wird nur die .db-Datei gebraucht. " + System.lineSeparator() +
                System.lineSeparator() + System.lineSeparator() +
                "   Entsprechende Informationen an aaronwey@posteo.de mailen, eine Rückmeldung innerhalb der nächsten 24 Stunden ist zu erwarten." + System.lineSeparator()
        );


        writer.close();


        cMain.updateStatus("Falls Hilfe benötigt wird, eine entsprechende  Datei wurde unter  " + "\n" +
                this.baseURL + "\n" + "erzeugt");
    }


    public void generateAdviceFile() {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(this.baseURL + "\\Hinweise.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.print("In dieser Datei stehen einige nützliche Dinge, um den Gebrauch des Programms zu erleichtern" + System.lineSeparator() + System.lineSeparator() +
                "   Das Programm testet auf invalide Eingabe seitens des Benutzers, die wichtigsten Regeln für die Eingaben sind: " + System.lineSeparator() +
                "       -Die Felder, die Nummern erwarten dürfen nur die Ziffern 0-9 enthalten  " + System.lineSeparator() +
                "       -Namensfelder sind soweit unbeschränkt. " + System.lineSeparator() +
                "       -Eine leere Eingabe ist nie erlaubt " + System.lineSeparator() +
                "" + System.lineSeparator() +
                "   Die Prioritäten des Programms sind erst Projekte, dann Schüler, somit kann es sein, das das Programm Eingaben verweigert." + System.lineSeparator() +
                "   In solchen Fällen sicherstellen, dass die Projekte existieren. " +
                "" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Hinweise zum Testen:" +
                "   -Es liegt in ihrer Verantwortung, die Korrektheit der Ergebnisse sicherzustellen! " + System.lineSeparator() +
                "   " +
                "   Allerdings wäre es gleichzeitig unsinnig, alle zu überprüfen, dann macht man letztlich doch nur die Arbeit," +
                "   die einem der Computer abnimmt." + System.lineSeparator() +
                "   " + System.lineSeparator() +
                "   Somit gilt es, ein effektives Testsystem anzuwenden, beispielsweise wie folgt: " + System.lineSeparator() +
                "       -Für alle Projekte zählen, wie viele Schüler zugeteilt worden, dies ist über das Suchen gut realisierbar, da" + System.lineSeparator() +
                "        die Ergebnisse der Suche direkt gezählt werden." + System.lineSeparator() +
                "       -Für kleine Projekte alle Schüler testen, ob sie eine entsprechende Präferenz geäußert haben" + System.lineSeparator() +
                "       -Die Gesamt-Anzahl der Schüler überprüfen" + System.lineSeparator() +
                "       -Stichprobenartig aus allen Klassen Schüler testen" + System.lineSeparator()

        );


        writer.close();


        cMain.updateStatus("Eine Datei mit Hinweisen wurde unter " + "\n" +
                this.baseURL + "\n" + "erzeugt");
    }


}
