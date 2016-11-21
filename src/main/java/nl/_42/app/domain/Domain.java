package nl._42.app.domain;

import nl._42.app.shared.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Domain extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
