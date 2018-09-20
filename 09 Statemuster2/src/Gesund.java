public class Gesund implements IZustand {

	private static Gesund instance = null;

	private Gesund() {

	}

	public static IZustand getInstance() {
		if (instance == null) {
			instance = new Gesund();
		}
		return instance;
	}

	@Override
	public IZustand heilen() {
		return instance;
	}

	@Override
	public IZustand leichtVerletzen() {
		return Verwundet.getInstance();
	}

	@Override
	public IZustand starkVerletzen() {
		return Bewegungsunfaehig.getInstance();
	}

	@Override
	public String toString() {
		return "Gesund";
	}

	@Override
	public IZustand toeten() {
		return Tot.getInstance();
	}

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
