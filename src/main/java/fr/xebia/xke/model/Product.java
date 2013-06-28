package fr.xebia.xke.model;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Product extends BaseEntity {

    private String name;

    private BigDecimal nominal;

    private Participants participants;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    public Participants getParticipants() {
        return participants;
    }

    public void setParticipants(Participants participants) {
        this.participants = participants;
    }

}
