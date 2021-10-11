package escape.coordinate;

public class TriangleCoordinate implements InternalCoordinate {

	private final int x;
	private final int y;
	
	public TriangleCoordinate(int xpos, int ypos) {
		x = xpos;
		y = ypos;
	}
	
	@Override
	public int DistanceTo(Coordinate c) {
		InternalCoordinate b = (InternalCoordinate) c;
		if (x == b.getX() && y == b.getY()) {
			return 0;
		}
		
		InternalCoordinate[] adj = getAdjacent();
		for (InternalCoordinate i : adj) {
			// Find whatever a coordinate that's closer to the target in our naive square place
			if (Math.abs(i.getX() - b.getX()) < Math.abs(x - b.getX())
					|| Math.abs(i.getY() - b.getY()) < Math.abs(y - b.getY())) {
				return 1 + i.DistanceTo(c);
			}
		}
		
		// If none of them are closer in square land we need shenanigans to go diagonally
		int dx = 0;
		int dy = 0;
		if (x - b.getX() != 0) { dx = x - b.getX() < 0 ? 1 : -1; }
		if (y - b.getY() != 0) { dy = y - b.getY() < 0 ? 1 : -1; }
		
		return 3 + new TriangleCoordinate(x + dx, y + dy).DistanceTo(c);
		
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public boolean jump(InternalCoordinate mid, InternalCoordinate end) {
		return DistanceTo(end) == 2;
	}
	
	@Override
	public boolean linear(InternalCoordinate c) {
		// There are three different ways this line could be going:
		
		// Horizontal
		if (x == c.getX()) {
			return true;
		}
		
		// The two diagonals
		int tmpx = x;
		int tmpy = y;
		if (isDown(x, y)) {
			// Down right
			if (c.getY() > y) {
				while (tmpx > c.getX() || tmpy < c.getY()) {
					tmpx += isDown(tmpx, tmpy) ? 0 : -1;
					tmpy += isDown(tmpx, tmpy) ? 1 : 0;
					
					if (tmpx == c.getX() && tmpy == c.getY()) {
						return true;
					}
				}
			
			//Down left
			} else if (c.getY() < y) {
				while (tmpx > c.getX() || tmpy > c.getY()) {
					tmpx += isDown(tmpx, tmpy) ? 0 : -1;
					tmpy += isDown(tmpx, tmpy) ? -1 : 0;
					
					if (tmpx == c.getX() && tmpy == c.getY()) {
						return true;
					}
				}
			}
		
		} else {
			// Up right
			if (c.getY() > y) {
				while (tmpx < c.getX() || tmpy < c.getY()) {
					tmpx += isDown(tmpx, tmpy) ? 1 : 0;
					tmpy += isDown(tmpx, tmpy) ? 0 : 1;
					
					if (tmpx == c.getX() && tmpy == c.getY()) {
						return true;
					}
				}
			
			//Up left
			} else if (c.getY() < y) {
				while (tmpx < c.getX() || tmpy > c.getY()) {
					tmpx += isDown(tmpx, tmpy) ? 1 : 0;
					tmpy += isDown(tmpx, tmpy) ? 0 : -1;
					
					if (tmpx == c.getX() && tmpy == c.getY()) {
						return true;
					}
				}
			}	
		}
		
		return false;
	}

	@Override
	public boolean orthogonal(InternalCoordinate c) {
		return false;
	}

	@Override
	public boolean diagonal(InternalCoordinate c) {
		return false;
	}

	@Override
	public InternalCoordinate[] getAdjacent() {
		if (isDown(x, y)) {
			TriangleCoordinate[] tmp = {
					new TriangleCoordinate(x, y-1),
					new TriangleCoordinate(x, y+1),
					new TriangleCoordinate(x+1, y)
			};
			return (InternalCoordinate[]) tmp;
			
		} else {
			TriangleCoordinate[] tmp = {
					new TriangleCoordinate(x, y-1),
					new TriangleCoordinate(x, y+1),
					new TriangleCoordinate(x-1, y)
			};
			return (InternalCoordinate[]) tmp;
			
		}
	}
	
	/**
	 * Check to see if a coordinate points up or down. Because all coordinates
	 * where x and y match point down, that means all coordinate where x and y
	 * are separated by a multiple of two point down.
	 * 
	 * @return
	 */
	private boolean isDown(int xpos, int ypos) {
		return ((xpos - ypos) % 2 == 0);
	}

	@Override
	public InternalCoordinate findJump(InternalCoordinate c) {
		// TODO Auto-generated method stub
		return null;
	}

}
