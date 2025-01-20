package org.example.cheapesTransferRoute.service;

import org.example.cheapesTransferRoute.service.model.CalculatedRoute;
import org.example.cheapesTransferRoute.service.model.Transfer;
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
    public void whenEmptyTransfers_thenReturnEmptyCalculatedRoute() {
        Random rand = new Random();
        int maxWeight = rand.nextInt(1000) + 1;
        List<Transfer> transferList = new ArrayList<>();

        CalculatedRoute result = transferRouteService.calculateRoute(maxWeight, transferList);
        assertTrue(result.getTransfers().size() == 0);
        assertEquals(0, result.getTotalCost());
        assertEquals(0, result.getTotalWight());
    }
}
