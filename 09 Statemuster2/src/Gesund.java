public class Gesund implements IZustand {

	private static Gesund instance = null;

	private Gesund() {

	}

	/**
	 * Pr�ft ob eine Instanz der Klasse vorhanden ist
	 * 
	 * @return eine vorhandene oder neu erstellte Instanz
	 */
	public static IZustand getInstance() {
		if (instance == null) {
			instance = new Gesund();
		}
		return instance;
	}

	/**
	 * Gibt die eigene Instanz zur�ck
	 */
	@Override
	public IZustand heilen() {
		return instance;
	}

	/**
	 * Gibt die Instanz der Klasse 'Verwundet' zur�ck
	 */
	@Override
	public IZustand leichtVerletzen() {
		return Verwundet.getInstance();
	}

	/**
	 * Gibt die Instanz der Klasse 'Bewegungsunfaehig' zur�ck
	 */
	@Override
	public IZustand starkVerletzen() {
		return Bewegungsunfaehig.getInstance();
	}

	/**
	 * Gibt den Namen des Zustandes als String zur�ck
	 */
	@Override
	public String toString() {
		return "Gesund";
	}

	/**
	 * Gibt den Zustand der Klasse 'Tot' zur�ck
	 */
	@Override
	public IZustand toeten() {
		return Tot.getInstance();
	}

	/**
	 * Ermittelt den Zustand f�r eine Entity, das kann sowohl ein Spieler als auch ein Monster sein.
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
