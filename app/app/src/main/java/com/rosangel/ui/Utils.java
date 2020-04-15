package com.rosangel.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.rosangel.R;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    /**
     * URL general del servidor
     * */
    //public static final String URL_SERVER = "http://10.10.1.108:9047/bin";
    //public static final String URL_SERVER = "http://carvel.redpacifico.com/bin";
    //public static String URL_SERVER = "http://carvel.redpacifico.com:9047/bin";
    public static String URL_SERVER;
    public static String PORT_SERVER = "9047";
    public static String DIR_SERVER = "bin";

    public static void showMessageShort(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static void showMessageLong(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static ActionBar configurarToolbar(final Activity activity, ActionBar actionBar, Toolbar toolbar, String titulo) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(titulo);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
                activity.finish();
            }
        });

        return actionBar;
    }

    public static ActionBar configurarToolbarSteps(final Activity activity, ActionBar actionBar, Toolbar toolbar, String titulo) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(titulo);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
                activity.finish();
            }
        });

        return actionBar;
    }

    public static String dateToString(Date fecha, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String fechaComoCadena = sdf.format(fecha);
        return fechaComoCadena;
    }

    public static Date stringToDate(String aDate, String aFormat) {
        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;
    }
    public static String getFechaFormateada(String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(new Date().getTime());
    }
    public static String getFechaFormateada(Date date, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(date);
    }
    public static Date sumarDiasAFecha(Date fecha, int dias){
        if (dias==0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }
}
