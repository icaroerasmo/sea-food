package com.icaroerasmo.seafood.core.repository.common.projections;

import java.time.Instant;

public interface DocumentBaseProjection {
    Instant getId();
    Instant getCreatedAt();
    Instant updatedAt();
}
