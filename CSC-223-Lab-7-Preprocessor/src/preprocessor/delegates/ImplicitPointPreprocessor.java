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
	 *  1. Compute the set of implied points.
	 *  make a list (or something) of the implied points
	 *  	
	 *  
	 *	2. Use the implicit points to compute the implicit minimal segments.
	 *
	 *
	 *	3. Identify the complete set of minimal segments (accounting for implicit points).
	 *	4. Construct the set of all non-minimal segments (based on minimal segments).
	 *	5. Populate a container all non-minimal segments.
	 *	6. Populate a container with all segments; this is our ‘database’
	 *    
	 */
	public static Set<Point> compute(PointDatabase givenPoints, List<Segment> givenSegments)
	{
		Set<Point> implicitPoints = new LinkedHashSet<Point>();

		// TODO

		return implicitPoints;
	}

}
