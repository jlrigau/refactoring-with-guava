package fr.xebia.xke.dto;

public class TaskShortInfo extends BaseShortInfo {

    private Long productId;

    private String taskCode;

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
