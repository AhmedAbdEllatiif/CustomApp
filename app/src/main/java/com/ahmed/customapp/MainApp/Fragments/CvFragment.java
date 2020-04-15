package com.ahmed.customapp.MainApp.Fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmed.customapp.BaseClasses.BaseFragment;
import com.ahmed.customapp.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CvFragment extends BaseFragment implements OnTapListener, OnPageScrollListener {

    private View view;
    private PDFView cv_pdf;
    private TextView txt_page;
    private ConstraintLayout pdf_num_container;
    private int totalPages_num;
    private int currentPage_num;
    private String pageNum_str;

    public CvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cv, container, false);

        initViews();
        cv_pdf.fromAsset("cv.pdf")
                .onTap(this)
                .onPageScroll(CvFragment.this)
                .nightMode(false) // toggle night mode
                .load();


        return view;
    }


    /**
     * To initialize views
     * */
    private void initViews(){
        cv_pdf = view.findViewById(R.id.cv_pdf);
        txt_page = view.findViewById(R.id.txt_page);
        pdf_num_container = view.findViewById(R.id.pdf_num_container);
    }

    /**
     * To hide or show page num
     * */
    private void hideAndShowPageNum(){
        if (pdf_num_container.getVisibility() == View.INVISIBLE){
            pdf_num_container.setVisibility(View.VISIBLE);
            setTextWithPageNum();
        }else {
            pdf_num_container.setVisibility(View.INVISIBLE);
        }
    }



    /**
     * call back {@link OnTapListener pdfView}
     * */
    @Override
    public boolean onTap(MotionEvent e) {
       hideAndShowPageNum();
        setTextWithPageNum();
        return false;
    }

    /**
     * call back {@link OnPageScrollListener pdfView}
     * */
    @Override
    public void onPageScrolled(int page, float positionOffset) {
        if (positionOffset > 0){
            pdf_num_container.setVisibility(View.INVISIBLE);
        }else if(positionOffset == 0){
            pdf_num_container.setVisibility(View.VISIBLE);
        }

    }

    /**
    * To get string format of page num (1/2)
    * */
    private String getPageNum_str(){
        totalPages_num = cv_pdf.getPageCount();
        currentPage_num = cv_pdf.getCurrentPage() + 1;
        return currentPage_num + "/" + totalPages_num;
    }

    /**
     * To set the pageNum text with page number
     * */
    private void setTextWithPageNum(){
        txt_page.setText(getPageNum_str());
    }

}
