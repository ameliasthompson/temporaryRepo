package escape.coordinate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class SquareCoordinateTest {

	// #1
	@Test
	public void coordinateAtInitializedLocation() {
		SquareCoordinate a = new SquareCoordinate(1, 1);
		
		assertEquals(a.getX(), 1);
		assertEquals(a.getY(), 1);
	}
	
	// #2
	@Test
	public void coordinateAtInitializedLocation2() {
		SquareCoordinate a = new SquareCoordinate(0, 0);
		
		assertEquals(a.getX(), 0);
		assertEquals(a.getY(), 0);
	}
	
	// #3
	@Test
	public void sideCoordinateDistanceIsOne() {
		SquareCoordinate a = new SquareCoordinate(0, 0);
		SquareCoordinate b = new SquareCoordinate(1, 0);
		
		assertEquals(a.DistanceTo(b), 1);
	}
	
	// #4
	@Test
	public void sameCoordinateDistanceIsZero() {
		SquareCoordinate a = new SquareCoordinate(0, 0);
		SquareCoordinate b = new SquareCoordinate(0, 0);
		
		assertEquals(a.DistanceTo(b), 0);
	}
	
	// #5
	@Test
	public void diagonalCoordinateDistanceIsOne() {
		SquareCoordinate a = new SquareCoordinate(0, 0);
		SquareCoordinate b = new SquareCoordinate(1, 1);
		
		assertEquals(a.DistanceTo(b), 1);
	}
	
	// #6 - This test was not expected to fail, but was added for completeness
	@Test
	public void knightCoordinateDistanceIsTwo() {
		SquareCoordinate a = new SquareCoordinate(0, 0);
		SquareCoordinate b = new SquareCoordinate(-2, 1);
		
		assertEquals(a.DistanceTo(b), 2);
	}
	
	// #7
	@Test
	public void verticalIsOrthogonal() {
		SquareCoordinate a = new SquareCoordinate(1, 1);
		SquareCoordinate b = new SquareCoordinate(1, 2);
		
		assertTrue(a.orthogonal(b));
	}
	
	// #8
	@Test
	public void horizontalIsOrthogonal() {
		SquareCoordinate a = new SquareCoordinate(1, 1);
		SquareCoordinate b = new SquareCoordinate(2, 1);
		
		assertTrue(a.orthogonal(b));
	}
	
	// #9
	@Test
	public void diagonalIsNotOrthogonal() {
		SquareCoordinate a = new SquareCoordinate(1, 1);
		SquareCoordinate b = new SquareCoordinate(2, 2);
		
		assertFalse(a.orthogonal(b));
	}
	
	// #7
	@Test
	public void verticalIsLinear() {
		SquareCoordinate a = new SquareCoordinate(1, 1);
		SquareCoordinate b = new SquareCoordinate(1, 2);
		
		assertTrue(a.linear(b));
	}
	
	// #8
	@Test
	public void horizontalIsLinear() {
		SquareCoordinate a = new SquareCoordinate(1, 1);
		SquareCoordinate b = new SquareCoordinate(2, 1);
		
		assertTrue(a.linear(b));
	}
	
	// #9
	@Test
	public void diagonalIsLinear() {
		SquareCoordinate a = new SquareCoordinate(1, 1);
		SquareCoordinate b = new SquareCoordinate(2, 2);
		
		assertTrue(a.linear(b));
	}
	
	// #11
	@Test
	public void linearJumpOkay() {
		SquareCoordinate a = new SquareCoordinate(1, 1);
		SquareCoordinate b = new SquareCoordinate(2, 2);
		SquareCoordinate c = new SquareCoordinate(3, 3);
		
		assertTrue(a.jump(b, c));
	}
	
	// #12
	@Test
	public void nonlinearJumpBad() {
		SquareCoordinate a = new SquareCoordinate(1, 1);
		SquareCoordinate b = new SquareCoordinate(2, 2);
		SquareCoordinate c = new SquareCoordinate(1, 2);
		
		assertFalse(a.jump(b, c));
	}
	
	// #13
	@Test
	public void longJumpBad() {
		SquareCoordinate a = new SquareCoordinate(1, 1);
		SquareCoordinate b = new SquareCoordinate(3, 3);
		SquareCoordinate c = new SquareCoordinate(5, 5);
		
		assertFalse(a.jump(b, c));
	}
}
