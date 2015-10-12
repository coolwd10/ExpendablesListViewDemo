package com.example.wdidevashah.expendableslistviewdemo;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public abstract class MultiLevelExpIndListAdapter extends RecyclerView.Adapter {
    
    private boolean mNotifyOnChange;


    private List<ExpIndData> mData;


    private HashMap<ExpIndData, List<? extends ExpIndData>> mGroups;


    public interface ExpIndData {

        List<? extends ExpIndData> getChildren();


        boolean isGroup();


        void setIsGroup(boolean value);


        void setGroupSize(int groupSize);

    }

    public MultiLevelExpIndListAdapter() {
        mData = new ArrayList<ExpIndData>();
        mGroups = new HashMap<ExpIndData, List<? extends ExpIndData>>();
        mNotifyOnChange = true;
    }

    public void add(ExpIndData item) {
        if (item != null) {
            mData.add(item);
            if (mNotifyOnChange)
                notifyItemChanged(mData.size() - 1);
        }
    }

    public void addAll(int position, Collection<? extends ExpIndData> data) {
        if (data != null && data.size() > 0) {
            mData.addAll(position, data);
            if (mNotifyOnChange)
                notifyItemRangeInserted(position, data.size());
        }
    }

    public void addAll(Collection<? extends ExpIndData> data) {
        addAll(mData.size(), data);
    }



    public ExpIndData getItemAt(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void expandGroup(int position) {
        ExpIndData firstItem = getItemAt(position);

        if (!firstItem.isGroup()) {
            return;
        }

        // get the group of the descendants of firstItem
        List<? extends ExpIndData> group = mGroups.remove(firstItem);

        firstItem.setIsGroup(false);
        firstItem.setGroupSize(0);

        notifyItemChanged(position);
        addAll(position + 1, group);
    }


    public void collapseGroup(int position) {
        ExpIndData firstItem = getItemAt(position);

        if (firstItem.getChildren() == null || firstItem.getChildren().isEmpty())
            return;

        // group containing all the descendants of firstItem
        List<ExpIndData> group = new ArrayList<ExpIndData>();
        // stack for depth first search
        List<ExpIndData> stack = new ArrayList<ExpIndData>();
        int groupSize = 0;

        for (int i = firstItem.getChildren().size() - 1; i >= 0; i--)
            stack.add(firstItem.getChildren().get(i));

        while (!stack.isEmpty()) {
            ExpIndData item = stack.remove(stack.size() - 1);
            group.add(item);
            groupSize++;
            // stop when the item is a leaf or a group
            if (item.getChildren() != null && !item.getChildren().isEmpty() && !item.isGroup()) {
                for (int i = item.getChildren().size() - 1; i >= 0; i--)
                    stack.add(item.getChildren().get(i));
            }

            mData.remove(item);
        }

        mGroups.put(firstItem, group);
        firstItem.setIsGroup(true);
        firstItem.setGroupSize(groupSize);

        notifyItemChanged(position);
        notifyItemRangeRemoved(position + 1, groupSize);
    }


    public void toggleGroup(int position) {
        if (getItemAt(position).isGroup()){
            expandGroup(position);
        } else {
            collapseGroup(position);
        }
    }

    public ArrayList<Integer> saveGroups() {
        boolean notify = mNotifyOnChange;
        mNotifyOnChange = false;
        ArrayList<Integer> groupsIndices = new ArrayList<Integer>();
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isGroup()) {
                expandGroup(i);
                groupsIndices.add(i);
            }
        }
        mNotifyOnChange = notify;
        return groupsIndices;
    }


    public void restoreGroups(List<Integer> groupsNum) {
        if (groupsNum == null)
            return;
        boolean notify = mNotifyOnChange;
        mNotifyOnChange = false;
        for (int i = groupsNum.size() - 1; i >= 0; i--) {
            collapseGroup(groupsNum.get(i));
        }
        mNotifyOnChange = notify;
    }
}