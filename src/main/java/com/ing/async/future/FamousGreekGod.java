package com.ing.async.future;

import javafx.util.Pair;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.Comparator.comparing;

public class FamousGreekGod {

    public Pair<String,Integer> getMostFamousGreekGod() throws ExecutionException, InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(3);
        List<String> greekGods =  getCompletableFutureOfGreekGods(ex,"https://my-json-server.typicode.com/jabrena/latency-problems/greek").get();
//
        Pair<String,Integer> longestGodInfo = greekGods.stream()
                .map(god-> {
                    return getGodAndDescriptionPair(ex, god);
                }).max(comparing(Pair::getValue)).get();

        System.out.println("Longest God Description:"+longestGodInfo.getKey());
        return longestGodInfo;
    }

    private  Pair<String, Integer> getGodAndDescriptionPair(ExecutorService ex, String god) {
        try {
            return new Pair<>(god,getCompletableFutureOfEachGreekGodsInfo(ex,"https://en.wikipedia.org/wiki/"+god).get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new Pair<>(god,0);
        }
    }

    private  CompletableFuture<List<String>> getCompletableFutureOfGreekGods(ExecutorService ex, String urlToInvoke) {
        return CompletableFuture.supplyAsync(() -> HelperUtility.getGods(urlToInvoke), ex).exceptionally(exception -> {
            System.err.println("exception: " + exception);
            return new ArrayList<>();
        });
    }

    private  CompletableFuture<Integer> getCompletableFutureOfEachGreekGodsInfo(ExecutorService ex, String urlToInvoke) {
        return CompletableFuture.supplyAsync(() -> HelperUtility.getGodsInfo(urlToInvoke), ex).exceptionally(exception -> {
            System.err.println("exception: " + exception);
            return 0;
        });
    }

}
