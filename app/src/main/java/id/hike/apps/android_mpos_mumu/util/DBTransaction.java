package id.hike.apps.android_mpos_mumu.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqBuyProduct;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelAppInfo;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksi;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksiPpob;

/**
 * Created by root on 14/07/17.
 */

public class DBTransaction extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "db_transaction.db";

    public static final String TRANSA_TABLE_NAME = "transa";


    public static final String TRANSA_COLUMN_TRANSAID = "transa_id";

    //jadi sebelum kostumer membatalkan transaksi. quequeid ini sama
    public static final String TRANSA_COLUMN_TRANSAQUEUEID = "queque_id";

    public static final String TRANSA_COLUMN_PRODUCTNAME = "product_name";
    public static final String TRANSA_COLUMN_PRODUCTID = "product_id";
    public static final String TRANSA_COLUMN_KASIR = "kasir";
    public static final String TRANSA_COLUMN_KASIRID = "kasir_id";
    public static final String TRANSA_COLUMN_KOSTUMER = "kostumer";
    public static final String TRANSA_COLUMN_KOSTUMERID = "kostumer_id";

    public static final String TRANSA_COLUMN_TRANSITEMSTATUS = "transaction_item_status"; //transaction item status
    public static final String TRANSA_COLUMN_TRANSASTATUS = "transaction_status"; //transaction status

    public static final String TRANSA_COLUMN_BASEPRICE = "baseprice";
    public static final String TRANSA_COLUMN_MAXPRICE = "maxprice";
    public static final String TRANSA_COLUMN_MINPRICE = "minprice";
    public static final String TRANSA_COLUMN_SALESPRICE = "salesprice";
    public static final String TRANSA_COLUMN_TOTALPCNTDISC = "totalpcntdisc";
    public static final String TRANSA_COLUMN_TOTALPRICEDISC = "totalpricedisc";

    public static final String TRANSA_COLUMN_STOCK = "server_stock";
    public static final String TRANSA_COLUMN_BASELINESTOCK = "baseline_stock";
    public static final String TRANSA_COLUMN_JUMLAHBELI = "jumlah";
    public static final String TRANSA_COLUMN_PICTURLPATH = "pict_url_path";
    public static final String TRANSA_COLUMN_CATEGORY = "category";
    public static final String TRANSA_COLUMN_BRAND = "brand";
    public static final String TRANSA_COLUMN_SATUAN = "satuan";
    public static final String TRANSA_COLUMN_UPTIME = "uptime";
    public static final String TRANSA_COLUMN_PROMO_ID = "promo_id";

    public static final String TRANSA_COLUMN_DETAILID = "detail_id";

    //---------------------------TABEL APPS INFO
    public static final String TRANSA_TABLE_APPS_INFO = "apps_info";
    private String TRANSA_COLUMN_APPSID = "apps_id";
    private String TRANSA_COLUMN_DEVICEID = "device_id";
    private String TRANSA_COLUMN_IMEI = "imei";

    //---------------------------TABEL TRAANSAKSI PPOB
    public static final String PPOB_TRANS_TABLE = "ppob_transaction";
    //jadi sebelum kostumer membatalkan transaksi. quequeid ini sama
    public static final String PPOB_TRANS_COLUMN_TRANSAID = "transa_id";
    public static final String PPOB_TRANS_COLUMN_TRANSAQUEUEID = "queque_id";

    public static final String PPOB_TRANS_COLUMN_PRODUCTID = "product_id";
    public static final String PPOB_TRANS_COLUMN_PRODUCTNAME = "product_name";
    public static final String PPOB_TRANS_COLUMN_KASIR = "kasir";
    public static final String PPOB_TRANS_COLUMN_KASIRID = "kasir_id";
    public static final String PPOB_TRANS_COLUMN_KOSTUMER = "kostumer";
    public static final String PPOB_TRANS_COLUMN_KOSTUMERID = "kostumer_id";

    public static final String PPOB_TRANS_COLUMN_TRANSITEMSTATUS = "transaction_item_status"; //transaction item status
    public static final String PPOB_TRANS_COLUMN_TRANSASTATUS = "transaction_status"; //transaction status
    public static final String PPOB_TRANS_COLUMN_JUMLAHBELI = "jumlah";

    public static final String PPOB_TRANS_COLUMN_BASEPRICE = "baseprice";
    public static final String PPOB_TRANS_COLUMN_MAXPRICE = "maxprice";
    public static final String PPOB_TRANS_COLUMN_MINPRICE = "minprice";
    public static final String PPOB_TRANS_COLUMN_SALESPRICE = "salesprice";

    public static final String PPOB_TRANS_COLUMN_CATEGORY = "category";
    public static final String PPOB_TRANS_COLUMN_TYPE = "type";
    public static final String PPOB_TRANS_COLUMN_DESCRIPTION = "description";
    public static final String PPOB_TRANS_COLUMN_SNITEM = "sn_item";

    public static final String PPOB_TRANS_COLUMN_TOTAL_BELANJA = "totalBelanja";
    public static final String PPOB_TRANS_COLUMN_DISCOUNT = "discount";
    public static final String PPOB_TRANS_COLUMN_SUBTOTAL = "subtotal";


    public DBTransaction(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TRANSA_TABLE_NAME +
                        "(" + TRANSA_COLUMN_TRANSAID + " integer primary key, "
                        + TRANSA_COLUMN_TRANSAQUEUEID + " text,"
                        + TRANSA_COLUMN_PRODUCTNAME + " text,"
                        + TRANSA_COLUMN_KASIR + " text,"
                        + TRANSA_COLUMN_KASIRID + " integer,"
                        + TRANSA_COLUMN_PRODUCTID + " integer," +
                        TRANSA_COLUMN_KOSTUMER + " text," +
                        TRANSA_COLUMN_KOSTUMERID + " integer," +

                        TRANSA_COLUMN_TRANSITEMSTATUS + " integer," +
                        TRANSA_COLUMN_TRANSASTATUS + " integer," +
                        TRANSA_COLUMN_TOTALPCNTDISC + " real," +
                        TRANSA_COLUMN_TOTALPRICEDISC + " real," +
                        TRANSA_COLUMN_BASEPRICE + " integer," +
                        TRANSA_COLUMN_MAXPRICE + " integer," +
                        TRANSA_COLUMN_MINPRICE + " integer default 0," +
                        TRANSA_COLUMN_SALESPRICE + " integer," +

                        TRANSA_COLUMN_STOCK + " integer," +
                        TRANSA_COLUMN_BASELINESTOCK + " integer," +

                        TRANSA_COLUMN_JUMLAHBELI + " integer," +

                        TRANSA_COLUMN_DETAILID + " integer default 0," +
                        TRANSA_COLUMN_PICTURLPATH + " text," +
                        TRANSA_COLUMN_CATEGORY + " text," +
                        TRANSA_COLUMN_BRAND + " text," +
                        TRANSA_COLUMN_SATUAN + " text," +
                        TRANSA_COLUMN_UPTIME + " text," +
                        TRANSA_COLUMN_PROMO_ID + " text)");

        db.execSQL(
                "create table " + PPOB_TRANS_TABLE +
                        "("
                        + PPOB_TRANS_COLUMN_TRANSAID + " integer primary key autoincrement, "
                        + PPOB_TRANS_COLUMN_TRANSAQUEUEID + " text,"
                        + PPOB_TRANS_COLUMN_PRODUCTNAME + " text,"
                        + PPOB_TRANS_COLUMN_KASIR + " text,"
                        + PPOB_TRANS_COLUMN_KASIRID + " integer,"
                        + PPOB_TRANS_COLUMN_PRODUCTID + " text," +
                        PPOB_TRANS_COLUMN_KOSTUMER + " text," +
                        PPOB_TRANS_COLUMN_KOSTUMERID + " integer," +

                        PPOB_TRANS_COLUMN_TRANSITEMSTATUS + " integer," +
                        PPOB_TRANS_COLUMN_TRANSASTATUS + " integer," +
                        PPOB_TRANS_COLUMN_JUMLAHBELI + " integer,"+
                        PPOB_TRANS_COLUMN_BASEPRICE + " integer," +
                        PPOB_TRANS_COLUMN_MAXPRICE + " integer," +
                        PPOB_TRANS_COLUMN_MINPRICE + " integer default 0," +
                        PPOB_TRANS_COLUMN_SALESPRICE + " integer," +

                        PPOB_TRANS_COLUMN_CATEGORY + " text," +
                        PPOB_TRANS_COLUMN_TYPE + " text," +
                        PPOB_TRANS_COLUMN_DESCRIPTION + " text," +
                        PPOB_TRANS_COLUMN_TOTAL_BELANJA + " text," +
                        PPOB_TRANS_COLUMN_DISCOUNT + " text," +
                        PPOB_TRANS_COLUMN_SUBTOTAL + " text," +
                        PPOB_TRANS_COLUMN_SNITEM + " text)"
        );

        db.execSQL(
                "create table " + TRANSA_TABLE_APPS_INFO +
                        "( "
                        + TRANSA_COLUMN_APPSID + " text,"
                        + TRANSA_COLUMN_DEVICEID + " text,"
                        + TRANSA_COLUMN_IMEI + " text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TRANSA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSA_TABLE_APPS_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + PPOB_TRANS_TABLE);
        onCreate(db);
    }



    public void insertTransaction(
            String productName, long productId, String transaQuequeId,
            String kasir, int kasirId, String kostumer, int kostumerId, double basePrice,
            double pcntPrice, double discPrice,
            int transItemStatus, int transaStatusFlag,
            double salesPrice, Integer stock, Integer baselineStock,
            long jumlahBeli, String pictUrlPath, String category,
            String brand, String satuan, String uptime, long maxPrice, long minPrice,
            String promoId
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSA_COLUMN_PRODUCTNAME, productName);
        contentValues.put(TRANSA_COLUMN_PRODUCTID, productId);
        contentValues.put(TRANSA_COLUMN_TRANSAQUEUEID, transaQuequeId);
        contentValues.put(TRANSA_COLUMN_KASIR, kasir);
        contentValues.put(TRANSA_COLUMN_KASIRID, kasirId);
        contentValues.put(TRANSA_COLUMN_KOSTUMER, kostumer);
        contentValues.put(TRANSA_COLUMN_KOSTUMERID, kostumerId);

        contentValues.put(TRANSA_COLUMN_TRANSASTATUS, transaStatusFlag);
        contentValues.put(TRANSA_COLUMN_TRANSITEMSTATUS, transItemStatus);

        contentValues.put(TRANSA_COLUMN_TOTALPCNTDISC, pcntPrice);
        contentValues.put(TRANSA_COLUMN_TOTALPRICEDISC, discPrice);

        contentValues.put(TRANSA_COLUMN_BASEPRICE, basePrice);
        contentValues.put(TRANSA_COLUMN_MAXPRICE, maxPrice);
        contentValues.put(TRANSA_COLUMN_MINPRICE, minPrice);
        contentValues.put(TRANSA_COLUMN_SALESPRICE, salesPrice);

        contentValues.put(TRANSA_COLUMN_STOCK, stock);
        contentValues.put(TRANSA_COLUMN_BASELINESTOCK, baselineStock);
        contentValues.put(TRANSA_COLUMN_JUMLAHBELI, jumlahBeli);
        contentValues.put(TRANSA_COLUMN_PICTURLPATH, pictUrlPath);
        contentValues.put(TRANSA_COLUMN_CATEGORY, category);
        contentValues.put(TRANSA_COLUMN_BRAND, brand);
        contentValues.put(TRANSA_COLUMN_SATUAN, satuan);
        contentValues.put(TRANSA_COLUMN_UPTIME, uptime);
        contentValues.put(TRANSA_COLUMN_PROMO_ID, promoId);

        db.insertOrThrow(TRANSA_TABLE_NAME, null, contentValues);

        db.close();
    }

    public void insertTransactionWithDetailId(
            String productName, long productId, String transaQuequeId,
            String kasir, int kasirId, String kostumer, int kostumerId, double basePrice,
            double pcntPrice, double discPrice,
            int transItemStatus, int transaStatusFlag,
            double salesPrice, Long stock, Integer baselineStock,
            long jumlahBeli, String pictUrlPath, String category,
            String brand, String satuan, String uptime, long detailId, long maxPrice, long minPrice
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSA_COLUMN_PRODUCTNAME, productName);
        contentValues.put(TRANSA_COLUMN_PRODUCTID, productId);
        contentValues.put(TRANSA_COLUMN_TRANSAQUEUEID, transaQuequeId);
        contentValues.put(TRANSA_COLUMN_KASIR, kasir);
        contentValues.put(TRANSA_COLUMN_KASIR, kasirId);
        contentValues.put(TRANSA_COLUMN_KOSTUMER, kostumer);
        contentValues.put(TRANSA_COLUMN_KOSTUMERID, kostumerId);

        contentValues.put(TRANSA_COLUMN_TRANSASTATUS, transaStatusFlag);
        contentValues.put(TRANSA_COLUMN_TRANSITEMSTATUS, transItemStatus);

        contentValues.put(TRANSA_COLUMN_TOTALPCNTDISC, pcntPrice);
        contentValues.put(TRANSA_COLUMN_TOTALPRICEDISC, discPrice);

        contentValues.put(TRANSA_COLUMN_BASEPRICE, basePrice);
        contentValues.put(TRANSA_COLUMN_MAXPRICE, maxPrice);
        contentValues.put(TRANSA_COLUMN_MINPRICE, minPrice);
        contentValues.put(TRANSA_COLUMN_SALESPRICE, salesPrice);

        contentValues.put(TRANSA_COLUMN_STOCK, stock);
        contentValues.put(TRANSA_COLUMN_BASELINESTOCK, baselineStock);
        contentValues.put(TRANSA_COLUMN_JUMLAHBELI, jumlahBeli);
        contentValues.put(TRANSA_COLUMN_PICTURLPATH, pictUrlPath);
        contentValues.put(TRANSA_COLUMN_CATEGORY, category);
        contentValues.put(TRANSA_COLUMN_BRAND, brand);
        contentValues.put(TRANSA_COLUMN_SATUAN, satuan);
        contentValues.put(TRANSA_COLUMN_UPTIME, uptime);

        contentValues.put(TRANSA_COLUMN_DETAILID, detailId);

        db.insertOrThrow(TRANSA_TABLE_NAME, null, contentValues);

        db.close();
    }

    public String getTotalHarga(int kostumerId, String recentTransId) {

        long totalHarga = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_KOSTUMERID + "=" + kostumerId + " AND " + TRANSA_COLUMN_TRANSAQUEUEID + " ='" + recentTransId + "'", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            totalHarga += res.getLong(res.getColumnIndex(TRANSA_COLUMN_SALESPRICE)) *
                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_JUMLAHBELI));
            res.moveToNext();
        }

        res.close();
        db.close();
        return UnitConversion.format2Rupiah(totalHarga);
    }

    public Long getJumlahBeli(String recentTransId, Long productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select " + TRANSA_COLUMN_JUMLAHBELI + " from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_PRODUCTID + "=" + productId + " AND " + TRANSA_COLUMN_TRANSAQUEUEID + " ='" + recentTransId + "'", null);
        res.moveToFirst();

        Long jumlahBeli = 0L;
        while (res.isAfterLast() == false) {
            jumlahBeli = res.getLong(res.getColumnIndex(TRANSA_COLUMN_JUMLAHBELI));
            res.moveToNext();
        }

        res.close();
        db.close();

        if (jumlahBeli == null) {
            jumlahBeli = 0L;
        }
        return jumlahBeli;
    }

    ;

    public long getTotalHargaInLong(int kostumerId, String recentTransId) {

        long totalHarga = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_KOSTUMERID + "=" + kostumerId + " AND " + TRANSA_COLUMN_TRANSAQUEUEID + " ='" + recentTransId + "'", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            totalHarga += res.getLong(res.getColumnIndex(TRANSA_COLUMN_SALESPRICE)) *
                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_JUMLAHBELI));
            res.moveToNext();
        }

        res.close();
        db.close();
        return totalHarga;
    }

    public int getJumlahBarang(int productId) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_PRODUCTID + "=" + productId, null);
        res.moveToFirst();

        int pId = 0;

        while (res.isAfterLast() == false) {
            pId = res.getInt(res.getColumnIndex(TRANSA_COLUMN_JUMLAHBELI));
            res.moveToNext();
        }

        res.close();
        db.close();
        return pId;
    }

    public int deleteItem(int productId) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_PRODUCTID + "=" + productId, null);
        res.moveToFirst();

        int pId = 0;

        while (res.isAfterLast() == false) {
            pId = res.getInt(res.getColumnIndex(TRANSA_COLUMN_JUMLAHBELI));
            res.moveToNext();
        }

        res.close();
        db.close();
        return pId;
    }

    public boolean isItemDeletedOnTheTrans(int productId, String transaQueue) {
        Boolean result = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TRANSA_TABLE_NAME + " where "
                + TRANSA_COLUMN_PRODUCTID + "=" + productId + " AND " + TRANSA_COLUMN_TRANSAQUEUEID + " ='" + transaQueue + "'" + " AND " + TRANSA_COLUMN_TRANSITEMSTATUS + "=" + Cfg.TIS_DELETE, null);

        if (res.getCount() > 0) {
            result = true;
        } else {
            result = false;
        }

        res.close();
        db.close();
        return result;
    }

    public void updateStock(Long stock, String transaQueue, Long productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("update " + TRANSA_TABLE_NAME + " set " + TRANSA_COLUMN_STOCK + "=" + stock + " where " +
                TRANSA_COLUMN_TRANSAQUEUEID + "='" + transaQueue + "' AND " + TRANSA_COLUMN_PRODUCTID + "=" + productId, null);

        //moveToFirst ini harus ada untuk update lewat rawQuery
        res.moveToFirst();
        res.close();
        db.close();
    }

    public void updateStockDanJumlah(Long stock, Long jumlahBeli, String transaQueue, Long productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("update " + TRANSA_TABLE_NAME + " set " + TRANSA_COLUMN_STOCK + "=" + stock + "," +
                TRANSA_COLUMN_JUMLAHBELI + "=" + jumlahBeli
                + " where " +
                TRANSA_COLUMN_TRANSAQUEUEID + "='" + transaQueue + "' AND " + TRANSA_COLUMN_PRODUCTID + "=" + productId, null);

        //moveToFirst ini harus ada untuk update lewat rawQuery
        res.moveToFirst();
        res.close();
        db.close();
    }

    public void updateStockDJumlahDDetailId(Long detailId, Long stock, Long jumlahBeli, String transaQueue, Long productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("update " + TRANSA_TABLE_NAME + " set " + TRANSA_COLUMN_STOCK + "=" + stock + "," +
                TRANSA_COLUMN_JUMLAHBELI + "=" + jumlahBeli + "," +
                TRANSA_COLUMN_DETAILID + "=" + detailId
                + " where " +
                TRANSA_COLUMN_TRANSAQUEUEID + "='" + transaQueue + "' AND " + TRANSA_COLUMN_PRODUCTID + "=" + productId, null);

        //moveToFirst ini harus ada untuk update lewat rawQuery
        res.moveToFirst();
        res.close();
        db.close();
    }

    public void updateJumlah(Integer jumlahBeli, String transaQueue, int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        /*ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSA_COLUMN_JUMLAHBELI , jumlahBeli);

        db.update(TRANSA_TABLE_NAME,
                contentValues,
                TRANSA_COLUMN_TRANSAQUEUEID + " = '?' AND " + TRANSA_COLUMN_PRODUCTID + " = ?",
                new String[]{transaQueue, String.valueOf(productId)});*/


        Cursor res = db.rawQuery("update " + TRANSA_TABLE_NAME + " set " + TRANSA_COLUMN_JUMLAHBELI + "=" + jumlahBeli + " where " +
                TRANSA_COLUMN_TRANSAQUEUEID + "='" + transaQueue + "' AND " + TRANSA_COLUMN_PRODUCTID + "=" + productId, null);

        //moveToFirst ini harus ada untuk update lewat rawQuery
        res.moveToFirst();
        res.close();
        db.close();
    }

    public void updateJumlah2(long jumlahBeli, String transaQueue, long productId, long detailId) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("update " + TRANSA_TABLE_NAME + " set " + TRANSA_COLUMN_JUMLAHBELI + "=" + jumlahBeli + "," +
                TRANSA_COLUMN_DETAILID + "=" + detailId + " where " +
                TRANSA_COLUMN_TRANSAQUEUEID + "='" + transaQueue + "' AND " + TRANSA_COLUMN_PRODUCTID + "=" + productId, null);

        //moveToFirst ini harus ada untuk update lewat rawQuery
        res.moveToFirst();
        res.close();
        db.close();
    }


    boolean result;

    public boolean checkProductExistence(int productId, String queueId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TRANSA_TABLE_NAME + " where "
                + TRANSA_COLUMN_PRODUCTID + "=" + productId + " AND " + TRANSA_COLUMN_TRANSAQUEUEID + " ='" + queueId + "'", null);

        if (res.getCount() > 0) {
            result = true;
        } else {
            result = false;
        }

        res.close();
        db.close();
        return result;
    }

    public boolean checkExistingPpob(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + PPOB_TRANS_TABLE + " where "
                + PPOB_TRANS_COLUMN_CATEGORY + "='" + Cfg.ppobCategory + "' ", null);

        if (res.getCount() > 0) {
            result = true;
        } else {
            result = false;
        }

        res.close();
        db.close();
        return result;

    }

    public List<ModelTransaksiPpob> getPpobTransactionList(String transQueueId) {
        List<ModelTransaksiPpob> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + PPOB_TRANS_TABLE + " where " + PPOB_TRANS_COLUMN_TRANSAID + "='" + transQueueId + "'", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            ModelTransaksiPpob ppob = new ModelTransaksiPpob();
            ppob.setTransaId(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_TRANSAID)));
            ppob.setProductId(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_PRODUCTID )));
            ppob.setType(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_TYPE)));
            ppob.setDescription(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_DESCRIPTION)));
            ppob.setSnItem(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_SNITEM)));

            ppob.setProductName(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_PRODUCTNAME)));

            ppob.setJumlahBeli(res.getInt(res.getColumnIndex(PPOB_TRANS_COLUMN_JUMLAHBELI)));
            ppob.setTransaStatusFlag(res.getInt(res.getColumnIndex(PPOB_TRANS_COLUMN_TRANSASTATUS)));
            ppob.setTransItemStatusFlag(res.getInt(res.getColumnIndex(PPOB_TRANS_COLUMN_TRANSITEMSTATUS)));

            ppob.setBasePrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_BASEPRICE)));
            ppob.setMaxPrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_MAXPRICE)));
            ppob.setMinPrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_MINPRICE)));
            ppob.setSalesPrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_SALESPRICE)));

            ppob.setTotalBelanja(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_TOTAL_BELANJA)));
            ppob.setDiscount(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_DISCOUNT)));
            ppob.setSubtotal(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_SUBTOTAL)));
            list.add(ppob);
            res.moveToNext();

        }
        res.close();
        db.close();

        return list;
    }

    public ModelTransaksiPpob getOnePpobTransactionById(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+PPOB_TRANS_TABLE+" where "+PPOB_TRANS_COLUMN_TRANSAID+" = '"+id+"'", null);
        res.moveToFirst();
        ModelTransaksiPpob ppob = new ModelTransaksiPpob();
        if (res.getCount() > 0) {
            ppob.setTransaId(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_TRANSAID)));
            ppob.setProductId(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_PRODUCTID )));
            ppob.setType(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_TYPE)));
            ppob.setDescription(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_DESCRIPTION)));
            ppob.setSnItem(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_SNITEM)));

            ppob.setProductName(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_PRODUCTNAME)));

            ppob.setJumlahBeli(res.getInt(res.getColumnIndex(PPOB_TRANS_COLUMN_JUMLAHBELI)));
            ppob.setTransaStatusFlag(res.getInt(res.getColumnIndex(PPOB_TRANS_COLUMN_TRANSASTATUS)));
            ppob.setTransItemStatusFlag(res.getInt(res.getColumnIndex(PPOB_TRANS_COLUMN_TRANSITEMSTATUS)));

            ppob.setBasePrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_BASEPRICE)));
            ppob.setMaxPrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_MAXPRICE)));
            ppob.setMinPrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_MINPRICE)));
            ppob.setSalesPrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_SALESPRICE)));

            ppob.setTotalBelanja(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_TOTAL_BELANJA)));
            ppob.setDiscount(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_DISCOUNT)));
            ppob.setSubtotal(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_SUBTOTAL)));

        }
        return ppob;
    }

    public ModelTransaksiPpob getOnePpobTransaction(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+PPOB_TRANS_TABLE+" limit 1", null);
        res.moveToFirst();
        ModelTransaksiPpob ppob = new ModelTransaksiPpob();
        if (res.getCount() > 0) {
            ppob.setTransaId(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_TRANSAID)));
            ppob.setProductId(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_PRODUCTID )));
            ppob.setType(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_TYPE)));
            ppob.setDescription(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_DESCRIPTION)));
            ppob.setSnItem(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_SNITEM)));

            ppob.setProductName(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_PRODUCTNAME)));

            ppob.setJumlahBeli(res.getInt(res.getColumnIndex(PPOB_TRANS_COLUMN_JUMLAHBELI)));
            ppob.setTransaStatusFlag(res.getInt(res.getColumnIndex(PPOB_TRANS_COLUMN_TRANSASTATUS)));
            ppob.setTransItemStatusFlag(res.getInt(res.getColumnIndex(PPOB_TRANS_COLUMN_TRANSITEMSTATUS)));

            ppob.setBasePrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_BASEPRICE)));
            ppob.setMaxPrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_MAXPRICE)));
            ppob.setMinPrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_MINPRICE)));
            ppob.setSalesPrice(res.getLong(res.getColumnIndex(PPOB_TRANS_COLUMN_SALESPRICE)));

            ppob.setTotalBelanja(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_TOTAL_BELANJA)));
            ppob.setDiscount(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_DISCOUNT)));
            ppob.setSubtotal(res.getString(res.getColumnIndex(PPOB_TRANS_COLUMN_SUBTOTAL)));

        }
        return ppob;
    }

    public void insertPpobTransaction(
            String transId,
            String transaQuequeId,
            String productId,
            String productName,
            String kasir,
            int kasirId,
            String kostumer,
            int kostumerId,
            int transItemStatus,
            int transaStatusFlag,
            int jumlahBeli,
            Long basePrice,
            Long maxPrice,
            Long minPrice,
            Long salesPrice,
            String type,
            String description,
            String snItem,
            String category,
            String totalBelanja,
            String discount,
            String subtotal

    ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PPOB_TRANS_COLUMN_TRANSAID, transId);
        contentValues.put(PPOB_TRANS_COLUMN_TRANSAQUEUEID, transaQuequeId);

        contentValues.put(PPOB_TRANS_COLUMN_PRODUCTID, productId);
        contentValues.put(PPOB_TRANS_COLUMN_PRODUCTNAME, productName);
        contentValues.put(PPOB_TRANS_COLUMN_KASIR, kasir);
        contentValues.put(PPOB_TRANS_COLUMN_KASIRID, kasirId);
        contentValues.put(PPOB_TRANS_COLUMN_KOSTUMER, kostumer);
        contentValues.put(PPOB_TRANS_COLUMN_KOSTUMERID, kostumerId);

        contentValues.put(PPOB_TRANS_COLUMN_TRANSITEMSTATUS, transItemStatus);
        contentValues.put(PPOB_TRANS_COLUMN_TRANSASTATUS, transaStatusFlag);
        contentValues.put(PPOB_TRANS_COLUMN_JUMLAHBELI, jumlahBeli);

        contentValues.put(PPOB_TRANS_COLUMN_BASEPRICE, basePrice);
        contentValues.put(PPOB_TRANS_COLUMN_MAXPRICE, maxPrice);
        contentValues.put(PPOB_TRANS_COLUMN_MINPRICE, minPrice);
        contentValues.put(PPOB_TRANS_COLUMN_SALESPRICE, salesPrice);

        contentValues.put(PPOB_TRANS_COLUMN_TYPE, type);
        contentValues.put(PPOB_TRANS_COLUMN_DESCRIPTION, description);
        contentValues.put(PPOB_TRANS_COLUMN_SNITEM, snItem);
        contentValues.put(PPOB_TRANS_COLUMN_CATEGORY, category);

        contentValues.put(PPOB_TRANS_COLUMN_TOTAL_BELANJA, totalBelanja);
        contentValues.put(PPOB_TRANS_COLUMN_DISCOUNT, discount);
        contentValues.put(PPOB_TRANS_COLUMN_SUBTOTAL, subtotal);

        db.insertOrThrow(PPOB_TRANS_TABLE, null, contentValues);
        db.close();

    }

    public void clearUnusedTransaksiPPOB(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("delete from " + PPOB_TRANS_TABLE + " where " + PPOB_TRANS_COLUMN_TRANSAID + "='newTrans'", null);
        res.moveToFirst();
        res.close();
        db.close();
    }

    public void updateIdTransaksiPPOB(String transId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("update "+PPOB_TRANS_TABLE+
                " set "+PPOB_TRANS_COLUMN_TRANSAID+" = "+transId+" where "+PPOB_TRANS_COLUMN_TRANSAID+" = 'newTrans'",null);
        res.moveToFirst();
        res.close();
        db.close();
    }

    public boolean changeTransactionFlag(int tis, int ts, String transactionId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSA_COLUMN_TRANSITEMSTATUS, tis);
        contentValues.put(TRANSA_COLUMN_TRANSASTATUS, ts);
        db.update(TRANSA_TABLE_NAME, contentValues, "queque_id = ? ", new String[]{transactionId});

        db.close();
        return true;
    }

    public void updateTotalBayarPpob(String id, Long totalBelanja) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(
                "update "+ PPOB_TRANS_TABLE +
                        " set "+
                        PPOB_TRANS_COLUMN_TOTAL_BELANJA + "='" + totalBelanja +"' "+
                        " where " + PPOB_TRANS_COLUMN_TRANSAID + "='" + id +"'", null);
        res.moveToFirst();
        res.close();
        db.close();
    }

    /**
     * @param tis
     * @param ts
     * @param localTransId: id sqlite
     * @return
     */
    public boolean changeTransactionFlag(int tis, int ts, int localTransId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSA_COLUMN_TRANSITEMSTATUS, tis);
        contentValues.put(TRANSA_COLUMN_TRANSASTATUS, ts);
        db.update(TRANSA_TABLE_NAME, contentValues, "transa_id = ? ", new String[]{String.valueOf(localTransId)});
        db.close();

        return true;
    }

    public boolean updateKostumer(String namaKostumer, long kostumerId, String queueId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSA_COLUMN_KOSTUMER, namaKostumer);
        contentValues.put(TRANSA_COLUMN_KOSTUMERID, kostumerId);
        db.update(TRANSA_TABLE_NAME, contentValues, "queque_id = ? ", new String[]{queueId});

        db.close();
        return true;
    }

    public int getBaselineStockByTransId(int transaId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select " + TRANSA_COLUMN_BASELINESTOCK + " from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_TRANSAID + " =" + transaId, null);
        res.moveToFirst();

        int baselineStock = 0;
        while (res.isAfterLast() == false) {
            baselineStock = res.getInt(res.getColumnIndex(TRANSA_COLUMN_BASELINESTOCK));
            res.moveToNext();
        }

        res.close();
        db.close();
        return baselineStock;
    }

    public int getStockByTransId(int transaId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select " + TRANSA_COLUMN_STOCK + " from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_TRANSAID + " =" + transaId, null);
        res.moveToFirst();

        int stock = 0;
        while (res.isAfterLast() == false) {
            stock = res.getInt(res.getColumnIndex(TRANSA_COLUMN_STOCK));
            res.moveToNext();
        }

        res.close();
        db.close();
        return stock;
    }

    public void deleteTransactionList(String transaQueue) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("delete from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_TRANSAQUEUEID + "=" + transaQueue, null);
        res.moveToFirst();
        res.close();

        db.close();
    }

    public boolean updateTransactionStatus(String transaStatus, String transItemStatus, int transaId) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSA_COLUMN_TRANSASTATUS, transaStatus);
        contentValues.put(TRANSA_COLUMN_TRANSITEMSTATUS, transItemStatus);
        db.update(TRANSA_TABLE_NAME, contentValues, "transa_id = ? ", new String[]{Integer.toString(transaId)});


        db.close();
        return true;
    }

    public void deleteTransaksi(List<Long> productId, String transaQueue) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < productId.size(); i++) {
            Cursor res = db.rawQuery("delete from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_PRODUCTID + "=" + productId.get(i) + " AND " + TRANSA_COLUMN_TRANSAQUEUEID + "='" + transaQueue + "'", null);
            res.moveToFirst();
            res.close();
        }

        db.close();
    }

    public void deleteAllTransaksi() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("delete from " + TRANSA_TABLE_NAME, null);
        res.moveToFirst();
        res.close();
        db.close();
    }

    public void updateLocalSalesPrice(Integer transaId, long editedSalesPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSA_COLUMN_SALESPRICE, editedSalesPrice);

        db.update(TRANSA_TABLE_NAME, contentValues,
                TRANSA_COLUMN_TRANSAID + " = ? ",
                new String[]{Integer.toString(transaId)});

        db.close();
    }

    public long getLocalSalesPrice(Integer transaId) {

        long savedLocalSalesPrice = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_TRANSAID + "=" + transaId, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            savedLocalSalesPrice = res.getLong(res.getColumnIndex(TRANSA_COLUMN_SALESPRICE));
            res.moveToNext();
        }

        res.close();
        db.close();
        return savedLocalSalesPrice;
    }

    public long getItemCountOnTrans(String transQueue) {

        long jumlah = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select " + TRANSA_COLUMN_JUMLAHBELI + " from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_TRANSAQUEUEID + "='" + transQueue
                + "' AND " + TRANSA_COLUMN_TRANSITEMSTATUS + "<>" + Cfg.TIS_DELETE, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            jumlah += res.getLong(res.getColumnIndex(TRANSA_COLUMN_JUMLAHBELI));
            res.moveToNext();
        }

        res.close();
        db.close();
        return jumlah;
    }

    public List<ModelTransaksi> getTransactionList(String transId) {
        List<ModelTransaksi> listTransaksi = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_TRANSAQUEUEID + "='" + transId + "'", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            listTransaksi.add(new ModelTransaksi(
                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_TRANSAID)),
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_PRODUCTNAME)),
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_KASIR)),
                    res.getLong(res.getColumnIndex(TRANSA_COLUMN_PRODUCTID)),
                    res.getLong(res.getColumnIndex(TRANSA_COLUMN_BASEPRICE)),
                    res.getLong(res.getColumnIndex(TRANSA_COLUMN_SALESPRICE)),
                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_STOCK)),

                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_TRANSASTATUS)),
                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_TRANSITEMSTATUS)),

                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_BASELINESTOCK)),
                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_JUMLAHBELI)),
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_PICTURLPATH)),
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_CATEGORY)),
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_BRAND)),
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_SATUAN)),
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_UPTIME)),
                    res.getLong(res.getColumnIndex(TRANSA_COLUMN_MAXPRICE)),
                    res.getLong(res.getColumnIndex(TRANSA_COLUMN_MINPRICE))
            ));
            res.moveToNext();
        }

        res.close();
        db.close();
        return listTransaksi;
    }

    public List<ReqBuyProduct> getTransactionModel(String transId, String promoId) {
        List<ReqBuyProduct> listTransaksi = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TRANSA_TABLE_NAME + " where " + TRANSA_COLUMN_TRANSAQUEUEID + "='" + transId + "'", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            listTransaksi.add(new ReqBuyProduct(
                    Long.parseLong(transId),
                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_PRODUCTID)),
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_TRANSITEMSTATUS)),
                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_JUMLAHBELI)),
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_PROMO_ID)),
                    Double.parseDouble(String.valueOf(res.getLong(res.getColumnIndex(TRANSA_COLUMN_BASEPRICE)))),
                    Double.parseDouble(String.valueOf(res.getLong(res.getColumnIndex(TRANSA_COLUMN_SALESPRICE)))),
                    Double.parseDouble(String.valueOf(res.getLong(res.getColumnIndex(TRANSA_COLUMN_TOTALPCNTDISC)))),
                    Double.parseDouble(String.valueOf(res.getLong(res.getColumnIndex(TRANSA_COLUMN_TOTALPRICEDISC)))),
                    MyUtils.getTransDate(), MyUtils.getTransDate(), res.getInt(res.getColumnIndex(TRANSA_COLUMN_DETAILID)),
                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_BASELINESTOCK)),
                    res.getInt(res.getColumnIndex(TRANSA_COLUMN_STOCK)),
                    res.getLong(res.getColumnIndex(TRANSA_COLUMN_MAXPRICE))
            ));
            res.moveToNext();
        }

        res.close();
        db.close();
        return listTransaksi;
    }

    //-------------------------METHOD TABEL APPS INFO
    public void insertAppsId(
            String appsId, String deviceId, String IMEI
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSA_COLUMN_APPSID, appsId);
        contentValues.put(TRANSA_COLUMN_DEVICEID, deviceId);
        contentValues.put(TRANSA_COLUMN_IMEI, IMEI);

        db.insertOrThrow(TRANSA_TABLE_APPS_INFO, null, contentValues);

        db.close();
    }

    public ModelAppInfo getAppInfo() {
        ModelAppInfo modelAppInfo=null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TRANSA_TABLE_APPS_INFO, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            modelAppInfo=new ModelAppInfo(
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_APPSID)),
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_DEVICEID)),
                    res.getString(res.getColumnIndex(TRANSA_COLUMN_IMEI))
            );
            res.moveToNext();
        }

        res.close();
        db.close();
        return modelAppInfo;
    }

    public boolean isAppsIdExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TRANSA_TABLE_APPS_INFO, null);
        res.moveToFirst();
        if (res.getCount() > 0) {
            return true;
        } else return false;
    }

    public boolean updateImei(String imei) {
        // Karena cuma ada 1 baris, di apps id
        // jadi rowid cuma ada 1
        String rowid="1";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSA_COLUMN_IMEI, imei);
        db.update(TRANSA_TABLE_APPS_INFO, contentValues, "rowid = ? ", new String[]{rowid});

        db.close();
        return true;
    }
}