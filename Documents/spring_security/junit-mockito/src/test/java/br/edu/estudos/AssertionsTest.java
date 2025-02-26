package br.edu.estudos;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AssertionsTest {

    // conhecendo as assertivas
    @Test
    void assertivas(){

        Assertions.assertEquals("Casa", "Casa");
        Assertions.assertNotEquals("Casa", "casa");
        Assertions.assertTrue("casa".equalsIgnoreCase("CASA"));
        Assertions.assertTrue("casa".startsWith("ca"));
        Assertions.assertTrue("casa".endsWith("sa"));

        List<String> s1 = new ArrayList<>();
        List<String> s2 = new ArrayList<>();
        List<String> s3 = null;

        Assertions.assertEquals(s1, s2);
        Assertions.assertSame(s1, s1);
        Assertions.assertNotEquals(s1, s3);
        Assertions.assertNull(s3);
        Assertions.assertNotNull(s1);

//        Assertions.fail("E falhouuuu");
    }
}
