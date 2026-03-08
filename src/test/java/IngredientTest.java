import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {
    @ParameterizedTest
    @MethodSource("ingredientsData")
    public void getTypeReturnsCorrectType(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }

    @ParameterizedTest
    @MethodSource("ingredientsData")
    public void getNameReturnsCorrectName(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest
    @MethodSource("ingredientsData")
    public void getPriceReturnsCorrectPrice(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0f);
    }

    public static Stream<Arguments> ingredientsData() {
        return Stream.of(
                Arguments.arguments(IngredientType.SAUCE, "hot sauce", 100f),
                Arguments.arguments(IngredientType.SAUCE, "sour cream", 200f),
                Arguments.arguments(IngredientType.FILLING, "cutlet", 100f),
                Arguments.arguments(IngredientType.FILLING, "dinosaur", 200f));
    }
}
