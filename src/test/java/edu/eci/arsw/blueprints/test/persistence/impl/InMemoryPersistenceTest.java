/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
                
        
    }

    @Test
    public void findExistingAuthorWithBlueprints() throws BlueprintPersistenceException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts1=new Point[]{new Point(0, 0),new Point(10, 10)};
        Point[] pts2=new Point[]{new Point(20, 0),new Point(0, 20)};
        Point[] pts3=new Point[]{new Point(13, 5),new Point(8, -10)};

        Blueprint bp1=new Blueprint("leo", "miCasita",pts1);
        Blueprint bp2=new Blueprint("leo", "miTiendita",pts2);
        Blueprint bp3=new Blueprint("leo", "miLotecito",pts3);

        ibpp.saveBlueprint(bp1);
        ibpp.saveBlueprint(bp2);
        ibpp.saveBlueprint(bp3);

        Set<Blueprint> blueprintsByLeo = ibpp.getBlueprintsByAuthor("leo");

        Set<Blueprint> expectedBlueprints = new HashSet<>();
        expectedBlueprints.add(bp1);
        expectedBlueprints.add(bp2);
        expectedBlueprints.add(bp3);
        assertEquals(blueprintsByLeo, expectedBlueprints);
    }
    
    @Test
    public void findNonExistingAutors(){
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Set<Blueprint> blueprintsByJeisont = ibpp.getBlueprintsByAuthor("jeisont");

        assertEquals(blueprintsByJeisont.size(), 0);

    }
}
