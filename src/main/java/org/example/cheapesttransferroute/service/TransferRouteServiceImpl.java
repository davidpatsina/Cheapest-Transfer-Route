package org.example.cheapesttransferroute.service;

import lombok.NonNull;
import org.example.cheapesttransferroute.service.model.CalculatedRoute;
import org.example.cheapesttransferroute.service.model.Transfer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

@Service
public class TransferRouteServiceImpl implements TransferRouteService {

    @Override
    public CalculatedRoute calculateRoute(int maxWeight, @NonNull List<Transfer> availableTransfers) {
        List<List<Integer>> dp = initialDP(availableTransfers.size() + 1, maxWeight +1);
        for (int i = 1; i < availableTransfers.size() + 1; i++) {
            for (int currentWeightLimit = 0; currentWeightLimit < maxWeight + 1; currentWeightLimit++) {
                Transfer currentTransfer = availableTransfers.get(i-1);
                int currentWeight = currentTransfer.getWeight();
                int currentCost = currentTransfer.getCost();
                if (currentWeight <= currentWeightLimit) {
                    int option1 = dp.get(i-1).get(currentWeightLimit);
                    int option2 = (currentWeightLimit - currentWeight) >= 0 ? (currentCost + dp.get(i-1).get(currentWeightLimit - currentWeight)): 0;
                    int value = max(option1, option2);
                    dp.get(i).set(currentWeightLimit,value);
                } else {
                    int value = dp.get(i-1).get(currentWeightLimit);
                    dp.get(i).set(currentWeightLimit, value);
                }
            }
        }
        return getRoute(availableTransfers, dp, maxWeight);
    }

    private List<List<Integer>> initialDP(int numTransfers, int maxWeightLimit){
        List<List<Integer>> dp = new ArrayList<>();
        for (int i = 0; i < numTransfers; i++) {
            dp.add(new ArrayList<>());
            for (int j = 0; j < maxWeightLimit; j++) {
                dp.get(i).add(0);
            }
        }
        return dp;
    }

    private CalculatedRoute getRoute(List<Transfer> availableTransfers, List<List<Integer>> dp, int weightLimit){
        CalculatedRoute calculatedRoute;
        List<Transfer> route = new ArrayList<>();
        int totalCost = dp.get(availableTransfers.size()).get(weightLimit);
        int totalWeight = 0;
        for (int i = availableTransfers.size() ; i >= 1; i--) {
            if (dp.get(i).get(weightLimit) !=  dp.get(i-1).get(weightLimit)) {
                Transfer transfer = availableTransfers.get(i-1);
                route.add(transfer);
                totalWeight += transfer.getWeight();
                weightLimit -= transfer.getWeight();
            }
        }

        calculatedRoute = CalculatedRoute.builder()
                .totalCost(totalCost)
                .totalWeight(totalWeight)
                .transfers(route)
                .build();
        return calculatedRoute;
    }

}
