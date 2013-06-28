package fr.xebia.xke.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Participants {

    /*
     * User participants
     */

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private User salesInitiator;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private User sales;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private User pricer;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private User structurer;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private User swapTrader;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private User cdsTrader;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private User xCcyTrader;

    /*
     * Team participants
     */

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private Team salesInitiatorTeam;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private Team salesTeam;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private Team pricerTeam;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private Team structurerTeam;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private Team swapTraderTeam;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private Team cdsTraderTeam;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private Team xCcyTraderTeam;

    public User getSalesInitiator() {
        return salesInitiator;
    }

    public void setSalesInitiator(User salesInitiator) {
        this.salesInitiator = salesInitiator;
    }

    public User getSales() {
        return sales;
    }

    public void setSales(User sales) {
        this.sales = sales;
    }

    public User getPricer() {
        return pricer;
    }

    public void setPricer(User pricer) {
        this.pricer = pricer;
    }

    public User getStructurer() {
        return structurer;
    }

    public void setStructurer(User structurer) {
        this.structurer = structurer;
    }

    public User getSwapTrader() {
        return swapTrader;
    }

    public void setSwapTrader(User swapTrader) {
        this.swapTrader = swapTrader;
    }

    public User getCdsTrader() {
        return cdsTrader;
    }

    public void setCdsTrader(User cdsTrader) {
        this.cdsTrader = cdsTrader;
    }

    public User getxCcyTrader() {
        return xCcyTrader;
    }

    public void setxCcyTrader(User xCcyTrader) {
        this.xCcyTrader = xCcyTrader;
    }

    public Team getSalesInitiatorTeam() {
        return salesInitiatorTeam;
    }

    public void setSalesInitiatorTeam(Team salesInitiatorTeam) {
        this.salesInitiatorTeam = salesInitiatorTeam;
    }

    public Team getSalesTeam() {
        return salesTeam;
    }

    public void setSalesTeam(Team salesTeam) {
        this.salesTeam = salesTeam;
    }

    public Team getPricerTeam() {
        return pricerTeam;
    }

    public void setPricerTeam(Team pricerTeam) {
        this.pricerTeam = pricerTeam;
    }

    public Team getStructurerTeam() {
        return structurerTeam;
    }

    public void setStructurerTeam(Team structurerTeam) {
        this.structurerTeam = structurerTeam;
    }

    public Team getSwapTraderTeam() {
        return swapTraderTeam;
    }

    public void setSwapTraderTeam(Team swapTraderTeam) {
        this.swapTraderTeam = swapTraderTeam;
    }

    public Team getCdsTraderTeam() {
        return cdsTraderTeam;
    }

    public void setCdsTraderTeam(Team cdsTraderTeam) {
        this.cdsTraderTeam = cdsTraderTeam;
    }

    public Team getxCcyTraderTeam() {
        return xCcyTraderTeam;
    }

    public void setxCcyTraderTeam(Team xCcyTraderTeam) {
        this.xCcyTraderTeam = xCcyTraderTeam;
    }

}
