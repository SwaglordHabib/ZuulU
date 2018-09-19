public class Verwundet implements IZustand {

	private static Verwundet instance = null;

	private Verwundet() {

	}

	public static IZustand getInstance() {
		if (instance == null) {
			instance = new Verwundet();
		}
		return instance;
	}

	@Override
	public IZustand heilen() {
		return Gesund.getInstance();
	}

	@Override
	public IZustand leichtVerletzen() {
		return Bewegungsunfaehig.getInstance();

	}

	@Override
	public IZustand starkVerletzen() {
		return Verwundet.getInstance();
	}

	public String toString() {
		return "Verwundet";
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
