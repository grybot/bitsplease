package gr.bitsplease.bitsplease.Utilities;

import java.util.List;

public class Checker {
    public static boolean Checker(List<Integer> integ, int id){
        boolean T = true;
        for (int i : integ){
            if(i != id){
            T = false;
            break;
            }
        }
        if(!T){
            return true;
        }else{
            return false;
        }
    }
}
