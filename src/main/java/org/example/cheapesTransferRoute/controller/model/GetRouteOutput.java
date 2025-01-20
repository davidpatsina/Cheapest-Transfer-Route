package org.example.cheapesTransferRoute.controller.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.cheapesTransferRoute.controller.model.dto.TransferDTO;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetRouteOutput {
    int totalWeight;
    int totalCost;
    List<TransferDTO> selectedTransfers;
}
