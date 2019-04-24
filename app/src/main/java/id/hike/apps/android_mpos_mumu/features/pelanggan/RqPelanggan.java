package id.hike.apps.android_mpos_mumu.features.pelanggan;

/**
 * Created by getwiz on 13/06/17.
 */

class RqPelanggan {
    static class RqTambahPelanggan {
        String phone, name,email,createdBy;

        public RqTambahPelanggan(String phone, String name, String email, String createdBy) {
            this.phone = phone;
            this.name = name;
            this.email = email;
            this.createdBy = createdBy;
        }
    }

    static class RqGetCustomers {
        String phone, name,email;

        RqGetCustomers(String phone, String name, String email) {
            this.phone = phone;
            this.name = name;
            this.email = email;
        }
    }

    static class RqGenerateToken{
        String grant_type,username,password;

        public RqGenerateToken(String grant_type, String username, String password) {
            this.grant_type = grant_type;
            this.username = username;
            this.password = password;
        }
    }


}
