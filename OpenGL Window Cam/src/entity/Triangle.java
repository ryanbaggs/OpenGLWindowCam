package entity;

public class Triangle {
	
	// Movement flags.
	private boolean moveUp = false;
	private boolean moveDown = false;
	private boolean moveLeft = false;
	private boolean moveRight = false;
	
	// The vertices of the triangle.
	private float[] points = {
		0.0f,  0.5f,  0.0f,
		0.5f, -0.5f,  0.0f,
		-0.5f, -0.5f,  0.0f
	};
	
	/**
	 * Updates the coordinates of the triangle
	 */
	public void update() {
		if(moveUp) {
			points[1] += 0.02f;
			points[4] += 0.02f;
			points[7] += 0.02f;
		}
	}
	
	public float[] getPoints() {
		return points;
	}

	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}

	public void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	
}
