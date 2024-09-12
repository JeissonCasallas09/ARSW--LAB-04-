package edu.eci.arsw.blueprints.test.persistence.impl;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.impl.subsamplingFilter;


public class subsamplingFilterTest {
    @Test
    public void testSubsamplingFilterSize() {

        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)};
        Blueprint blueprint = new Blueprint("jeisson", "casita", points);


        subsamplingFilter subsamplingFilter = new subsamplingFilter();
        subsamplingFilter.filter(blueprint);

        int filteredSize = blueprint.getPoints().size();
        int expectedSize = 3; 
        assertEquals(expectedSize, filteredSize);
    }

    @Test
    public void testSubsamplingFilterSize2() {

        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4),new Point(5, 5)};
        Blueprint blueprint = new Blueprint("andre", "ksa", points);


        subsamplingFilter subsamplingFilter = new subsamplingFilter();
        subsamplingFilter.filter(blueprint);

        int filteredSize = blueprint.getPoints().size();
        int expectedSize = 3; 
        assertEquals(expectedSize, filteredSize);
    }

    @Test
    public void testSubsamplingFilterSinglePointSize() {
 
        Point[] points = {new Point(0, 0)};
        Blueprint blueprint = new Blueprint("melani", "el darien", points);

 
        subsamplingFilter subsamplingFilter = new subsamplingFilter();
        subsamplingFilter.filter(blueprint);


        int filteredSize = blueprint.getPoints().size();
        int expectedSize = 1;
        assertEquals(expectedSize, filteredSize);
    }
}
