import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {


    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;


    Burger burger;

    @BeforeEach
    void setUp(){
        burger = new Burger();
    }


    @Test
    public void testSetBunNotNull(){
        burger.setBuns(new Bun("Черная булка", 14.50F));
        assertEquals("Черная булка",burger.bun.getName());
        assertEquals(14.50F,burger.bun.getPrice());
    }

    @Test
    public void testAddIngredient(){
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "mayo", 18.200F);
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());

    }

    @Test
    public void testRemoveIngredient(){
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "mayo", 18.200F));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "ketchup", 14.500F));
        burger.removeIngredient(1);
        assertEquals(1, burger.ingredients.size());
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
    public void testMoveIngredient(Ingredient ingredient0, Ingredient ingredient1){
        burger.addIngredient(ingredient0);
        burger.addIngredient(ingredient1);
        burger.moveIngredient(1, 0);
        assertEquals(ingredient1.getType(), burger.ingredients.get(0).getType());
        assertEquals(ingredient1.getName(), burger.ingredients.get(0).getName());
        assertEquals(ingredient1.getPrice(), burger.ingredients.get(0).getPrice());
    }

    public static Stream<Arguments> burgerData() {
        return Stream.of (
                Arguments.arguments( new Bun("Черная булка", 14.50F), new Ingredient(IngredientType.FILLING,"salad", 100),
                        new Ingredient(IngredientType.SAUCE,"ketchup", 100), 229),
                Arguments.arguments(new Bun("Красная булка", 20.50F),new Ingredient(IngredientType.FILLING,"salad", 100),
                        new Ingredient(IngredientType.SAUCE,"salad", 100), 241)
        );
    }

    @ParameterizedTest
    @MethodSource("burgerData")
    public void testGetBurgerPrice(Bun bun, Ingredient ingredient0, Ingredient ingredient1, float finalPrice){
        burger.addIngredient(ingredient0);
        burger.addIngredient(ingredient1);
        burger.setBuns(bun);
        assertEquals(finalPrice, burger.getPrice());

    }

    @Test
    public void getPriceWithNoIngredientsReturnsBunPriceDoubled() {
        when(bun.getPrice()).thenReturn(150f);
        burger.setBuns(bun);

        assertEquals(300f, burger.getPrice(), 0f);
    }

    @Test
    public void getReceiptContainsBunName() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("black bun"));
    }

    @Test
    public void getReceiptContainsIngredientInfo() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("hot sauce");
        when(ingredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("sauce"));
        assertTrue(receipt.contains("hot sauce"));
    }

    @Test
    public void getReceiptContainsPrice() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Price:"));
    }
}
