package id.hike.apps.android_mpos_mumu.features.summary;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.home.Home;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.features.login.df.DfLoading;
import id.hike.apps.android_mpos_mumu.features.payment.Payment;
import id.hike.apps.android_mpos_mumu.features.payment.df.DfBatalPayment;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqBuyProduct;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqGlobal;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqGlobalSales;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqGlobalSalesItem;
import id.hike.apps.android_mpos_mumu.features.summary.model.ResCheckListStock;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelAppInfo;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksi;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksiPpob;
import id.hike.apps.android_mpos_mumu.global.global_model.ReqStok;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.util.DBTransaction;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 24/09/17.
 */

public class SummaryPresenter implements SummaryContract.SummaryInteractorInPres {
    SummaryActivity summaryActivity;
    SharedPreferences secPrefs;
    SummaryContract.SummaryInteractor summaryInteractor;
    List<Long> deletedProductIdList = new ArrayList<>();
    boolean isPressedTambah = false;
    ReqStok reqStoks = new ReqStok();
    public long totalPaymentAfterDisc = 0;
    DfLoading dfLoading;
    ModelAppInfo modelAppInfo = null;

    public SummaryPresenter(SummaryActivity summaryActivity, SharedPreferences secPrefs) {
        this.summaryActivity = summaryActivity;
        this.secPrefs = secPrefs;
        summaryInteractor = new SummaryInteractorImpl(new DBTransaction(summaryActivity));
        dfLoading = summaryActivity.dfLoading;
        modelAppInfo = summaryInteractor.getModelAppInfo();
    }

    void batalBayar() {
        secPrefs.edit().putString(Cfg.prefsTotalHarga_Str, countHarga(summaryInteractor.getTransactionList(secPrefs))).apply();
        DfBatalPayment dfBatalPayment = new DfBatalPayment();
        dfBatalPayment.show(summaryActivity.getSupportFragmentManager(), "smr");
    }
    void batalBayarPpob() {
        DfBatalPayment dfBatalPayment = new DfBatalPayment();
        dfBatalPayment.show(summaryActivity.getSupportFragmentManager(), Cfg.ppobCategory);
    }

    void tambahProduk() {
        List<ReqBuyProduct> mm = summaryInteractor.getTransactionModel(secPrefs);
        List<ReqGlobalSalesItem> reqGlobalSalesItems = new ArrayList<>();

        for (int i = 0; i < mm.size(); i++) {
            if (Integer.parseInt(mm.get(i).itemStatus) == Cfg.TIS_DELETE) {
                reqGlobalSalesItems.add(
                        new ReqGlobalSalesItem(secPrefs.getString(Cfg.prefsRecentTransIdStr, ""),
                                String.valueOf(mm.get(i).productId),
                                mm.get(i).lastRequest,
                                mm.get(i).salesPrice.toString(),
                                mm.get(i).detailId != null ? mm.get(i).detailId.toString() : "",
                                mm.get(i).pcntDisc != null ? mm.get(i).pcntDisc.toString() : "",
                                Cfg.TIS_DELETE.toString(),
                                mm.get(i).qty.toString(),
                                mm.get(i).lastUpdate,
                                secPrefs.getString(Cfg.prefsTransDate_STR, ""),
                                summaryActivity.edDiskon.getText().toString(),
                                mm.get(i).promoId,
                                mm.get(i).basePrice.toString()
                        ));

                // Menghitung jumlah barang yang didelete, agar menghapus item
                // yang ada di Sqlite
                deletedProductIdList.add(mm.get(i).productId);
//                deletedItemSum++;
            }
        }

        isPressedTambah = true;


        //jika ada barang yg didelete
        if (reqGlobalSalesItems.size() > 0) {
            List<ReqGlobalSales> reqGlobalSales = new ArrayList<>();
            reqGlobalSales.add(new ReqGlobalSales(
                    secPrefs.getString(Cfg.prefsNamaKasir_STR, null),
                    mm.get(0).lastRequest,
                    String.valueOf(mm.size()),
                    secPrefs.getString(Cfg.prefsRecentTransIdStr, null),
                    secPrefs.getString(Cfg.prefsOutletId_STR, null),
                    "", "", "",
                    summaryActivity.edDiskon.getText().toString(), ""
                    , String.valueOf(secPrefs.getLong(Cfg.prefsKostumerId_Long, Cfg.defaultKostumerId)),
                    "",
                    secPrefs.getString(Cfg.prefsTotalHarga_Str, "0"),
                    "", secPrefs.getString(Cfg.prefsNamaKasir_STR, null),
                    mm.get(0).lastUpdate,
                    String.valueOf(secPrefs.getLong(Cfg.prefsKostumerId_Long, Cfg.defaultKostumerId)),
                    String.valueOf(Long.parseLong(secPrefs.getString(Cfg.prefsTotalHarga_Str, "0")) - Long.parseLong(summaryActivity.edDiskon.getText().toString())),
                    Cfg.TS_OPEN.toString(),
                    modelAppInfo.getAppsId(),
                    modelAppInfo.getDeviceId(),
                    modelAppInfo.getImei(),
                    String.valueOf(secPrefs.getInt(Cfg.prefsStoreId_INT, Cfg.defaultStoreId))
            ));

            sendItemTransaction(new ReqGlobal(reqGlobalSales, reqGlobalSalesItems), Cfg.TS_OPEN.toString());

        } else {
            Toast.makeText(summaryActivity, summaryActivity.getString(R.string.tambah_produk), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(summaryActivity, Home.class);
            // Agar dari halaman produk
            intent.putExtra(Cfg.BUNDLE_ISFROM_SUMMARY_BOOLEAN, Cfg.DIRECTLY_SUMMARY);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            summaryActivity.startActivity(intent);
        }
    }

    void simpanTransaksiPpob(String transId){
        dfLoading.show(summaryActivity.getSupportFragmentManager(), "");
        Gson gson = new Gson();
        ModelTransaksiPpob ppobTrans = summaryInteractor.getPpobTransactionById(transId);
        Type ppobTransListType = new TypeToken<List<ModelTransaksiPpob>>(){}.getType();
        String ppobListTransString = secPrefs.getString(Cfg.ppobTransList,null);
        List<ModelTransaksiPpob> listPpobTrans = null;
        if(ppobListTransString!=null){
            listPpobTrans = gson.fromJson(ppobListTransString, ppobTransListType);
        }else {
            listPpobTrans = new ArrayList<>();
        }

        for(ModelTransaksiPpob m : listPpobTrans){
            if(m.getTransaId().equals(ppobTrans.getTransaId())){
                listPpobTrans.remove(m);
            }
        }

        listPpobTrans.add(ppobTrans);

        ppobListTransString = gson.toJson(listPpobTrans);
        secPrefs.edit().putString(Cfg.ppobTransList, ppobListTransString).apply();
        Toast.makeText(summaryActivity, summaryActivity.getString(R.string.transaksi_disimpan), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(summaryActivity, LandingPage.class);
        intent.putExtra(Cfg.ppobStringKey,Cfg.ppobCategory);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        summaryActivity.startActivity(intent);
        dfLoading.dismiss();

    }

    void simpanTransaksi() {
        secPrefs.edit().putString(Cfg.prefsTotalHarga_Str, countHarga(summaryInteractor.getTransactionList(
                secPrefs
        ))).apply();

        List<ReqBuyProduct> mm = summaryInteractor.getTransactionModel(secPrefs);

        String diskon=getRawPriceFromFormatted(summaryActivity.edDiskon);

        List<ReqGlobalSalesItem> reqGlobalSalesItems = new ArrayList<>();
        for (int i = 0; i < mm.size(); i++) {
            if (Integer.parseInt(mm.get(i).itemStatus) == Cfg.TIS_DELETE) {
                reqGlobalSalesItems.add(
                        new ReqGlobalSalesItem(secPrefs.getString(Cfg.prefsRecentTransIdStr, ""),
                                String.valueOf(mm.get(i).productId),
                                mm.get(i).lastRequest,
                                mm.get(i).salesPrice.toString(),
                                mm.get(i).detailId != null ? mm.get(i).detailId.toString() : "",
                                mm.get(i).pcntDisc != null ? mm.get(i).pcntDisc.toString() : "",
                                Cfg.TIS_DELETE.toString(),
                                mm.get(i).qty.toString(),
                                mm.get(i).lastUpdate,
                                secPrefs.getString(Cfg.prefsTransDate_STR, ""),
                                summaryActivity.edDiskon.getText().toString(),
                                mm.get(i).promoId,
                                mm.get(i).basePrice.toString()
                        ));
            } else {
                reqGlobalSalesItems.add(
                        new ReqGlobalSalesItem(secPrefs.getString(Cfg.prefsRecentTransIdStr, ""),
                                String.valueOf(mm.get(i).productId),
                                mm.get(i).lastRequest,
                                mm.get(i).salesPrice.toString(),
                                mm.get(i).detailId != null ? mm.get(i).detailId.toString() : "",
                                mm.get(i).pcntDisc != null ? mm.get(i).pcntDisc.toString() : "",
                                Cfg.TIS_UNLOCK.toString(),
                                mm.get(i).qty.toString(),
                                mm.get(i).lastUpdate,
                                "",
                                summaryActivity.edDiskon.getText().toString(),
                                mm.get(i).promoId,
                                mm.get(i).basePrice.toString()
                        ));
            }
        }



        List<ReqGlobalSales> reqGlobalSales = new ArrayList<>();
        reqGlobalSales.add(new ReqGlobalSales(
                secPrefs.getString(Cfg.prefsNamaKasir_STR, null),
                mm.get(0).lastRequest,
                String.valueOf(mm.size()),
                secPrefs.getString(Cfg.prefsRecentTransIdStr, null),
                secPrefs.getString(Cfg.prefsOutletId_STR, null),
                "", "", "",
                summaryActivity.edDiskon.getText().toString(), ""
                , String.valueOf(secPrefs.getLong(Cfg.prefsKasirId_INT, Cfg.defaultKasirId)),
                "",
                secPrefs.getString(Cfg.prefsTotalHarga_Str, "0"),
                "", secPrefs.getString(Cfg.prefsNamaKasir_STR, null),
                mm.get(0).lastUpdate,
                String.valueOf(secPrefs.getLong(Cfg.prefsKostumerId_Long, Cfg.defaultKostumerId)),
                String.valueOf(Long.parseLong(secPrefs.getString(Cfg.prefsTotalHarga_Str, "0")) - Long.parseLong(diskon)),
                Cfg.TS_POSTPONE.toString(),
                modelAppInfo.getAppsId(),
                modelAppInfo.getDeviceId(),
                modelAppInfo.getImei(),
                String.valueOf(secPrefs.getInt(Cfg.prefsStoreId_INT, Cfg.defaultStoreId))
        ));

        sendItemTransaction(new ReqGlobal(reqGlobalSales, reqGlobalSalesItems), Cfg.TS_POSTPONE.toString());
    }

    String getRawPriceFromFormatted(TextView textView) {
        // Menghilangkan titik di string diskon
        String input = textView.getText().toString();
        if (input.contains(".")) {
            input = input.replaceAll("\\.", "");
        }
        return input;
    }

    void bayarTransaksiPpob(String transId){
        String inputDiskon = summaryActivity.edDiskon.getText().toString();
        if (inputDiskon.contains(".")) {
            inputDiskon = inputDiskon.replaceAll("\\.", "");
        }
        ModelTransaksiPpob trans = getPpobTransactionById(transId);
        Long totalHargaTrans = Long.parseLong(trans.getTotalBelanja());
        if (!summaryActivity.edDiskon.getText().toString().isEmpty()) {
            secPrefs.edit().putString(Cfg.prefsNegoDisc_Str, inputDiskon).apply();
        } else {
            secPrefs.edit().putString(Cfg.prefsNegoDisc_Str, "0").apply();
        }
        secPrefs.edit().putLong(Cfg.prefsTotalItem_Double, trans.getJumlahBeli().longValue()).apply();
        secPrefs.edit().putLong(Cfg.prefsTotalTrans_Double, totalHargaTrans
                - Long.parseLong(inputDiskon)).apply();

        Toast.makeText(summaryActivity, summaryActivity.getString(R.string.transaksi_dikunci), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(summaryActivity, Payment.class);
        intent.putExtra(Cfg.transactionTypeKey, Cfg.pulsaTypeVal);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        summaryActivity.startActivity(intent);

    }

    void bayarTransaksi() {
        // Menghilangkan titik di string diskon
        String inputDiskon = summaryActivity.edDiskon.getText().toString();
        if (inputDiskon.contains(".")) {
            inputDiskon = inputDiskon.replaceAll("\\.", "");
        }

        secPrefs.edit().putString(Cfg.prefsTotalHarga_Str, countHarga(summaryInteractor.getTransactionList(secPrefs))).apply();

        if (!summaryActivity.edDiskon.getText().toString().isEmpty()) {
            secPrefs.edit().putString(Cfg.prefsNegoDisc_Str, inputDiskon).apply();
        } else {
            secPrefs.edit().putString(Cfg.prefsNegoDisc_Str, "0").apply();
        }

        secPrefs.edit().putLong(Cfg.prefsTotalItem_Double, summaryInteractor.getItemCountOnTrans(secPrefs)).apply();


        // Total harga yg muncul di list aktifitas
        secPrefs.edit().putLong(Cfg.prefsTotalTrans_Double, Long.parseLong(getCountHarga())
                - Long.parseLong(inputDiskon)).apply();

        List<ReqBuyProduct> mm = summaryInteractor.getTransactionModel(secPrefs);

        List<ReqGlobalSalesItem> reqGlobalSalesItems = new ArrayList<>();
        for (int i = 0; i < mm.size(); i++) {
            // Barang yang status delete dan detail id tidak 0
            // detailId=0 => barang baru
            if (mm.get(i).detailId == 0 && Integer.parseInt(mm.get(i).itemStatus) != Cfg.TIS_DELETE) {
                reqStoks.trsalesitem.add(new ReqStok.ReqStokList(mm.get(i).qty, mm.get(i).stock - mm.get(i).qty, mm.get(i).productId));
                reqGlobalSalesItems.add(
                        new ReqGlobalSalesItem(secPrefs.getString(Cfg.prefsRecentTransIdStr, ""),
                                String.valueOf(mm.get(i).productId),
                                mm.get(i).lastRequest,
                                mm.get(i).salesPrice.toString(),
                                mm.get(i).detailId != null ? mm.get(i).detailId.toString() : "",
                                mm.get(i).pcntDisc != null ? mm.get(i).pcntDisc.toString() : "",
                                Cfg.TIS_LOCK.toString(),
                                mm.get(i).qty.toString(),
                                mm.get(i).lastUpdate,
                                secPrefs.getString(Cfg.prefsTransDate_STR, ""),
                                summaryActivity.edDiskon.getText().toString(),
                                mm.get(i).promoId,
                                mm.get(i).basePrice.toString()
                        ));

            }
            // Barang yang tidak didelete dan detailId bukan 0
            else if (mm.get(i).detailId != 0 && (Integer.parseInt(mm.get(i).itemStatus) != Cfg.TIS_DELETE)) {
                reqStoks.trsalesitem.add(new ReqStok.ReqStokList(mm.get(i).qty, mm.get(i).stock - mm.get(i).qty, mm.get(i).productId));
                reqGlobalSalesItems.add(
                        new ReqGlobalSalesItem(secPrefs.getString(Cfg.prefsRecentTransIdStr, ""),
                                String.valueOf(mm.get(i).productId),
                                mm.get(i).lastRequest,
                                mm.get(i).salesPrice.toString(),
                                mm.get(i).detailId != null ? mm.get(i).detailId.toString() : "",
                                mm.get(i).pcntDisc != null ? mm.get(i).pcntDisc.toString() : "",
                                Cfg.TIS_LOCK.toString(),
                                mm.get(i).qty.toString(),
                                mm.get(i).lastUpdate,
                                secPrefs.getString(Cfg.prefsTransDate_STR, ""),
                                summaryActivity.edDiskon.getText().toString(),
                                mm.get(i).promoId,
                                mm.get(i).basePrice.toString()
                        ));

            }
            // Barang yg stok = 0 && item status delete -> tidak dikirim
            else if (Integer.parseInt(mm.get(i).itemStatus) == Cfg.TIS_DELETE && mm.get(i).stock == 0) {
                // Nothing
            }
            // Barang yang stok = 0 -> tidak dikirim
            // Barang yg dibeli 0 -> tidak dikirim
            // 1 ASD
            else if (mm.get(i).stock == 0 || mm.get(i).qty == 0) {
                // Nothing
            }
            // Barang yg didelete -> dikirim
            // Barang yg didelete -> tidak dicekstok di server
            // 2 ASD
            else if (Integer.parseInt(mm.get(i).itemStatus) == Cfg.TIS_DELETE) {
                reqGlobalSalesItems.add(
                        new ReqGlobalSalesItem(secPrefs.getString(Cfg.prefsRecentTransIdStr, ""),
                                String.valueOf(mm.get(i).productId),
                                mm.get(i).lastRequest,
                                mm.get(i).salesPrice.toString(),
                                mm.get(i).detailId != null ? mm.get(i).detailId.toString() : "",
                                mm.get(i).pcntDisc != null ? mm.get(i).pcntDisc.toString() : "",
                                Cfg.TIS_DELETE.toString(),
                                mm.get(i).qty.toString(),
                                mm.get(i).lastUpdate,
                                secPrefs.getString(Cfg.prefsTransDate_STR, ""),
                                inputDiskon,
                                mm.get(i).promoId,
                                mm.get(i).basePrice.toString()
                        ));
            }
        }


        List<ReqGlobalSales> reqGlobalSales = new ArrayList<>();
        reqGlobalSales.add(new ReqGlobalSales(
                secPrefs.getString(Cfg.prefsNamaKasir_STR, null),
                mm.get(0).lastRequest,
                String.valueOf(mm.size()),
                secPrefs.getString(Cfg.prefsRecentTransIdStr, null),
                secPrefs.getString(Cfg.prefsOutletId_STR, null),
                "", "", "",
                summaryActivity.edDiskon.getText().toString(), ""
                , String.valueOf(secPrefs.getLong(Cfg.prefsKasirId_INT, Cfg.defaultKasirId)),
                "",
                secPrefs.getString(Cfg.prefsTotalHarga_Str, "0"),
                "", secPrefs.getString(Cfg.prefsNamaKasir_STR, null),
                mm.get(0).lastUpdate,
                String.valueOf(secPrefs.getLong(Cfg.prefsKostumerId_Long, Cfg.defaultKostumerId)),
                String.valueOf(Long.parseLong(secPrefs.getString(Cfg.prefsTotalHarga_Str, "0")) - Long.parseLong(inputDiskon)),
                Cfg.TS_OPEN.toString(),
                modelAppInfo.getAppsId(),
                modelAppInfo.getDeviceId(),
                modelAppInfo.getImei(),
                String.valueOf(secPrefs.getInt(Cfg.prefsStoreId_INT, Cfg.defaultStoreId))
        ));

        sendItemTransaction(new ReqGlobal(reqGlobalSales, reqGlobalSalesItems), "bayar");
    }

    public String getCountHarga() {
        return countHarga(summaryInteractor.getTransactionList(secPrefs));
    }

    public String getTotalMinPrice() {
        return countTotalMinPrice(summaryInteractor.getTransactionList(secPrefs));
    }

    public String getTotalBasePrice() {
        return countTotalBasePrice(summaryInteractor.getTransactionList(secPrefs));
    }

    List<ModelTransaksi> getTransactionList() {
        return summaryInteractor.getTransactionList(secPrefs);
    }

    private String countHarga(List<ModelTransaksi> mm) {
        int subTotal = 0;
        for (ModelTransaksi item : mm) {
            if (item.transItemStatus_flag != Cfg.TIS_DELETE) {
                subTotal += item.salesPrice * item.jumlahBeli;
            }
        }
        return String.valueOf(subTotal);
    }

    private String countTotalBasePrice(List<ModelTransaksi> mm) {

        int rawPrice = 0;
        for (ModelTransaksi item : mm) {
            if (item.transItemStatus_flag != Cfg.TIS_DELETE) {
                rawPrice += item.basePrice * item.jumlahBeli;
            }
        }
        return String.valueOf(rawPrice);
    }

    public void resetInputDiskon() {
        summaryActivity.edDiskon.setText("0");
    }

    private String countTotalMinPrice(List<ModelTransaksi> mm) {
        int rawPrice = 0;
        for (ModelTransaksi item : mm) {
            if (item.transItemStatus_flag != Cfg.TIS_DELETE) {
                rawPrice += item.minPrice * item.jumlahBeli;
            }
        }
        return String.valueOf(rawPrice);
    }

    public void updateModel(int productId, int jumlahBeli) {
        summaryInteractor.updateJumlah(jumlahBeli, secPrefs, productId);
        updateSubtotal();
    }

    public void deleteTransactionItem(List<Long> productId, String recentTransQueue) {
        summaryInteractor.deleteTransaksi(productId, secPrefs);
    }

    public void updateSalesPrice(int transId, long savedSalesPrice) {
        summaryInteractor.updateLocalSalesPrice(transId, savedSalesPrice);
        updateSubtotal();
        updateTotal();
    }

    public long getRecentSalesPrice(int transId) {
        return summaryInteractor.getLocalSalesPrice(transId);
    }

    public void updateSubtotal() {
        summaryActivity.tvSubtotal.setText(
                UnitConversion.format2Rupiah(Long.parseLong(
                        getCountHarga()
                )));
    }

    public void updateTotal() {
        summaryActivity.etTotal.setText(UnitConversion.format2Rupiah(
                Long.parseLong(getCountHarga()) -
                        Long.parseLong(getRawPriceFromFormatted(summaryActivity.edDiskon)) -
                        Long.parseLong(getRawPriceFromFormatted(summaryActivity.tvPPN))
                )
        );
    }

    public int getBaselineStock(int transaId) {
        return summaryInteractor.getBaselineStockByTransId(transaId);
    }

    public int getStock(int transaId) {
        return summaryInteractor.getStockByTransId(transaId);
    }

    TextWatcher etDiskonTextwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            summaryActivity.edDiskon.removeTextChangedListener(this);
            Long totalMinPrice = Long.parseLong(getTotalMinPrice());
            Long totalSalesPrice = Long.parseLong(getCountHarga());
            Long totalDiskon = totalSalesPrice - totalMinPrice;

            //filter input text untuk menghilangkan leading zero
            String inputDiskon = s.toString().isEmpty() || s.toString().equalsIgnoreCase("null") ? "0" : s.toString();
            inputDiskon = inputDiskon.replaceFirst("^0+(?!$)", "");

            if (inputDiskon.contains(".")) {
                inputDiskon = inputDiskon.replaceAll("\\.", "");
            }

            if (Long.parseLong(inputDiskon) > totalDiskon) {
                summaryActivity.etTotal.setText(UnitConversion.format2Rupiah(totalSalesPrice));
                summaryActivity.edDiskon.setText(String.valueOf(0L));
//                summaryActivity.edDiskon.setSelection(1);
                Toast.makeText(summaryActivity, summaryActivity.getString(R.string.input_diskon_kurang_dari_baseprice), Toast.LENGTH_LONG).show();
            } else {
                //harga yang diinput valid
                summaryActivity.edDiskon.setText(inputDiskon);
//                summaryActivity.edDiskon.setSelection(inputDiskon.length());
                Long diskon = Long.parseLong(inputDiskon);
                totalPaymentAfterDisc = totalSalesPrice - diskon;
                summaryActivity.etTotal.setText(UnitConversion.format2Rupiah(totalPaymentAfterDisc));
            }
            summaryActivity.edDiskon.addTextChangedListener(this);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public void reloadActivity(Activity activity) {
        Intent intent = activity.getIntent();
        intent.putExtra(Cfg.BUNDLE_SMR_INFO, "stokHabis");
        activity.finish();
        activity.startActivity(intent);
    }

    void sendItemTransaction(ReqGlobal reqGlobal, final String status) {
        dfLoading.setCancelable(false);
        dfLoading.show(summaryActivity.getSupportFragmentManager(), "");
        Gson gson = new Gson();

        // Mengirimkan json untuk diproses di paidTrans2()
        secPrefs.edit().putString(Cfg.prefsReqStok_STR, gson.toJson(reqStoks)).apply();
//        SummaryService summaryService = ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI).create(SummaryService.class);
        final SummaryService summaryService = ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI_GLOBAL).create(SummaryService.class);
        secPrefs.edit().putString(Cfg.prefsReqGlobal_STR, gson.toJson(reqGlobal)).apply();

        Call<ResCheckListStock> call = summaryService.sendItemTransaction(reqGlobal);
        call.enqueue(new Callback<ResCheckListStock>() {
            @Override
            public void onResponse(Call<ResCheckListStock> call, Response<ResCheckListStock> response) {
                dfLoading.dismiss();
                ResCheckListStock rsp = response.body();
                if (isPressedTambah == true) {
                    // Kalo kesini, udah pasti ada list item yang didelete
                    // Dan bersiap untuk pindah ke halaman Home
                    // Item yang masuk disini, itemnya didelete di SqLite
                    summaryInteractor.deleteTransactionItem(deletedProductIdList, secPrefs);

                    Toast.makeText(summaryActivity, summaryActivity.getString(R.string.tambah_produk), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(summaryActivity, Home.class);
                    intent.putExtra(Cfg.BUNDLE_ISFROM_SUMMARY_BOOLEAN, Cfg.DIRECTLY_SUMMARY);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    summaryActivity.startActivity(intent);

                    return;
                }

                if (status.equalsIgnoreCase(Cfg.TS_POSTPONE.toString())) {
                    Toast.makeText(summaryActivity, summaryActivity.getString(R.string.transaksi_disimpan), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(summaryActivity, LandingPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    summaryActivity.startActivity(intent);
                }

                // Jika klik tombol bayar
                if (status.equalsIgnoreCase("bayar")) {
                    // Jika transaksi gagal karena stok habis
                    if (rsp.isStatus() == false) {
                        for (int i = 0; i < rsp.getData().size(); i++) {
                            summaryInteractor.updateStockDanJumlah(Long.parseLong(rsp.getData().get(i).getStock()),
                                    Long.parseLong(rsp.getData().get(i).getStock())
                                    , secPrefs,
                                    Long.parseLong(rsp.getData().get(i).getProductId()));
                        }
                        reloadActivity(summaryActivity);

                    } else {
                        // Jika transaksi berhasil karena stok masih ada
                        Toast.makeText(summaryActivity, summaryActivity.getString(R.string.transaksi_dikunci), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(summaryActivity, Payment.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        summaryActivity.startActivity(intent);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResCheckListStock> call, Throwable t) {
                dfLoading.dismiss();
                Toast.makeText(summaryActivity, MyUtils.getThisMethodNameForError(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public String getPpobPrice(String transId){
        ModelTransaksiPpob trans = summaryInteractor.getPpobTransactionById(transId);
        int subTotal = 0;
        if (trans.getTransItemStatusFlag() != Cfg.TIS_DELETE) {
            subTotal += trans.getSalesPrice() * trans.getJumlahBeli();
        }

        return String.valueOf(subTotal);
    }
    public ModelTransaksiPpob getPpobTransactionById(String id){
        return summaryInteractor.getPpobTransactionById(id);
    }

    public void updateSubtotalPpob(String transId) {
        summaryActivity.tvSubtotal.setText(
                UnitConversion.format2Rupiah(Long.parseLong(
                        getPpobPrice(transId)
                )));
    }

    public void updateTotalPpob(String transId) {
        summaryActivity.etTotal.setText(UnitConversion.format2Rupiah(
                Long.parseLong(getPpobPrice(transId)) -
                        Long.parseLong(getRawPriceFromFormatted(summaryActivity.edDiskon)) -
                        Long.parseLong(getRawPriceFromFormatted(summaryActivity.tvPPN))
                )
        );
    }

    private String countHargaPpob(List<ModelTransaksiPpob> mm) {
        int subTotal = 0;
        for (ModelTransaksiPpob item : mm) {
            if (item.transItemStatusFlag != Cfg.TIS_DELETE) {
                subTotal += item.salesPrice * item.jumlahBeli;
            }
        }
        return String.valueOf(subTotal);
    }

    public Boolean checkExistingPpob(){
        return summaryInteractor.checkExistingPpob();
    }
}
