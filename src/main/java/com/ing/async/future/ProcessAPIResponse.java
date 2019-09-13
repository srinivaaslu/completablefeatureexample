package com.ing.async.future;


import com.ing.async.future.pojo.FinalResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pf46pnd on 27/08/2019.
 */
public class ProcessAPIResponse {

    HelperUtility helperUtility = new HelperUtility();

    public FinalResponse getProcessedResponseFromApi(String godNameStartWith) throws InterruptedException, java.util.concurrent.ExecutionException {
        ExecutorService ex = Executors.newFixedThreadPool(3);

        CompletableFuture<List<String>> finalCombinedFuture = getCompletableFutureResult(ex, "https://my-json-server.typicode.com/jabrena/latency-problems/greek")
                                                                .thenCombine(getSecondFutureResult(ex),(value1, value2)-> Stream.concat(value1.stream(), value2.stream()).collect(Collectors.toList()))
                                                                .thenCombine(getThirdFutureResult(ex),(value3,value4)->Stream.concat(value3.stream(),value4.stream()).collect(Collectors.toList()));

        List<String> allGodNames = finalCombinedFuture.get();
        System.out.println("Total Gods List is  :"+allGodNames);

        List<String> godNamesStartingWithN = allGodNames.stream().filter(x->x.startsWith(godNameStartWith)).collect(Collectors.toList());
        System.out.println("Total Gods List whose names starting with N are :"+godNamesStartingWithN + "\n Total Gods count starting with N are:"+godNamesStartingWithN.size());
        return new FinalResponse(allGodNames,godNamesStartingWithN,godNamesStartingWithN.size());

    }

    private CompletableFuture<List<String>> getCompletableFutureResult(ExecutorService ex, String urlToInvoke) {
        return CompletableFuture.supplyAsync(() -> helperUtility.getGods(urlToInvoke), ex).exceptionally(exception -> {
            System.err.println("exception: " + exception);
            return new ArrayList<>();
        });
    }


    private CompletableFuture<List<String>> getSecondFutureResult(ExecutorService ex) {
        return getCompletableFutureResult(ex, "https://my-json-server.typicode.com/jabrena/latency-problems/roman");
    }

    private CompletableFuture<List<String>> getThirdFutureResult(ExecutorService ex) {
        return getCompletableFutureResult(ex, "https://my-json-server.typicode.com/jabrena/latency-problems/nordic");
    }





}