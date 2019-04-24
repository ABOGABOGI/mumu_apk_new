package id.hike.apps.android_mpos_mumu.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by root on 24/09/17.
 */

public class FormatCurrencyEditInCursor implements TextWatcher {
    private EditText editText;
    private int afterCursorIndex, afterCountInputLenght, afterDotCount;
    private int beforeCountInputLenght, beforeCursorIndex, beforeDotCount;

    public FormatCurrencyEditInCursor(EditText editText) {
        this.editText=editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        String originalString = editText.getText().toString();
        checkBeforeCursorFocus(originalString);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        editText.removeTextChangedListener(this);
        String originalString = editText.getText().toString();

        if (s.toString().isEmpty()) {
            originalString = "0";
//            return;
        }

        // Untuk menghilangkan titik separator
        if (originalString.contains(".") || originalString.contains(",")) {
            originalString = originalString.replaceAll("\\.", "");
            originalString = originalString.replaceAll("\\,", "");
        }

        long uangInput = Long.parseLong(originalString);

        // Format curency
        String formattedString = MyUtils.formatCurrency(uangInput);

        checkAfterCursorFocus(formattedString);
        editText.setText(formattedString);

        // <=== FORMAT POSISI CURSOR ===>

        // TOTAL LENGTH BERKURANG
        if (beforeCountInputLenght > afterCountInputLenght) {
            if (afterCursorIndex == 1) {
                editText.setSelection(afterCursorIndex);
            } else if (afterCursorIndex == 0) {
                editText.setSelection(afterCursorIndex);
            } else {
                // DOT BERKURANG
                if (beforeDotCount > afterDotCount) {
                    editText.setSelection(afterCursorIndex - 1);
                } else if (beforeDotCount < afterDotCount) {
                    // impossible
                }
                // JUMLAH DOT SAMA
                else {
                    editText.setSelection(afterCursorIndex);
                }
            }
        } else {
            // TOTAL LENGTH BERTAMBAH
            if (beforeDotCount > afterDotCount) {
                // impossible
            }
            // DOT BERTAMBAH
            else if (beforeDotCount < afterDotCount) {
                editText.setSelection(afterCursorIndex + 1);
            }
            // JUMLAH DOT SAMA
            else {
                if (afterCursorIndex <= 0) {
                    editText.setSelection(editText.getText().length());
                } else {
                    // Ini menghilangkan bug jika hanya ada angka 0
                    // lalu dihapus
                    if (editText.getText().toString().length()==1){
                        editText.setSelection(1);
                    } else {
                        editText.setSelection(afterCursorIndex);
                    }
                }
            }
        }

        // buat menghindari pembuatan angka jadi puluhan, menambahkan angka didepan angka 0.
        if (originalString.equalsIgnoreCase("0")){
            editText.setSelection(1);
        }
        // <=== END FORMAT POSISI CURSOR ===>
        editText.addTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void checkAfterCursorFocus(String text) {
        afterCursorIndex = editText.getSelectionStart();
        afterCountInputLenght = editText.getText().length();
        afterDotCount = dot_InString(text);
    }

    private void checkBeforeCursorFocus(String text) {
        beforeCountInputLenght = editText.getText().length();
        beforeCursorIndex = editText.getSelectionStart();
        beforeDotCount = dot_InString(text);
    }

    private int dot_InString(String formattedString) {
        return formattedString.replaceAll("[^.]", "").length();
    }
}
