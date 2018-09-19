
public class Tot implements IZustand {

	private static Tot instance = new Tot();

	private Tot() {

	}

	public static IZustand getInstance() {
		return instance;
	}

	@Override
	public IZustand heilen() {
		return Bewegungsunfaehig.getInstance();
	}

	@Override
	public IZustand leichtVerletzen() {
		return this;
	}

	@Override
	public IZustand starkVerletzen() {
		return this;
	}

	@Override
	public String toString() {
		return "Tot";
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
