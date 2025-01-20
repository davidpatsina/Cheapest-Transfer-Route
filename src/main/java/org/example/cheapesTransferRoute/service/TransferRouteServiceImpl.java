package org.example.cheapesTransferRoute.service;

import org.example.cheapesTransferRoute.service.model.CalculatedRoute;
import org.example.cheapesTransferRoute.service.model.Transfer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferRouteServiceImpl implements TransferRouteService {

    @Override
    public CalculatedRoute calculateRoute(int maxWeight, List<Transfer> availableTransfers) {
        return null;
    }

}
