package id.hike.apps.android_mpos_mumu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;

public class FragmentPulangPergi extends Fragment {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    EditText editTanggalBerangkat, editTanggalPulang;

    public FragmentPulangPergi() {
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
        return inflater.inflate(R.layout.fragment_pulang_pergi, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
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

        ImageView tanggalBerangkat = view.findViewById(R.id.imageView17);
        editTanggalBerangkat = view.findViewById(R.id.tanggalBerangkat);
        editTanggalBerangkat.setEnabled(false);

        ImageView kalenderPulang = view.findViewById(R.id.kalenderPulang);
        editTanggalPulang = view.findViewById(R.id.tanggalPulang);
        editTanggalPulang.setEnabled(false);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        kalenderPulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialogDua();
            }
        });

        tanggalBerangkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        tambahDewasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String b = String.valueOf((totalDewasa.getText().toString()));

                    if(b == null || b.isEmpty()){
                        int a = 0;
                        a = a + 1;
                        totalDewasa.setText(String.valueOf(a));
                    }else{
                        int a = Integer.parseInt((totalDewasa.getText().toString()));
                        a = a + 1;
                        //int c = a < 10 ? a : 10;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalDewasa.setText(String.valueOf(a));
                    }
                }catch(NumberFormatException ex){
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        kurangDewasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String b = String.valueOf((totalDewasa.getText().toString()));

                    if(b == null || b.isEmpty()){
                        int a = 0;
                        a = a - 0;
                        totalDewasa.setText(String.valueOf(a));
                    }else{
                        int a = Integer.valueOf(totalDewasa.getText().toString());
                        a = a - 1;
                        int u = a > 0 ? a : 0;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalDewasa.setText(String.valueOf(u));
                    }
                }catch(NumberFormatException ex){
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        tambahAnakanak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String b = String.valueOf((totalAnakanak.getText().toString()));

                    if(b == null || b.isEmpty()){
                        int a = 0;
                        a = a + 1;
                        totalAnakanak.setText(String.valueOf(a));
                    }else{
                        int a = Integer.parseInt((totalAnakanak.getText().toString()));
                        a = a + 1;
                        //int c = a < 10 ? a : 10;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalAnakanak.setText(String.valueOf(a));
                    }
                }catch(NumberFormatException ex){
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

                try{

                    String b = String.valueOf((totalAnakanak.getText().toString()));

                    if(b == null || b.isEmpty()){
                        int a = 0;
                        a = a - 0;
                        totalAnakanak.setText(String.valueOf(a));
                    }else{
                        int a = Integer.valueOf(totalAnakanak.getText().toString());
                        a = a - 1;
                        int u = a > 0 ? a : 0;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalAnakanak.setText(String.valueOf(u));
                    }
                }catch(NumberFormatException ex){
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        tambahBayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String b = String.valueOf((totalBayi.getText().toString()));

                    if(b == null || b.isEmpty()){
                        int a = 0;
                        a = a + 1;
                        totalBayi.setText(String.valueOf(a));
                    }else{
                        int a = Integer.parseInt((totalBayi.getText().toString()));
                        a = a + 1;
                        //int c = a < 10 ? a : 10;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalBayi.setText(String.valueOf(a));
                    }
                }catch(NumberFormatException ex){
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        kurangBayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String b = String.valueOf((totalBayi.getText().toString()));

                    if(b == null || b.isEmpty()){
                        int a = 0;
                        a = a - 0;
                        totalBayi.setText(String.valueOf(a));
                    }else{
                        int a = Integer.valueOf(totalBayi.getText().toString());
                        a = a - 1;
                        int u = a > 0 ? a : 0;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        totalBayi.setText(String.valueOf(u));
                    }
                }catch(NumberFormatException ex){
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });
    }
    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */

        /*datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                editTanggalBerangkat.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));*/

        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                editTanggalBerangkat.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    private void showDateDialogDua(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */

        /*datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                editTanggalBerangkat.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));*/

        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                editTanggalPulang.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}