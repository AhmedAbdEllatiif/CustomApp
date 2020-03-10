package com.ahmed.customapp.Calulator;

import android.util.Log;

import java.util.ArrayList;

public class CalculatorHelper {

   private static final String TAG = "CalculatorHelper";

   public static final int ONE = 1;
   public static final int TWO = 2;
   public static final int THREE = 3;
   public static final int FOUR = 4;
   public static final int FIVE = 5;
   public static final int SIX = 6;
   public static final int SEVEN = 7;
   public static final int EIGHT = 8;
   public static final int NINE = 9;


   public static final String ADD_str = "+";
   public static final String SUB_str = "-";
   public static final String MULTI_str = "*";
   public static final String DIV_str = "÷";
   public static final String EQUAL_str = "=";
   public static final String FLIP_str = "+/-";
   public static final String MOD_str = "%";
   public static final String CLR_str = "AC";

   public static final int DOT = 100;
   public static final int ADD = 101;
   public static final int MULTI = 102;
   public static final int DIV = 103;
   public static final int EQUAL = 104;
   public static final int FLIP = 105;
   public static final int MOD = 106;
   public static final int CLR = 107;
   public static final int SUB = 108;



   private static ArrayList<String> operatorsList;
   public static ArrayList<String> getOperatorList(){

      Log.e(TAG, "getOperator: not null ");
      if (operatorsList != null) return operatorsList;

      operatorsList = new ArrayList<>();
      operatorsList.add("%");
      operatorsList.add("+/-");
      operatorsList.add("÷");
      operatorsList.add("*");
      operatorsList.add("-");
      operatorsList.add("+");
      Log.e(TAG, "getOperator: null" );
      return operatorsList;

   }



   private static ArrayList<String> getCalc_values(){
      ArrayList<String>  str_list = new ArrayList<>();

      str_list.add("AC");
      str_list.add("%");
      str_list.add("+/-");
      str_list.add("÷");
      str_list.add("7");
      str_list.add("8");
      str_list.add("9");
      str_list.add("*");
      str_list.add("4");
      str_list.add("5");
      str_list.add("6");
      str_list.add("-");
      str_list.add("1");
      str_list.add("2");
      str_list.add("3");
      str_list.add("+");
      str_list.add("0");
      str_list.add(".");
      str_list.add("=");

      return str_list;
   }

   public static ArrayList<Calc_ItemList> getCalc_list(){
      ArrayList<Calc_ItemList> calc_itemLists = new ArrayList<>();
      for (String s:
           getCalc_values()) {
         calc_itemLists.add(new Calc_ItemList(s));
      }
      return calc_itemLists;
   }


}
