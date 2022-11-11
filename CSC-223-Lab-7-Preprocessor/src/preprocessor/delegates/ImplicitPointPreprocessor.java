package preprocessor.delegates;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;

public class ImplicitPointPreprocessor
{
	/**
	 * It is possible that some of the defined segments intersect
	 * and points that are not named; we need to capture those
	 * points and name them.
	 * 
	 * Algorithm:
	 *    TODO
	 *   //i=0
		//i=j+1
		//call segmentIntersection
		//check that x,y are in segment 1 and segment 2
		//if so add to implicitPoints
	 *    
	 *    
	 *  1. Compute the set of implied points.
	 *  make a list (or something) of the implied points and 
	 *  calling the required methods
	 *  	
	 *  
	 *	2. Use the implicit points to compute the implicit minimal segments.
	 *
	 *
	 *	3. Identify the complete set of minimal segments (accounting for implicit points).
	 *
	 *	4. Construct the set of all non-minimal segments (based on minimal segments).
	 *
	 *	5. Populate a container all non-minimal segments.
	 *	
	 *
	 *	6. Populate a container with all segments; this is our ‘database’
	 *
	 *
	 *    
	 */
	public static Set<Point> compute(PointDatabase givenPoints, List<Segment> givenSegments)
	{
		Set<Point> implicitPoints = new LinkedHashSet<Point>();
		
		//begin with a segment from given segments
		for (int i = 0; i < givenSegments.size()-1; i++ ) {
			
			
			//for each segment AFTER that segment, compare
			for (int j = i+1; j < givenSegments.size(); j++) {
				
				
				Segment seg1 = new Segment(givenSegments.get(i));
				Segment seg2 = new Segment(givenSegments.get(j));
				
				
				//Get the point comprising where seg1 and seg2 would overlap
				Point p = seg1.segmentIntersection(seg2);
				
				//If that point is within both endpoints add to implicit points
				if (seg2.pointLiesBetweenEndpoints(p) && seg1.pointLiesBetweenEndpoints(p)) {
					
					//check that the point does not already exist
					if (givenPoints.getPoint(p) == null) {
						implicitPoints.add(p);
					}				
					
				}
					
			}
		}

		return implicitPoints;
	}
	
	
	
}
