public class Bewegungsunfaehig implements IZustand {

	private static Bewegungsunfaehig instance = new Bewegungsunfaehig();


	/**
	 * Konstruktor des Zustandes Bewegungsunf�hig
	 */
	private Bewegungsunfaehig() {

	}

	/**
	 * Pr�fen ob eine Objekt der Klasse Bewegungsunf�hig bereits vorhanden ist.
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
	 * Gibt den Zustand Verwundet zur�ck, sofern der aktuelle Zustand
	 * Bewegungsunf�hig ist.
	 */
	@Override
	public IZustand heilen() {
		return Verwundet.getInstance();
	}

	/**
	 * Gibt sich selbst als Zustand zur�ck, sofern der aktuelle Zustand
	 * Bewegungsunf�hig ist.
	 */
	@Override
	public IZustand leichtVerletzen() {
		return instance;

	}

	/**
	 * Gibt den Zustand Tot zur�ck, sofern der aktuelle Zustand Bewegungsunf�hig
	 * ist.
	 */
	@Override
	public IZustand starkVerletzen() {
		return Tot.getInstance();
	}

	/**
	 * Gibt den Namen des Zustandes zur�ck.
	 */
	public String toString() {
		return "Bewegungsunf�hig";
	}

	/**
	 * Gibt den Zustand Tot zur�ck, sofern der aktuelle Zustand Bewegungsunf�hig
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
