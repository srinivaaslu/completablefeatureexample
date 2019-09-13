package com.ing.async.future;


import com.ing.async.future.pojo.FinalResponse;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ProcessAPIResponseTest {

    ProcessAPIResponse processAPIResponse = new ProcessAPIResponse();



    @Test
    void getResponseFromAPIInSuccessScenario()throws Exception{
        FinalResponse finalResponse = processAPIResponse.getProcessedResponseFromApi("N");
        assertEquals(4, finalResponse.getCountOfGodsStartingWithLetter());
        assertEquals(36, finalResponse.getAllGods().size());

    }


}
