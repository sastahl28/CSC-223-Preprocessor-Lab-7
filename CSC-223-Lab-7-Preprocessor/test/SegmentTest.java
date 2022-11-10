import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.Point;

class SegmentTest {

	//create the points in the segments
	Point _p1 = new Point(0 ,2);
	Point _p2 = new Point(2 ,0);
	Point _p3 = new Point(2 ,4);
	Point _p4 = new Point(4 ,2);
	Point _p5 = new Point(5 ,1);
	Point _p6 = new Point(7, 2);
	Point _p7 = new Point(9, 2);
	Point _p8 = new Point(7, 0);
	Point _p9 = new Point(10 ,9);
	Point _p10 = new Point(10 ,7);
	Point _p11 = new Point(7 ,5);
	Point _p12 = new Point(8, 7);
	Point _p13 = new Point(2 ,10);
	Point _p14 = new Point(2 ,5);
	Point _p15 = new Point(2,6);
	Point _p16 = new Point(4 ,4);

	//create the set of segments in the figure
	Segment _a = new Segment(_p2 , _p3);
	Segment _b = new Segment(_p1 , _p7);
	Segment _c = new Segment(_p4 , _p6);
	Segment _d = new Segment(_p8 , _p9);
	Segment _e = new Segment(_p5 , _p11);
	Segment _f = new Segment(_p11 , _p12);
	Segment _g = new Segment(_p10 , _p13);
	Segment _h = new Segment(_p11 , _p13);
	Segment _i = new Segment(_p14 , _p15);
	Segment _j = new Segment(_p7 , _p1);
	Segment _k = new Segment(_p1 ,_p1);
	Segment _l = new Segment(_p4 , _p16);
	Segment _m = new Segment(_p6 , _p7);


	//used for comparing methods
	public static final double POSITIVE_INFINITY = 1.0 / 0.0;


	@Test
	void testGetPoint1() {

		//get the first point in various segments
		assertEquals( _p2 , _a.getPoint1());
		assertEquals( _p1 , _b.getPoint1());
		assertEquals( _p4 , _c.getPoint1());
		assertEquals( _p8 , _d.getPoint1());
		assertEquals( _p5 , _e.getPoint1());
		assertEquals( _p11 , _f.getPoint1());

	}

	@Test
	void testGetPoint2() {

		//get the second point in various segments
		assertEquals( _p3 , _a.getPoint2());
		assertEquals( _p7 , _b.getPoint2());
		assertEquals( _p6 , _c.getPoint2());
		assertEquals( _p9 , _d.getPoint2());
		assertEquals( _p11 , _e.getPoint2());
		assertEquals( _p12 , _f.getPoint2());
	}


	@Test
	void testLength() {

		//check the length of various segments 
		assertEquals(4.0 , _a.length());
		assertEquals(9.0 , _b.length());
		assertEquals(3.0 , _c.length());
		assertEquals(9.486832980505138 , _d.length());
		assertEquals(4.47213595499958 , _e.length());
		assertEquals(2.23606797749979, _f.length());
		assertEquals(8.54400374531753 , _g.length());
		assertEquals(1.0 , _i.length());

		//check the length of a segment where the endpoints are the same
		assertEquals( 0.0 , _k.length());

		//check two segments that are the same length
		assertEquals(_b.length() , _j.length());

	}



	@Test
	void testSlope() {

		//test on segments that are horizontal
		assertEquals( -0.0 , _b.slope());
		assertEquals(-0.0 , _c.slope());

		//test on segments that are vertical
		assertEquals( POSITIVE_INFINITY , _a.slope());

		//test on other segments that are neither
		assertEquals(-0.375 , _g.slope());
		assertEquals(3.0 , _d.slope());
		assertEquals(2.0 , _f.slope());
		assertEquals(-1.0 , _h.slope());
		assertEquals(2.0 , _e.slope());


		//compare slopes of horizontal segments
		assertTrue( _j.slope() ==_b.slope());

		//compare slopes of vertical segments
		assertEquals( _i.slope() , _a.slope());

		//test one a segment that is a point
		assertEquals(POSITIVE_INFINITY , _k.slope());

	}



	@Test
	void testSegmentIntersection() {

		//create the points of intersection to compare to
		Point point1 = new Point(2 ,2);
		Point point2 = new Point(5.5 ,2);
		Point point3 = new Point(9.1432857142857143 , 6.428571428571429);

		//find intersection of two segments
		assertEquals(point1 ,_a.segmentIntersection(_b));
		assertEquals(point2 , _e.segmentIntersection(_b));
		assertEquals(point2 , _e.segmentIntersection(_c));
		//question about this one!
		//assertEquals( point3,_e.segmentIntersection(_g));

		//test in segments where the endpoint falls on segment
		assertEquals(null , _b.segmentIntersection(_l));
		assertEquals(null , _e.segmentIntersection(_f));

		//test on segments that do no intersect at all
		assertEquals(null , _b.segmentIntersection(_i));
		assertEquals(null , _e.segmentIntersection(_g));
		assertEquals(null , _h.segmentIntersection(_d));
		assertEquals(null , _k.segmentIntersection(_a));

		//test on segments that share an endpoint
		assertEquals(null , _m.segmentIntersection(_c));
		assertEquals(null , _g.segmentIntersection(_f));

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

		//verify that elements are not intersecting 
		assertEquals(null , _b.segmentIntersection(_i));
		assertEquals(null , _e.segmentIntersection(_g));
		assertEquals(null , _h.segmentIntersection(_d));

		//test on elements that do not overlap
		assertFalse(_b.HasSubSegment(_i));
		assertFalse( _e.HasSubSegment(_g));
		assertFalse( _h.HasSubSegment(_d));

		//test with segments that are contained within the segment
		assertTrue(_b.HasSubSegment(_c));
		//test on segment that shares endpoint and contained
		assertTrue(_b.HasSubSegment(_m));

		//test on segments that are tangent to each other 
		Point point1 = new Point(2 ,2);
		Point point2 = new Point(5.5 ,2);
		//verify that points intersect
		assertEquals(point1 ,_a.segmentIntersection(_b));
		assertEquals(point2 , _e.segmentIntersection(_b));
		assertEquals(point2 , _e.segmentIntersection(_c));

		assertFalse(_a.HasSubSegment(_b));
		assertFalse(_e.HasSubSegment(_b));
		assertFalse(_e.HasSubSegment(_c));
	}



	@Test
	void testSharedVertex() {
		//test on points that do not intersect
		assertEquals(null , _l.sharedVertex(_a));
		assertEquals(null , _g.sharedVertex(_e));

		//test on points that overlap more than one point
		assertEquals(null , _b.sharedVertex(_c));

		//test on points that intersect but not at vertex
		Point point1 = new Point(2 ,2);
		Point point2 = new Point(5.5 ,2);
		//verify that points intersect
		assertEquals(point1 ,_a.segmentIntersection(_b));
		assertEquals(point2 , _e.segmentIntersection(_b));
		assertEquals(point2 , _e.segmentIntersection(_c));


		assertEquals(null , _a.sharedVertex(_b));
		assertEquals(null ,_e.sharedVertex(_b));
		assertEquals(null ,_e.sharedVertex(_c));

		//test on points with shared vertex
		assertEquals(_p11 , _e.sharedVertex(_f));
		assertEquals(_p6 , _m.sharedVertex(_c));
		assertEquals(_p7 , _b.sharedVertex(_m));

	}

	@Test
	void testEquals() {
		fail("Not yet implemented");

		//test on two very different segments




		//test on segments that intersect


		//test on segments that overlap


		//test on segments that are the same with name


		//test on segment that have inverse points

	}


	@Test
	void testIsCollinearWith() {
		fail("Not yet implemented");
	}

	@Test
	void testHas() {
		fail("Not yet implemented");
		//test with a point not on segment
		assertFalse(_b.has(_p9));
		assertFalse(_b.has(_p10));
		assertFalse(_b.has(_p11));
		assertFalse(_b.has(_p5));
		assertFalse(_b.has(_p14));


		//test with point on segment but not endpoint

		//test with the endpoint



		//do the same with another segment and endpoint
	}


	@Test
	void testIsHorizontal() {


		//test on segments that are horizontal 
		assertTrue(_b.isHorizontal());
		assertTrue(_c.isHorizontal());
		assertTrue(_m.isHorizontal());

		//continue to test on segments that are vertical 
		assertFalse(_a.isHorizontal());
		assertFalse(_d.isHorizontal());
		assertFalse(_i.isHorizontal());
		
		//test on other segments
		assertFalse(_e.isHorizontal());
		assertFalse(_f.isHorizontal());
		assertFalse(_g.isHorizontal());

		//test on another horizontal segment
		

	}

	@Test
	void testIsVertical() {
		
		
		//test on segments that are horizontal
		assertTrue(_a.isVertical());
		assertTrue(_l.isVertical());
		assertTrue(_i.isVertical());

		//test on segments that are horizontal 
		assertFalse(_b.isVertical());
		assertFalse(_c.isVertical());
		assertFalse(_m.isVertical());

		//continute to test on segments 
		assertFalse(_e.isVertical());
		assertFalse(_f.isVertical());
		assertFalse(_g.isVertical());
		
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
