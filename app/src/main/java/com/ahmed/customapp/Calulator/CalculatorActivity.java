package com.ahmed.customapp.Calulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ahmed.customapp.Helpers.MyRecyclerViewDecoration;
import com.ahmed.customapp.R;

import java.util.Objects;

import static com.ahmed.customapp.Calulator.CalculatorHelper.*;

public class CalculatorActivity extends AppCompatActivity {

    private static final String TAG = "CalculatorActivity";


    private TextView screenTwoText;
    private TextView screenThreeText;
    private EditText screenOneText;


    //operation
    private final char Addition = '+';
    private final char Subtruction = '-';
    private final char Multiplication = '*';
    private final char Divison = '÷';
    private final char mod = '%';
    private final char Equ = 0;
    private char charOpe;
    private double num1 = Double.NaN;
    private double num2;


    private CalculatorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //initializeViews
        initViews();

        //initialize recyclerView and Adapter
        initRecyclerView();

    }



    /**
     * To initialize views
     * */
    private void initViews(){
        screenTwoText = findViewById(R.id.txtResult);
        screenThreeText = findViewById(R.id.txtFinalResult);
        screenOneText = findViewById(R.id.txtCalc);
        screenOneText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }

        });
    }


    /**
     * To initialize recyclerView and its adapter
     */
    private void initRecyclerView() {
        int size = getCalc_list().size();
        GridLayoutManager gridLayoutManager = getGridLayoutManger(size);
        RecyclerView recyclerView = findViewById(R.id.calc_recyclerView);
        adapter = new CalculatorAdapter(this, getCalc_list());
        recyclerView.addItemDecoration(new MyRecyclerViewDecoration(10));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        onAdapterClicked();
    }


    /**
     * To Get the gridLayoutManger
     */
    private GridLayoutManager getGridLayoutManger(final int listSize) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int mod = (position + 1) % 4;

                if (position == listSize - 1) return 2;

                if (position == listSize - 3) return 2;

                if (mod == 0 && position != 0) return 2;

                return 1;

            }
        });
        return gridLayoutManager;
    }

    private boolean isTextCalcIsEmpty() {
        if (getValueScreenOne().equals("")) {
            addToScreenOne(null);
            return true;
        }
        return false;
    }

    /**
     * Return true if screen one value matches number pattern
     * */
    private boolean isScreenOneHasNum(){
        if (!getValueScreenOne().equals("")){
            String currentValue = getValueScreenOne();
            return  currentValue.matches(getString(R.string.num_format));
        }
        return false;
    }

    /**
     * Return true if the value matches the pattern of numbers
     * */
    private boolean isNum(String value) throws NullPointerException {
        if (value!= null) {
            return Objects.requireNonNull(value).matches(getString(R.string.num_format));
        }
        return false;
    }






    /**
     * To clear the numbers and all screens
     * */
    private void clr_screens() {
        num1 = Double.NaN;
        num2 = Double.NaN;
        addToScreenOne(null);
        addToScreenTwo(null);
        addToScreenThree(null);

    }


    /**
     * When user press flip operator with dot or zero
     * don't make changes
     * */
    private boolean isFlipPressedWithDotORZero() {
        if (getValueScreenOne().equals(getString(R.string.dot)) || getValueScreenOne().equals("0")) {
            addToScreenOne(null);
            return true;
        }
        return false;
    }

    /**
    * To flip the number from positive to negative and vice versa
    * */
    private void addFlip_operator() {
        if (!isTextCalcIsEmpty() && !isFlipPressedWithDotORZero()) {
            String flippedNum = String.valueOf(-Double.parseDouble(getValueScreenOne()));
            addToScreenOne(flippedNum);
        }
    }



    /**
     * To Check which operator is pressed
     * Then compute
     * */
    private void chooseOperatorAndCompute(String operator) {
        switch (operator) {
            case ADD_str:
                charOpe = Addition;
                break;
            case SUB_str:
                charOpe = Subtruction;
                break;
            case MULTI_str:
                charOpe = Multiplication;
                break;
            case DIV_str:
                charOpe = Divison;
                break;
            case MOD_str:
                charOpe = mod;
                break;
            case EQUAL_str:
                charOpe = Equ;
                break;
            case FLIP_str:
                addFlip_operator();
                return;//must return to prevent compute method
        }
        compute();
        //Check if screen two has operator
        if (isOperator(getValueScreenTwo(charOpe))) { // user  press on operator
            addOperatorToLastResult(charOpe);
        }
        if (!isOperator(getValueScreenTwo(charOpe))) { //user press on non operator
            addToScreenTwo(screenTwoText.getText().toString() + screenOneText.getText().toString() + charOpe);
            addToScreenOne(null);
        }
    }



    /**
     * To add operator to the last result
     * When user press on operator after finishing from the last calculation
     * In this case ScreenTwo & ScreenThree have values
     * so must clear them
     * And start a new calculation process with lastResult (Which is in screenThree)
     * so add the value of screenThree with the operator to screenTwo
     * */
    private void addOperatorToLastResult(char charOpe){
        if (!TextUtils.isEmpty(screenThreeText.getText()))
            addToScreenTwo(screenThreeText.getText().toString() + " " + charOpe  + " ");
        addToScreenOne(null);
        addToScreenThree(null);
    }


    /**
     * When any button of the calculator pressed
     */
    private void onAdapterClicked() {
        adapter.setMyOnClickLister(new CalculatorAdapter.MyOnClickLister() {
            @Override
            public void onBtnClicked(Calc_ItemList calc_itemList, int position) {
                String itemValue = calc_itemList.getValue();
                Log.e(TAG, "onBtnClicked: " + calc_itemList.getValue());

                if (isCLR_Pressed(itemValue)) {
                    clr_screens();
                    return;
                }
                if (isOperator(itemValue)) {
                    chooseOperatorAndCompute(itemValue);
                    return;
                }

                if (isEqualPressed(itemValue)) {
                    Log.e(TAG, "onBtnClicked: " + calc_itemList.getValue());
                    if (isEqualPressedWithOnlyNumber()) {
                        chooseOperatorAndCompute(ADD_str);
                    } else if (isScreenOneHasNum()) {
                        getCalculationResult();
                    }
                    return;
                }

                if (!TextUtils.isEmpty(screenTwoText.getText()) && !TextUtils.isEmpty(screenThreeText.getText())){
                    clr_screens();
                    Log.e(TAG, "Testing: ");
                }
                String valueAfterButtonPressed = getValueScreenOne() + itemValue;
                String oldValue = itemValue;
                checkForNumValidity_ADDTo_ScreenOne(oldValue,valueAfterButtonPressed);


            }
        });
    }

    /**
     * When user press equal with only number in the editText
     * */
    private boolean isEqualPressedWithOnlyNumber() {
        return !getValueScreenOne().equals("") && TextUtils.isEmpty(screenTwoText.getText().toString());
    }

    /**
     * To get the final result
     * */
    private void getCalculationResult() {
        compute();
        charOpe = Equ;
        screenTwoText.setText(getCalculationHistory());
        screenThreeText.setText(String.valueOf(num1));
        addToScreenOne(null);
    }


    /**
     * To return the full history of the calculation
     * */
    private String getCalculationHistory(){
        if (!getValueOfScreenTwo().equals("") && !getValueScreenOne().equals("")){
            return getValueOfScreenTwo() + getValueScreenOne() ;
        }
        return "";
    }




    /**
     * Value is the value after user press any button
     * Must check the value before adding it to the screen one
     * To check the value if value is one of those we can use it
     * if num is [0-9].
     * Or [1-9].[0-9]
     * Or .[0-9]
     * Or [.]
     * Or [0]
     * */
    private void checkForNumValidity_ADDTo_ScreenOne(String oldValue, String wholeValue) {
        if (isNum(wholeValue)) {
            addToScreenOne(wholeValue);
            Log.e(TAG, "checkForNumValidity: 1");
        } else if (wholeValue.matches("([0-9]+[.])")) {
            addToScreenOne(wholeValue);
            Log.e(TAG, "checkForNumValidity: 2");
        } else if (wholeValue.matches("([1-9]+(\\.[0-9]+)?)")) {
            addToScreenOne(wholeValue);
            Log.e(TAG, "checkForNumValidity: 3");
        } else if (wholeValue.matches("([.]+[0-9]+)")) {
            addToScreenOne(wholeValue);
            Log.e(TAG, "checkForNumValidity: 4");
        } else if (oldValue == ".") {
            addToScreenOne(oldValue);
            Log.e(TAG, "checkForNumValidity: 5");
        }else if (oldValue == "0"){
            addToScreenOne("0");
        }

    }

    /**
     * To compute the value of calculation
     * */
    public void compute() {
        if (!Double.isNaN(num1) && !getValueScreenOne().equals("")) {
            if (isScreenOneHasNum()){
                num2 = Double.parseDouble(getValueScreenOne());
                switch (charOpe) {
                    case Addition:
                        num1 = num1 + num2;
                        break;
                    case Subtruction:
                        num1 = num1 - num2;
                        break;
                    case Multiplication:
                        num1 = num1 * num2;
                        break;
                    case Divison:
                        num1 = num1 / num2;
                        break;
                    case mod:
                        num1 = num1 % num2;
                        break;
                    case Equ:
                        break;
                }
            }

        } else {
            if (!getValueScreenOne().equals("")) {
                num1 = Double.parseDouble(getValueScreenOne());
            }
        }
    }




    /**
     * return true user press on operator
     * */
    private boolean isOperator(String value) {
        return getOperatorList().contains(value);
    }

    /**
     * Return true if "=" pressed
     * */
    private boolean isEqualPressed(String value) {
        return value.equals(getString(R.string.equal));
    }

    /**
     * Return true if "AC" pressed
     * */
    private boolean isCLR_Pressed(String value) {
        return value.equals(getString(R.string.clr));
    }


    /*///////////////////////////////////////////////Setters And Getter of Screens////////////////////////////////////////////////////////////////////////////////////////////*/
    /**
    * To set value to screen one
    * */
    private void addToScreenOne(String s){
        screenOneText.setText(s);
    }
    /**
     * Return value of screen one
     * */
    private String getValueScreenOne() {
        if (!TextUtils.isEmpty(screenOneText.getText())) {
            return screenOneText.getText().toString();
        }
        return "";
    }


    /**
     * To set value to screen two
     * */
    private void addToScreenTwo(String s){
        screenTwoText.setText(s);
    }

    /**
     * Return value of screen two
     * */
    private String getValueScreenTwo(char operator) {
        if (!getValueOfScreenTwo().equals("")){
            return getCalculationHistory() +  getValueScreenOne() + operator;
        }
        return getValueScreenOne() + operator;
    }

    /**
     * To set value to screen three
     * */
    private void addToScreenThree(String s){
        screenThreeText.setText(s);
    }
    /**
     * To return text of the result
     * */
    private String getValueOfScreenTwo(){
        if (!TextUtils.isEmpty(screenTwoText.getText())){
            return screenTwoText.getText().toString();
        }
        return "";
    }







    private void onViewsClicked() {
     /*   btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCalc.setText(txtCalc.getText().toString() + "1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCalc.setText(txtCalc.getText().toString() + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCalc.setText(txtCalc.getText().toString() + "3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCalc.setText(txtCalc.getText().toString() + "4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCalc.setText(txtCalc.getText().toString() + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCalc.setText(txtCalc.getText().toString() + "6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCalc.setText(txtCalc.getText().toString() + "7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCalc.setText(txtCalc.getText().toString() + "8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCalc.setText(txtCalc.getText().toString() + "9");
            }
        });
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCalc.setText(txtCalc.getText().toString() + "0");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCalc.setText(txtCalc.getText().toString() + ".");
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCalc.getText().toString().equals("")) {
                    txtCalc.setText(null);
                } else {
                    compute();
                    charOpe = Addition;
                    txtResult.setText(txtCalc.getText().toString() + "+");
                    txtCalc.setText(null);
                }
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCalc.getText().toString().equals("")) {
                    txtCalc.setText(null);
                } else {

                    compute();
                    charOpe = Subtruction;
                    txtResult.setText(txtCalc.getText() + "-");
                    txtCalc.setText(null);
                }
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCalc.getText().toString().equals("")) {
                    txtCalc.setText(null);
                } else {
                    compute();
                    charOpe = Multiplication;
                    txtResult.setText(txtCalc.getText().toString() + "*");
                    txtCalc.setText(null);
                }
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCalc.getText().toString().equals("")) {
                    txtCalc.setText(null);
                } else {
                    compute();
                    charOpe = Divison;
                    txtResult.setText(txtCalc.getText().toString() + "/");
                    txtCalc.setText(null);
                }
            }
        });

        btnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Double.NaN;
                num2 = Double.NaN;
                txtCalc.setText(null);
                txtResult.setText(null);
            }
        });

        btnFilp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtCalc.getText().toString().equals("")) {
                    txtCalc.setText(null);
                } else if (txtCalc.getText().toString().equals(".")) {
                    txtCalc.setText(null);
                } else if (txtCalc.getText().toString().equals("0")) {
                    txtCalc.setText("0");
                } else
                    txtCalc.setText(String.valueOf(-Double.parseDouble(txtCalc.getText().toString())));
            }
        });

        btnModules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCalc.getText().toString().equals("")) {
                    txtCalc.setText(null);
                } else {
                    compute();
                    charOpe = mod;
                    txtResult.setText(txtCalc.getText().toString() + "%");
                    txtCalc.setText(null);
                }
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCalc.getText().toString().equals("")) {
                    txtCalc.setText(null);
                } else if ((!txtCalc.getText().toString().equals(null)) && txtResult.getText().toString().equals(null)) {
                    compute();
                    charOpe = Addition;
                    txtResult.setText(String.valueOf(Double.parseDouble(txtCalc.getText().toString()) + num1));
                } else {
                    compute();
                    charOpe = Equ;
                    txtResult.setText(txtResult.getText().toString() + String.valueOf(num2) + "=" + String.valueOf(num1));
                    txtCalc.setText(String.valueOf(num1));
                }
            }
        });

*/
    }


}
