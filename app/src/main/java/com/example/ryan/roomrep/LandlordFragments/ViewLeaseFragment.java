package com.example.ryan.roomrep.LandlordFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ryan.roomrep.Activities.MainActivityLandlord;
import com.example.ryan.roomrep.App.Permission;
import com.example.ryan.roomrep.App.UI.LeaseText;
import com.example.ryan.roomrep.Classes.Login.Login;
import com.example.ryan.roomrep.Classes.PDF.PDFViewer;
import com.example.ryan.roomrep.Classes.Users.Homeowner;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.ViewModels.HomeownerViewModel;
import com.squareup.picasso.Picasso;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ViewLeaseFragment extends Fragment {



    private ImageView imgPDF;
    private List<LeaseText> leaseTextList;
    private PDFViewer pdfViewer;
    private int pageNumber;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pdf_viewer, container, false);
        imgPDF = view.findViewById(R.id.imgViewPDF);
        pdfViewer = PDFViewer.getInstance(getActivity());
        Bitmap bitmap = pdfViewer.renderPageBitmapAtIndex(pageNumber);
        imgPDF.setImageBitmap(bitmap);

        return view;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<LeaseText> getLeaseTextList() {
        return leaseTextList;
    }

    public void setLeaseTextList(List<LeaseText> leaseTextList) {
        this.leaseTextList = leaseTextList;
    }
}


