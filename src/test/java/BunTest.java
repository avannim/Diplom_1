
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Bun;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BunTest {

    @ParameterizedTest
    @MethodSource("bunsData")
    public void getNameReturnCorrectName(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @ParameterizedTest
    @MethodSource("bunsData")
    public void getPriceReturnCorrectPrice(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice());
    }

    public static Stream<Arguments> bunsData() {
        return Stream.of (
                Arguments.arguments("Флюоресцентная булка R2-D3", 988),
                Arguments.arguments("Краторная булка N-200i", 1255)
        );
    }

}
