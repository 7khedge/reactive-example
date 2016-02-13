package com.sf.delta.domain;

/**
 * Created by adityasofat on 12/02/2016.
 */
public enum DeltaOperation {
    NO_CHANGE,
    PENDING_CREATE,
    PENDING_UPDATE,
    PENDING_DELETE,
    CREATE,
    UPDATE,
    DELETE;
}
