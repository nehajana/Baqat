package shubham.com.baqat.HomeFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import java.util.List;

import shubham.com.baqat.HomeFragment.ApiModel.TopNewsDataModel;
import shubham.com.baqat.R;


public class SlidingImage_Adapter extends PagerAdapter {
    ViewPager mPager;
    List<TopNewsDataModel> image;
    private LayoutInflater inflater;
    private Context context;
    public SlidingImage_Adapter(Context context, List<TopNewsDataModel> IMAGES, ViewPager mPager) {
        this.context = context;
        this.image=IMAGES;
        this.mPager  = mPager;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public int getCount() {
        return image.size();
    }
    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;

        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                   Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.get(position).getWebsite()));
                context.startActivity(browserIntent);
            }
        });


        Picasso.with(context).load(image.get(position).getMediaPath())
                    .placeholder(R.drawable.celcebrid_icon)
                    .into(imageView);

        // imageView.setImageResource(image.get(position));
        view.addView(imageLayout, 0);

        return imageLayout;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }
    @Override
    public Parcelable saveState() {
        return null;
    }
}
