package edu.eci.arsw.blueprints.test.persistence.impl;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.impl.DuplicateFilter;


public class DuplicateFilterTest {
    @Test
    public void testDuplicateFilterWithDuplicatesSize() {

        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(1, 1), new Point(2, 2)};
        Blueprint blueprint = new Blueprint("jeissonC", "calle", points);

 
        DuplicateFilter duplicateFilter = new DuplicateFilter();
        duplicateFilter.filter(blueprint);

        int filteredSize = blueprint.getPoints().size();
        int expectedSize = 3;
        assertEquals(expectedSize, filteredSize);
    }


    @Test
    public void testDuplicateFilterWithDuplicatesSize2() {

        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(3, 3)};
        Blueprint blueprint = new Blueprint("ola", "adios", points);

 
        DuplicateFilter duplicateFilter = new DuplicateFilter();
        duplicateFilter.filter(blueprint);

        int filteredSize = blueprint.getPoints().size();
        int expectedSize = 4;
        assertEquals(expectedSize, filteredSize);
    }

    @Test
    public void testDuplicateFilterWithNoConsecutivePoints() {

        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(1, 1), new Point(3, 3), new Point(3, 3)};
        Blueprint blueprint = new Blueprint("jeissonC", "calle", points);

 
        DuplicateFilter duplicateFilter = new DuplicateFilter();
        duplicateFilter.filter(blueprint);

        int filteredSize = blueprint.getPoints().size();
        int expectedSize = 5;
        assertEquals(expectedSize, filteredSize);
    }

    @Test
    public void testDuplicateFilterNoDuplicatesSize() {

        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 2)};
        Blueprint blueprint = new Blueprint("author3", "blueprint3", points);

        DuplicateFilter duplicateFilter = new DuplicateFilter();
        duplicateFilter.filter(blueprint);


        int filteredSize = blueprint.getPoints().size();
        int expectedSize = 3;
        assertEquals(expectedSize, filteredSize);
    }
}
