package com.example.ryan.roomrep.Classes.PDF;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.Environment;
import android.os.ParcelFileDescriptor;

import com.example.ryan.roomrep.App.UI.LeaseText;
import com.example.ryan.roomrep.Classes.House.Lease;
import com.example.ryan.roomrep.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import androidx.core.content.ContextCompat;


public class PDFViewer {

    private PdfDocument document;
    private PdfRenderer pdfRenderer;
    private Context context;
    private List<LeaseText> pageOneValues;
    private List<LeaseText> pageTwoValues;
    private List<LeaseText> pageThreeValues;
    private List<LeaseText> pageFourValues;
    private List<LeaseText> pageFiveValues;
    private List<LeaseText> pageSixValues;



    private static PDFViewer instance;


    public static PDFViewer getInstance(Context context) {
        if (instance == null) {
            instance = new PDFViewer(context);
        }
        return instance;
    }

    private PDFViewer(Context context) {
        pdfRenderer = openRenderer(context);
        document = new PdfDocument();
        this.context = context;

    }

    public static PDFViewer getInstance() {
        return instance;
    }

    public static void setInstance(PDFViewer instance) {
        PDFViewer.instance = instance;
    }

    public Bitmap renderPageBitmapAtIndex(int index) {
        PdfRenderer.Page page = pdfRenderer.openPage(index);
        Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);
        Bitmap checkbox = getBitmap(R.drawable.ic_check_black_24dp);
        switch (index) {
            case 0:
                draw(getPageOneValues(), canvas, checkbox, page);
                break;
            case 1:
                draw(getPageTwoValues(), canvas, checkbox, page);
                break;
            case 2:
                draw(getPageThreeValues(), canvas, checkbox, page);
                break;
            case 3:
                draw(getPageFourValues(), canvas, checkbox, page);
                break;
            case 4:
                draw(getPageFiveValues(), canvas, checkbox, page);
                break;
            case 5:
                draw(getPageSixValues(), canvas, checkbox, page);
                break;
        }
        canvas.save();
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        page.close();
        return bitmap;
    }

    private void draw(List<LeaseText> pageValues, Canvas canvas, Bitmap checkbox, PdfRenderer.Page page) {
        if (pageValues == null){
            return;
        }
        for (LeaseText leaseText : pageValues){
            if (leaseText.getText().equals("X")){
                canvas.drawBitmap(checkbox, leaseText.getX(),  page.getHeight() - leaseText.getY() - checkbox.getHeight() + 2, new Paint());
                continue;
            }
            canvas.drawText(leaseText.getText(), leaseText.getX(), page.getHeight() - leaseText.getY(), new Paint());
        }
    }


    private Bitmap getBitmap(int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        Bitmap bitmap = Bitmap.createBitmap(15, 15, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private PdfRenderer openRenderer(Context context){
        // In this sample, we read a PDF from the assets directory.
        File file = new File(context.getCacheDir(), "OntarioLeaseAgreementTemplate.pdf");
        if (file.exists()) {
            try {
                ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
                return new PdfRenderer(parcelFileDescriptor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public void saveFile(String fileName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            document.writeTo(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void buildDocument(int pageNumber, Bitmap bitmap) {
        PdfRenderer.Page renderedPage = pdfRenderer.openPage(pageNumber);
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(renderedPage.getWidth(), renderedPage.getHeight(), pageNumber).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.save();
        document.finishPage(page);
        renderedPage.close();
    }

    public PdfRenderer getPdfRenderer() {
        return pdfRenderer;
    }

    private List<LeaseText> getPageOneValues() {
        return this.pageOneValues;
    }

    public void setPageOneValues(List<LeaseText> leaseTexts) {
        this.pageOneValues = leaseTexts;
    }

    public List<LeaseText> getPageTwoValues() {
        return pageTwoValues;
    }

    public void setPageTwoValues(List<LeaseText> pageTwoValues) {
        this.pageTwoValues = pageTwoValues;
    }

    public List<LeaseText> getPageThreeValues() {
        return pageThreeValues;
    }

    public void setPageThreeValues(List<LeaseText> pageThreeValues) {
        this.pageThreeValues = pageThreeValues;
    }

    public List<LeaseText> getPageFourValues() {
        return pageFourValues;
    }

    public void setPageFourValues(List<LeaseText> pageFourValues) {
        this.pageFourValues = pageFourValues;
    }

    public List<LeaseText> getPageFiveValues() {
        return pageFiveValues;
    }

    public void setPageFiveValues(List<LeaseText> pageFiveValues) {
        this.pageFiveValues = pageFiveValues;
    }

    public List<LeaseText> getPageSixValues() {
        return pageSixValues;
    }

    public void setPageSixValues(List<LeaseText> pageSixValues) {
        this.pageSixValues = pageSixValues;
    }
}
