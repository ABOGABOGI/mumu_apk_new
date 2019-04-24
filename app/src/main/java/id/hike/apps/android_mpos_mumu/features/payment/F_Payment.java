package id.hike.apps.android_mpos_mumu.features.payment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.core.view.animation.PathInterpolatorCompat;
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.ButterKnife;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.features.landing_page.model.ResEditTrans;
import id.hike.apps.android_mpos_mumu.features.payment.adapter.MonthAdapter;
import id.hike.apps.android_mpos_mumu.features.payment.adapter.YearAdapter;
import id.hike.apps.android_mpos_mumu.features.payment.df.DfBatalPayment;
import id.hike.apps.android_mpos_mumu.features.payment.df.DfCreditCard;
import id.hike.apps.android_mpos_mumu.features.payment.df.DfDebitCard;
import id.hike.apps.android_mpos_mumu.features.payment.df.DfEMoney;
import id.hike.apps.android_mpos_mumu.features.payment.df.DfInfoStockHabis;
import id.hike.apps.android_mpos_mumu.features.payment.df.DfQrCode;
import id.hike.apps.android_mpos_mumu.features.payment.model.ModelPaymentTypeItem;
import id.hike.apps.android_mpos_mumu.features.payment.model.ResDataSuccess;
import id.hike.apps.android_mpos_mumu.features.payment.model.ResPaymentType;
import id.hike.apps.android_mpos_mumu.features.payment.model.RqPaidTrans;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryActivity;
import id.hike.apps.android_mpos_mumu.features.summary.SummaryService;
import id.hike.apps.android_mpos_mumu.features.summary.df.DfTerimakasih;
import id.hike.apps.android_mpos_mumu.features.summary.model.ReqGlobal;
import id.hike.apps.android_mpos_mumu.features.summary.model.ResCheckListStock;
import id.hike.apps.android_mpos_mumu.features.summary.model.ResCheckListStockItem;
import id.hike.apps.android_mpos_mumu.global.global_model.ModelTransaksiPpob;
import id.hike.apps.android_mpos_mumu.global.global_model.ReqStok;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import id.hike.apps.android_mpos_mumu.util.DBTransaction;
import id.hike.apps.android_mpos_mumu.util.MyUtils;
import id.hike.apps.android_mpos_mumu.util.UnitConversion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class F_Payment extends BaseFragment {

    public F_Payment() {
        // Required empty public constructor
    }

    ResPaymentType resPaymentType;

    EditText edInputCash;

    public long totalHarga = 0;
    Long[] listHarga;
    DecimalFormat df;
    ViewAnimator vaCashInfo;
    TextView tvKembalian;
    boolean toggleCash = false;
    boolean toggleDebit = false;
    boolean toggleCard = false;
    public DBTransaction dbTransaction;
    TextView tvContact;
    TextView btnCash;
    LinearLayout btnDebit, btnCreditCard, btnQrCode, btnEMoney;

    int afterCursorIndex, afterCountInputLenght, afterDotCount;
    int beforeCountInputLenght, beforeCursorIndex, beforeDotCount;

    public List<ResCheckListStockItem> listStockItems = new ArrayList<ResCheckListStockItem>();

    DigitalProductPaymentService ppobTransService;
    private Boolean isPpob;
    private String ppobTransId;
    private Long customerPay, totalPayment, change;
    private Integer cekStatusCounter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cekStatusCounter = 0;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f__payment, container, false);

        ButterKnife.bind(this, view);
        dbTransaction = new DBTransaction(getActivity());
        ppobTransService = ApiClient.serviceGenerator(Cfg.BASEURL_PPOB_APIS).create(DigitalProductPaymentService.class);

        btnDebit = (LinearLayout) view.findViewById(R.id.btnDebit);
        btnDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DfDebitCard dfDebitCard = new DfDebitCard();
                dfDebitCard.show(getFragmentManager(), "");
            }
        });

        btnCreditCard = (LinearLayout) view.findViewById(R.id.btnCreditCard);
        btnEMoney = (LinearLayout) view.findViewById(R.id.btnEMoney);

        totalHarga = Long.parseLong(getFragSecPrefs().getString(Cfg.prefsTotalHarga_Str, "0")) -
                Long.parseLong(getFragSecPrefs().getString(Cfg.prefsNegoDisc_Str, "0"));

        btnQrCode = (LinearLayout) view.findViewById(R.id.btnQrCode);
        btnQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DfQrCode dfQrCode = new DfQrCode();
                dfQrCode.show(getFragmentManager(), UnitConversion.format2Rupiah(totalHarga));
            }
        });

        btnEMoney = (LinearLayout) view.findViewById(R.id.btnEMoney);
        btnEMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DfEMoney dfEMoney = new DfEMoney();
                dfEMoney.show(getFragmentManager(), "");
            }
        });

        btnCreditCard = (LinearLayout) view.findViewById(R.id.btnCreditCard);
        btnCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DfCreditCard dfCreditCard = new DfCreditCard();
                dfCreditCard.show(getFragmentManager(), "");
            }
        });

        Animation slideDownOut = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down_out);
        Animation slideDownIn = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down_in);

        tvKembalian = (TextView) view.findViewById(R.id.tvKembalian);

        vaCashInfo = (ViewAnimator) view.findViewById(R.id.vaInfo);
        vaCashInfo.setInAnimation(slideDownIn);
        vaCashInfo.setOutAnimation(slideDownOut);

        final LinearLayout contentCash = (LinearLayout) view.findViewById(R.id.contentCash);
        final LinearLayout contentDebit = (LinearLayout) view.findViewById(R.id.contentDebit);
//        final LinearLayout contentCard = (LinearLayout) view.findViewById(R.id.contentCard);

        btnCash = (TextView) view.findViewById(R.id.btnCash);
        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!toggleCash) {
                    expand(contentCash);
                    toggleCash = !toggleCash;
                } else {
                    collapse(contentCash);
                    toggleCash = !toggleCash;
                }

                getExistPaymentType(Cfg.PAYMENT_METHOD_CASH);
            }
        });

        String[] month = getResources().getStringArray(R.array.months);
        MonthAdapter monthAdapter = new MonthAdapter(getActivity(), 0, month);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        TextView tvTotalHarga = (TextView) view.findViewById(R.id.tvTotalHarga);
        df = new DecimalFormat("###,###,###,###,###,###,###");
        tvTotalHarga.setText("Rp " + df.format(totalHarga));

        List<String> years = new ArrayList<>();
        Calendar date = new GregorianCalendar();
        int currentYear = date.get(Calendar.YEAR);
        years.add(String.valueOf(currentYear));
        for (int i = 0; i < 10; i++) {
            currentYear += 1;
            years.add(String.valueOf(currentYear));
        }
        years.add("YY");

        YearAdapter yearsDataAdapter = new YearAdapter(getActivity(), 0, years);
        yearsDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /*ySpinner.setAdapter(yearsDataAdapter);
        ySpinner.setSelection(years.size() - 1);*/

        edInputCash = (EditText) view.findViewById(R.id.edInputCash);
        edInputCash.setSelection(1);
        edInputCash.addTextChangedListener(cashEditTextWatcher);

        edInputCash.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    long sisa;

                    // Untuk menghilangkan titik separator
                    String originalString = edInputCash.getText().toString();
                    if (originalString.isEmpty()) {
                        originalString = "0";
                    }
                    if (originalString.contains(".")) {
                        originalString = originalString.replaceAll("\\.", "");
                    }
                    long uangInput = Long.parseLong(originalString);

                    sisa = uangInput - totalHarga;
                    if (Math.signum(sisa) == 0) {
                        tvKembalian.setText("Rp 0");
                        vaCashInfo.setDisplayedChild(2);
                        edInputCash.setSelection(edInputCash.getText().length());
                    } else if (Math.signum(sisa) > 0) {
                        tvKembalian.setText("Rp " + df.format(sisa));
                        vaCashInfo.setDisplayedChild(2);
                    } else {
                        vaCashInfo.setDisplayedChild(1);
                    }
                    getFragSecPrefs().edit().putLong(Cfg.prefsKembalian_Long, sisa).apply();

                    // Format curency
                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(uangInput);
                    formattedString = formattedString.replaceAll(",", ".");
                    edInputCash.setText(formattedString);
                    edInputCash.setSelection(edInputCash.getText().toString().length());
                }
                return false;
            }
        });

        onComponent(view);

        dfLoading.show(getActivity().getSupportFragmentManager(), "");
//        loadingProgress.show();
        getPaymentType();
        generateButton();

        //Kalo tekan tombol back, maka mengirimkan servis untuk mengubah barang
        ((Payment) getActivity()).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPpob){
                    getActivity().finish();
                }else{
                    btnCashUbah.performClick();

                }
            }
        });

        return view;
    }

    void generateButton() {
        final ArrayList<Long> suggestList = MyUtils.getSuggestion(totalHarga);

        final float perPage = 3F;
        int totalImageIndex = 0;
        float suggestionSize = MyUtils.getSuggestion(totalHarga).size();
        final double paging = Math.ceil(suggestionSize / perPage);


        for (int i = 0; i < paging; i++) {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            laySuggestButton.addView(linearLayout, params);

            for (int u = 0; u < suggestionSize; u++) {
                if (u >= perPage) {
                    suggestionSize = suggestionSize - perPage;
                } else {
                    final Button imageButton = new Button(getActivity());
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.333F);
                    imageButton.setText(String.valueOf(suggestList.get(totalImageIndex)));
                    imageButton.setText(UnitConversion.format2Rupiah2(suggestList.get(totalImageIndex)));
                    linearLayout.addView(imageButton, params1);
                    final Long[] sisa = new Long[1];

                    final int finalTotalImageIndex = totalImageIndex;
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sisa[0] = (suggestList.get(finalTotalImageIndex)) - totalHarga;
                            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
                            formatter.applyPattern("#,###,###,###");
                            String formattedString = formatter.format(suggestList.get(finalTotalImageIndex));
                            formattedString = formattedString.replaceAll(",", ".");

                            if (Math.signum(sisa[0]) == 0) {
                                tvKembalian.setText("Rp 0");
                                vaCashInfo.setDisplayedChild(2);
                                edInputCash.setText(formattedString);
                                edInputCash.setSelection(suggestList.get(finalTotalImageIndex).toString().length());
                                btnCashBayar.setEnabled(true);
                                btnCashBayar.setBackground(getResources().getDrawable(R.drawable.btn_style));
                            } else if (Math.signum(sisa[0]) > 0) {
                                tvKembalian.setText("Rp " + df.format(sisa[0]));
                                edInputCash.setText(formattedString);
                                edInputCash.setSelection(suggestList.get(finalTotalImageIndex).toString().length());
                                vaCashInfo.setDisplayedChild(2);
                                btnCashBayar.setEnabled(true);
                                btnCashBayar.setBackground(getResources().getDrawable(R.drawable.btn_style));
                            } else {
                                vaCashInfo.setDisplayedChild(1);
                                btnCashBayar.setEnabled(false);
                                btnCashBayar.setBackground(getResources().getDrawable(R.drawable.gray_button));
                            }
                            getFragSecPrefs().edit().putLong(Cfg.prefsKembalian_Long, sisa[0]).apply();
                        }
                    });
                    totalImageIndex++;
                }
            }
        }
    }

    Button btnCashBayar;
    public Button btnCashUbah;
    Button btnCashBatal;
    LinearLayout laySuggestButton;

    private void onComponent(View view) {
        btnCashBatal = (Button) view.findViewById(R.id.btnCashBatal);
        btnCashUbah = (Button) view.findViewById(R.id.btnCashUbah);
        btnCashBayar = (Button) view.findViewById(R.id.btnCashBayar);
        laySuggestButton = (LinearLayout) view.findViewById(R.id.laySuggestButton);
        tvContact = (TextView) view.findViewById(R.id.tvContact);
        isPpob = false;

        if(getTag().equals(Cfg.pulsaTypeVal)){
            btnCashUbah.setVisibility(View.GONE);
            isPpob = true;
        }

        if (getFragSecPrefs().getString(Cfg.prefsTeleponKostumerStr, "").toString().isEmpty()) {
            tvContact.setText(
                    getFragSecPrefs().getString(Cfg.prefsKostumerName_STR, Cfg.defaultKostumerName)
            );
        } else {
            tvContact.setText(
                    getFragSecPrefs().getString(Cfg.prefsKostumerName_STR, Cfg.defaultKostumerName) + " (" +
                            getFragSecPrefs().getString(Cfg.prefsTeleponKostumerStr, "") + ")"
            );


        }

        btnCashBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String tag = "cashPayment";
            if(isPpob){
                tag = Cfg.ppobCategory;
            }
            DfBatalPayment dfBatalPayment = new DfBatalPayment();
            dfBatalPayment.show(getFragmentManager(), tag);
            }
        });

        btnCashUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTransaksi(getFragSecPrefs().getString(Cfg.prefsRecentTransIdStr, null));
            }
        });

        btnCashBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dfLoading.show(getActivity().getSupportFragmentManager(), "");
                dfLoading.setCancelable(false);
                if(isPpob){
                    ppobPaymentConfirm(Cfg.PAYMENT_METHOD_CASH);

                }else{
                    getExistPaymentType(Cfg.PAYMENT_METHOD_CASH);
                    cekGlobalStok(Cfg.PAYMENT_METHOD_CASH);
                }

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvContact.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {

                // untuk running text
                Layout l = tvContact.getLayout();
                // dicek dulu apakah ada banyak baris
                // kalo lebih dari 1 baris, maka itu ellipsize (truncate)
                if (l != null) {
                    int lines = l.getLineCount();
                    if (lines > 0)
                        if (l.getEllipsisCount(lines - 1) > 0) {

                            // untuk running text
                            tvContact.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                            tvContact.setMarqueeRepeatLimit(-1);
                        }
                }
                return true;
            }
        });
    }

    TextWatcher cashEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            String originalString = edInputCash.getText().toString();
            checkBeforeCursorFocus(originalString);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            edInputCash.removeTextChangedListener(this);

            try {
                String originalString = s.toString();
                if (s.toString().isEmpty()) {
                    originalString = "0";
                }

                if (originalString.contains(".")) {
                    originalString = originalString.replaceAll("\\.", "");
                }

                long uangInput = Long.parseLong(originalString);
                long kembalian = uangInput - totalHarga;

                if (kembalian == 0) {
                    tvKembalian.setText("Rp 0");
                    vaCashInfo.setDisplayedChild(2);
//                        edInputCash.setSelection(edInputCash.getText().length());
                    btnCashBayar.setEnabled(true);
                    btnCashBayar.setBackground(getResources().getDrawable(R.drawable.btn_style));
                } else if (kembalian > 0) {
                    tvKembalian.setText("Rp " + df.format(kembalian));
                    vaCashInfo.setDisplayedChild(2);
                    btnCashBayar.setEnabled(true);
                    btnCashBayar.setBackground(getResources().getDrawable(R.drawable.btn_style));
                } else {
                    vaCashInfo.setDisplayedChild(1);
                    btnCashBayar.setEnabled(false);
                    btnCashBayar.setBackground(getResources().getDrawable(R.drawable.gray_button));
                }

                getFragSecPrefs().edit().putLong(Cfg.prefsKembalian_Long, kembalian).apply();

                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
                formatter.applyPattern("#,###,###,###");
                String formattedString = formatter.format(uangInput);
                formattedString = formattedString.replaceAll(",", ".");

                checkAfterCursorFocus(formattedString);
                edInputCash.setText(formattedString);

                // <=== FORMAT POSISI CURSOR ===>
                // TOTAL LENGTH BERKURANG
                if (beforeCountInputLenght > afterCountInputLenght) {
                    if (afterCursorIndex == 1) {
                        edInputCash.setSelection(afterCursorIndex);
                    } else if (afterCursorIndex == 0) {
                        edInputCash.setSelection(afterCursorIndex + 1);
                    } else {
                        if (beforeDotCount > afterDotCount) {
                            edInputCash.setSelection(afterCursorIndex - 1);
                        } else if (beforeDotCount < afterDotCount) {
                            // impossible
                        } else {
                            edInputCash.setSelection(afterCursorIndex);
                        }
                    }
                } else {
                    // TOTAL LENGTH BERTAMBAH
                    if (beforeDotCount > afterDotCount) {
                        // impossible
                    } else if (beforeDotCount < afterDotCount) {
                        edInputCash.setSelection(afterCursorIndex + 1);
                    } else {
                        if (afterCursorIndex <= 2) {
                            edInputCash.setSelection(edInputCash.getText().length());
                        } else {
                            edInputCash.setSelection(afterCursorIndex);
                        }
                    }
                }
                // <=== END FORMAT POSISI CURSOR ===>

//                    edInputCash.setSelection(edInputCash.getText().toString().length());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
            edInputCash.addTextChangedListener(this);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * Untuk mendapatkan detailId
     * untuk mengupdate jumlah. bukan menginsert lagi
     */
    void editTransaksi(String transId) {
        dfLoading.show(getFragmentManager(), "");
        PaymentService menulist = ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI).create(PaymentService.class);
        Call<ResEditTrans> call = menulist.editTrans(transId);
        call.enqueue(new Callback<ResEditTrans>() {
            @Override
            public void onResponse(Call<ResEditTrans> call, Response<ResEditTrans> response) {
                dfLoading.dismiss();
                if (response.isSuccessful()) {
                    ResEditTrans mm = response.body();

                    for (int i = 0; i < mm.getData().size(); i++) {
                        Long stock = mm.getData().get(i).getStock();
                        Long pembelian = mm.getData().get(i).getQty();
                        Long jumlahBeli = 0L;
                        if (pembelian > stock) {
                            jumlahBeli = stock;
                        } else if (pembelian == stock) {
                            jumlahBeli = stock;
                        } else if (stock > pembelian) {
                            jumlahBeli = pembelian;
                        }

                        dbTransaction.updateStockDJumlahDDetailId(
                                mm.getData().get(i).getDetailId(),
                                mm.getData().get(i).getStock(),
                                jumlahBeli,
                                String.valueOf(mm.getData().get(i).getItemTransId()),
                                mm.getData().get(i).getProductId()
                        );
                    }

                    getFragSecPrefs().edit().putString(Cfg.prefsNegoDisc_Str, String.valueOf(mm.getModelEditTransTrsales().getNegoDisc() != null ?
                            mm.getModelEditTransTrsales().getNegoDisc() : 0L)).apply();

                    Intent intent = new Intent(getActivity(), SummaryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    Log.d(LOG, "editTrans OK");
                } else {
                    Log.d(LOG, "Error !200");
                }
            }

            @Override
            public void onFailure(Call<ResEditTrans> call, Throwable t) {
                dfLoading.dismiss();
                Toast.makeText(getActivity(), MyUtils.getThisMethodNameForError(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    Interpolator easeInOutQuart = PathInterpolatorCompat.create(0.77f, 0f, 0.175f, 1f);

    public Animation expand(final View view) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) view.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = view.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0 so use 1 instead.
        view.getLayoutParams().height = 1;
        view.setVisibility(View.VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                view.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);

                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        animation.setInterpolator(easeInOutQuart);
        animation.setDuration(computeDurationFromHeight(view));
        view.startAnimation(animation);

        return animation;
    }

    public Animation collapse(final View view) {
        final int initialHeight = view.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setInterpolator(easeInOutQuart);

        int durationMillis = computeDurationFromHeight(view);
        a.setDuration(durationMillis);

        view.startAnimation(a);

        return a;
    }

    private static int computeDurationFromHeight(View view) {
        // 1dp/ms * multiplier
        return (int) (view.getMeasuredHeight() / view.getContext().getResources().getDisplayMetrics().density);
    }


    public void paidTrans(final String tipe) {

        getFragSecPrefs().edit().putString(Cfg.prefsPaymentType_STR, modelPaymentTypeItem.getDomainValue()).apply();
        PaymentService paymentService = ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI).create(PaymentService.class);
        Call<ResDataSuccess> call = paymentService.paidTrans(
                new RqPaidTrans(
                        String.valueOf(getFragSecPrefs().getLong(Cfg.prefsKasirId_INT, Cfg.defaultKasirId)),
                        getFragSecPrefs().getLong(Cfg.prefsOutletId_STR, Cfg.defaultOutletId),
                        getFragSecPrefs().getString(Cfg.prefsTransDate_STR, ""),
                        getFragSecPrefs().getString(Cfg.prefsNamaKasir_STR, Cfg.defaultKasirName),
                        modelPaymentTypeItem.getDomainValue(),
                        getFragSecPrefs().getString(Cfg.prefsRecentTransIdStr, "")
                ));

        call.enqueue(new Callback<ResDataSuccess>() {
            @Override
            public void onResponse(Call<ResDataSuccess> call, Response<ResDataSuccess> response) {
//                if (response.isSuccessful()){
                paidTrans2(tipe);
                /*} else {
                    Toast.makeText(getActivity(), "paid !200", Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onFailure(Call<ResDataSuccess> call, Throwable t) {
                dfLoading.dismiss();
//                loadingProgress.dismiss();
                Toast.makeText(getActivity(), "paid error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void paidTrans2(String tipe) {
        PaymentService paymentService = ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI).create(PaymentService.class);

        Gson gson = new Gson();
        ReqStok reqStoks = gson.fromJson(getFragSecPrefs().getString(Cfg.prefsReqStok_STR, ""), ReqStok.class);

        Call<ResDataSuccess> call = null;

        // Parsing text
        String originalString = edInputCash.getText().toString();
        if (originalString.contains(".")) {
            originalString = originalString.replaceAll("\\.", "");
        }
        long uangInput = Long.parseLong(originalString);

        switch (tipe) {
            case Cfg.PAYMENT_METHOD_CASH:
                call = paymentService.paidTrans2(
                        getFragSecPrefs().getString(Cfg.prefsRecentTransIdStr, null),
                        getFragSecPrefs().getLong(Cfg.prefsKembalian_Long, 0),
                        getFragSecPrefs().getLong(Cfg.prefsNegoDisc_Str, 0),
                        uangInput,
                        0,
                        getFragSecPrefs().getLong(Cfg.prefsTotalTrans_Double, 0),
                        getFragSecPrefs().getLong(Cfg.prefsTotalItem_Double, 0),
                        Double.parseDouble(getFragSecPrefs().getString(Cfg.prefsTotalHarga_Str, null)),
                        0, Cfg.TS_PAID.toString(),
                        reqStoks
                );
                break;
            case Cfg.PAYMENT_METHOD_DEBIT:
                call = paymentService.paidTrans2(
                        getFragSecPrefs().getString(Cfg.prefsRecentTransIdStr, null),
                        0,
                        getFragSecPrefs().getLong(Cfg.prefsNegoDisc_Str, 0),
                        totalHarga,
                        0,
                        getFragSecPrefs().getLong(Cfg.prefsTotalTrans_Double, 0),
                        getFragSecPrefs().getLong(Cfg.prefsTotalItem_Double, 0),
                        Double.parseDouble(getFragSecPrefs().getString(Cfg.prefsTotalHarga_Str, null)),
                        0, Cfg.TS_PAID.toString(),
                        reqStoks
                );
                break;
        }


        call.enqueue(new Callback<ResDataSuccess>() {
            @Override
            public void onResponse(Call<ResDataSuccess> call, Response<ResDataSuccess> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "paid success", Toast.LENGTH_SHORT).show();
                    getFragSecPrefs().edit().putString(Cfg.prefsTunaiStr, edInputCash.getText().toString()).apply();
                    DfTerimakasih dfTerimakasih = new DfTerimakasih();
                    dfTerimakasih.show(getActivity().getSupportFragmentManager(), "terimakasih");
                } else {
                    Toast.makeText(getActivity(), "paid2 !200", Toast.LENGTH_SHORT).show();
                }

                dfLoading.dismiss();
//                loadingProgress.dismiss();
            }

            @Override
            public void onFailure(Call<ResDataSuccess> call, Throwable t) {
                Toast.makeText(getActivity(), "paid2 error", Toast.LENGTH_SHORT).show();
                dfLoading.dismiss();
//                loadingProgress.dismiss();
            }
        });
    }

    ModelPaymentTypeItem modelPaymentTypeItem;

    public ModelPaymentTypeItem getExistPaymentType(String paymentType) {
        for (int i = 0; i < resPaymentType.getData().size(); i++) {
            if (resPaymentType.getData().get(i).getDomainValue().equalsIgnoreCase(paymentType)) {
                return modelPaymentTypeItem = (ModelPaymentTypeItem) resPaymentType.getData().get(i);
            }
        }

        return null;
    }

    void getPaymentType() {
        PaymentService paymentService = ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI).create(PaymentService.class);
        Call<ResPaymentType> call = paymentService.getPaymentType();
        call.enqueue(new Callback<ResPaymentType>() {
            @Override
            public void onResponse(Call<ResPaymentType> call, Response<ResPaymentType> response) {
                resPaymentType = response.body();
//                loadingProgress.dismiss();
//                dfLoading.show(getActivity().getSupportFragmentManager(),"");
                dfLoading.dismiss();
            }

            @Override
            public void onFailure(Call<ResPaymentType> call, Throwable t) {
//                loadingProgress.dismiss();
//                dfLoading.show(getActivity().getSupportFragmentManager(),"");
                dfLoading.dismiss();
            }
        });
    }

    private void ppobPaymentConfirm(String paymentType){
//        Boolean isTokenExpired = TokenUtils.isTokenExpired(getFragSecPrefs());
//        if(paymentType.equals(Cfg.PAYMENT_METHOD_CASH)){
//            if(isTokenExpired){
//                doCashWithRefreshToken();
//            }else{
//                paidPpobTransaction();
//            }
//
//        }else{
//            Toast.makeText(getContext(), "Pembayaran jenis ini belum didukung", Toast.LENGTH_SHORT).show();
//            dfLoading.dismiss();
//        }

    }
    private void doCashWithRefreshToken(){

    }
    private void paidPpobTransaction(){

        String originalString = edInputCash.getText().toString();
        if (originalString.isEmpty()) {
            originalString = "0";
        }
        if (originalString.contains(".")) {
            originalString = originalString.replaceAll("\\.", "");
        }

        customerPay = Long.parseLong(originalString);
        totalPayment = totalHarga;
        change = customerPay - totalPayment;

        String transId = getFragSecPrefs().getString(Cfg.pulsaTransIdKey, "");


    }

    private void checkStatusTransaksiPpob(){
        if(!ppobTransId.isEmpty() && cekStatusCounter < 10){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doCheckStatusTransaksiPpob(ppobTransId);
                }
            }, 6000);
        }else{
            dfLoading.dismiss();
        }
    }

    private void removeTransactionPending(String transId){
        Gson gson = new Gson();
        Type ppobTransListType = new TypeToken<List<ModelTransaksiPpob>>(){}.getType();
        String ppobListTransString = getFragSecPrefs().getString(Cfg.ppobTransList,null);
        List<ModelTransaksiPpob> listPpobTrans = null;
        if(ppobListTransString!=null){
            listPpobTrans = gson.fromJson(ppobListTransString, ppobTransListType);
            for(int counter=0;counter<listPpobTrans.size();counter++){
                if(listPpobTrans.get(counter).getTransaId().equals(transId)){
                    listPpobTrans.remove(listPpobTrans.get(counter));
                    ppobListTransString = gson.toJson(listPpobTrans);
                    getFragSecPrefs().edit().putString(Cfg.ppobTransList, ppobListTransString).apply();
                    break;

                }

            }

        }

    }

    private void goHomeAndRemove(String idTrans){
        removeTransactionPending(idTrans);
        goLandingPage();
    }

    private void goLandingPage(){
        Intent intent = new Intent(getContext(), LandingPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void doCheckStatusTransaksiPpob(String transId){
        if(transId.contains("Invalid") || transId.contains("invalid")){
            Toast.makeText(getContext(), "Transaksi Gagal", Toast.LENGTH_LONG).show();
            goLandingPage();
            return;
        }
//        HomeService service = ApiClient.serviceGenerator(Cfg.BASEURL_PPOB_APIS).create(HomeService.class);
//        Call<ResCekTransaksiPulsa> call = service.cekTransaksiPulsa(TokenUtils.getBearer(getFragSecPrefs()), transId);
//        call.enqueue(new Callback<ResCekTransaksiPulsa>() {
//            @Override
//            public void onResponse(Call<ResCekTransaksiPulsa> call, Response<ResCekTransaksiPulsa> response) {
//                ResCekTransaksiPulsa data = response.body();
//                String msg = "";
//                String tag = Cfg.ppobCategory;
//                if(data.getStatus().equals(PPOB_STATE.IS_SUCCESS.getText())){
//                    cekStatusCounter = 0;
//                    msg = getContext().getString(R.string.ppob_transaksi_sukses);
//                    ppobPaymentFinish(msg,data, tag);
//                    removeTransactionPending(data.getIdTransaction());
//                    dfLoading.dismiss();
//                }
//                else if(data.getStatus().equals(PPOB_STATE.IS_FAILED.getText())){
//                    msg = data.getMessage().isEmpty()?data.getMessage() : getContext().getString(R.string.ppob_transaksi_gagal);
//                    cekStatusCounter = 0;
//                    dfLoading.dismiss();
//                    Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
//                    goHomeAndRemove(data.getIdTransaction());
//                }
//
//                else if(data.getStatus().equals(PPOB_STATE.IS_CANCELED.getText())){
//                    msg = getContext().getString(R.string.ppob_transaksi_batal);
//                    dfLoading.dismiss();
//                    Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
//                    goHomeAndRemove(data.getIdTransaction());
//
//                }
//                else if(data.getStatus().equals(PPOB_STATE.ON_TRANSACTION.getText())){
////                    msg = getContext().getString(R.string.ppob_transaksi_gagal);
////                    cekStatusCounter = 0;
////                    dfLoading.dismiss();
////                    Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
////                    goHomeAndRemove(data.getIdTransaction());
//
//                    cekStatusCounter++;
//                    checkStatusTransaksiPpob();
//
//                }
//                else if(data.getStatus().equals(PPOB_STATE.ON_WAITING.getText())){
//                    cekStatusCounter++;
//                    checkStatusTransaksiPpob();
//                }
//                else {
//                    msg = "Status Transaksi : "+data.getStatus();
//                    dfLoading.dismiss();
//                    Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
//                    goHomeAndRemove(data.getIdTransaction());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResCekTransaksiPulsa> call, Throwable throwable) {
//                Log.e(Cfg.TAG_COMMON, "error get status transaksi pulsa");
//                dfLoading.dismiss();
//            }
//        });

    }



    void cekGlobalStok(final String mode) {
        final Gson gson = new Gson();
//        SummaryService summaryService =ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI).create(SummaryService.class);
        SummaryService summaryService = ApiClient.serviceGenerator(Cfg.BASEURL_TRANSAKSI_GLOBAL).create(SummaryService.class);

        ReqGlobal reqGlobal = gson.fromJson(getFragSecPrefs().getString(Cfg.prefsReqGlobal_STR, null), ReqGlobal.class);
        Call<ResCheckListStock> call = summaryService.checkGlobalStockItem(reqGlobal);
        call.enqueue(new Callback<ResCheckListStock>() {
            @Override
            public void onResponse(Call<ResCheckListStock> call, Response<ResCheckListStock> response) {
                ResCheckListStock rsp = response.body();
                //TODO: nanti ini diunblock karena belum diupload sama dimas
                // Jika klik tombol bayar
                // Jika transaksi gagal karena stok habis
                if (rsp.isStatus() == false) {
                    Toast.makeText(getActivity(), "Stok item habis", Toast.LENGTH_SHORT).show();

                    //TODO: [IMPROVE] updateStock -> updateStock(List<T> productIdList)
                    for (int i = 0; i < rsp.getData().size(); i++) {

                        Long stock = Long.parseLong(rsp.getData().get(i).getStock());
                        Long pembelian = Long.parseLong(rsp.getData().get(i).getQty());
                        Long jumlahBeli = 0L;
                        if (pembelian > stock) {
                            jumlahBeli = stock;
                        } else if (pembelian == stock) {
                            jumlahBeli = stock;
                        }
                        dbTransaction.updateStockDanJumlah(
                                Long.parseLong(rsp.getData().get(i).getStock()),
                                jumlahBeli,
                                getFragSecPrefs().getString(Cfg.prefsRecentTransIdStr, null),
                                Long.parseLong(rsp.getData().get(i).getProductId()));

                        listStockItems.add(new ResCheckListStockItem(
                                rsp.getData().get(i).getProductId(),
                                rsp.getData().get(i).getQty(),
                                rsp.getData().get(i).getStock(),
                                rsp.getData().get(i).getProductName()
                        ));
                    }

                    DfInfoStockHabis dfInfoStockHabis = new DfInfoStockHabis();
                    dfInfoStockHabis.show(getFragmentManager(), "");
                    return;
                } else {
                    // Jika transaksi berhasil karena stok masih ada
                    paidTrans(mode);
                }
            }

            @Override
            public void onFailure(Call<ResCheckListStock> call, Throwable t) {
                Toast.makeText(getActivity(), MyUtils.getThisMethodNameForError(), Toast.LENGTH_LONG).show();
                dfLoading.dismiss();
            }
        });
    }

    void checkAfterCursorFocus(String text) {
        afterCursorIndex = edInputCash.getSelectionStart();
        afterCountInputLenght = edInputCash.getText().length();
        afterDotCount = dot_InString(text);
    }

    void checkBeforeCursorFocus(String text) {
        beforeCountInputLenght = edInputCash.getText().length();
        beforeCursorIndex = edInputCash.getSelectionStart();
        beforeDotCount = dot_InString(text);
    }

    int dot_InString(String formattedString) {
        return formattedString.replaceAll("[^.]", "").length();
    }
}