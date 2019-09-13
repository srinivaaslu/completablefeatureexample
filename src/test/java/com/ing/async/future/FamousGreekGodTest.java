package com.ing.async.future;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class FamousGreekGodTest {


    @Test
    public void testFamousGreekGod()throws Exception{
        FamousGreekGod famousGreekGod = new FamousGreekGod();
        Pair<String, Integer> mostFamousGreekGod = famousGreekGod.getMostFamousGreekGod();
        assertEquals("Apollo", mostFamousGreekGod.getKey());

    }
}
