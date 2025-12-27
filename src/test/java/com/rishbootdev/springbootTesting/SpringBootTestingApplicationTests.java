package com.rishbootdev.springbootTesting;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;


/* even after removing this annotation the test will run because
   all the test methods are part of the junit not spring directly

   Hence @SpringBootTest is completely optional as per the unit testing
   purposes
 */
@SpringBootTest
@Slf4j
class SpringBootTestingApplicationTests {

    @BeforeEach
    void setup(){
        log.info("Starting the method, setting up config");
    }

    @AfterAll
    static void relocate(){
        log.info("Removing the prior dependencies before shutting down");
    }

    @Test
    public void contextLoads() {
        log.info("context loading test case");
    }

    @Test
    @DisplayName("test1")
    public void testNumberOne(){
        log.info("This is the test number 1");

        int a = 5;
        int b = 3;
        int result = addTwoNumber(a, b);

        //This is a important method for asserting or checking the values to be equal or not
       // Assertions.assertEquals(8, result);  this is method is from the JUnit framewrok which was used earlier

        Assertions.assertThat(result).isEqualTo(8)
                .isCloseTo(9, Offset.offset(1));


    }

    @Test
    @DisplayName("test2")
    public void testNumberTwo(){
        log.info("This is the test number 2");
    }

    @Test
    @Disabled
    @DisplayName("test3")
    public void testNumberThree(){
        log.info("This is the test number 3");
    }

    @Test
    @BeforeEach
    @DisplayName("test4")
    public void testNumberFour(){
        log.info("This is the test number 4");
    }

    @BeforeAll
    @DisplayName("test5")
    public static void testNumberFive(){
        log.info("This is the test number 5");
    }

    public int addTwoNumber(int a, int b){
        return a + b;
    }

}

/*
     the methods annotated with the @BeforeAll or @AfterAll should be made static
     and they should not contain the @Test method on top of them.
     But in case of the annotations @BeforeEach and @BeforeAfter the methods can have
     @Test annotation but the @Test annotations says that the methods annotated with
     the @Test should not be static.

     Junit is a java framework which is used widely for unit testing the java applications
     include j2ee and springboot applications.
     And if we talk about the assertJ -> it is a testing library which is developed providing
     fluent and expressive assertions, enhancing the readability and maintainability of
     the test code

AssertJ provides a fluent and readable assertion style using assertThat().

1. Number Assertions

Used to verify numeric values.

assertThat(5)
    .isEqualTo(5)
    .isNotEqualTo(10)
    .isGreaterThan(4);


Key methods:

isEqualTo(expected) → checks equality

isNotEqualTo(value) → checks inequality

isGreaterThan(value) → checks greater than condition

2. String Assertions

Used to validate string content and patterns.

assertThat("hello")
    .startsWith("he")
    .endsWith("lo")
    .contains("ell");


Key methods:

startsWith(prefix)

endsWith(suffix)

contains(substring)

3. Boolean Assertions

Used to verify boolean conditions.

assertThat(true).isTrue();


OR

assertThat(false).isFalse();


Key methods:

isTrue()

isFalse()

4. List / Array Assertions

Used to validate collections.

assertThat(List.of("apple", "banana"))
    .contains("apple")
    .doesNotContain("orange")
    .hasSize(2);


Key methods:

contains(element)

doesNotContain(element)

hasSize(size)

Why AssertJ?

✔ Fluent and readable
✔ Method chaining
✔ Better error messages than traditional JUnit assertions


Exception Assertions (AssertJ) – Short Note

Used to verify that code throws (or does not throw) exceptions.

assertThatThrownBy(() -> { #code })
        .isInstanceOf(Exception.class)
    .hasMessageContaining("error");


        Other common forms:

        assertThatExceptionOfType(NullPointerException.class)
    .isThrownBy(() -> { #code });

        assertThatCode(() -> { #code })
        .doesNotThrowAnyException();

        Key methods:
        assertThatThrownBy(), assertThatExceptionOfType(), hasMessage(), hasMessageContaining(), assertThatCode()
 */
