package com.example.wdidevashah.expendableslistviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wdidevashah.expendableslistviewdemo.model.MyGroup;
import com.example.wdidevashah.expendableslistviewdemo.model.ListInfo;

/**
 * Created by WDIDEVASHAH on 10/10/15.
 */
public class MyAdapter extends MultiLevelExpIndListAdapter {
    public static final int VIEW_TYPE_ITEM = 0;

    private static final int VIEW_TYPE_CONTENT = 1;

    private final View.OnClickListener mListener;

    private final Context mContext;

    private final int mPaddingDP = 5;

    public static class GroupViewHolder extends RecyclerView.ViewHolder {
        private View colorBand;
        public TextView groupTextView;
        public TextView groupInfoTextView;
        public TextView groupCountTextView;
        private View view;

        private static final String[] indColors = {"#000000", "#3366FF", "#E65CE6",
                "#E68A5C", "#00E68A", "#CCCC33"};

        public GroupViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            groupTextView = (TextView) itemView.findViewById(R.id.tv_for_group);
            groupInfoTextView = (TextView) itemView.findViewById(R.id.tv_for_group_info);
            colorBand = itemView.findViewById(R.id.color_band);
            groupCountTextView = (TextView) itemView.findViewById(R.id.hidden_comments_count_textview);
        }

        public void setColorBandColor(int indentation) {
            int color = Color.parseColor(indColors[indentation]);
            colorBand.setBackgroundColor(color);
        }

        public void setPaddingLeft(int paddingLeft) {
            view.setPadding(paddingLeft,0,0,0);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        public TextView contentTextView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            contentTextView = (TextView) itemView.findViewById(R.id.content_textview);
        }
    }

    public MyAdapter(Context context, View.OnClickListener listener) {
        super();
        mContext = context;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                int resource = R.layout.recyclerview_item;
                v = LayoutInflater.from(parent.getContext())
                        .inflate(resource, parent, false);
                viewHolder = new GroupViewHolder(v);
                break;
            case VIEW_TYPE_CONTENT:
                resource = R.layout.recyclerview_content;
                v = LayoutInflater.from(parent.getContext())
                        .inflate(resource, parent, false);
                viewHolder = new ContentViewHolder(v);
                break;
            default:
                throw new IllegalStateException("unknown viewType");
        }

        v.setOnClickListener(mListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                GroupViewHolder cvh = (GroupViewHolder) viewHolder;
                MyGroup comment = (MyGroup) getItemAt(position);

                cvh.groupTextView.setText(comment.author);
                cvh.groupInfoTextView.setText(comment.comment);

                if (comment.getIndentation() == 0) {
                    cvh.colorBand.setVisibility(View.GONE);
                    cvh.setPaddingLeft(0);
                } else {
                    cvh.colorBand.setVisibility(View.VISIBLE);
                    cvh.setColorBandColor(comment.getIndentation());
                    int leftPadding = Utils.getPaddingPixels(mContext, mPaddingDP) * (comment.getIndentation() - 1);
                    cvh.setPaddingLeft(leftPadding);
                }

                if (comment.isGroup()) {
                    cvh.groupCountTextView.setVisibility(View.VISIBLE);
                    cvh.groupCountTextView.setText(Integer.toString(comment.getGroupSize()));
                } else {
                    cvh.groupCountTextView.setVisibility(View.GONE);
                }
                break;
            case VIEW_TYPE_CONTENT:
                ContentViewHolder contentVH = (ContentViewHolder) viewHolder;
                ListInfo content = (ListInfo) getItemAt(position);
                contentVH.contentTextView.setText(content.content);
                break;
            default:
                throw new IllegalStateException("unknown viewType");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return VIEW_TYPE_CONTENT;
        return VIEW_TYPE_ITEM;
    }
}