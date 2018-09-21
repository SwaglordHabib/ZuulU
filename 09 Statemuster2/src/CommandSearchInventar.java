public class CommandSearchInventar implements ICommands{
    private Spieler spieler;

    /**
     * Erzeugt ein CommandSearchInventar und initialisiert den Spieler
     *
     * @param spieler
     */
    public CommandSearchInventar(Spieler spieler){
        this.spieler = spieler;
    }

    /**
     * Gibt alle Gegenstände die im Inventar liegen auf der Konsole aus.
     *
     * @param befehl
     */
    @Override
    public void execute(Befehl befehl) {
        for (Gegenstand g:spieler.getGegenstaende()) {
            System.out.println(g.getName());
        }
    }

    @Override
    public String getDescription() {
        return "Dieser Befehl gibt das Spielerinventar aus";
    }

    @Override
    public String getCommand() {
        return "inventar";
    }

    @Override
    public String getSyntax() {
        return "Befehl 'inventar': inventar";
    }
}
