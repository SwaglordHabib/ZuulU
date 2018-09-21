import java.util.Scanner;

public class CommandTake implements ICommands {

    private Spieler spieler;

    private Scanner scanner;

    /**
     * Erzuegt ein CommandTake und initialisiert den Spieler
     *
     * @param spieler
     */
    public CommandTake(Spieler spieler) {
        this.spieler = spieler;
    }

    /**
     * Der Spieler nimmt ein bestimmten Gegenstand in sein Inventar auf, dann wird der Gegenstand aus dem Raum entfert.
     *
     * @param befehl
     */
    @Override
    public void execute(Befehl befehl) {
        String eingabe = befehl.gibZweitesWort();
        noItemSelected(eingabe);
        findItemAndAddToInventory(eingabe);
    }

    private void findItemAndAddToInventory(String eingabe) {
        for (Gegenstand g : this.spieler.aktuellerRaum.getAlleGegenstaende()) {
            if (g.getName().equals(eingabe)) {
                if (this.spieler.getTragkraft() + g.getGewicht() >= this.spieler.getTragkraft()) {
                    this.spieler.getGegenstaende().add(g);
                    this.spieler.getAktuellerRaum().getAlleGegenstaende().remove(g);
                    System.out.println("Das Item: " + g.getName()
                            + " hast du vom Boden aufgenommen und in dein Inventar gelegt.");
                    return;
                } else {
                    System.out.println(
                            "Du kannst dieses Item nicht aufnehmen, da deine Tragkraft nicht ausreicht. \n Lege ein Item ab oder lass es liegen.");
                }
            }
        }
        System.out.println("Diesen Gegenstand gibt es nur in einer anderen Matrix. Boom!");
    }

    private void noItemSelected(String eingabe) {
        if (eingabe == "") {
            System.out.println("Welchen Gegenstand möchten Sie aufnehmen?");
            for (Gegenstand g : this.spieler.aktuellerRaum.getAlleGegenstaende()) {
                System.out.println(g.getName() + "\n");
            }
        }
    }

    @Override
    public String getDescription() {
        return "Mit dem Befehl 'take' und der Angabe eines Items, wird dieses Item in das Spieler inventar aufgenommen.";
    }

    @Override
    public String getCommand() {
        return "take";
    }

    @Override
    public String getSyntax() {
        return "Befehl 'take': take <Itemname>";
    }

}
