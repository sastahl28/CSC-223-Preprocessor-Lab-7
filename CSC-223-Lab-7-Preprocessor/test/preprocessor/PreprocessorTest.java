package preprocessor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import input.InputFacade;
import input.components.FigureNode;
import preprocessor.delegates.ImplicitPointPreprocessor;

class PreprocessorTest
{
	
	
	@Test
	void lineTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/lineseg.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		Preprocessor pp = new Preprocessor(database, segSet);
		
		Set<Segment> implicitBaseSegments = new LinkedHashSet<Segment>();
		
		/**
		 * 
		 * COMPUTE IMPLICIT BASE SEGMENTS
		 */
		
		implicitBaseSegments = pp.computeImplicitBaseSegments(impPoint);
		
		assertEquals(0,implicitBaseSegments.size());
		
		/***
		 * 
		 * 
		 * COMPUTE MINIMAL SEGMENTS
		 */
		
		Set<Segment> minimalSeg = pp.identifyAllMinimalSegments(impPoint, segSet, implicitBaseSegments);
		
		assertEquals(1,minimalSeg.size());
		
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,1);
		
		Segment seg = new Segment(p1,p2);
		
		assertTrue(minimalSeg.contains(seg));
		
		/**
		 * ALL NONMINIMAL SEGMENTS
		 */
		
		Set<Segment> nonMinimal = pp.constructAllNonMinimalSegments(minimalSeg);
		assertEquals(0,nonMinimal.size());
		
	}
	
	
	
	@Test
	void SingleTriangleTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/single_triangle.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		Preprocessor pp = new Preprocessor(database, segSet);
		
		Set<Segment> implicitBaseSegments = new LinkedHashSet<Segment>();
		
		/**
		 * IMPLICITE BASE SEGMENTS
		 */
		
		implicitBaseSegments = pp.computeImplicitBaseSegments(impPoint);
		
		assertEquals(0,implicitBaseSegments.size());
		
		 /**
		  * COMPUTE MINIMAL
		  */
		
		Set<Segment> minimalSeg = pp.identifyAllMinimalSegments(impPoint, segSet, implicitBaseSegments);
		
		assertEquals(3,minimalSeg.size());
		
		Point p1 = new Point(0,0);
		Point p2 = new Point(1,1);
		Point p3 = new Point(1,0);
		
		Segment seg1 = new Segment(p1,p2);
		Segment seg2 = new Segment(p2,p3);
		Segment seg3 = new Segment(p1,p3);
		
		assertTrue(minimalSeg.contains(seg1));
		assertTrue(minimalSeg.contains(seg2));
		assertTrue(minimalSeg.contains(seg3));
		
		/**
		 * NON MINIMAL
		 */
		
		Set<Segment> nonMinimal = pp.constructAllNonMinimalSegments(minimalSeg);
		assertEquals(0,nonMinimal.size());
		
	}
	
	@Test
	void CollinearTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/collinear_line_segments.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		Preprocessor pp = new Preprocessor(database, segSet);
		
		Set<Segment> implicitBaseSegments = new LinkedHashSet<Segment>();
		
		/**
		 * COMPUTE IMPLICIT
		 */
		
		implicitBaseSegments = pp.computeImplicitBaseSegments(impPoint);
		
		assertEquals(0,implicitBaseSegments.size());
		
		/**
		 * 
		 * COMPUTE MINIMAL
		 */
		
		Set<Segment> minimalSeg = pp.identifyAllMinimalSegments(impPoint, segSet, implicitBaseSegments);
		
		assertEquals(5,minimalSeg.size());
		
		Point p1 = new Point(0,0);
		Point p2 = new Point(4,0);
		Point p3 = new Point(9,0);
		Point p4 = new Point(11,0);
		Point p5 = new Point(16,0);
		Point p6 = new Point(26,0);
		
		Segment seg1 = new Segment(p1,p2);
		Segment seg2 = new Segment(p2,p3);
		Segment seg3 = new Segment(p3,p4);
		Segment seg4 = new Segment(p4,p5);
		Segment seg5 = new Segment(p5,p6);
		
		assertTrue(minimalSeg.contains(seg1));
		assertTrue(minimalSeg.contains(seg2));
		assertTrue(minimalSeg.contains(seg3));
		assertTrue(minimalSeg.contains(seg4));
		assertTrue(minimalSeg.contains(seg5));
		
		/**
		 * NONMINIMAL
		 * 
		 */
		
		Set<Segment> nonMinimal = pp.constructAllNonMinimalSegments(minimalSeg);
		assertEquals(10,nonMinimal.size());
		
		Segment s1 = new Segment(p1,p3);
		Segment s2 = new Segment(p1,p4);
		Segment s3 = new Segment(p1,p5);
		Segment s4 = new Segment(p1,p6);
		Segment s5 = new Segment(p2,p4);
		Segment s6 = new Segment(p2,p5);
		Segment s7 = new Segment(p2,p6);
		Segment s8 = new Segment(p3,p5);
		Segment s9 = new Segment(p3,p6);
		Segment s10 = new Segment(p4,p6);
		
		assertTrue(nonMinimal.contains(s1));
		assertTrue(nonMinimal.contains(s2));
		assertTrue(nonMinimal.contains(s3));
		assertTrue(nonMinimal.contains(s4));
		assertTrue(nonMinimal.contains(s5));
		assertTrue(nonMinimal.contains(s6));
		assertTrue(nonMinimal.contains(s7));
		assertTrue(nonMinimal.contains(s8));
		assertTrue(nonMinimal.contains(s9));
		assertTrue(nonMinimal.contains(s10));
		
	}
	
	@Test
	void CrossingSymmetricTriangleTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/crossing_symmetric_triangle.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		Preprocessor pp = new Preprocessor(database, segSet);
		
		Set<Segment> implicitBaseSegments = new LinkedHashSet<Segment>();
		
		implicitBaseSegments = pp.computeImplicitBaseSegments(impPoint);
		
		assertEquals(4,implicitBaseSegments.size());
		Point IMP1 = new Point(3,3);
		Point p1 = new Point(2,4);
		Point p2 = new Point(4,4);
		Point p3 = new Point(0,0);
		Point p4 = new Point(6,0);
		
		Segment seg1 = new Segment(IMP1, p1);
		Segment seg2 = new Segment(IMP1,p2);
		Segment seg3 = new Segment(IMP1, p3);
		Segment seg4 = new Segment(IMP1, p4);
		
		assertTrue(implicitBaseSegments.contains(seg1));
		assertTrue(implicitBaseSegments.contains(seg2));
		assertTrue(implicitBaseSegments.contains(seg3));
		assertTrue(implicitBaseSegments.contains(seg4));
		
		/**
		 * COMPUTE MINIMAL
		 */
		
		Set<Segment> minimalSeg = pp.identifyAllMinimalSegments(impPoint, segSet, implicitBaseSegments);
		
		assertEquals(10,minimalSeg.size());
		
		Point pB = new Point(2,4);
		Point pC = new Point(4,4);
		Point pD = new Point(0,0);
		Point pE = new Point(6,0);
		Point pA = new Point(3,6);
		Point pX = new Point(3,3);
		
		Segment s1 = new Segment(pA,pB);
		Segment s2 = new Segment(pA,pC);
		Segment s3 = new Segment(pB,pD);
		Segment s4 = new Segment(pB,pC);
		Segment s5 = new Segment(pB,pX);
		Segment s6 = new Segment(pC,pX);
		Segment s7 = new Segment(pC,pE);
		Segment s8 = new Segment(pD,pX);
		Segment s9 = new Segment(pD,pE);
		Segment s10 = new Segment(pE,pX);
		
		assertTrue(minimalSeg.contains(s1));
		assertTrue(minimalSeg.contains(s2));
		assertTrue(minimalSeg.contains(s3));
		assertTrue(minimalSeg.contains(s4));
		assertTrue(minimalSeg.contains(s5));
		assertTrue(minimalSeg.contains(s6));
		assertTrue(minimalSeg.contains(s7));
		assertTrue(minimalSeg.contains(s8));
		assertTrue(minimalSeg.contains(s9));
		assertTrue(minimalSeg.contains(s10));
		
		/**
		 * COMPUTE NONMINIMAL
		 */
		Set<Segment> nonMinimal = pp.constructAllNonMinimalSegments(minimalSeg);
		assertEquals(4,nonMinimal.size());
		
		Segment b1 = new Segment(pA, pD);
		Segment b2 = new Segment(pA, pE);
		Segment b3 = new Segment(pB, pE);
		Segment b4 = new Segment(pC, pD);
		
		assertTrue(nonMinimal.contains(b1));
		assertTrue(nonMinimal.contains(b2));
		assertTrue(nonMinimal.contains(b3));
		assertTrue(nonMinimal.contains(b4));
		
	}
	
	@Test
	void Tri_QuadTestComputeImplicitBase() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/Tri_Quad_With_Implied.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		Preprocessor pp = new Preprocessor(database, segSet);
		
		Set<Segment> implicitBaseSegments = new LinkedHashSet<Segment>();
		
		implicitBaseSegments = pp.computeImplicitBaseSegments(impPoint);
		
		assertEquals(4,implicitBaseSegments.size());
		Point IMP1 = new Point(6,8.5);
		Point pG = new Point(4,12);
		Point pH = new Point(8,12);
		Point pC = new Point(4,5);
		Point pD = new Point(8,5);
		
		Segment seg1 = new Segment(IMP1, pG);
		Segment seg2 = new Segment(IMP1, pH);
		Segment seg3 = new Segment(IMP1, pC);
		Segment seg4 = new Segment(IMP1, pD);
		
		assertTrue(implicitBaseSegments.contains(seg1));
		assertTrue(implicitBaseSegments.contains(seg2));
		assertTrue(implicitBaseSegments.contains(seg3));
		assertTrue(implicitBaseSegments.contains(seg4));
		
		/**
		 * COMPUTE MINIMAL
		 */
		
		Set<Segment> minimalSeg = pp.identifyAllMinimalSegments(impPoint, segSet, implicitBaseSegments);
		
		assertEquals(12,minimalSeg.size());
		
		Point pE = new Point(0,10);
		Point pF = new Point(12,10);
		Point pA = new Point(4,0);
		Point pB = new Point(8,0);
		
		Segment seg5 = new Segment(pG,pE);
		Segment seg6 = new Segment(pF,pH);
		Segment seg7 = new Segment(pF,pD);
		Segment seg8 = new Segment(pE,pC);
		Segment seg9 = new Segment(pD,pC);
		Segment seg10 = new Segment(pD,pB);
		Segment seg11 = new Segment(pA,pC);
		Segment seg12 = new Segment(pA,pB);
		
		assertTrue(minimalSeg.contains(seg1));
		assertTrue(minimalSeg.contains(seg2));
		assertTrue(minimalSeg.contains(seg3));
		assertTrue(minimalSeg.contains(seg4));
		assertTrue(minimalSeg.contains(seg5));
		assertTrue(minimalSeg.contains(seg6));
		assertTrue(minimalSeg.contains(seg7));
		assertTrue(minimalSeg.contains(seg8));
		assertTrue(minimalSeg.contains(seg9));
		assertTrue(minimalSeg.contains(seg10));
		assertTrue(minimalSeg.contains(seg11));
		assertTrue(minimalSeg.contains(seg12));
		
		/***
		 * 
		 * 
		 * 
		 * NON MINIMAL
		 * 
		 */
		Set<Segment> nonMinimal = pp.constructAllNonMinimalSegments(minimalSeg);
		assertEquals(2,nonMinimal.size());
		
		Segment b1 = new Segment(pG, pD);
		Segment b2 = new Segment(pH, pC);
		
		assertTrue(nonMinimal.contains(b1));
		assertTrue(nonMinimal.contains(b2));		
		
	}
	
	
	@Test
	void Crossed_LinesTestComputeImplicitBase() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/crossed_lines.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		Preprocessor pp = new Preprocessor(database, segSet);
		
		Set<Segment> implicitBaseSegments = new LinkedHashSet<Segment>();
		
		implicitBaseSegments = pp.computeImplicitBaseSegments(impPoint);
		
		assertEquals(4,implicitBaseSegments.size());
		Point IMP1 = new Point(3,4);
		Point p1 = new Point(3,5);
		Point p2 = new Point(3,0);
		Point p3 = new Point(0,4);
		Point p4 = new Point(5,4);
		
		Segment seg1 = new Segment(IMP1, p1);
		Segment seg2 = new Segment(IMP1, p2);
		Segment seg3 = new Segment(IMP1, p3);
		Segment seg4 = new Segment(IMP1, p4);
		
		assertTrue(implicitBaseSegments.contains(seg1));
		assertTrue(implicitBaseSegments.contains(seg2));
		assertTrue(implicitBaseSegments.contains(seg3));
		assertTrue(implicitBaseSegments.contains(seg4));
		
		/**
		 * COMPUTE MINIMAL
		 */
		
		
		Set<Segment> minimalSeg = pp.identifyAllMinimalSegments(impPoint, segSet, implicitBaseSegments);
		
		
		assertEquals(4,minimalSeg.size());
		assertTrue(minimalSeg.contains(seg1));
		assertTrue(minimalSeg.contains(seg2));
		assertTrue(minimalSeg.contains(seg3));
		assertTrue(minimalSeg.contains(seg4));
		
		/***
		 * 
		 * 
		 * 
		 * NON MINIMAL
		 * 
		 */
		Set<Segment> nonMinimal = pp.constructAllNonMinimalSegments(minimalSeg);
		assertEquals(2,nonMinimal.size());
		
		Segment b1 = new Segment(p1, p2);
		Segment b2 = new Segment(p3, p4);
		
		//assertTrue(nonMinimal.contains(b1));
		assertTrue(nonMinimal.contains(b2));	
		
		

	}
	
	@Test
	void Overlapping_RectangleTestComputeImplicitBase() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/Overlapping_Rectangles.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		Preprocessor pp = new Preprocessor(database, segSet);
		
		Set<Segment> implicitBaseSegments = new LinkedHashSet<Segment>();
		
		implicitBaseSegments = pp.computeImplicitBaseSegments(impPoint);
		
		for(Segment seg: implicitBaseSegments) {
			System.out.print(seg.toString());
		}
		
		assertEquals(12,implicitBaseSegments.size());
		Point IMP1 = new Point(1,3);
		Point IMP2 = new Point(3,1);
		Point IMP3 = new Point(1,1);
		Point IMP4 = new Point(3,3);
		
		Point p1 = new Point(1,4);
		Point p2 = new Point(3,4);
		Point p3 = new Point(1,0);
		Point p4 = new Point(3,0);
		
		Point p5 = new Point(0,3);
		Point p6 = new Point(4,3);
		Point p7 = new Point(0,1);
		Point p8 = new Point(4,1);
		
		
		Segment seg1 = new Segment(IMP1, p1);
		Segment seg2 = new Segment(IMP1, p5);
		
		Segment seg3 = new Segment(IMP2, p4);
		Segment seg4 = new Segment(IMP2, p8);
		
		Segment seg5 = new Segment(IMP3, p3);
		Segment seg6 = new Segment(IMP3, p7);
		
		Segment seg7 = new Segment(IMP4, p2);
		Segment seg8 = new Segment(IMP4, p6);
		
		Segment seg9 = new Segment(IMP1, IMP3);
		Segment seg10 = new Segment(IMP1, IMP4);
		Segment seg11 = new Segment(IMP2, IMP3);
		Segment seg12 = new Segment(IMP2, IMP4);
		
		Segment seg13 = new Segment(p1, p2);
		Segment seg14 = new Segment(p3, p4);
		Segment seg15 = new Segment(p5, p7);
		Segment seg16 = new Segment(p6, p8);
		
		assertTrue(implicitBaseSegments.contains(seg1));
		assertTrue(implicitBaseSegments.contains(seg2));
		assertTrue(implicitBaseSegments.contains(seg3));
		assertTrue(implicitBaseSegments.contains(seg4));
		
		assertTrue(implicitBaseSegments.contains(seg5));
		assertTrue(implicitBaseSegments.contains(seg6));
		assertTrue(implicitBaseSegments.contains(seg7));
		assertTrue(implicitBaseSegments.contains(seg8));
		
		assertTrue(implicitBaseSegments.contains(seg9));
		assertTrue(implicitBaseSegments.contains(seg10));
		assertTrue(implicitBaseSegments.contains(seg11));
		assertTrue(implicitBaseSegments.contains(seg12));
		
		/**
		 * 
		 * MINIMAL SEGMENTS
		 */
		
		Set<Segment> minimalSeg = pp.identifyAllMinimalSegments(impPoint, segSet, implicitBaseSegments);
		
		assertEquals(16,minimalSeg.size());
		
		assertTrue(minimalSeg.contains(seg1));
		assertTrue(minimalSeg.contains(seg2));
		assertTrue(minimalSeg.contains(seg3));
		assertTrue(minimalSeg.contains(seg4));
		assertTrue(minimalSeg.contains(seg5));
		assertTrue(minimalSeg.contains(seg6));
		assertTrue(minimalSeg.contains(seg7));
		assertTrue(minimalSeg.contains(seg8));
		assertTrue(minimalSeg.contains(seg9));
		assertTrue(minimalSeg.contains(seg10));
		assertTrue(minimalSeg.contains(seg11));
		assertTrue(minimalSeg.contains(seg12));
		assertTrue(minimalSeg.contains(seg13));
		assertTrue(minimalSeg.contains(seg14));
		assertTrue(minimalSeg.contains(seg15));
		assertTrue(minimalSeg.contains(seg16));
		
		/**
		 * NON MINIMAL SEGMENTS
		 */
		
		Set<Segment> nonMinimal = pp.constructAllNonMinimalSegments(minimalSeg);
		assertEquals(12,nonMinimal.size());
		
		Segment b1 = new Segment(p1,IMP3);
		Segment b2 = new Segment(p1, p3);
		Segment b3 = new Segment(IMP1, p3);
		
		Segment b4 = new Segment(p2,IMP2);
		Segment b5 = new Segment(p2, p4);
		Segment b6 = new Segment(IMP4, p4);
		
		Segment b7 = new Segment(p5,IMP4);
		Segment b8 = new Segment(p5, p6);
		Segment b9 = new Segment(IMP1, p6);
		
		Segment b10 = new Segment(p7,IMP2);
		Segment b11 = new Segment(p7, p8);
		Segment b12 = new Segment(IMP3, p8);
		
		assertTrue(nonMinimal.contains(b1));
		assertTrue(nonMinimal.contains(b2));
		assertTrue(nonMinimal.contains(b3));
		assertTrue(nonMinimal.contains(b4));
		assertTrue(nonMinimal.contains(b5));
		assertTrue(nonMinimal.contains(b6));
		assertTrue(nonMinimal.contains(b7));
		assertTrue(nonMinimal.contains(b8));
		assertTrue(nonMinimal.contains(b9));
		assertTrue(nonMinimal.contains(b10));
		assertTrue(nonMinimal.contains(b11));
		assertTrue(nonMinimal.contains(b12));
		
		
	}
	
	
	
	

	
	/**
	 * 
	 * 
	 * 
	 * Provided Test
	 * 
	 * 
	 */
	
	@Test
	void test_implicit_crossings()
	{
		FigureNode fig = InputFacade.extractFigure("jsonfiles/fully_connected_irregular_polygon.json");

		Map.Entry<PointDatabase, Set<Segment>> pair = InputFacade.toGeometryRepresentation(fig);

		PointDatabase points = pair.getKey();

		Set<Segment> segments = pair.getValue();

		Preprocessor pp = new Preprocessor(points, segments);

		// 5 new implied points inside the pentagon
		Set<Point> iPoints = ImplicitPointPreprocessor.compute(points, new ArrayList<Segment>(segments));
		assertEquals(5, iPoints.size());

		System.out.println(iPoints);

		//
		//
		//		               D(3, 7)
		//
		//
		//   E(-2,4)       D*      E*
		//		         C*          A*       C(6, 3)
		//                      B*
		//		       A(2,0)        B(4, 0)
		//
		//		    An irregular pentagon with 5 C 2 = 10 segments

		Point a_star = new Point(56.0 / 15, 28.0 / 15);
		Point b_star = new Point(16.0 / 7, 8.0 / 7);
		Point c_star = new Point(8.0 / 9, 56.0 / 27);
		Point d_star = new Point(90.0 / 59, 210.0 / 59);
		Point e_star = new Point(194.0 / 55, 182.0 / 55);

		assertTrue(iPoints.contains(a_star));
		assertTrue(iPoints.contains(b_star));
		assertTrue(iPoints.contains(c_star));
		assertTrue(iPoints.contains(d_star));
		assertTrue(iPoints.contains(e_star));

		//
		// There are 15 implied segments inside the pentagon; see figure above
		//
		Set<Segment> iSegments = pp.computeImplicitBaseSegments(iPoints);
		assertEquals(15, iSegments.size());
		
		List<Segment> expectedISegments = new ArrayList<Segment>();

		expectedISegments.add(new Segment(points.getPoint("A"), c_star));
		expectedISegments.add(new Segment(points.getPoint("A"), b_star));

		expectedISegments.add(new Segment(points.getPoint("B"), b_star));
		expectedISegments.add(new Segment(points.getPoint("B"), a_star));

		expectedISegments.add(new Segment(points.getPoint("C"), a_star));
		expectedISegments.add(new Segment(points.getPoint("C"), e_star));

		expectedISegments.add(new Segment(points.getPoint("D"), d_star));
		expectedISegments.add(new Segment(points.getPoint("D"), e_star));

		expectedISegments.add(new Segment(points.getPoint("E"), c_star));
		expectedISegments.add(new Segment(points.getPoint("E"), d_star));

		expectedISegments.add(new Segment(c_star, b_star));
		expectedISegments.add(new Segment(b_star, a_star));
		expectedISegments.add(new Segment(a_star, e_star));
		expectedISegments.add(new Segment(e_star, d_star));
		expectedISegments.add(new Segment(d_star, c_star));

		for (Segment iSegment : iSegments)
		{
			assertTrue(iSegments.contains(iSegment));
		}

		//
		// Ensure we have ALL minimal segments: 20 in this figure.
		//
		List<Segment> expectedMinimalSegments = new ArrayList<Segment>(iSegments);
		expectedMinimalSegments.add(new Segment(points.getPoint("A"), points.getPoint("B")));
		expectedMinimalSegments.add(new Segment(points.getPoint("B"), points.getPoint("C")));
		expectedMinimalSegments.add(new Segment(points.getPoint("C"), points.getPoint("D")));
		expectedMinimalSegments.add(new Segment(points.getPoint("D"), points.getPoint("E")));
		expectedMinimalSegments.add(new Segment(points.getPoint("E"), points.getPoint("A")));
		
		Segment seg1 = new Segment(points.getPoint("A"), points.getPoint("B"));
		Segment seg2 = new Segment(points.getPoint("B"), points.getPoint("C"));
		Segment seg3 = new Segment(points.getPoint("C"), points.getPoint("D"));
		Segment seg4 = new Segment(points.getPoint("D"), points.getPoint("E"));
		Segment seg5 = new Segment(points.getPoint("E"), points.getPoint("A"));
		
		
		Set<Segment> minimalSegments = pp.identifyAllMinimalSegments(iPoints, segments, iSegments);
		assertEquals(expectedMinimalSegments.size(), minimalSegments.size());

		System.out.println();
		System.out.println(expectedMinimalSegments.size());
		
		//for (Segment minimalSeg : minimalSegments)
		//{
			//assertTrue(expectedMinimalSegments.contains(minimalSeg));
		//}
		/*
		 * assertTrue(minimalSegments.contains(seg1));
		 * assertTrue(minimalSegments.contains(seg2));
		 * assertTrue(minimalSegments.contains(seg3));
		 * assertTrue(minimalSegments.contains(seg4));
		 * assertTrue(minimalSegments.contains(seg5));
		 */

		
		//
		// Construct ALL figure segments from the base segments
		//
		Set<Segment> computedNonMinimalSegments = pp.constructAllNonMinimalSegments(minimalSegments);
		
		//
		// All Segments will consist of the new 15 non-minimal segments.
		//
		assertEquals(15, computedNonMinimalSegments.size());

		//
		// Ensure we have ALL minimal segments: 20 in this figure.
		//
		List<Segment> expectedNonMinimalSegments = new ArrayList<Segment>();
		expectedNonMinimalSegments.add(new Segment(points.getPoint("A"), d_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("D"), c_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("A"), points.getPoint("D")));
		
		expectedNonMinimalSegments.add(new Segment(points.getPoint("B"), c_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("E"), b_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("B"), points.getPoint("E")));
		
		expectedNonMinimalSegments.add(new Segment(points.getPoint("C"), d_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("E"), e_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("C"), points.getPoint("E")));		

		expectedNonMinimalSegments.add(new Segment(points.getPoint("A"), a_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("C"), b_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("A"), points.getPoint("C")));
		
		expectedNonMinimalSegments.add(new Segment(points.getPoint("B"), e_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("D"), a_star));
		expectedNonMinimalSegments.add(new Segment(points.getPoint("B"), points.getPoint("D")));
		
		//
		// Check size and content equality
		//
		assertEquals(expectedNonMinimalSegments.size(), computedNonMinimalSegments.size());

		for (Segment computedNonMinimalSegment : computedNonMinimalSegments)
		{
			assertTrue(expectedNonMinimalSegments.contains(computedNonMinimalSegment));
		}
	
	}
	
}