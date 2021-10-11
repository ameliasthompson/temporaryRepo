package escape.coordinate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class TriangleCoordinateTest {

	// #1
	@Test
	public void sameTileDistIsZero() {
		TriangleCoordinate a = new TriangleCoordinate(1,1);
		TriangleCoordinate b = new TriangleCoordinate(1,1);
		
		assertEquals(a.DistanceTo(b), 0);
	}
	
	// #2
	@Test
	public void adjacantDistIsOne() {
		TriangleCoordinate a = new TriangleCoordinate(1,1);
		TriangleCoordinate b = new TriangleCoordinate(1,2);
		
		assertEquals(a.DistanceTo(b), 1);
	}
	
	// #3
	@Test
	public void twoAwayDistIsTwo() {
		TriangleCoordinate a = new TriangleCoordinate(1,1);
		TriangleCoordinate b = new TriangleCoordinate(1,3);
		
		assertEquals(a.DistanceTo(b), 2);
	}
	
	// #4
	@Test
	public void diagonalDistIsThree() {
		TriangleCoordinate a = new TriangleCoordinate(1,1);
		TriangleCoordinate b = new TriangleCoordinate(2,3);
		
		assertEquals(a.DistanceTo(b), 3);
	}
	
	
	// #5
	@Test
	public void diagonalDistIsThree2() {
		TriangleCoordinate a = new TriangleCoordinate(1,2);
		TriangleCoordinate b = new TriangleCoordinate(2,2);
		
		assertEquals(a.DistanceTo(b), 3);
	}
	
	// #6
	@Test
	public void diagonalDistIsThree3() {
		TriangleCoordinate a = new TriangleCoordinate(2,2);
		TriangleCoordinate b = new TriangleCoordinate(1,2);
		 
		assertEquals(a.DistanceTo(b), 3);
	}
	 
	
	// #7 - (this one and the diagonal one are kinda just for completeness.)
	@Test
	public void nothingIsOrthogonal() {
		TriangleCoordinate a = new TriangleCoordinate(2,2);
		TriangleCoordinate b = new TriangleCoordinate(1,2);
		
		assertFalse(a.orthogonal(b));
	}
	
	// #8
	@Test
	public void nothingIsDiagonal() {
		TriangleCoordinate a = new TriangleCoordinate(2,2);
		TriangleCoordinate b = new TriangleCoordinate(1,2);
		
		assertFalse(a.diagonal(b));
	}
	
	// #9
	@Test
	public void cornersAreNotLinear() {
		TriangleCoordinate a = new TriangleCoordinate(3,4);
		TriangleCoordinate b = new TriangleCoordinate(4,4);
		
		assertFalse(a.linear(b));
	}
	
	// #10
	@Test
	public void horizontalIsLinear() {
		TriangleCoordinate a = new TriangleCoordinate(3,4);
		TriangleCoordinate b = new TriangleCoordinate(3,8);
		
		assertTrue(a.linear(b));
	}
	
	// #11
	@Test
	public void diagonalUpRightIsLinear() {
		TriangleCoordinate a = new TriangleCoordinate(2,1);
		TriangleCoordinate b = new TriangleCoordinate(3,3);
		
		assertTrue(a.linear(b));
	}
	
	// I initially thought that this was linear but upon examining the charts it is not
//	// #12
//	@Test
//	public void bottomSideIsLinear() {
//		TriangleCoordinate a = new TriangleCoordinate(3,4);
//		TriangleCoordinate b = new TriangleCoordinate(2,4);
//		
//		assertTrue(a.linear(b));
//	}
 
	// #13
	@Test
	public void thingsAreAdjacent() {
		TriangleCoordinate a = new TriangleCoordinate(2,5);
		InternalCoordinate[] adj = a.getAdjacent();
		
		assertEquals(0, adj[0].DistanceTo(new TriangleCoordinate(2,4)));
		assertEquals(0, adj[1].DistanceTo(new TriangleCoordinate(2,6)));
		assertEquals(0, adj[2].DistanceTo(new TriangleCoordinate(1,5)));
	}
	
	// #14
	@Test
	public void thingsAreAdjacent2() {
		TriangleCoordinate a = new TriangleCoordinate(1,5);
		InternalCoordinate[] adj = a.getAdjacent();
		
		assertEquals(0, adj[0].DistanceTo(new TriangleCoordinate(1,4)));
		assertEquals(0, adj[1].DistanceTo(new TriangleCoordinate(1,6)));
		assertEquals(0, adj[2].DistanceTo(new TriangleCoordinate(2,5)));
	}
	
	// #15
	@Test
	public void thingsAreAdjacent3() {
		TriangleCoordinate a = new TriangleCoordinate(3,7);
		InternalCoordinate[] adj = a.getAdjacent();
		
		assertEquals(0, adj[0].DistanceTo(new TriangleCoordinate(3,6)));
		assertEquals(0, adj[1].DistanceTo(new TriangleCoordinate(3,8)));
		assertEquals(0, adj[2].DistanceTo(new TriangleCoordinate(4,7)));
	}
	
	// #16
	@Test
	public void upIsNotLinear() {
		TriangleCoordinate a = new TriangleCoordinate(1,4);
		TriangleCoordinate b = new TriangleCoordinate(2,4);
		
		assertFalse(a.linear(b));
	}
	
	// #17
	@Test
	public void downIsNotLinear() {
		TriangleCoordinate a = new TriangleCoordinate(2,4);
		TriangleCoordinate b = new TriangleCoordinate(1,4);
		
		assertFalse(a.linear(b));
	}
	
	// #18
	@Test
	public void straightJumpIsOkay() {
		TriangleCoordinate a = new TriangleCoordinate(2,5);
		TriangleCoordinate b = new TriangleCoordinate(2,6);
		TriangleCoordinate c = new TriangleCoordinate(3,6);
		
		assertTrue(a.jump(b,c));
	}
	
	// #19
	@Test
	public void longJumpIsBad() {
		TriangleCoordinate a = new TriangleCoordinate(2,5);
		TriangleCoordinate b = new TriangleCoordinate(3,6);
		TriangleCoordinate c = new TriangleCoordinate(4,7);
		
		assertFalse(a.jump(b,c));
	}
	
	// #20
	@Test
	public void diagonalUpLeftIsLinear() {
		TriangleCoordinate a = new TriangleCoordinate(2,5);
		TriangleCoordinate b = new TriangleCoordinate(3,4);
		
		assertTrue(a.linear(b));
	}
	
	// #21
	@Test
	public void diagonalDownRightIsLinear() {
		TriangleCoordinate a = new TriangleCoordinate(3,3);
		TriangleCoordinate b = new TriangleCoordinate(2,5);
		
		assertTrue(a.linear(b));
	}
	
	// #22
	@Test
	public void diagonalDownLeftIsLinear() {
		TriangleCoordinate a = new TriangleCoordinate(3,5);
		TriangleCoordinate b = new TriangleCoordinate(2,4);
		
		assertTrue(a.linear(b));
	}
}
