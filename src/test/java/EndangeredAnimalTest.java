import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Test
    public void Animals_instantiatesCorrectly_true() {
        EndangeredAnimal testAnimal = new EndangeredAnimal("leopard", "ok",4);
        assertEquals(true, testAnimal instanceof EndangeredAnimal);
    }
    @Test

    public void Animals_instantiatesWithName_String() {
        EndangeredAnimal testAnimal = new EndangeredAnimal("leopard", "ok",4);
        assertEquals("leopard", testAnimal.getName());
    }
    @Test
    public void all_returnsAllInstancesOfMonster_true() {
        EndangeredAnimal testAnimal = new EndangeredAnimal("leopard", "ok",4);
        testAnimal.save();
        EndangeredAnimal secondAnimal = new EndangeredAnimal("dog", "ill",7);
        secondAnimal.save();
        assertEquals(true, EndangeredAnimal.all().get(0).equals(testAnimal));
        assertEquals(true, EndangeredAnimal.all().get(1).equals(secondAnimal));
    }
}