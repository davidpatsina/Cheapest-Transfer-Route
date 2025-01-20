package org.example.cheapesTransferRoute.service.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CalculatedRoute {
    List<Transfer> transfers;
    int totalCost;
    int totalWight;
}
