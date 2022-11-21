package pgdp.oop;

import java.awt.*;
import java.io.File;

public class Fish extends Animal {

	static String filename = "fish.png";

	public Fish(int x, int y) {
		super(x, y);

		f = new File(filename);
		image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
	}

	public boolean canEat(Animal animal) {
		return animal != null && animal.eatenBy(this);
	}

	@Override
	public void move() {

		if (!alive) return;
		antarktis[x][y] = null;

		if (antarktis[x][fixPosition(y-1)] == null && canMove(x, fixPosition(y-1), 1)) {
			y = fixPosition(y-1);
		}
		else if (antarktis[fixPosition(x+1)][y] == null && canMove(fixPosition(x+1), y, 2)) {
			x = fixPosition(x+1);
		}
		else if (antarktis[x][fixPosition(y+1)] == null && canMove(x, fixPosition(y+1), 3)) {
			y = fixPosition(y+1);
		}
		else if (antarktis[fixPosition(x-1)][y] == null && canMove(fixPosition(x-1), y, 0)) {
			x = fixPosition(x-1);
		}

		antarktis[x][y] = this;

	}

	@Override
	protected boolean eatenBy(Penguin penguin) {
		return true;
	}

	@Override
	protected boolean eatenBy(PlayerPenguin playerPenguin) {
		return true;
	}

	@Override
	protected boolean eatenBy(Whale whale) {
		return true;
	}

	@Override
	protected boolean eatenBy(LeopardSeal leopardSeal) {
		return true;
	}

	@Override
	protected boolean eatenBy(Fish fish) {
		return false;
	}

}
