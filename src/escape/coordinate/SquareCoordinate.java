package escape.coordinate;

import java.lang.Math;

public class SquareCoordinate implements InternalCoordinate {

	private final int x;
	private final int y;
	
	public SquareCoordinate(int xpos, int ypos) {
		x = xpos;
		y = ypos;
	}
	
	@Override
	public int DistanceTo(Coordinate c) {
		InternalCoordinate b = (InternalCoordinate) c;
		
		int dist = 0;
		int tmpx = b.getX();
		int tmpy = b.getY();
		while (tmpx != x || tmpy != y) {
			
			// Move tmpx and tmpy toward x and y.
			if (tmpx != x) { tmpx += (tmpx < x) ? 1 : -1; }
			if (tmpy != y) { tmpy += (tmpy < y) ? 1 : -1; }
			
			dist++;
		}
		
		return dist;
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
		return DistanceTo(end) == 2
				&& (linear(mid) && linear(end))
				&& x - mid.getX() == mid.getX() - end.getX()
				&& x - mid.getY() == mid.getY() - end.getY();
	}
	
	@Override
	public boolean linear(InternalCoordinate c) {
		return orthogonal(c) || diagonal(c);
	}

	@Override
	public boolean orthogonal(InternalCoordinate c) {
		return x == c.getX() || y == c.getY();
	}
	
	@Override
	public boolean diagonal(InternalCoordinate c) {
		return Math.abs(x - c.getX()) == Math.abs(y - c.getY());
	}

	@Override
	public InternalCoordinate[] getAdjacent() {
		InternalCoordinate coords[] = {
				new SquareCoordinate(x - 1, y - 1),
				new SquareCoordinate(x, y - 1),
				new SquareCoordinate(x + 1, y - 1),
				new SquareCoordinate(x - 1, y),
				new SquareCoordinate(x + 1, y),
				new SquareCoordinate(x - 1, y + 1),
				new SquareCoordinate(x, y + 1),
				new SquareCoordinate(x + 1, y + 1)
		};
		
		return coords;
	}

	@Override
	public InternalCoordinate findJump(InternalCoordinate c) {
		return (InternalCoordinate) new SquareCoordinate(
				c.getX() - (c.getX() - x),
				c.getY() - (c.getY() - y));
	}

}
