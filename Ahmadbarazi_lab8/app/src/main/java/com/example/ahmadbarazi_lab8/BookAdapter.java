package com.example.ahmadbarazi_lab8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> implements Filterable {
    private ArrayList<BookItem> mBookList;
    private ArrayList<BookItem> mBookListFull;
    private OnItemClickListener mListener;

  //Filter for the search
    private Filter bookFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<BookItem> filtereList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtereList.addAll(mBookListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (BookItem item : mBookListFull) {
                    if (item.getText1().toLowerCase().contains(filterPattern)) {
                        filtereList.add(item);

                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtereList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mBookList.clear();
            mBookList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public BookAdapter(ArrayList<BookItem> bookList) {
        mBookList = bookList;
        mBookListFull = new ArrayList<>(bookList);
    }

    public void setOnItemClickListener(OnItemClickListener lisnter) {
        mListener = lisnter;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        BookViewHolder evh = new BookViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        BookItem currentItem = mBookList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        if (mBookList.size() > 10) {
            return 4;

        }
        return mBookList.size();
    }

    @Override
    public Filter getFilter() {
        return bookFilter;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public BookViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
