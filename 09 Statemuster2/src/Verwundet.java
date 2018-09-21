public class Verwundet implements IZustand {

	private static Verwundet instance = null;

	private Verwundet() {

	}

	/**
	 * Pr�ft ob eine Instanz der Klasse vorhanden ist
	 * 
	 * @return eine vorhandene oder neu erstellte Instanz
	 */
	public static IZustand getInstance() {
		if (instance == null) {
			instance = new Verwundet();
		}
		return instance;
	}

	/**
	 * Gibt die Instanz der Klasse 'Bewegungsunfaehig' zur�ck
	 */
	@Override
	public IZustand heilen() {
		return Gesund.getInstance();
	}

	/**
	 * Gibt die Instanz der Klasse 'Bewegungsunfaehig' zur�ck
	 */
	@Override
	public IZustand leichtVerletzen() {
		return Bewegungsunfaehig.getInstance();

	}

	/**
	 * Gibt die Instanz der Klasse 'Verwundet' zur�ck
	 */
	@Override
	public IZustand starkVerletzen() {
		return Verwundet.getInstance();
	}

	/**
	 * Gibt den Namen des Zustandes als String zur�ck
	 */
	public String toString() {
		return "Verwundet";
	}

	/**
	 * Gibt die Instanz der Klasse 'Tot' zur�ck
	 */
	@Override
	public IZustand toeten() {
		return Tot.getInstance();
	}

	/**
	 * �ndert den Zustand anhand der aktuelle HP
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
