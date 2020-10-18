/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.validate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natha
 */
public class ValidateCPFTest {

    public ValidateCPFTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of validate method, of class ValidateCPF.
     */
    @Test
    public void testValidate() {
        System.out.println("Validate");
        String userCPF = "11111111111";
        ValidateCPF instance = new ValidateCPF();
        boolean expResult = false;
        boolean result = instance.validate(userCPF);
        assertEquals(expResult, result);
    }
}
