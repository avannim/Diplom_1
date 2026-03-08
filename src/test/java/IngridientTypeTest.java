import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngridientTypeTest {
    @Test
    public void ingredientTypeContainsSauce() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void ingredientTypeContainsFilling() {
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    public void ingredientTypeHasTwoValues() {
        assertEquals(2, IngredientType.values().length);
    }
}
