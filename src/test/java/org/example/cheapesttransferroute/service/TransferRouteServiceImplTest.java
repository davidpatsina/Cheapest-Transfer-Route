package org.example.cheapesttransferroute.service;

import org.example.cheapesttransferroute.service.model.CalculatedRoute;
import org.example.cheapesttransferroute.service.model.Transfer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TransferRouteServiceImplTest {

    @InjectMocks
    private TransferRouteServiceImpl transferRouteService;

    @Test
    public void whenAvailableTransfersIsNull_throwNullPointerException() {
        Random rand = new Random();
        int maxWeight = rand.nextInt(1000) + 1;
        assertThrows(NullPointerException.class, () -> transferRouteService.calculateRoute(maxWeight, null));
    }

    @Test
    public void whenEmptyAvailableTransfers_thenReturnEmptyCalculatedRoute() {
        Random rand = new Random();
        int maxWeight = rand.nextInt(1000) + 1;
        List<Transfer> transferList = new ArrayList<>();

        CalculatedRoute result = transferRouteService.calculateRoute(maxWeight, transferList);
        assertTrue(result.getTransfers().size() == 0);
        assertEquals(0, result.getTotalCost());
        assertEquals(0, result.getTotalWeight());
    }

    @Test
    public void whenAvailableTransferIsOneAndWeightIsLessThanMaxWeight_thenReturnCalculatedRoute() {
        Random rand = new Random();

        int maxWeight = 100;
        int weight = 99;
        int cost = 10;

        Transfer transfer = Transfer.builder()
                .weight(weight)
                .cost(cost)
                .build();
        List<Transfer> transferList = new ArrayList<>();
        transferList.add(transfer);

        CalculatedRoute result = transferRouteService.calculateRoute(maxWeight, transferList);
        assertEquals(cost, result.getTotalCost());
        assertEquals(weight, result.getTotalWeight());
        assertEquals(transfer, result.getTransfers().get(0));
        assertTrue(result.getTransfers().size() == 1);
    }

    @Test
    public void whenAvailableTransferIsOneAndWeightIsMoreThanMaxWeight_thenReturnEmptyCalculatedRoute() {
        Random rand = new Random();

        int maxWeight = rand.nextInt(0, 100);
        int weight = rand.nextInt(1, maxWeight) + maxWeight;
        int cost = rand.nextInt();

        Transfer transfer = Transfer.builder()
                .weight(weight)
                .cost(cost)
                .build();
        List<Transfer> transferList = new ArrayList<>();
        transferList.add(transfer);

        CalculatedRoute result = transferRouteService.calculateRoute(maxWeight, transferList);
        assertTrue(result.getTransfers().size() == 0);
        assertEquals(0, result.getTotalCost());
        assertEquals(0, result.getTotalWeight());
    }

    @Test
    public void whenInputIsValid_thenReturnCalculatedRoute() {
        int maxWeight = 15;
        List<Transfer> transferList = new ArrayList<>();
        Transfer transfer1 = Transfer.builder()
                .weight(5)
                .cost(10)
                .build();
        transferList.add(transfer1);
        Transfer transfer2 = Transfer.builder()
                .weight(10)
                .cost(20)
                .build();
        transferList.add(transfer2);
        Transfer transfer3 = Transfer.builder()
                .weight(3)
                .cost(5)
                .build();
        transferList.add(transfer3);
        Transfer transfer4 = Transfer.builder()
                .weight(8)
                .cost(15)
                .build();
        transferList.add(transfer4);
        CalculatedRoute result = transferRouteService.calculateRoute(maxWeight, transferList);
        assertTrue(result.getTransfers().size() == 2);
        assertEquals(30, result.getTotalCost());
        assertEquals(15, result.getTotalWeight());
        assertTrue(result.getTransfers().contains(transfer1));
        assertTrue(result.getTransfers().contains(transfer1));
        assertTrue(result.getTransfers().contains(transfer2));
    }

    @Test
    public void whenInputIsValid1_thenReturnCalculatedRoute() {
        int maxWeight = 10;
        List<Transfer> transferList = new ArrayList<>();
        Transfer transfer1 = Transfer.builder()
                .weight(2)
                .cost(3)
                .build();
        transferList.add(transfer1);
        Transfer transfer2 = Transfer.builder()
                .weight(3)
                .cost(4)
                .build();
        transferList.add(transfer2);
        Transfer transfer3 = Transfer.builder()
                .weight(4)
                .cost(5)
                .build();
        transferList.add(transfer3);
        Transfer transfer4 = Transfer.builder()
                .weight(5)
                .cost(6)
                .build();
        transferList.add(transfer4);
        CalculatedRoute result = transferRouteService.calculateRoute(maxWeight, transferList);
        assertTrue(result.getTransfers().size() == 3);
        assertEquals(13, result.getTotalCost());
        assertEquals(10, result.getTotalWeight());
        assertTrue(result.getTransfers().contains(transfer1));
        assertTrue(result.getTransfers().contains(transfer2));
        assertTrue(result.getTransfers().contains(transfer4));
    }
}
