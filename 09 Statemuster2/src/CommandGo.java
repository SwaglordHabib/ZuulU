import java.util.Scanner;

public class CommandGo implements ICommands {

	private Spieler e;
	private Raum raum;
	private Raum alterRaum;
	private Scanner scanner;

	public CommandGo(Spieler e, Raum raum) {
		this.e = e;
		this.raum = raum;
		this.alterRaum = e.aktuellerRaum;
	}

	@Override
	public void execute() {
		if (raum.isTeleporter()) {
			e.setAktuellerRaum(Spiel.getRaumliste().get(Spiel.getRandom(Spiel.getRaumliste().size() - 1)));
			execute();
		}
		if (raum.getMonsterliste().size() > 0) {
			fight();
		}
		e.setAktuellerRaum(raum);
		raumInfoausgeben(raum);
	}

	private void fight() {
		StringBuilder sb = new StringBuilder();
		String eingabe;
		scanner = new Scanner(System.in);
		boolean attacke = false;
		sb.append("Es sind " + raum.getMonsterliste().size() + "Monster in diesem Raum!\n");
		sb.append("Innerhalb eines Kampfes, kannst du 'attacke' oder 'fluechten' verwenden.\n");
		sb.append("Wenn du fluechtest, kannst du den Raum nicht betreten!\n");
		System.out.println(sb);

		sb.delete(0, sb.length());

		do {
			eingabe = scanner.nextLine().toLowerCase();

			if (eingabe == "attacke" || eingabe == "a") {

				for (int i = 0; i < raum.getMonsterliste().size(); i++)
					System.out.println("Monster " + i + " Leben: " + raum.getMonsterliste().get(i).hp);

				attacke = true;

				while (attacke) {
					System.out.println("Welches Monster möchtest du angreifen: ");
					eingabe = scanner.nextLine();
					try {
						e.doDamage(raum.getMonsterliste().get(Integer.parseInt(eingabe)));
						if (raum.getMonsterliste().get(Integer.parseInt(eingabe)).aktuellerZustand == Tot
								.getInstance()) {
							raum.getMonsterliste().remove(Integer.parseInt(eingabe));
							System.out.println("Das Monster wurde besiegt!!! Gurr!");
						}

						for (Monster m : raum.getMonsterliste()) {
							m.doDamage(e);

							if (e.aktuellerZustand == Tot.getInstance()) {
								System.out.println("Du wurdest besiegt!");
								System.exit(0);
							}
						}
						attacke = false;
					} catch (Exception e) {
						System.out.println("Dieses Monster gibt es nicht.");
					}
				}
			} else if (eingabe == "fluechten" || eingabe == "f") {
				this.raum = this.alterRaum;
				execute();
				System.out.println("Du bist geflohen, skrr!");
				break;
			} else {
				System.out.println("Die Eingabe war falsch!");
			}
		} while (raum.getMonsterliste().size() > 0);

	}

	@Override
	public String getDescription() {
		return "Mit dem Befehl 'go' und der Angabe einer Richtung, wird der Raum gewechselt.";
	}

	@Override
	public String getSyntax() {
		return "Befehl 'go': go <Richtung>";
	}

	private String raumInfoausgeben(Raum r) {
		return r.getLangeBeschreibung();
	}

	@Override
	public String getCommand() {
		return "go";
	}

}
