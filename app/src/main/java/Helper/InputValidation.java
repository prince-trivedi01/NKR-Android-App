package Helper;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

/**
 * Created by Prince on 18-04-2018.
 */

public class InputValidation {



    private Context context;
    /**
     * constructor
     *
     * @param context
     */
    public InputValidation(Context context) {

        this.context = context;
    }

    /**
     * method to check InputEditText filled .
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */
    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        try {
            if (value.isEmpty()) {
                textInputLayout.setError(message);

                return false;
            } else {
                textInputLayout.setErrorEnabled(true);
            }

            return true;
        }
        catch (Exception e) {
            return false;

        }
    }


    /**
     * method to check InputEditText has valid User .
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */
    public boolean isInputEditTextUserId(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        try {
            if (value.isEmpty()) {
                textInputLayout.setError(message);

                return false;
            } else {
                textInputLayout.setErrorEnabled(false);
            }

            return true;

        }
        catch (Exception e){
            return false;

        }

    }


    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        try {
            if (!value1.contentEquals(value2)) {
                textInputLayout.setError(message);

                return false;
            } else {
                textInputLayout.setErrorEnabled(false);
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }




}
