package io.instag.nearbyposts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.instag.nearbyposts.R;
import io.instag.nearbyposts.model.NearbyPost;

/**
 * Created by javed on 19/08/2017.
 */

public class NearbyPostsAdapter extends BaseAdapter {

    private Context mContext;
    LayoutInflater mInflater;
    ArrayList<NearbyPost> mNearbyPostsArray = new ArrayList<>();

    private static class NearbyPostHolder {
        TextView userName;
        TextView fullName;

        ImageView profilePicture;
        ImageView postImage;

        TextView locationName;
        TextView caption;

        TextView likes;
        TextView comments;
    }

    public NearbyPostsAdapter(Context context, ArrayList<NearbyPost> posts) {
        this.mContext = context;

        this.mNearbyPostsArray = posts;

        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NearbyPost nearbyPost = getItem(position);
        NearbyPostHolder viewHolder; // view lookup cache stored in tag

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new NearbyPostHolder();

            convertView = mInflater.inflate(R.layout.nearby_post_item_layout, parent, false);

            viewHolder.fullName = (TextView) convertView.findViewById(R.id.full_name);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.user_name);
            viewHolder.profilePicture = (ImageView) convertView.findViewById(R.id.profile_picture);

            viewHolder.postImage = (ImageView) convertView.findViewById(R.id.post_image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (NearbyPostHolder) convertView.getTag();
        }

        // Populate data

        // Full Name
        viewHolder.fullName.setText(nearbyPost.getFullName());

        // User Name
        viewHolder.userName.setText(nearbyPost.getUserName());

        // Profile Picture
        Picasso.with(mContext)
                .load(nearbyPost.getProfilePicture())
                    .placeholder(R.mipmap.ic_insert_photo_black_48dp)
                    .into(viewHolder.profilePicture);
        // Post image
        Picasso.with(mContext)
                .load(nearbyPost.getPostImage())
                //.placeholder(R.mipmap.ic_insert_photo_black_48dp)
                .into(viewHolder.postImage);

        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public NearbyPost getItem(int position) {
        return this.mNearbyPostsArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return this.mNearbyPostsArray.size();
    }
}

