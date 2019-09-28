package com.pakistan.jkutils.utils.manager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionManager {
    
    private static PermissionManager instance;
    private Context mContext;

    private List<String> mPermissionsList;

    private final String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    // Permission Interface
    public interface AllPermissionsGrantedListener {
        void allGranted(boolean granted);
    }
    
    private AllPermissionsGrantedListener mListener;
    
    public void setListener(AllPermissionsGrantedListener listener){
        mListener = listener;
    }
    
    public AllPermissionsGrantedListener getListener() {
        return mListener;
    }
    
    public static PermissionManager getInstance(Context context){
        if(instance == null)
            instance = new PermissionManager(context);
        return instance;
    }
    
    private PermissionManager(Context context){
        mContext = context;
        mPermissionsList = new ArrayList<>();
        addDefaultPermissions();
        askPermissions();
    }

    private void addDefaultPermissions() {
        mPermissionsList.clear();
        mPermissionsList.addAll(Arrays.asList(PERMISSIONS));
    }

    public void askPermissions(){

        if(isPermissionRequired() && !isPermissionsEnabled()){

            Dexter
                    .withActivity((Activity) mContext)
                    .withPermissions(mPermissionsList)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if(report.areAllPermissionsGranted())
                                mListener.allGranted(true);
                            else
                                mListener.allGranted(false);
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    })
                    .check();

        }

    }

    private boolean isPermissionRequired(){
        boolean yesFlag = false;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            yesFlag = true;
        }

        return yesFlag;
    }

    private boolean isPermissionsEnabled(){
        boolean flag = false;
        
        for(int i = 0; i < mPermissionsList.size(); i++){
            if(ContextCompat.checkSelfPermission(mContext,mPermissionsList.get(i)) == PackageManager.PERMISSION_GRANTED){
                flag = true;
            }else {
                flag = false;
                break;
            }
        }
        
        return flag;
    }

    public void addPermission(String permission){
        mPermissionsList.add(permission);
    }

}
