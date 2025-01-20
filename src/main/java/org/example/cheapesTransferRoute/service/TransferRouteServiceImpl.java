package org.example.cheapesTransferRoute.service;

import lombok.NonNull;
import org.example.cheapesTransferRoute.service.model.CalculatedRoute;
import org.example.cheapesTransferRoute.service.model.Transfer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferRouteServiceImpl implements TransferRouteService {

    @Override
    public CalculatedRoute calculateRoute(int maxWeight, @NonNull List<Transfer> availableTransfers) {
        return CalculatedRoute.builder()
                .transfers(new ArrayList<>())
                .totalCost(0)
                .totalWight(0)
                .build();
    }

}
