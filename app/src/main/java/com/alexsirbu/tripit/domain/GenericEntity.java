package com.alexsirbu.tripit.domain;

public abstract class GenericEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
