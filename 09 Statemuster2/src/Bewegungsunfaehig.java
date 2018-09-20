public class Bewegungsunfaehig implements IZustand {

	private static Bewegungsunfaehig instance = new Bewegungsunfaehig();


	/**
	 * Konstruktor des Zustandes Bewegungsunfähig
	 */
	private Bewegungsunfaehig() {

	}

	/**
	 * Prüfen ob eine Objekt der Klasse Bewegungsunfähig bereits vorhanden ist.
	 * Ist dem nicht so, wird ein neues Objekt erstellt.
	 * 
	 * @return die Instanz des Zustandes
	 */
	public static IZustand getInstance() {
		if (instance == null) {
			instance = new Bewegungsunfaehig();
		}
		return instance;
	}


	/**
	 * Gibt den Zustand Verwundet zurück, sofern der aktuelle Zustand
	 * Bewegungsunfähig ist.
	 */
	@Override
	public IZustand heilen() {
		return Verwundet.getInstance();
	}

	/**
	 * Gibt sich selbst als Zustand zurück, sofern der aktuelle Zustand
	 * Bewegungsunfähig ist.
	 */
	@Override
	public IZustand leichtVerletzen() {
		return instance;

	}

	/**
	 * Gibt den Zustand Tot zurück, sofern der aktuelle Zustand Bewegungsunfähig
	 * ist.
	 */
	@Override
	public IZustand starkVerletzen() {
		return Tot.getInstance();
	}

	/**
	 * Gibt den Namen des Zustandes zurück.
	 */
	public String toString() {
		return "Bewegungsunfähig";
	}

	/**
	 * Gibt den Zustand Tot zurück, sofern der aktuelle Zustand Bewegungsunfähig
	 * ist.
	 */
	@Override
	public IZustand toeten() {
		return Tot.getInstance();
	}

	/**
	 * Wechselt, anhand der aktuellen HP des Entities, den aktuellen Zustand.
	 */
	@Override
	public void changeZustand(Entity e) {
		if (e.hp >= e.maxHp * 0.7) {
			e.aktuellerZustand = this.heilen();
		} else if (e.hp >= e.maxHp * 0.3 && e.hp < e.maxHp * 0.7) {
			e.aktuellerZustand = this.leichtVerletzen();
		} else if (e.hp >= 1 && e.hp < e.maxHp + 0.3) {
			e.aktuellerZustand = this.starkVerletzen();
		} else {
			e.aktuellerZustand = this.toeten();
		}

	}

}
