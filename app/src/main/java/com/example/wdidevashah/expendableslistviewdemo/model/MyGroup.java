package com.example.wdidevashah.expendableslistviewdemo.model;

import com.example.wdidevashah.expendableslistviewdemo.MultiLevelExpIndListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyGroup implements MultiLevelExpIndListAdapter.ExpIndData {
    private int mIndentation;
    private List<MyGroup> mChildren;
    private boolean mIsGroup;
    private int mGroupSize;

    public String author;
    public String comment;

    public MyGroup(String author, String comment) {
        this.author = author;
        this.comment = comment;
        mChildren = new ArrayList<MyGroup>();

        setIndentation(0);
    }

    @Override
    public List<? extends MultiLevelExpIndListAdapter.ExpIndData> getChildren() {
        return mChildren;
    }

    @Override
    public boolean isGroup() {
        return mIsGroup;
    }

    @Override
    public void setIsGroup(boolean value) {
        mIsGroup = value;
    }

    @Override
    public void setGroupSize(int groupSize) {
        mGroupSize = groupSize;
    }

    public int getGroupSize() {
        return mGroupSize;
    }

    public void addChild(MyGroup child) {
        mChildren.add(child);
        child.setIndentation(getIndentation() + 1);
    }

    public int getIndentation() {
        return mIndentation;
    }

    private void setIndentation(int indentation) {
        mIndentation = indentation;
    }
}