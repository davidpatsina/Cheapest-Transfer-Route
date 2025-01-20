package org.example.cheapesttransferroute.controller;

import org.example.cheapesttransferroute.controller.model.GetRouteInput;
import org.example.cheapesttransferroute.controller.model.GetRouteOutput;
import org.example.cheapesttransferroute.controller.model.dto.TransferDTO;
import org.example.cheapesttransferroute.service.TransferRouteService;
import org.example.cheapesttransferroute.service.model.CalculatedRoute;
import org.example.cheapesttransferroute.service.model.Transfer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RouteControllerTest {

    @Mock
    private TransferRouteService transferRouteService;

    @InjectMocks
    private RouteController routeController;

    @Test
    public void getRoute_ValidInput_ReturnsExpectedRoute() {
        List<TransferDTO> transferDTOs = List.of(
                TransferDTO.builder().weight(2).cost(3).build(),
                TransferDTO.builder().weight(3).cost(4).build()
        );

        GetRouteInput input = GetRouteInput.builder()
                .maxWeight(5)
                .availableTransfers(transferDTOs)
                .build();

        List<Transfer> transfers = List.of(
                Transfer.builder().weight(2).cost(3).build(),
                Transfer.builder().weight(3).cost(4).build()
        );

        CalculatedRoute calculatedRoute = CalculatedRoute.builder()
                .totalWeight(5)
                .totalCost(7)
                .transfers(transfers)
                .build();

        when(transferRouteService.calculateRoute(eq(5), any())).thenReturn(calculatedRoute);

        ResponseEntity<GetRouteOutput> response = routeController.getRoute(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTotalWeight()).isEqualTo(5);
        assertThat(response.getBody().getTotalCost()).isEqualTo(7);
    }

    @Test
    public void getRoute_ZeroMaxWeight_ReturnsEmptyRoute() {
        GetRouteInput input = GetRouteInput.builder()
                .maxWeight(0)
                .availableTransfers(List.of())
                .build();

        CalculatedRoute calculatedRoute = CalculatedRoute.builder()
                .totalWeight(0)
                .totalCost(0)
                .transfers(List.of())
                .build();

        when(transferRouteService.calculateRoute(eq(0), any())).thenReturn(calculatedRoute);

        ResponseEntity<GetRouteOutput> response = routeController.getRoute(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTotalWeight()).isEqualTo(0);
        assertThat(response.getBody().getTotalCost()).isEqualTo(0);
        assertThat(response.getBody().getSelectedTransfers().size()).isEqualTo(0);

    }
}