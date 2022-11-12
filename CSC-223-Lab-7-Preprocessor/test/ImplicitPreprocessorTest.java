import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import input.InputFacade;
import input.components.FigureNode;
import preprocessor.delegates.ImplicitPointPreprocessor;

class ImplicitPreprocessorTest {

	/**
	 * 
	 * N    N     oooo  
	 * N N  N    o    o 
	 * N  N N    o    o
	 * N    N     oooo
	 * 
	 * 
	 */
	
	
	@Test
	void lineTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/lineseg.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		assertEquals(0,impPoint.size());
		
	}
	
	@Test
	void singleTriangleTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/single_triangle.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		assertEquals(0,impPoint.size());
		
	}
	
	@Test
	void squareTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/square.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		assertEquals(0,impPoint.size());
		
	}
	
	@Test
	void collinear_line_segmentsTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/collinear_line_segments.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		assertEquals(0,impPoint.size());
		
	}
	
	
	
	/**
	 * Y   Y   EEEEE    SSSS
	 *  Y Y    E       S
	 *   Y     EEEEE    SSSS
	 *   Y     E            S
	 *   Y     EEEEE    SSSS
	 * 
	 */
	
	@Test
	void crossing_symmetric_triangleTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/crossing_symmetric_triangle.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		assertEquals(1,impPoint.size());
		Point p1 = new Point(3,3);
		
		assertTrue(impPoint.contains(p1));
		
	}
	
	@Test
	void Tri_Quad_ImpliedTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/Tri_Quad_With_Implied.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		assertEquals(1,impPoint.size());
		
		Point p1 = new Point(6,8.5);
		
		assertTrue(impPoint.contains(p1));
		
		
	}
	
	@Test
	void Crossed_SquareTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/Crossed_Square.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		assertEquals(1,impPoint.size());
		
		Point p1 = new Point(3,3);
		assertTrue(impPoint.contains(p1));

	}
	
	@Test
	void Crossed_LinesTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/crossed_lines.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		assertEquals(1,impPoint.size());
		
		Point p1 = new Point(3,4);
		assertTrue(impPoint.contains(p1));

	}
	
	@Test
	void overlapping_rectangleTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/Overlapping_Rectangles.json");
		Map.Entry<PointDatabase, Set<Segment>> map = InputFacade.toGeometryRepresentation(figNode);
		
		PointDatabase database = map.getKey();
		Set<Segment> segSet = map.getValue();
		
		Set<Point> impPoint = ImplicitPointPreprocessor.compute(database, segSet.stream().toList());
		
		assertEquals(4,impPoint.size());
		Point p1 = new Point(1,3);
		Point p2 = new Point(3,3);
		Point p3 = new Point(1,1);
		Point p4 = new Point(3,1);
		
		assertTrue(impPoint.contains(p1));
		assertTrue(impPoint.contains(p2));
		assertTrue(impPoint.contains(p3));
		assertTrue(impPoint.contains(p4));

	}
	
	
	
	
	
}
