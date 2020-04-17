package com.example.ryan.roomrep.LandlordFragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.Activities.MainActivityLandlord;
import com.example.ryan.roomrep.Adapters.FragmentStatePagerAdapter;
import com.example.ryan.roomrep.Adapters.StatePagerAdapter;
import com.example.ryan.roomrep.App.Permission;
import com.example.ryan.roomrep.App.UI.DepthPageTransformer;
import com.example.ryan.roomrep.App.UI.LeaseText;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Login.Login;
import com.example.ryan.roomrep.Classes.PDF.PDFViewer;
import com.example.ryan.roomrep.Classes.Users.Homeowner;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.ViewModels.HomeownerViewModel;
import com.example.ryan.roomrep.ViewModels.HouseViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


public class LeaseStatePagerFragment extends Fragment implements LifecycleOwner {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private Button btnBack, btnDownload;
    private PdfRenderer pdfRenderer;
    private PDFViewer pdfViewer;
    private LifecycleRegistry lifecycleRegistry;
    private HomeownerViewModel homeownerViewModel;
    private HouseViewModel houseViewModel;
    private Intent intent;
    private String filename;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lease_state_pager, container, false);
        Permission permission = new Permission(getActivity());
        if (!permission.doesHaveWritePermission()) {
            permission.requestWriteExternalStoragePermission();
        }
        pdfViewer = PDFViewer.getInstance(getActivity());
        pdfRenderer = pdfViewer.getPdfRenderer();
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        tabLayout = view.findViewById(R.id.leaseTabLayout);
        viewPager = view.findViewById(R.id.leaseViewPager);
        viewPager.registerOnPageChangeCallback(onPageChangeListener);
        viewPager.setPageTransformer(new DepthPageTransformer());
        btnBack = view.findViewById(R.id.btnLeaseBack);
        btnDownload = view.findViewById(R.id.btnLeaseDownload);
        btnDownload.setOnClickListener(onDownload);
        btnDownload.setEnabled(false);
        tabLayout.getTabAt(0).setText(Integer.toString(0));
        intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

        Login login = ((MainActivityLandlord)getActivity()).getLogin();
        List<LeaseText> pageOneValues = new ArrayList<>();
        List<LeaseText> pageTwoValues = new ArrayList<>();
        List<LeaseText> pageThreeValues = new ArrayList<>();
        List<LeaseText> pageFourValues = new ArrayList<>();
        List<LeaseText> pageFiveValues = new ArrayList<>();
        List<LeaseText> pageSixValues = new ArrayList<>();
        homeownerViewModel = ViewModelProviders.of(getActivity()).get(HomeownerViewModel.class);
        homeownerViewModel.getHomeowner(login.getEmail()).observe(getActivity(), new Observer<Homeowner>() {
            @Override
            public void onChanged(Homeowner homeowner) {
                if (homeowner != null) {
                    pageOneValues.add(new LeaseText(homeowner.getFullName(), 40, 448));
                    pdfViewer.setPageOneValues(pageOneValues);
                    pageTwoValues.add(new LeaseText(homeowner.getHomeownerLocation().getUnitNumber(), 22, 706));
                    pageTwoValues.add(new LeaseText(Integer.toString(homeowner.getHomeownerLocation().getHomeownerStreetNumber()), 111, 706));
                    pageTwoValues.add(new LeaseText(homeowner.getHomeownerLocation().getHomeownerStreetName(), 201, 706));
                    pageTwoValues.add(new LeaseText(homeowner.getHomeownerLocation().getPoBox(), 526, 706));
                    pageTwoValues.add(new LeaseText(homeowner.getHomeownerLocation().getHomeownerCity(), 22, 679));
                    pageTwoValues.add(new LeaseText(homeowner.getHomeownerLocation().getHomeownerProvince(), 272, 679));
                    pageTwoValues.add(new LeaseText(homeowner.getHomeownerLocation().getHomeownerPostalCode(), 471, 679));
                    pageTwoValues.add(new LeaseText("X", 22, 628));
                    pageTwoValues.add(new LeaseText(homeowner.getEmail(), 22, 592));
                    pageTwoValues.add(new LeaseText("X", 22, 558));
                    pageTwoValues.add(new LeaseText(homeowner.getPhoneNumber(), 22, 523));



                }
            }
        });

        houseViewModel = ViewModelProviders.of(getActivity()).get(HouseViewModel.class);
        houseViewModel.getSelected().observe(getActivity(), new Observer<House>() {
            @Override
            public void onChanged(House house) {
                if (house != null){
                    filename = house.getAddress() + " LeaseAgreement.pdf";
                    intent.putExtra(Intent.EXTRA_TITLE, filename);
                    pageOneValues.add(new LeaseText(house.getLease().getRentalUnitLocation().getUnitName(), 22, 164));
                    pageOneValues.add(new LeaseText(Integer.toString(house.getLease().getRentalUnitLocation().getStreetNumber()), 220, 164));
                    pageOneValues.add(new LeaseText(house.getLease().getRentalUnitLocation().getStreetName(), 310, 164));
                    pageOneValues.add(new LeaseText(house.getLease().getRentalUnitLocation().getCity(), 22, 136));
                    pageOneValues.add(new LeaseText(house.getLease().getRentalUnitLocation().getProvince(), 310, 136));
                    pageOneValues.add(new LeaseText(house.getLease().getRentalUnitLocation().getPostalCode(), 508, 136));
                    pageOneValues.add(new LeaseText(Integer.toString(house.getLease().getRentalUnitLocation().getParkingSpaces()), 22, 93));
                    pageOneValues.add(house.getLease().getRentalUnitLocation().isCondo() ? new LeaseText("X", 72, 56) : new LeaseText("X", 22, 56));
                    pageTwoValues.add(new LeaseText("X", 22, 395));
                    pageTwoValues.add(new LeaseText(house.getLease().getRentDetail().getRentDueDate(), 152, 258));
                    pageTwoValues.add(new LeaseText("X", 41, 237));
                    pageTwoValues.add(new LeaseText("$" + house.getLease().getRentDetail().getBaseRent() + ".00", 360, 172));
                    pageTwoValues.add(new LeaseText("$" + house.getLease().getServices().get(0).getAmount() + ".00", 360, 155));
                    pdfViewer.setPageTwoValues(pageTwoValues);
                    pageThreeValues.add(new LeaseText(house.getLease().getRentDetail().getRentMadePayable(), 39, 692));
                    pageThreeValues.add(new LeaseText(house.getLease().getRentDetail().isPaymentMethod1() ? "Cash" : "", 39, 648));
                    pageThreeValues.add(new LeaseText(house.getLease().getRentDetail().isPaymentMethod2() ? "Cheque" : "", 73, 648));
                    pageThreeValues.add(new LeaseText(house.getLease().getRentDetail().isPaymentMethod3() ? "Credit" : "", 120, 648));
                    pageThreeValues.add(new LeaseText("20.00", 158, 497));
                    pageThreeValues.add(house.getLease().getServices().get(1).isChecked() ? new LeaseText("X", 348, 386) : new LeaseText("X", 388, 386));
                    pageThreeValues.add(house.getLease().getServices().get(2).isChecked() ? new LeaseText("X", 348, 364) : new LeaseText("X", 388, 364));
                    pageThreeValues.add(house.getLease().getServices().get(3).isChecked() ? new LeaseText("X", 348, 343) : new LeaseText("X", 388, 343));
                    pageThreeValues.add(house.getLease().getServices().get(4).isChecked() ? new LeaseText("X", 348, 321) : new LeaseText("X", 388, 321));
                    pageThreeValues.add(house.getLease().getServices().get(5).isChecked() ? new LeaseText("X", 348, 300) : new LeaseText("X", 388, 300));
                    pdfViewer.setPageThreeValues(pageThreeValues);
                    pageFourValues.add(house.getLease().getUtilities().get(0).getResponsibilityOf().equals("Homeowner") ? new LeaseText("X", 76, 729) : new LeaseText("X", 148, 729));
                    pageFourValues.add(house.getLease().getUtilities().get(1).getResponsibilityOf().equals("Homeowner") ? new LeaseText("X", 76, 707) : new LeaseText("X", 148, 707));
                    pageFourValues.add(house.getLease().getUtilities().get(2).getResponsibilityOf().equals("Homeowner") ? new LeaseText("X", 76, 686) : new LeaseText("X", 148, 686));
                    pageFourValues.add(new LeaseText("X", 22, 402));
                    pageFourValues.add(new LeaseText("X", 22, 163));
                    pdfViewer.setPageFourValues(pageFourValues);
                    pageFiveValues.add(new LeaseText("X", 22, 714));
                    pageFiveValues.add(new LeaseText("X", 22, 431));
                    pageFiveValues.add(new LeaseText("X", 22, 146));
                    pdfViewer.setPageFiveValues(pageFiveValues);
                    pageSixValues.add(new LeaseText("X", 22, 196));
                    pdfViewer.setPageSixValues(pageSixValues);

                }
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        PDFDocumentBuilderAsyncTask asyncTask = new PDFDocumentBuilderAsyncTask();
        asyncTask.execute();
    }

    private StatePagerAdapter setupViewPager(){
        StatePagerAdapter adapter = new StatePagerAdapter(getChildFragmentManager(), lifecycleRegistry);
        for (int i = 0; i<pdfRenderer.getPageCount(); i++){
            ViewLeaseFragment viewLeaseFragment = new ViewLeaseFragment();
            viewLeaseFragment.setPageNumber(i);
            adapter.addFragment(viewLeaseFragment);
        }
        return adapter;
    }


    ViewPager2.OnPageChangeCallback onPageChangeListener = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            tabLayout.getTabAt(0).setText(Integer.toString(position + 1));
        }
    };


    private View.OnClickListener onDownload = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/pdf");
            startActivityForResult(intent, 3);
        }
    };



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == 3 && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                pdfViewer.saveFile(filename);
            }
        }
    }

    private class PDFDocumentBuilderAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < pdfRenderer.getPageCount(); i++){
                Bitmap bitmap = pdfViewer.renderPageBitmapAtIndex(i);
                pdfViewer.buildDocument(i, bitmap);
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewPager.setAdapter(setupViewPager());
            btnDownload.setEnabled(true);
        }


    }



}
