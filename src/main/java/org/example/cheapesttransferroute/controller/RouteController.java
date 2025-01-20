package org.example.cheapesttransferroute.controller;

import org.example.cheapesttransferroute.controller.model.GetRouteInput;
import org.example.cheapesttransferroute.controller.model.GetRouteOutput;
import org.example.cheapesttransferroute.controller.model.dto.TransferDTO;
import org.example.cheapesttransferroute.service.TransferRouteService;
import org.example.cheapesttransferroute.service.model.CalculatedRoute;
import org.example.cheapesttransferroute.service.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/route")
public class RouteController {

    @Autowired
    private TransferRouteService transferRouteService;

    @GetMapping
    public ResponseEntity<GetRouteOutput> getRoute(@RequestBody GetRouteInput input) {
        int maxWeight = input.getMaxWeight();
        List<Transfer> transferList = input.getAvailableTransfers().stream().map(
                transferDTO -> Transfer.builder()
                        .cost(transferDTO.getCost())
                        .weight(transferDTO.getWeight())
                        .build()
        ).toList();

        CalculatedRoute calculatedRoute = transferRouteService.calculateRoute(maxWeight, transferList);

        List<TransferDTO> selectedTransfersDTO = calculatedRoute.getTransfers().stream().map(
                transfer -> TransferDTO.builder()
                        .cost(transfer.getCost())
                        .weight(transfer.getWeight())
                        .build()
        ).toList();

        GetRouteOutput output = GetRouteOutput.builder()
                .totalWeight(calculatedRoute.getTotalWeight())
                .totalCost(calculatedRoute.getTotalCost())
                .selectedTransfers(selectedTransfersDTO)
                .build();
        return ResponseEntity.ok(output);
    }
}
