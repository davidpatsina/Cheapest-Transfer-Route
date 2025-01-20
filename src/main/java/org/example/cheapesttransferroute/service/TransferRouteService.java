package org.example.cheapesttransferroute.service;

import org.example.cheapesttransferroute.service.model.CalculatedRoute;
import org.example.cheapesttransferroute.service.model.Transfer;

import java.util.List;

public interface TransferRouteService {

    CalculatedRoute calculateRoute(int maxWeight, List<Transfer> availableTransfers);

}
