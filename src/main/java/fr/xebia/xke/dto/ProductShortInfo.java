package fr.xebia.xke.dto;

import com.google.common.collect.Lists;
import fr.xebia.xke.model.Task;
import fr.xebia.xke.model.User;

import java.math.BigDecimal;
import java.util.List;

public class ProductShortInfo extends BaseShortInfo {

    private String name;

    private BigDecimal nominal;

    private String sales;

    private String pricer;

    private String trader;

    private List<TaskShortInfo> tasks = Lists.newArrayList();

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

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getPricer() {
        return pricer;
    }

    public void setPricer(String pricer) {
        this.pricer = pricer;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public List<TaskShortInfo> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskShortInfo> tasks) {
        this.tasks = tasks;
    }

    private String getParticipantName(User participant) {
        return participant != null ? participant.getName() : null;
    }

}
