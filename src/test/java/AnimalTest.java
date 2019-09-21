import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    @Test
    public void Animals_instantiatesCorrectly_true() {
       Animal animal = new Animal("leopard");
        assertEquals(true, animal instanceof Animal);
    }
    @Test

    public void Animals_instantiatesWithName_String() {
       Animal animal = new Animal("leopard");
        assertEquals("leopard", animal.getName());
    }
    @Test
    public void all_returnsAllInstancesOfMonster_true() {
       Animal animal = new Animal("leopard");
        animal.save();
       Animal second = new Animal("dog");
        second.save();
        assertEquals(true, Animal.all().get(0).equals(animal));
        assertEquals(true, Animal.all().get(1).equals(second));
    }
}