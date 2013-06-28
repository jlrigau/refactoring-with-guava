package fr.xebia.xke.dto;

import com.google.common.base.Objects;

public abstract class BaseShortInfo {

    protected Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getName() + " - " + id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof BaseShortInfo)) {
            return false;
        }

        BaseShortInfo other = (BaseShortInfo) obj;

        return !(id == null && other.id == null) && Objects.equal(id, other.id);
    }

}
