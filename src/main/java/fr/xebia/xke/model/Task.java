package fr.xebia.xke.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Task extends BaseEntity {

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "TASK_CODE")
    private String taskCode;

    @Column(name = "ASSIGNED_USER")
    private String assignedUser;

    @Column(name = "ASSIGNED_TEAM")
    private String assignedTeam;

    @Column(name = "ASSIGNED_ROLE")
    private String assignedRole;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public String getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(String assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    public String getAssignedRole() {
        return assignedRole;
    }

    public void setAssignedRole(String assignedRole) {
        this.assignedRole = assignedRole;
    }

}
