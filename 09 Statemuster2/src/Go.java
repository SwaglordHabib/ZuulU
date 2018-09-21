import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Go {
	private Entity e;
	private Raum raum;
	private Raum alterRaum;
	private List<Raum> raumliste;
	private Scanner scanner;

	/**
	 * Initialisiert die Attribute des Befehles 'go'
	 * @param e, ist das Entity
	 * @param raum, ist der Raum
	 * @param raumliste, ist die Liste an Räumen, welche der vorher genannte Raum zur Verfügung hat
	 */
	public void init(Entity e, Raum raum, ArrayList<Raum> raumliste) {
		this.e = e;
		this.raum = raum;
		this.alterRaum = e.aktuellerRaum;
		this.raumliste = raumliste;
	}

}
