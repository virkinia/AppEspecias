package com.example.appfotos.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appfotos.fragments.EspeciasModelFragment.OnListFragmentInteractionListener;
import com.example.appfotos.model.EspeciaModel;
import com.example.appfotos.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link EspeciaModel} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyEspeciasModelRecyclerViewAdapter extends RecyclerView.Adapter<MyEspeciasModelRecyclerViewAdapter.ViewHolder> {

    private final List<EspeciaModel> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyEspeciasModelRecyclerViewAdapter(List<EspeciaModel> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_especiasmodel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mIdLatitud.setText(mValues.get(position).getLatitud().toString());
        holder.mIdLongitud.setText(mValues.get(position).getLongitud().toString());
        holder.mImageViewList.setImageBitmap(mValues.get(position).getImageBase64());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues != null ? mValues.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mIdLatitud;
        public final TextView mIdLongitud;
        public final ImageView mImageViewList;
        public EspeciaModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mIdLatitud = (TextView) view.findViewById(R.id.txt_latitud);
            mIdLongitud = (TextView) view.findViewById(R.id.txt_longitud);
            mImageViewList = (ImageView) view.findViewById(R.id.imageViewList);
        }

        @Override
        public String toString() {
            return super.toString() + " '"  + "'";
        }
    }
}
