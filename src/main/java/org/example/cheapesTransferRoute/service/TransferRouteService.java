package org.example.cheapesTransferRoute.service;

import org.example.cheapesTransferRoute.service.model.CalculatedRoute;
import org.example.cheapesTransferRoute.service.model.Transfer;

import java.util.List;

public interface TransferRouteService {

    CalculatedRoute calculateRoute(int maxWeight, List<Transfer> availableTransfers);

}
