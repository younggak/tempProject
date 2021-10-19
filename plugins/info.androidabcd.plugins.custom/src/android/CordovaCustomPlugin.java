package info.androidabcd.plugins.custom;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import com.siin.One.MainActivity;
import com.siin.One.DataCenter;
import com.siin.One.PhoneBook;
import com.siin.One.SMSBook;

import android.content.Context;
import com.siin.One.PreferenceManager;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class CordovaCustomPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getPhonebookList")) {
            // String message = args.getString(0);
            // this.coolMethod(message, callbackContext);
            // String result ="";
            // int i = 0;
            // ArrayList<PhoneBook> list = DataCenter.getInstance().getList();

            // while(i < list.size()){
            //     result = result +"@"+list.get(i);
            //     i++;
            // }
            // callbackContext.success(result);
            // return true;
        }
        else if(action.equals("getListSize")){
            int result =0;
            ArrayList<SMSBook> list = DataCenter.getInstance().getSmsBookList();
            result=list.size();
            callbackContext.success(result);
            return true;
        }
        else if(action.equals("getSMS")){
            String numbers="";
            String texts="";
            int i = 0;
            ArrayList<SMSBook> list = DataCenter.getInstance().getSmsBookList();

            while(i < list.size()){
                numbers = numbers +"@"+list.get(i).getNumber();
                texts = texts +"@"+list.get(i).getText();
                i++;
            }

            callbackContext.success(numbers + "#" + texts);
            return true;
        }
        else if(action.equals("sharedPreferenceClear")){
            Context applicationContext = MainActivity.getContextOfApplication();
            PreferenceManager.clear(applicationContext);
            callbackContext.success("");
            return true;
        }
        return false;
    }


    private void getPhonebookList(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void getListSize(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void getSMS(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void sharedPreferenceClear(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
