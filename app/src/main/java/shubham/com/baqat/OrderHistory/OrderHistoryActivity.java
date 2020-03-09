package shubham.com.baqat.OrderHistory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import shubham.com.baqat.R;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;


public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RelativeLayout back_layout;
    TextView text_career;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;


    private Order_RecyclerViewAdapter mAdapter;

    private ArrayList<OrderHistoryAbstractModel> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        text_career=(TextView)findViewById(R.id.text_career);
        back_layout=(RelativeLayout) findViewById(R.id.back_layout);
        // ButterKnife.bind(this);
        text_career.setVisibility(View.VISIBLE);
        text_career.setText("Order History");
        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        findViews();
        setAdapter();


    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }


    private void setAdapter() {


        modelList.add(new OrderHistoryAbstractModel("Android", "Hello " + " Android"));
        modelList.add(new OrderHistoryAbstractModel("Beta", "Hello " + " Beta"));
        modelList.add(new OrderHistoryAbstractModel("Cupcake", "Hello " + " Cupcake"));
        modelList.add(new OrderHistoryAbstractModel("Donut", "Hello " + " Donut"));
        modelList.add(new OrderHistoryAbstractModel("Eclair", "Hello " + " Eclair"));



        mAdapter = new Order_RecyclerViewAdapter(OrderHistoryActivity.this, modelList);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);


        mAdapter.SetOnItemClickListener(new Order_RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, OrderHistoryAbstractModel model) {

                //handle item click events here
                Toast.makeText(OrderHistoryActivity.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();


            }
        });


    }


}
