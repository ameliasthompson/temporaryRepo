package escape.coordinate;

public interface InternalCoordinate extends Coordinate {
	
	// We can't provide default implementations of these despite how
	// standard they should be because we can't have private variables
	// in an interface.
	public int getX();
	public int getY();
	
	public boolean linear(InternalCoordinate c);
	public boolean jump(InternalCoordinate mid, InternalCoordinate end);
	public boolean orthogonal(InternalCoordinate c);
	public boolean diagonal(InternalCoordinate c);
	public InternalCoordinate[] getAdjacent();
	public InternalCoordinate findJump(InternalCoordinate c);
	
}
