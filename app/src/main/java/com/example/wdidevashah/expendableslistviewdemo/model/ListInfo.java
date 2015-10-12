package com.example.wdidevashah.expendableslistviewdemo.model;

import com.example.wdidevashah.expendableslistviewdemo.MultiLevelExpIndListAdapter;

import java.util.List;

public class ListInfo implements MultiLevelExpIndListAdapter.ExpIndData {
    public String content;

    public ListInfo(String content) {
        this.content = content;
    }

    @Override
    public List<? extends MultiLevelExpIndListAdapter.ExpIndData> getChildren() {
        return null;
    }

    @Override
    public boolean isGroup() {
        return false;
    }

    @Override
    public void setIsGroup(boolean value) {
    }

    @Override
    public void setGroupSize(int groupSize) {

    }
}
