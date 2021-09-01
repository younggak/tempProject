package info.androidabcd.plugins.custom;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import com.siin.One.DataCenter;
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
        if (action.equals("getContentList")) {
            // String message = args.getString(0);
            // this.coolMethod(message, callbackContext);
            String result1="happy";
            String result2="poop";
            String result ="";
            int i = 0;
            ArrayList<String> list = DataCenter.getInstance().getList();
           
            result1 = DataCenter.getInstance().getNumber();
            result2 = DataCenter.getInstance().getText();

            while(i < list.size()){
                result = result +"@"+list.get(i);
                i++;
            }
            callbackContext.success(result);
            return true;
        }
        else if(action.equals("getListSize")){
            int result =0;
            ArrayList<String> list = DataCenter.getInstance().getList();
            result=list.size();
            callbackContext.success(result);
            return true;
        }
        else if(action.equals("getSMS")){
            String result1="happy";
            String result2="SMS";
            result1 = DataCenter.getInstance().getNumber();
            result2 = DataCenter.getInstance().getText();
            callbackContext.success(result1 + " " + result2);
            return true;
        }
        return false;
    }

    private void getContentList(String message, CallbackContext callbackContext) {
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
}
