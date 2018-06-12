package in.antitheft.hackathon_security;

/**
 * Created by intel on 22-02-2015.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;


public class MySMSApp extends BroadcastReceiver {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Text = "textKey";
    SharedPreferences sharedpreferences;



    public static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {


        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        // TODO Auto-generated method stub
        if (intent.getAction().equals(ACTION)){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++){
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                for (SmsMessage message : messages){

                    String strMessageFrom = message.getDisplayOriginatingAddress();
                    String strMessageBody = message.getDisplayMessageBody();

                    //Toast.makeText(context, "SMS Message received from:" +strMessageFrom, Toast.LENGTH_LONG).show();
                    //Toast.makeText(context, "SMS Message content" +strMessageBody, Toast.LENGTH_LONG).show();
                    String text = sharedpreferences.getString(Text, "");

if(strMessageBody.contains(text)){

   /* Intent i = new Intent(context, open.class);
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(i);*/
    GPSTracker gps;
    gps = new GPSTracker(context);

    // check if GPS enabled
    if(gps.canGetLocation()){

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        // \n is for new line
        //Toast.makeText(context, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(strMessageFrom, null, "Lat: " + latitude + "\nLong: " + longitude, null, null);
          //  Toast.makeText(context, "SMS sent.",
            //        Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            //Toast.makeText(context,
                 //   "SMS faild, please try again.",
               //     Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }else{
        // can't get location
        // GPS or Network is not enabled
        // Ask user to enable GPS/network in settings
        gps.showSettingsAlert();
    }
}


                }
            }
        }
    }


}