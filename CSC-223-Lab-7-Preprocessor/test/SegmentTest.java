import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.Point;

class SegmentTest {

	Point p1 = new Point(0 ,2);
	Point p2 = new Point(2 ,0);
	Point p3 = new Point(2 ,4);
	Point p4 = new Point(4 ,2);
	Point p5 = new Point(5 ,1);
	Point p6 = new Point(7, 2);
	Point p7 = new Point(9, 2);
	Point p8 = new Point(7, 0);
	Point p9 = new Point(10 ,9);
	Point p10 = new Point(10 ,7);
	Point p11 = new Point(7 ,5);
	Point p12 = new Point(8, 7);
	Point p13 = new Point(2 ,10);
	Point p14 = new Point(2 ,5);
	Point p15 = new Point(2,6);
	
	
	Segment _a = new Segment(p2 , p3);
	Segment _b = new Segment(p1 , p7);
	Segment _c = new Segment(p4 , p6);
	Segment _d = new Segment(p8 , p9);
	Segment _e = new Segment(p5 , p11);
	Segment _f = new Segment(p11 , p12);
	Segment _g = new Segment(p10 , p13);
	Segment _h = new Segment(p11 , p13);
	Segment _i = new Segment(p14 , p15);
	Segment _j = new Segment(p7 , p1);
	
	
	@Test
	void testGetPoint1() {
		
		assertEquals( p2 , _a.getPoint1());
		assertEquals( p1 , _b.getPoint1());
		assertEquals( p4 , _c.getPoint1());
		assertEquals( p8 , _d.getPoint1());
		assertEquals( p5 , _e.getPoint1());
		assertEquals( p11 , _f.getPoint1());
		
	}
	
	@Test
	void testGetPoint2() {
		fail("Not yet implemented");
	}
	
	
	@Test
	void testLength() {
		fail("Not yet implemented");
	}
	
	
	
	@Test
	void testSlope() {
		fail("Not yet implemented");
	}
	
	
	
	@Test
	void testSegmentIntersection() {
		fail("Not yet implemented");
	}
	

	
	@Test
	void testPointLiesOn() {
		fail("Not yet implemented");
	}

	
	@Test
	void testPointLiesOnSegment() {
		fail("Not yet implemented");
	}
	
	
	@Test
	void testPointLiesBetweenEndpoints() {
		fail("Not yet implemented");
	}
	
	@Test
	void testHasSubSegment() {
		fail("Not yet implemented");
	}
	
	
	
	@Test
	void testSharedVertex() {
		fail("Not yet implemented");
	}
	
	@Test
	void testEquals() {
		fail("Not yet implemented");
	}
	
	
	@Test
	void testIsCollinearWith() {
		fail("Not yet implemented");
	}
	@Test
	void testHas() {
		fail("Not yet implemented");
	}
	
	
	@Test
	void testIsHorizontal() {
		fail("Not yet implemented");
	}
	
	@Test
	void testIsVertical() {
		fail("Not yet implemented");
	}
	
	

	@Test
	void testOther() {
		fail("Not yet implemented");
	}
	
	@Test
	void testHashCode() {
		fail("Not yet implemented");
	}
	
	
	@Test
	void testCoincideWtihoutOverlap() {
		fail("Not yet implemented");
	}

	@Test
	void testCollectOrderedPointsOnSegment() {
		fail("Not yet implemented");
	}
	

	
}
