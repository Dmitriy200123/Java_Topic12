import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegerDecode_Should {

    @Test
    public void Decode_Exception_WhenStringIsEmpty(){
        var exception = assertThrows(NumberFormatException.class, () -> Integer.decode(""));

        assert "Zero length string".equals(exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"#-01", "0x+13", "0-12"})
    public void Decode_Exception_WhenInvalidFormat16Or8RadixNumber(String value){
        var exception = assertThrows(NumberFormatException.class, () -> Integer.decode(value));
        System.out.println(exception.getMessage());
        assert "Sign character in wrong position".equals(exception.getMessage());
    }

    @Test
    public void Decode_SimplePositiveValue_WhenSimpleNumber(){
        var act = Integer.decode("12");

        assert act == 12;
    }

    @Test
    public void Decode_SimplePositiveValue_WhenNumberWithPlusSign(){
        var act = Integer.decode("+12");

        assert act == 12;
    }

    @Test
    public void Decode_SimpleNegativeValue_WhenNumberWithMinusSign(){
        var act = Integer.decode("+12");

        assert act == 12;
    }

    @Test
    public void Decode_SimplePositive8RadixNumber_When8RadixNumber(){
        var act = Integer.decode("023");

        assert 023 == act;
    }

    @ParameterizedTest
    @ValueSource(strings = {"0X11", "0x11", "#11"})
    public void Decode_SimplePositive16RadixNumber_When16RadixNumberAndIgnoreCase(String value){
        var act = Integer.decode(value);

        assert 0x11 == act;
    }

    @Test
    public void Decode_MaxInteger_WhenStringWithMaxIntValue(){
        var numberFromString = String.valueOf(Integer.MAX_VALUE);

        var act = Integer.decode(numberFromString);

        assert Integer.MAX_VALUE == act;
    }

    @Test
    public void Decode_MinInteger_WhenStringWithMinIntValue(){
        var numberFromString = String.valueOf(Integer.MIN_VALUE);

        var act = Integer.decode(numberFromString);

        assert Integer.MIN_VALUE == act;
    }
}
