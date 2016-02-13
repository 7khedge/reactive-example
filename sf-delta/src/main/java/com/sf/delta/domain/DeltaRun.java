package com.sf.delta.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * Created by adityasofat on 10/02/2016.
 */
public class DeltaRun {
    private final Long deltaRunId;
    private final String deltaRunName;
    private final LocalDateTime startDateTime;
    private final Optional<LocalDateTime> stopDateTime;

    public DeltaRun(Long deltaRunId, String deltaRunName, LocalDateTime startDateTime, Optional<LocalDateTime> stopDateTime) {
        this.startDateTime = startDateTime;
        this.deltaRunName = deltaRunName;
        this.deltaRunId = deltaRunId;
        this.stopDateTime = getOptionalDate(stopDateTime);
    }

    public Long getDeltaRunId() {
        return deltaRunId;
    }

    public String getDeltaRunName() {
        return deltaRunName;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public Optional<LocalDateTime> getStopDateTime() {
        return getOptionalDate(this.stopDateTime);
    }

    private Optional<LocalDateTime> getOptionalDate(Optional<LocalDateTime>  optionalDate){
        if ( optionalDate.isPresent() ) {
            return Optional.of( this.stopDateTime.get());
        }
        return Optional.empty();
    }
}
