package com.pakistan.jkutils.utils.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtils {

    private FragmentUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Can not instantiate Abstract Class");
    }

    // Adding Fragment
    public static void addFragment(FragmentManager fm,int id, Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(id, fragment, fragment.getTag());
        ft.commit();
    }

    // Replacing Fragment
    public static void replaceFragment(FragmentManager fm,int id, Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment, fragment.getTag());
        ft.commit();
    }

	// Removing One or Fragments
	public static void removeFragment(FragmentManager fm, Fragment... fragments){
        for(Fragment f : fragments){
			FragmentTransaction ft = fm.beginTransaction();
			ft.remove(f);
			ft.commit();
		}
    }

}
