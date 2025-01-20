package org.example.cheapesttransferroute.controller.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.cheapesttransferroute.controller.model.dto.TransferDTO;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetRouteInput {

    int maxWeight;

    List<TransferDTO> availableTransfers;

}
