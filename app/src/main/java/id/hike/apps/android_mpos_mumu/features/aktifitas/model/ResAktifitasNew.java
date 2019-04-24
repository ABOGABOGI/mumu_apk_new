package id.hike.apps.android_mpos_mumu.features.aktifitas.model;

import java.util.List;

/**
 * Created by M Alvin Syahrin on 4/25/2018.
 */

public class ResAktifitasNew {

    /**
     * data : [{"store_id":"4","outlet_id":"49","is_digital":1,"type_item":"PLS","updated_date":1524671999000,"product_name":"SI20","total_payment":20350,"id_tr_sales":"646","status":5},{"store_id":"4","outlet_id":"49","is_digital":1,"type_item":"PLS","updated_date":1524671353000,"product_name":"SI20","total_payment":20350,"id_tr_sales":"639","status":5}]
     * message : Success
     */

    private String message;
    private List<DataAktifitas> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataAktifitas> getData() {
        return data;
    }

    public void setData(List<DataAktifitas> data) {
        this.data = data;
    }

    public static class DataAktifitas {
        /**
         * store_id : 4
         * outlet_id : 49
         * is_digital : 1
         * type_item : PLS
         * updated_date : 1524671999000
         * product_name : SI20
         * total_payment : 20350
         * id_tr_sales : 646
         * status : 5
         */

        private String store_id;
        private String outlet_id;
        private int is_digital;
        private String type_item;
        private long updated_date;
        private String product_name;
        private Double total_payment;
        private String id_tr_sales;
        private int status;

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getOutlet_id() {
            return outlet_id;
        }

        public void setOutlet_id(String outlet_id) {
            this.outlet_id = outlet_id;
        }

        public int getIs_digital() {
            return is_digital;
        }

        public void setIs_digital(int is_digital) {
            this.is_digital = is_digital;
        }

        public String getType_item() {
            return type_item;
        }

        public void setType_item(String type_item) {
            this.type_item = type_item;
        }

        public long getUpdated_date() {
            return updated_date;
        }

        public void setUpdated_date(long updated_date) {
            this.updated_date = updated_date;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public Double getTotal_payment() {
            return total_payment;
        }

        public void setTotal_payment(Double total_payment) {
            this.total_payment = total_payment;
        }

        public String getId_tr_sales() {
            return id_tr_sales;
        }

        public void setId_tr_sales(String id_tr_sales) {
            this.id_tr_sales = id_tr_sales;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
