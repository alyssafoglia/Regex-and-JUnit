package plc.homework;

//import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Contains JUnit tests for {@link Regex}. A framework of the test structure 
 * is provided, you will fill in the remaining pieces.
 *
 * To run tests, either click the run icon on the left margin, which can be used
 * to run all tests or only a specific test. You should make sure your tests are
 * run through IntelliJ (File > Settings > Build, Execution, Deployment > Build
 * Tools > Gradle > Run tests using <em>IntelliJ IDEA</em>). This ensures the
 * name and inputs for the tests are displayed correctly in the run window.
 */
public class RegexTests {

    /**
     * This is a parameterized test for the {@link Regex#EMAIL} regex. The
     * {@link ParameterizedTest} annotation defines this method as a
     * parameterized test, and {@link MethodSource} tells JUnit to look for the
     * static method {@link #testEmailRegex()}.
     *
     * For personal preference, I include a test name as the first parameter
     * which describes what that test should be testing - this is visible in
     * IntelliJ when running the tests (see above note if not working).
     */
    @ParameterizedTest
    @MethodSource
    public void testEmailRegex(String test, String input, boolean success) {
        test(input, Regex.EMAIL, success);
    }

    /**
     * This is the factory method providing test cases for the parameterized
     * test above - note that it is static, takes no arguments, and has the same
     * name as the test. The {@link Arguments} object contains the arguments for
     * each test to be passed to the function above.
     */
    public static Stream<Arguments> testEmailRegex() {
        return Stream.of(
                //given tests
                Arguments.of("Alphanumeric", "thelegend27@gmail.com", true),
                Arguments.of("UF Domain", "otherdomain@ufl.edu", true),
                Arguments.of("Missing Domain Dot", "missingdot@gmailcom", false),
                Arguments.of("Symbols", "symbols#$%@gmail.com", false),

                //my tests
                Arguments.of("Digits in after @", "alyssafog@5678.com", true),
                Arguments.of("Tildes", "alyssa@ufl~.com", true),
                Arguments.of("Hyphens", "alyssa@ufl.gm-ail.com", true),
                Arguments.of("Underscores", "Alyssa_fog@gmail.com", true),
                Arguments.of("Periods", "aly.f.og@gmail.com", true),
                Arguments.of("Domain w/ 2 periods", "aly@gmail.ufl.com", true),

                Arguments.of("Short top-level domain", "alyfog@gmail.co", false),
                Arguments.of("Long top-level domain", "alyfog@gmail.comm", false),
                Arguments.of("Short username", "a@gmail.com", false),
                Arguments.of("Symbol in domain", "alyfog@gma$il.com", false),
                Arguments.of("More than one @ symbol", "alyssa@gmail@ufl.com", false)

        );
    }

    @ParameterizedTest
    @MethodSource
    public void testOddStringsRegex(String test, String input, boolean success) {
        test(input, Regex.ODD_STRINGS, success);
    }

    public static Stream<Arguments> testOddStringsRegex() {
        return Stream.of(
                //given
                Arguments.of("11 Characters", "automobiles", true),
                Arguments.of("13 Characters", "i<3pancakes13", true),
                Arguments.of("5 Characters", "5five", false),
                Arguments.of("14 Characters", "i<3pancakes14!", false),

                //my test cases
                Arguments.of("16 Characters", "alyssafoglia!!!!", false),
                Arguments.of("3 Characters", "A@@", false),
                Arguments.of("12 Characters", "AlySSA$$9872", false),
                Arguments.of("19 Characters", "alyssafoglia!!!!aly", true),
                Arguments.of("17 Characters", "alyssafoglia!!!!A", true),
                Arguments.of("15 Characters", "alyss@foglia!!!", true)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testCharacterListRegex(String test, String input, boolean success) {
        test(input, Regex.CHARACTER_LIST, success);
    }

    public static Stream<Arguments> testCharacterListRegex() {
        return Stream.of(
                //given tests
                Arguments.of("Single Element", "['a']", true),
                Arguments.of("Multiple Elements", "['a','b','c']", true),
                Arguments.of("Missing Brackets", "'a','b','c'", false),
                Arguments.of("Missing Commas", "['a' 'b' 'c']", false),

                //my tests
                Arguments.of("Empty List", "[]", true),
                Arguments.of("Mixed Spaces", "['a', 'b','c']", true),
                Arguments.of("Four Elements", "['a','b','c','d']", true),
                Arguments.of("Missing Quotes", "[a]", false),
                Arguments.of("Trailing Comma", "['a',]", false),
                Arguments.of("Quotes with no element", "['']", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testDecimalRegex(String test, String input, boolean success) {
        //throw new UnsupportedOperationException(); //TODO
        test(input, Regex.DECIMAL, success);
    }

    public static Stream<Arguments> testDecimalRegex() {
        //throw new UnsupportedOperationException(); //TODO
        return Stream.of(
                //true arguments
                Arguments.of("Negative","-1.0", true),
                Arguments.of("Long Decimal", "3.89898989898", true),
                Arguments.of("Long Whole Number", "37876776555.2", true),
                Arguments.of("Ending Zeros", "3.600", true),
                Arguments.of("Less than 1", "0.1", true),
                Arguments.of("Zeroes in the Middle", "100.002", true),
                //false arguments
                Arguments.of("No Number Before Decimal", ".5", false),
                Arguments.of("Single Digit", "1", false),
                Arguments.of("Leading Zeroes", "0009.0", false),
                Arguments.of("Multiple Decimals", "1.0.8.0", false),
                Arguments.of("Two Negative Signs", "--1.8", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testStringRegex(String test, String input, boolean success) {
        //throw new UnsupportedOperationException();
        test(input, Regex.STRING, success);
    }

    public static Stream<Arguments> testStringRegex() {
        //throw new UnsupportedOperationException();
        return Stream.of(
                //true arguments
                Arguments.of("String", "\"Hello, World!\"", true),
                Arguments.of("Just Quotes", "\"\"", true),
                Arguments.of("Punctuation", "\".!;:\"", true),
                Arguments.of("Numbers", "\"123\"", true),
                Arguments.of("All Caps", "\"ALYSSA\"", true),
                Arguments.of("Lots of Quotes", "\"Aly\"ssa\"", true),
                Arguments.of("Symbols", "\"@^*\"", true),
                Arguments.of("Sentence", "\"Hello world it is Alyssa\"", true),
                Arguments.of("Escape b", "\"\\b Alyssa\"", true),
                Arguments.of("Escape n", "\"\\n Alyssa\"", true),
                Arguments.of("Escape r", "\"\\r Alyssa\"", true),
                Arguments.of("Whitespace", "\" A  LY  \"", true),

                //false arguments
                Arguments.of("Missing Quote At Beginning", "Alyssa\"", false),
                Arguments.of("Missing Quote End", "\"alyssa", false),
                Arguments.of("No Quotes at All", "This is not a string", false),
                Arguments.of("Invalid Escape", "\"\\invalid\"", false),
                Arguments.of("Several Quotes", "\"A\"l\"Y", false)

        );

    }

    /**
     * Asserts that the input matches the given pattern. This method doesn't do
     * much now, but you will see this concept in future assignments.
     */
    private static void test(String input, Pattern pattern, boolean success) {
        Assertions.assertEquals(success, pattern.matcher(input).matches());
    }

}
