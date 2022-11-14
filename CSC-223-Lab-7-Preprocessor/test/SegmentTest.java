import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.Point;

class SegmentTest {

	//create the points in the figure
	Point _p1 = new Point(0 ,2);
	Point _p2 = new Point(2 ,0);
	Point _p3 = new Point(2 ,4);
	Point _p4 = new Point(4 ,2);
	Point _p5 = new Point(5 ,1);
	Point _p6 = new Point(7, 2);
	Point _p7 = new Point(9, 2);
	Point _p8 = new Point(7, 0);
	Point _p9 = new Point(10 ,9);
	Point _p10 = new Point(10 ,6);
	Point _p11 = new Point(7 ,5);
	Point _p12 = new Point(8, 7);
	Point _p13 = new Point(2 ,10);
	Point _p14 = new Point(2 ,5);
	Point _p15 = new Point(2,7);
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
		assertEquals(8.94427190999916 , _g.length());
		assertEquals(2.0 , _i.length());

		//check the length of a segment where the endpoints are the same
		assertEquals( 0.0 , _k.length());

		//check two segments that are the same length
		assertEquals(_b.length() , _j.length());
		assertEquals(_i.length(), _l.length());

	}



	@Test
	void testSlope() {

		//test on segments that are horizontal
		assertEquals( -0.0 , _b.slope());
		assertEquals(-0.0 , _c.slope());

		//test on segments that are vertical
		assertEquals( POSITIVE_INFINITY , _a.slope());

		//test on other segments that are neither
		assertEquals(-0.5 , _g.slope());
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

		//find intersection of two segments
		assertEquals(point1 ,_a.segmentIntersection(_b));
		assertEquals(point2 , _e.segmentIntersection(_b));
		assertEquals(point2 , _e.segmentIntersection(_c));


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

		//test segments and lines that fall on segment
		assertTrue(_b.pointLiesOn(_p1));
		assertTrue(_b.pointLiesOn(_p4));
		assertTrue(_b.pointLiesOn(_p6));
		assertTrue(_b.pointLiesOn(_p7));

		//test points not on the segment
		assertFalse(_b.pointLiesOn(_p8));
		assertFalse(_b.pointLiesOn(_p10));
		assertFalse(_b.pointLiesOn(_p9));
		assertFalse(_b.pointLiesOn(_p14));

		//test points on a segment
		assertTrue(_g.pointLiesOn(_p10));
		assertTrue(_g.pointLiesOn(_p12));
		assertTrue(_g.pointLiesOn(_p13));

		//test points not on the segment
		assertFalse(_g.pointLiesOn(_p8));
		assertFalse(_g.pointLiesOn(_p15));
		assertFalse(_g.pointLiesOn(_p9));
		assertFalse(_g.pointLiesOn(_p14));

	}


	@Test
	void testPointLiesOnSegment() {
		//test segments and lines that fall on segment
		assertTrue(_b.pointLiesOnSegment(_p1));
		assertTrue(_b.pointLiesOnSegment(_p4));
		assertTrue(_b.pointLiesOnSegment(_p6));
		assertTrue(_b.pointLiesOnSegment(_p7));

		//test points not on the segment
		assertFalse(_b.pointLiesOnSegment(_p8));
		assertFalse(_b.pointLiesOnSegment(_p10));
		assertFalse(_b.pointLiesOnSegment(_p9));
		assertFalse(_b.pointLiesOnSegment(_p14));

		//test points on a segment
		assertTrue(_g.pointLiesOnSegment(_p10));
		assertTrue(_g.pointLiesOnSegment(_p12));
		assertTrue(_g.pointLiesOnSegment(_p13));

		//test points not on the segment
		assertFalse(_g.pointLiesOnSegment(_p8));
		assertFalse(_g.pointLiesOnSegment(_p15));
		assertFalse(_g.pointLiesOnSegment(_p9));
		assertFalse(_g.pointLiesOnSegment(_p14));
	}


	@Test
	void testPointLiesBetweenEndpoints() {
		
		//test endpoints of a segment
		assertFalse(_b.pointLiesBetweenEndpoints(_p1));
		assertFalse(_b.pointLiesBetweenEndpoints(_p7));
		
		//test on points on the segment
		assertTrue(_b.pointLiesBetweenEndpoints(_p4));
		assertTrue(_b.pointLiesBetweenEndpoints(_p6));
		

		//test points not on the segment
		assertFalse(_b.pointLiesBetweenEndpoints(_p8));
		assertFalse(_b.pointLiesBetweenEndpoints(_p10));
		assertFalse(_b.pointLiesBetweenEndpoints(_p9));
		assertFalse(_b.pointLiesBetweenEndpoints(_p14));

		//test the endpoints of a segment
		assertFalse(_g.pointLiesBetweenEndpoints(_p10));
		assertFalse(_g.pointLiesBetweenEndpoints(_p13));
		
		//test with points on the segment
		assertTrue(_g.pointLiesBetweenEndpoints(_p12));

		//test points not on the segment
		assertFalse(_g.pointLiesBetweenEndpoints(_p8));
		assertFalse(_g.pointLiesBetweenEndpoints(_p15));
		assertFalse(_g.pointLiesBetweenEndpoints(_p9));
		assertFalse(_g.pointLiesBetweenEndpoints(_p14));
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

		//test on two very different segments
		assertFalse(_a.equals(_g));
		assertFalse(_e.equals(_m));
		assertFalse(_k.equals(_a));
		assertFalse(_m.equals(_c));

		//test on segments that intersect
		
		assertFalse(_b.equals(_a));
		assertFalse(_b.equals(_l));
		assertFalse(_e.equals(_b));


		//test on segments that overlap
		assertFalse(_b.equals(_c));
		assertFalse(_m.equals(_b));
		assertFalse(_c.equals(_m));

		//test on segments that are the same with name
		assertTrue(_b.equals(_j));

		//test on segment that have inverse points
		assertFalse(_i.equals(_l));

	}


	@Test
	void testIsCollinearWith() {

		//test on two segments that do not overlap
		assertFalse(_a.isCollinearWith(_l));
		assertFalse(_i.isCollinearWith(_d));

		//test on segments that coincide without overlap
		assertTrue(_c.isCollinearWith(_m));
		assertTrue(_e.isCollinearWith(_f));

		//test on segment that intersect
		assertFalse(_d.isCollinearWith(_g));
		assertFalse(_c.isCollinearWith(_e));
		assertFalse(_b.isCollinearWith(_e));
		assertFalse(_a.isCollinearWith(_b));

		//test on segment that has one endpoint on segment
		assertFalse(_f.isCollinearWith(_g));
		assertFalse(_b.isCollinearWith(_l));

		//test on segments that coincide but with overlap
		assertTrue(_c.isCollinearWith(_b));
		assertTrue(_m.isCollinearWith(_b));


	}

	@Test
	void testHas() {

		//test with a point not on segment
		assertFalse(_b.has(_p9));
		assertFalse(_b.has(_p10));
		assertFalse(_b.has(_p11));
		assertFalse(_b.has(_p5));
		assertFalse(_b.has(_p14));


		//test with point on segment but not endpoint
		assertTrue(_b.pointLiesOnSegment(_p4));
		assertTrue(_b.pointLiesOnSegment(_p6));

		//test with the endpoint
		assertTrue(_b.pointLiesOnSegment(_p1));
		assertTrue(_b.pointLiesOnSegment(_p7));


		//test with a point not on segment
		assertFalse(_g.has(_p9));
		assertFalse(_g.has(_p1));
		assertFalse(_g.has(_p11));
		assertFalse(_g.has(_p5));
		assertFalse(_g.has(_p14));

		//do the same with another segment and endpoint
		assertTrue(_g.has(_p10));
		assertTrue(_g.has(_p13));
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

		//continue to test on segments 
		assertFalse(_e.isVertical());
		assertFalse(_f.isVertical());
		assertFalse(_g.isVertical());

	}



	@Test
	void testOther() {

		//test with both x and y values being given
		assertEquals( _p3 , _a.other(_p2));
		assertEquals( _p2 , _a.other(_p3));
		assertEquals( _p1 , _b.other(_p7));
		assertEquals( _p7 , _b.other(_p1));
		assertEquals( _p6 , _c.other(_p4));
		assertEquals( _p9 , _d.other(_p8));
		assertEquals( _p11 , _e.other(_p5));
		assertEquals( _p12 , _f.other(_p11));
		assertEquals( _p13 , _g.other(_p10));
		assertEquals( _p11 , _h.other(_p13));
		assertEquals( _p15 , _i.other(_p14));
		assertEquals( _p7 , _j.other(_p1));


		//test on segments that share an endpoint
		assertEquals( _e.other(_p5) , _f.other(_p12));
		assertEquals( _e.other(_p5) , _h.other(_p13));
		assertEquals( _g.other(_p10) , _h.other(_p11));

		//test with an endpoint not on the line
		assertEquals( null , _d.other(_p1));
		assertEquals( null , _h.other(_p1));
		assertEquals( null , _m.other(_p1));

	}


	@Test
	void testHashCode() {

		//test on the same segment
		assertTrue(_b.hashCode()==_j.hashCode());
		assertEquals(_b.hashCode(), _j.hashCode());


		//test on the same length segment
		assertFalse(_l.hashCode()==_m.hashCode());
		assertFalse(_f.hashCode()==_i.hashCode());


		//segments that are the same length and same slope
		assertTrue(_i.hashCode()==_l.hashCode());
		assertEquals(_i.hashCode(), _l.hashCode());


		//test on two very different segments
		assertFalse(_g.hashCode()==_e.hashCode());
		assertFalse(_a.hashCode()==_g.hashCode());
		assertFalse(_e.hashCode()==_m.hashCode());
		assertFalse(_k.hashCode()==_e.hashCode());
		assertFalse(_c.hashCode()==_l.hashCode());


	}


	@Test
	void testCoincideWtihoutOverlap() {

		//test on two segments that do not overlap
		assertFalse(_a.coincideWithoutOverlap(_l));
		assertFalse(_i.coincideWithoutOverlap(_d));

		//test on segments that coincide without overlap
		assertTrue(_c.coincideWithoutOverlap(_m));
		assertTrue(_e.coincideWithoutOverlap(_f));

		//test on segment that intersect
		assertFalse(_d.coincideWithoutOverlap(_g));
		assertFalse(_c.coincideWithoutOverlap(_e));
		assertFalse(_b.coincideWithoutOverlap(_e));
		assertFalse(_a.coincideWithoutOverlap(_b));

		//test on segment that has one endpoint on segment
		assertFalse(_f.coincideWithoutOverlap(_g));
		assertFalse(_b.coincideWithoutOverlap(_l));

		assertFalse(_b.coincideWithoutOverlap(_c));
		assertFalse(_b.coincideWithoutOverlap(_m));


	}

	@Test
	void testCollectOrderedPointsOnSegment() {

		//create a set of points
		Set<Point> allPoints = new TreeSet<Point>();

		//add points to the set
		allPoints.add(_p1);
		allPoints.add(_p2);
		allPoints.add(_p3);
		allPoints.add(_p4);
		allPoints.add(_p5);
		allPoints.add(_p6);
		allPoints.add(_p7);
		allPoints.add(_p8);
		allPoints.add(_p9);
		allPoints.add(_p10);

		//create the set of expected points
		SortedSet<Point> expected = new TreeSet<Point>();

		//add the expected points to the expected set
		expected.add(_p1);
		expected.add(_p6);
		expected.add(_p4);
		expected.add(_p7);

		//ensure expected is correct
		assertEquals(expected , _b.collectOrderedPointsOnSegment(allPoints));

		assertTrue(expected.equals(_b.collectOrderedPointsOnSegment(allPoints)));

		//add unexpected elements to the expected set
		expected.add(_p10);

		//test the outcome
		_b.collectOrderedPointsOnSegment(allPoints);

		assertFalse(expected.equals(_b.collectOrderedPointsOnSegment(allPoints)));

		//clear set of expected and test on another segment
		expected.clear();

		//add more points to all points
		allPoints.add(_p11);
		allPoints.add(_p12);
		allPoints.add(_p13);


		//add expected elements to the set of expected elements
		expected.add(_p10);
		expected.add(_p12);
		expected.add(_p13);

		//test that the outcome is equal
		assertEquals(expected , _g.collectOrderedPointsOnSegment(allPoints));

		assertTrue(expected.equals(_g.collectOrderedPointsOnSegment(allPoints)));


	}


}
