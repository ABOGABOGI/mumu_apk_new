package id.hike.apps.android_mpos_mumu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;

public class FragmentSekaliJalan extends Fragment {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    EditText editTanggalBerangkat;

    @BindView(R.id.pilihKotaAsal)
    ImageView pilihKotaAsal;

    @BindView(R.id.pilihKotaTujuan)
    ImageView pilihKotaTujuan;

    /*@BindView(R.id.imageView18)
    ImageView jumlahDewasaDiKurang;

    @BindView(R.id.imageView19)
    ImageView jumlahDewasaDiTambah;

    @BindView(R.id.editText)
    EditText totalDewasa;

    @BindView(R.id.imageView20)
    ImageView anakanakDikurang;

    @BindView(R.id.imageView22)
    ImageView anakanakDitambah;

    @BindView(R.id.editText21)
    ImageView totalAnakanak;

    @BindView(R.id.imageView23)
    ImageView jumlahBayiDiKurang;

    @BindView(R.id.imageView25)
    ImageView jumlahBayiDiTambah;

    @BindView(R.id.editText24)
    ImageView totalBayi;*/

    public FragmentSekaliJalan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_fragment_sekali_jalan, container, false);

        ConstraintLayout fragmetnSekaliJalan = view.findViewById(R.id.fragmentSekaliJalan);
        ConstraintLayout constraintLayout = view.findViewById(R.id.isi);
        ConstraintLayout constraintLayoutDua = view.findViewById(R.id.constraintLayout2);
        ConstraintLayout constraintLayoutTiga = view.findViewById(R.id.constraintLayout3);
        ConstraintLayout constraintLayoutEmpat = view.findViewById(R.id.constraintLayout9);


        ImageView pilihKotaAsal = view.findViewById(R.id.pilihKotaAsal);
        TextView namaKotaTujuan = view.findViewById(R.id.textView55);
        try {
            String message = getArguments().getString(Cfg.KOTA_TUJUAN);
            if (message != null) {
                namaKotaTujuan.setText(message);
            } else {
                namaKotaTujuan.setText("-");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        ImageView kurangDewasa = view.findViewById(R.id.imageView18);
        ImageView tambahDewasa = view.findViewById(R.id.imageView19);
        EditText totalDewasa = view.findViewById(R.id.editText);

        ImageView kurangAnakanak = view.findViewById(R.id.imageView20);
        ImageView tambahAnakanak = view.findViewById(R.id.imageView22);
        EditText totalAnakanak = view.findViewById(R.id.editText21);

        ImageView kurangBayi = view.findViewById(R.id.imageView23);
        ImageView tambahBayi = view.findViewById(R.id.imageView25);
        EditText totalBayi = view.findViewById(R.id.editText24);

        //Button button = view.findViewById(R.id.btnCari);

        ImageView tanggalBerangkat = view.findViewById(R.id.imageView17);
        editTanggalBerangkat = view.findViewById(R.id.tanggalBerangkat);
        editTanggalBerangkat.setEnabled(false);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tanggalBerangkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        pilihKotaAsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                constraintLayout.setVisibility(View.GONE);

//                constraintLayout.setVisibility(View.GONE);
//                constraintLayoutDua.setVisibility(View.GONE);
//                constraintLayoutTiga.setVisibility(View.GONE);
//                constraintLayoutEmpat.setVisibility(View.GONE);
//                button.setVisibility(View.GONE);

                FragmentTransaction fm = getFragmentManager().beginTransaction();
                fm.replace(R.id.fragmentSekaliJalan, new KotaAsal());
                fm.addToBackStack(null).commit();

            }
        });

        tambahDewasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String b = String.valueOf((totalDewasa.getText().toString()));

                    if (b == null || b.isEmpty()) {
                        int a = 0;
                        a = a + 1;
                        totalDewasa.setText(String.valueOf(a));
                    } else {
                        int a = Integer.parseInt((totalDewasa.getText().toString()));
                        a = a + 1;
                        //int c = a < 10 ? a : 10;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalDewasa.setText(String.valueOf(a));
                    }
                } catch (NumberFormatException ex) {
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        kurangDewasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String b = String.valueOf((totalDewasa.getText().toString()));

                    if (b == null || b.isEmpty()) {
                        int a = 0;
                        a = a - 0;
                        totalDewasa.setText(String.valueOf(a));
                    } else {
                        int a = Integer.valueOf(totalDewasa.getText().toString());
                        a = a - 1;
                        int u = a > 0 ? a : 0;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalDewasa.setText(String.valueOf(u));
                    }
                } catch (NumberFormatException ex) {
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        tambahAnakanak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String b = String.valueOf((totalAnakanak.getText().toString()));

                    if (b == null || b.isEmpty()) {
                        int a = 0;
                        a = a + 1;
                        totalAnakanak.setText(String.valueOf(a));
                    } else {
                        int a = Integer.parseInt((totalAnakanak.getText().toString()));
                        a = a + 1;
                        //int c = a < 10 ? a : 10;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalAnakanak.setText(String.valueOf(a));
                    }
                } catch (NumberFormatException ex) {
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        kurangAnakanak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*int b = Integer.valueOf(totalAnakanak.getText().toString());

                //int[] nilai = new int[b];
                //jika incompatible type harus menggunakan kondisi if

                int i = b - 1;
                int u = i > 0 ? i : 0;

                //int min = u;
                //min = e > c;
                totalAnakanak.setText(String.valueOf(u));*/

                try {

                    String b = String.valueOf((totalAnakanak.getText().toString()));

                    if (b == null || b.isEmpty()) {
                        int a = 0;
                        a = a - 0;
                        totalAnakanak.setText(String.valueOf(a));
                    } else {
                        int a = Integer.valueOf(totalAnakanak.getText().toString());
                        a = a - 1;
                        int u = a > 0 ? a : 0;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalAnakanak.setText(String.valueOf(u));
                    }
                } catch (NumberFormatException ex) {
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        tambahBayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String b = String.valueOf((totalBayi.getText().toString()));

                    if (b == null || b.isEmpty()) {
                        int a = 0;
                        a = a + 1;
                        totalBayi.setText(String.valueOf(a));
                    } else {
                        int a = Integer.parseInt((totalBayi.getText().toString()));
                        a = a + 1;
                        //int c = a < 10 ? a : 10;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalBayi.setText(String.valueOf(a));
                    }
                } catch (NumberFormatException ex) {
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        kurangBayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String b = String.valueOf((totalBayi.getText().toString()));

                    if (b == null || b.isEmpty()) {
                        int a = 0;
                        a = a - 0;
                        totalBayi.setText(String.valueOf(a));
                    } else {
                        int a = Integer.valueOf(totalBayi.getText().toString());
                        a = a - 1;
                        int u = a > 0 ? a : 0;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalBayi.setText(String.valueOf(u));
                    }
                } catch (NumberFormatException ex) {
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        return view;
    }

    private void showDateDialog() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */

        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                editTanggalBerangkat.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
