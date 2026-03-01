import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    @Test
    public void testSetBunNotNull(){
        Burger burger = new Burger();
        burger.setBuns(new Bun("Черная булка", 14.50F));
        assertEquals("Черная булка",burger.bun.getName());
        assertEquals(14.50F,burger.bun.getPrice());
    }

    @Test
    public void testAddIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "mayo", 18.200F));
    }

    @Test
    public void testRemoveIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "mayo", 18.200F));
    }

    public static Stream<Arguments> ingredientsData() {
        return Stream.of (
                Arguments.arguments( new Ingredient(IngredientType.FILLING,"salad", 100),
                        new Ingredient(IngredientType.SAUCE,"ketchup", 100), 0, 1),
                Arguments.arguments(new Ingredient(IngredientType.FILLING,"salad", 100),
                        new Ingredient(IngredientType.SAUCE,"salad", 100), 0, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("ingredientsData")
    public void testMoveIngredient(Ingredient ingredient0, Ingredient ingredient1, int newPosition, int currentPosition){
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(IngredientType.FILLING,"salad", 100));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE,"salad", 100));
        burger.moveIngredient(1, 0);
        assertEquals(0, burger.ingredients.indexOf(ingredient1));
    }


}
