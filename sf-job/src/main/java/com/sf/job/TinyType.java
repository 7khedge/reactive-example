package com.sf.job;

import com.google.common.base.Preconditions;

/**
 * Created by adityasofat on 08/02/2016.
 */
public abstract class TinyType<T> {
    private final T value;

    protected TinyType(T value) {
        Preconditions.checkNotNull(value);
        this.value = value;
    }

    public T value() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        return getClass().isInstance(o) && ((TinyType) o).value.equals(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
