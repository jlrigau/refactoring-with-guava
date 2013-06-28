package fr.xebia.xke.dto;

import fr.xebia.xke.model.Task;

public class TaskShortInfo extends BaseShortInfo {

    private Long productId;

    private String taskCode;

    public TaskShortInfo(Task task) {
        id = task.getId();
        productId = task.getProductId();
        taskCode = task.getTaskCode();
    }

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

}
