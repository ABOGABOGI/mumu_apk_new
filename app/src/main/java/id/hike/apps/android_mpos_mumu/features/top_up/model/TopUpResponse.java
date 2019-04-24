package id.hike.apps.android_mpos_mumu.features.top_up.model;

public class TopUpResponse {
    private String deposit_id;
    private String created_at;
    private String updated_at;
    private int nominal;
    private int nominaltrf;
    private String status;
    private String msg;
    private Object content;

    public String getDeposit_id() {
        return deposit_id;
    }

    public void setDeposit_id(String deposit_id) {
        this.deposit_id = deposit_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public int getNominaltrf() {
        return nominaltrf;
    }

    public void setNominaltrf(int nominaltrf) {
        this.nominaltrf = nominaltrf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TopUpResponse{" +
                "deposit_id='" + deposit_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", nominal=" + nominal +
                ", nominaltrf=" + nominaltrf +
                ", status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", content=" + content +
                '}';
    }
}
