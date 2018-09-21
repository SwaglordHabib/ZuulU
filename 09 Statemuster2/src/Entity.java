public class Entity {
	protected int hp;
	protected int maxHp;
	protected IZustand aktuellerZustand;
	protected int staerke;
	protected Raum aktuellerRaum;

	/**
	 * Erzeugt ein Entity und initilaisiert die hp, maxHp und staerke mit Ã¼bergebenen Werten und der Zustand wird auf Gesund gesetzt.
	 *
	 * @param hp
	 * @param staerke
	 */
	public Entity(int hp, int staerke) {
		this.hp = hp;
		this.maxHp = hp;
		this.staerke = staerke;
		this.aktuellerZustand = Gesund.getInstance();
	}

    /**
     * Gibt den aktuellen Raum zurück
     *
     * @return Raum, der aktuelle Raum
     */
	public Raum getAktuellerRaum() {
		return aktuellerRaum;
	}

    /**
     * Setzt den aktuellen Raum auf den mitgelieferten Raum
     *
     * @param aktuellerRaum
     */
	public void setAktuellerRaum(Raum aktuellerRaum) {
		this.aktuellerRaum = aktuellerRaum;
	}

    /**
     * Fügt dem mitgelieferten Entity schaden zu
     *
     * @param e, ist das anzugreifende Entity
     */
	public void doDamage(Entity e) {
		e.takeDamage((int) (this.staerke * Math.PI));
	}

	/**
	 * Zieht den angegebenen schaden von der aktuellen HP ab
	 * @param damage, ist der abzuziehende Schaden
	 */
	private void takeDamage(int damage) {
		this.hp -= damage;
		this.aktuellerZustand.changeZustand(this);
	}
}
