package id.hike.apps.android_mpos_mumu;

/**
 * Created by getwiz on 13/06/17.
 */
public class Cfg {
    public static final String BUNDLE_BASELINE_STOCK_INT = "bundleBaselineStock";
    public static final String BUNDLE_MAXPRICE_LONG = "bundleMaxPrice";
    public static final String CTGRY_ELK = "ELK";
    public static final String TAG_FRAG_PPOB = "tagFragPPOB";
    public static final String TAG_FRAG_PPOB_TV = "tagFragPPOBTv";
    public static final String TAG_FRAG_PPOB_LISTRIK = "tagFragPPOBListrik";
    public static final String TAG_FRAG_PPOB_PULSA = "tagFragPPOBPulsa";
    public static final String TAG_SEARCH_RESULT_BARCODE_STR = "tagFragSearchBarcode";
    public static final String TAG_SEARCH_RESULT_TYPING_STR = "tagFragSearchTyping";
    public static final String TAG_FRAG_PPOB_LISTRIK_POSTPAID = "tagFragListrikPostpaid";
    public static final String TAG_FRAG_PPOB_LISTRIK_TOKEN = "tagFragListrikToken";
    public static final String BUNDLE_BIAYA_ADMIN_LONG = "bundleBiayaAdminLong";
    public static final String BUNDLE_BIAYA_TAGIHAN_LONG = "bundleBiayaTagihanLong";
    public static final String BUNDLE_DETAIL_ID_LONG = "bundleDetailId";
    public static final String BUNDLE_ISCLICK_BANNER_BOOLEAN = "bundleIsClickBanner";
    public static final String TAG_CLICK_BANNER = "tagClickBanner";
    public static final String ERR = " error!";
    public static final int EVEN_NUM = 1;
    public static final int ODD_NUM = 0;
    public static final String BUNDLE_DESKRIPSI_PRODUK_STR = "bundleDeskripsiProduk";
    public static final String BUNDLE_ISFROM_SUMMARY_BOOLEAN = "extrasTambahProduk";
    public static final String URL_TAMBAH_PRODUK = "http://apps.smltech.co.id:7180/MPOS/tambahproduk/v1";
    public static final String BUNDLE_JUMLAH_ITEM_INT = "bundleJumlahItemInt";
    public static final String BUNDLE_MINPRICE_LONG = "bundleMinpriceLong";
    public static final String BUNDLE_TRANSID_STR = "bundleTransidStr";
    public static final String TAG_CLICK_FRONT_BANNER = "tagFrontBanner";
    public static final Boolean DIRECTLY_SUMMARY = true;
    public static final String BUNDLE_HARGA_KURANG_DISKON_LONG = "hargaKurangDiskon";
    public static final int BT_CONNECT_CR_FAILED = 51;
    public static final int BT_NOT_SUPPORTED = 52;
    public static final String BUNDLE_CR_ENABLED_BOOLEAN = "bundleCrEnabledBoolean";
    public static final int BT_CONNECT_BT = 53;
    public static final String BUNDLE_ITEM_SUMMARY_POSITION_INT = "bundleItemSummaryPositionInt";
    public static final String BUNDLE_SALESPRICE_LONG = "bundleSalesPriceLong";
    public static final int BLUETOOTH_REQUEST_CODE = 901;
    public static final String BUNDLE_SMR_INFO = "bundleSmrInfo";
    public static final String prefsReqGlobal_STR = "prefsReqGlobal_STR";
    public static final String BUNDLE_LIST_ITEM_STOK_HABIS = "listItemStokHabis";

    public final static int BT_RETURN_SUCCESS = 0;
    public final static int BT_ALREADY_CONNECT = 15;
    public final static Integer BT_SEND_CMD_TO_DEVICE_FAILED = 8;
    public final static Integer BT_RECEIVER_CMD_ERROR = 17;
    public final static Integer BT_NONE_CONNECT_DEVICE = 14;
    public final static Integer BT_SEND_CMD_ERROR = 16;
    public final static Integer BT_OFF = 50;
    public static final String BUNDLE_PELANGGAN_INFO = "bundlePelangganInfo";
    public static String prefsRealEmployeeName_Str = "prefsRealEmployeeName";

    public static final String BUNDLE_PROMO_WIDTH_INT = "bundlePromoWIdthInt";

    public static final String TAG_PULSA = "pulsa";
    public static final String TAG_LISTRIK = "listrik";
    public static final String TAG_PDAM = "pdam";
    public static final String TAG_TELKOM = "telkom";

    /**
     * transaksi global: detail produk, summary, f_payment (cek global item)
     */

//===================================== GLOBAL URL =================================================
//    APPS

//    public static String BASE_URL_SERVER = "http://10.0.2.2";
    public static String BASE_URL_SERVER = "http://35.247.144.76";
//    public static String BASE_URL_SERVER = "http://192.168.0.8";

    public static String BASE_URL_OAUTH = "http://35.247.144.76";

    public static String BASE_PORT_SERVICE = "30006";
    public static String BASE_PORT_OAUTH = "30008";

    public static String BASE_URL_DEV = BASE_URL_SERVER + ":" + BASE_PORT_SERVICE + "/";
    public static String BASE_URL_DEV_TOKEN = BASE_URL_OAUTH + ":" + BASE_PORT_OAUTH + "/";

    public static String BASE__PUBLIK_URL = BASE_URL_DEV + "resource-service/";
    public static String BASE_ASSET_URL = BASE__PUBLIK_URL + "asset/";
    public static String BASE__PUBLIK_URL_APIS = BASE_URL_DEV + "apis/";

    public static String INFRAME_URL = "http://172.104.34.189:8989/";

    public static String DB_SPINNER_DONASI = "/resource-service/produk/zakat";
    public static String DB_SPINNER_DONASI_DUA = "/resource-service/produk/zakatSub";
//    public static String INFRAME_URL = "https://wlgiro.posindonesia.co.id:4443";

//==================================================================================================


    //===================================== PUBLIK =================================================
    public static final String BASEURL_AUTH = BASE__PUBLIK_URL + "mposau/";
    //public static final String BASEURL_PRODUK = BASE__PUBLIK_URL + "mposin/";
    public static final String BASEURL_PRODUK = "produkpaged/";
    public static final String IMAGE_URL = BASE__PUBLIK_URL + "mposin/image/";
    public static final String PROFIL_IMAGEURL = BASE__PUBLIK_URL + "mposid/image/";
    //public static final String BASEURL_TRANSAKSI = BASE__PUBLIK_URL + "mpostr/";
    public static final String BASEURL_TRANSAKSI = "transaksi/";
    public static final String BASEURL_REGISTER = BASE__PUBLIK_URL + "mposid/";
    public static final String BASEURL_EWALLET = BASE__PUBLIK_URL + "mposew/";
    public static final String BASEURL_DONASI = BASE__PUBLIK_URL + "mposdn/";
    //public static String BASEURL_TRANSAKSI = "http://192.168.2.101:8080/mpos-transaksi/";

    public static final String BASEURL_LANDINGPAGE = BASE__PUBLIK_URL + "mposlp/";
    public static final String BASEURL_IDENTITY = BASE__PUBLIK_URL + "mposid/";
    public static final String BASEURL_MODAL = BASE__PUBLIK_URL + "mpostr/";
    public static final String BASEURL_TRANSAKSI_GLOBAL = BASEURL_TRANSAKSI;

    public static final String BASEURL_TOKEN_OAUTH = BASE_URL_DEV_TOKEN;

    public static final String BASEURL_WALLET_APIS = BASE__PUBLIK_URL_APIS + "v1/wallet/";
    public static final String BASEURL_PPOB_APIS = BASE__PUBLIK_URL_APIS + "v1/ppob/";
    public static final String BASEURL_PROMO = BASE__PUBLIK_URL + "/mpospr/";
    
//==================================================================================================


    public static final String prefWalletActive = "walletActive";
    public static String authTokenPrefs = "authToken";
    public static String prefsKostumerName_STR = "prefsKostumerName_STR";
    public static String prefsKostumerId_Long = "prefsKostumerId_Long";
    public static String prefsTeleponKostumerStr = "prefsTeleponKostumerStr";

    public static String defaultKostumerName = "Guest";
    public static int defaultKostumerId = 0;

    public static String defaultKasirName = "abc";
    public static int defaultKasirId = 0;
    public final static String PAYMENT_METHOD_CASH = "cash";
    public static final String PAYMENT_METHOD_DEBIT = "deb";
    public final static String PAYMENT_METHOD_CASH_TXT = "cash";
    public static final String PAYMENT_METHOD_DEBIT_TXT = "debit";

    public static String prefsRecentTransIdStr = "prefsRecentTransIdStr";
    public static String prefsNegoDisc_Str = "prefsNegoDisc";
    public static String prefsTotalHarga_Str = "prefsTotalHargaStr";
    public static String prefsKembalian_Long = "prefsKembalian_Long";
    public static String prefsTunaiStr = "prefsTunaiStr";
    public static String prefsWalletRekening = "prefsWalletRekening";
    public static String prefsWalletSaldo = "prefsWalletSaldo";
    public static String prefsWalletEnabled = "prefsWalletEnabled";

    public static String prefsNamaKasir_STR = "prefsNamaKasir_STR";
    public static String prefsKasirId_INT = "prefsKasirId_INT";
    public static String prefUserdata_USER = "prefsUserData_USER";

    public static String prefsOutletId_STR = "prefsOutletId_STR";
    public static String prefsTransDate_STR = "prefsTransDate_STR";

    public static String prefsReqStok_STR = "prefsReqStok_STR";

    public static int defaultOutletId = 1;

    public static Integer TIS_LOCK = 1;
    public static Integer TIS_UNLOCK = 0;
    public static Integer TIS_DELETE = 9;

    public final static Integer TS_OPEN = 1;
    public final static Integer TS_POSTPONE = 2;
    public final static Integer TS_PAID = 3;
    public final static Integer TS_CANCEL = 4;

    public static String prefsTotalTrans_Double = "prefsTotalTransDouble";
    public static String prefsTotalItem_Double = "prefsTotalItem";
    public static String prefsEmailKostumer_Str = "prefsEmailKostumerStr";
    public static String prefsTotalHarga_Long = "prefsTotalHargaStr";
    public static String prefsAlamatOutlet_Str = "prefsAddressOutletStr";
    public static String prefsTeleponOutlet_Str = "prefsTeleponOutletStr";
    public static String prefsNamaOutlet_Str = "prefsNamaOutletStr";
    public static String ERR500 = "Error: 500";
    public static String BUNDLE_URLPRODUK = "urlProduk";
    public static String TAG_HOME_STR = "home";

    public final static String TAG_SEARCH_RESULT_STR = "tagSearchResult";
    public static final String TAG_BARCODE_RESULT_STR = "barcode";

    public final static String TAG_FRAG_HOME_STR = "tagFragHome";
    public final static String TAG_COMMON = "mposTag";
    public final static String BUNDLE_HARGA_PRODUK_LONG = "etHargaProduk";
    public static String BUNDLE_STOK_PRODUK_LONG = "stokProduk";
    public static String BUNDLE_PRODUK_ID_INT = "produkId";
    public static String BUNDLE_NAMAPRODUK_STRING = "namaProduk";
    public static String prefsAlamatKasir_Str = "prefsAlamatKasir";
    public static String prefsGenderKasir_Str = "prefsGenderKasir";
    public static String prefsPinKasir_Str = "prefsPinKasir";
    public static String prefsTeleponKasir_Str = "prefsTeleponKasir";
    public static String ERRNOT200 = "Error !200";
    public static String prefsUrlAvatarKasir_Str = "prefsUrlAvatarKasir_Str";
    public static String prefsKasirEmail_Str = "prefsEmailKasir";
    public static String prefsTransStatus_STR = "prefsTransStatus_Str";
    public static String ERR404 = "Error: 404";
    public static String prefsModalId_Long = "prefsModalId_Long";
    public static String prefsClickedBanner = "prefsClickedbanner";
    public static String defaultAlamatKasir = "-";
    public static String prefsStoreId_INT = "prefsStoreIdStr";
    public static String prefsCardReaderConnected = "prefsCardReaderConnected";
    public static int defaultStoreId = 0;
    public static String prefsPhotoUri_STR = "prefsFotoUri";
    public static String prefsLoginToken_STR = "prefsLoginToken";
    public static String prefsPaymentType_STR = "prefsPaymentType_STR";

    public static String prefsUserId = "prefsUserId";
    public static String prefsUserStoreId = "prefsUserStoreId";
    public static String prefsUserName = "prefsUserName";
    public static String prefsUserEmail = "prefsUserEmail";
    public static String prefsUserPhone = "prefsUserPhone";
    public static String prefsUserWalletId = "prefsUserWalletId";
    public static String prefsUserDepositId = "prefsUserDepositId";
    public static String prefsUserTopUpTransfer = "prefsUserTopUpTransfer";
    //public static String prefsUserPendingTopUp = "prefsUserPendingTopUp";
    public static String prefsUserToken = "";

    public static final String prefDonasiType = "prefDonasiType";
    public static final String prefDonasiProgram = "prefDonasiProgram";
    public static final String prefDonasiMuzakki = "prefDonasiMuzakki";
    public static final String prefDonasiPhone = "prefDonasiPhone";
    public static final String prefDonasiAmount = "prefDonasiAmount";
    public static final String prefDompetDhuafa = "DOMPET DHUAFA";

    // Wakaf.info
    public static String gs_kode_agen = "gs_kode_agen";
    public static String gs_WakafInfo_Muzakki = "gs_WakafInfo_Muzakki";
    public static String gs_WakafInfo_KodeProduk = "gs_WakafInfo_KodeProduk";
    public static String gs_WakafInfo_MetaKey    = "gs_WakafInfo_MetaKey";

    //public static String oauthTokenBasic = "Basic bW9iaWxlX2lkOg==";
    public static String oauthTokenBasic = "Basic b2EtcmVhZC1jbGllbnQ6Q2xpZW50b2EwODY0IUAj";

    //public static String oauthClientId = "mobile_id";
    public static String oauthClientId = "oa-read-client";

    public static String oauthDataKey = "oauthData";
    public static String tokenExpirationKey = "tokenExpiration";

    public static String oauthAccessToken = "oauthAccessToken";
    public static String oauthRefreshToken = "oauthRefreshToken";

    public static String pulsaListKey = "pulsaListKey";
    public static String listrikListKey = "listrikListKey";
    public static String tvListKey = "tvListKey";
    public static String ppobCategory = "PPOB";
    public static String depositKey = "depositKey";
    public static String pulsaTransIdKey = "pulsaTransIdKey";
    public static String pulsaTypeVal = "pulsaTypeVal";
    public static String transactionTypeKey = "transactionTypeKey";
    public static String productNameKey = "productNameKey";
    public static String ppobStringKey = "ppobStringKey";
    public static String ppobTotalHargaVal = "ppobTotalHargaVal";
    public static String ppobCusPayVal = "ppobCusPayVal";
    public static String ppobChangeVal = "ppobChangeVal";
    public static String ppobTransDate = "ppobTransDate";
    public static String ppobSerialNumber = "ppobSerialNumber";
    public static String ppobTransList = "ppobTransList";
    public static String transTypeValPulsa = "PULSA";
    public static String transTypeValNonPpob = "NON PPOB";
    public static String transTypeValListrik = "LISTRIK";
    public static String ppobBackKey = "ppobBackKey";
    public static String ppobBackVal = "ppobBackVal";

    public static final String STRUK_MSG_KEY = "STRUK_MSG_KEY";
    public static final String IS_FROM_DETAIL_TRANS_KEY = "IS_FROM_DETAIL_TRANS_KEY";
    public static final String IS_STRUK_PRINTED_KEY = "IS_STRUK_PRINTED_KEY";
    public static final String resCekTransaksiPulsaKey = "resCekTransaksiPulsaKey";
    public static final String resCekTransaksiPulsaSecPrefKey = "resCekTransaksiPulsaSecPrefKey";

    public static final String PROMO_LIST_KEY = "PROMO_LIST_KEY";
    public static final String BUNDLE_PROMO_STR = "BUNDLE_PROMO_STR";
    public static final String PROMO_SINGLE_KEY = "PROMO_SINGLE_KEY";

    public static final String USERNAME_LOGIN_KEY = "USERNAME_LOGIN_KEY";
    public static final String REMEMBER_ME_STATUS_KEY = "REMEMBER_ME_STATUS_KEY";

}
