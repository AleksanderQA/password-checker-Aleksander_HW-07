package dto;

public class OrderDto {
    private String status;
    private int courierId;
    private String customerName;
    private String customerPhone;
    private String comment;
    int id;

    public OrderDto(String status, int courierId, String customerName, String customerPhone, String comment, int id) {
        this.status = status;
        this.courierId = courierId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.comment = comment;
        this.id = id;
    }

    public OrderDto() {
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(int id) {
        this.id = id;
    }
}
