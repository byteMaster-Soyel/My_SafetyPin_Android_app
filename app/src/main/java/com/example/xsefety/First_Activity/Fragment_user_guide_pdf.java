package com.example.xsefety.First_Activity;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xsefety.R;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
//import com.joanzapata.pdfview.PDFView;


public class Fragment_user_guide_pdf extends Fragment {

    View view;
    private ImageView imageView;
    //PDFView pdfView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_guide_pdf, container, false);

        imageView = (ImageView) view.findViewById(R.id.id_open_pdf);
        return view;

    }
}