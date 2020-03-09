package shubham.com.baqat.CreateNormalAddScreen.CategorySction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import shubham.com.baqat.CreateNormalAddScreen.SubOccasionDataModel;
import shubham.com.baqat.R;

import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Handler;


public class CategorySection extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_category_section);
    setContentView(R.layout.activity_category_section_one);

    }

    private LinearLayout LLEducationAndCareer1;
    private Spinner educationCareerSpinner1;
    private Spinner educationCareerSpinner2;
    private LinearLayout LLEducationAndCareer2;
    private Spinner educationCareerSpinner3;
    private Spinner educationCareerSpinner4;
    private Spinner educationCareerSpinner5;
    private LinearLayout LLEducationAndCareer3;
    private Spinner educationCareerSpinner6;
    private LinearLayout LLEducationAndCareer4;
    private Spinner educationCareerSpinner7;
    private LinearLayout LLEducationAndCareer5;
    private Spinner educationCareerSpinner8;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-02-03 22:38:07 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        LLEducationAndCareer1 = (LinearLayout)findViewById( R.id.LL_Education_And_Career_1 );
        educationCareerSpinner1 = (Spinner)findViewById( R.id.education_career_spinner_1 );
        educationCareerSpinner2 = (Spinner)findViewById( R.id.education_career_spinner_2 );
        LLEducationAndCareer2 = (LinearLayout)findViewById( R.id.LL_Education_And_Career_2 );
        educationCareerSpinner3 = (Spinner)findViewById( R.id.education_career_spinner_3 );
        educationCareerSpinner4 = (Spinner)findViewById( R.id.education_career_spinner_4 );
        educationCareerSpinner5 = (Spinner)findViewById( R.id.education_career_spinner_5 );
        LLEducationAndCareer3 = (LinearLayout)findViewById( R.id.LL_Education_And_Career_3 );
        educationCareerSpinner6 = (Spinner)findViewById( R.id.education_career_spinner_6 );
        LLEducationAndCareer4 = (LinearLayout)findViewById( R.id.LL_Education_And_Career_4 );
        educationCareerSpinner7 = (Spinner)findViewById( R.id.education_career_spinner_7 );
        LLEducationAndCareer5 = (LinearLayout)findViewById( R.id.LL_Education_And_Career_5 );
        educationCareerSpinner8 = (Spinner)findViewById( R.id.education_career_spinner_8 );
    }



}
