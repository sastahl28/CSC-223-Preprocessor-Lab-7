package preprocessor;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import preprocessor.delegates.ImplicitPointPreprocessor;
import geometry_objects.Segment;

public class Preprocessor
{
	// The explicit points provided to us by the user.
	// This database will also be modified to include the implicit
	// points (i.e., all points in the figure).
	protected PointDatabase _pointDatabase;

	// Minimal ('Base') segments provided by the user
	protected Set<Segment> _givenSegments;

	// The set of implicitly defined points caused by segments
	// at implicit points.
	protected Set<Point> _implicitPoints;

	// The set of implicitly defined segments resulting from implicit points.
	protected Set<Segment> _implicitSegments;

	// Given all explicit and implicit points, we have a set of
	// segments that contain no other subsegments; these are minimal ('base') segments
	// That is, minimal segments uniquely define the figure.
	protected Set<Segment> _allMinimalSegments;

	// A collection of non-basic segments
	protected Set<Segment> _nonMinimalSegments;

	// A collection of all possible segments: maximal, minimal, and everything in between
	// For lookup capability, we use a map; each <key, value> has the same segment object
	// That is, key == value. 
	protected Map<Segment, Segment> _segmentDatabase;
	public Map<Segment, Segment> getAllSegments() { return _segmentDatabase; }

	public Preprocessor(PointDatabase points, Set<Segment> segments)
	{
		_pointDatabase  = points;
		_givenSegments = segments;

		_segmentDatabase = new HashMap<Segment, Segment>();

		analyze();
	}

	/**
	 * Invoke the precomputation procedure.
	 */
	public void analyze()
	{
		//
		// Implicit Points
		//
		_implicitPoints = ImplicitPointPreprocessor.compute(_pointDatabase, _givenSegments.stream().toList());

		//
		// Implicit Segments attributed to implicit points
		//
		_implicitSegments = computeImplicitBaseSegments(_implicitPoints);

		//
		// Combine the given minimal segments and implicit segments into a true set of minimal segments
		//     *givenSegments may not be minimal
		//     * implicitSegmen
		//
		_allMinimalSegments = identifyAllMinimalSegments(_implicitPoints, _givenSegments, _implicitSegments);

		//
		// Construct all segments inductively from the base segments
		//
		_nonMinimalSegments = constructAllNonMinimalSegments(_allMinimalSegments);

		//
		// Combine minimal and non-minimal into one package: our database
		//
		_allMinimalSegments.forEach((segment) -> _segmentDatabase.put(segment, segment));
		_nonMinimalSegments.forEach((segment) -> _segmentDatabase.put(segment, segment));
	}


	protected Set<Segment> getSegments(SortedSet<Point> point){
		
		Set<Segment> SegSet = new LinkedHashSet<Segment>(); 
		
		List<Point> sortedList = point.stream().toList();
		
		for (int p=0; p < sortedList.size() -1; p++) {
			
			Point Left = sortedList.get(p);
			Point Right = sortedList.get(p+1);
			
			Segment seg = new Segment(Left, Right);
			SegSet.add(seg);
			Left = Right;
		}
		

		return SegSet;
	}

	protected Set<Segment> computeImplicitBaseSegments(Set<Point> implicitPoints){
		
		Set<Segment> segSet = new LinkedHashSet<Segment>();
		
		for (Segment s: _givenSegments) {
			
			SortedSet<Point> pointSet = s.collectOrderedPointsOnSegment(implicitPoints);
			
			if (!(pointSet.isEmpty())) {
				
				pointSet.add(s.getPoint1());
				pointSet.add(s.getPoint2());
				
				Set<Segment> orderedSet = this.getSegments(pointSet);
				
				segSet.addAll(orderedSet);
				
				
			}
			
			
		}
		
		return segSet;
	}
	
	//private
	protected Set<Segment> identifyAllMinimalSegments(Set<Point> implicitPoints , Set<Segment> givenSegments , Set<Segment> implicitSegments){
		Set<Segment> segSet = new LinkedHashSet<Segment>();
 		segSet.addAll(implicitSegments);
		
		
		for (Segment seg: givenSegments) {
			
			boolean isMinimal = true;
			
			//implied points
			for (Point imp: implicitPoints) {
				
				if (seg.pointLiesBetweenEndpoints(imp)){
					
					isMinimal=false;
				}	
			}
			
			if (isMinimal) {
				
				segSet.add(seg);
				
			}
		}
				  
		  return segSet;
		 
	}


	protected Set<Segment> constructAllNonMinimalSegments(Set<Segment> allMinimalSegments){
		Set<Segment> allSegs = new LinkedHashSet<Segment>();
		
		Queue<Segment> workList = new LinkedList<Segment>();
		
		workList.addAll(allMinimalSegments);
		
		while (!(workList.isEmpty())) {
			
			Segment seg1 = workList.remove();
			
			for (Segment seg2 : allMinimalSegments) {
				if (!(seg1.equals(seg2))) {
					
					if (seg1.coincideWithoutOverlap(seg2)) {
						
						Point p = seg1.sharedVertex(seg2);
						
						if(p != null) {
							
							Segment newSeg = new Segment(seg1.other(p), seg2.other(p));
							allSegs.add(newSeg);
							workList.add(newSeg);
							
						}
					}
				}
			}
		}			
			return allSegs;
	}
	



}

