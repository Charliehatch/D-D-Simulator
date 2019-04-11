package entities.statics;

import entities.Entity;
import test.Handler;

//Static entities do not move, unlike creature entities

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

	}

}
