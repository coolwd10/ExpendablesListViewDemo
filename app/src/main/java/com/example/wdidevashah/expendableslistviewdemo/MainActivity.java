package com.example.wdidevashah.expendableslistviewdemo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wdidevashah.expendableslistviewdemo.model.MyGroup;
import com.example.wdidevashah.expendableslistviewdemo.model.ListInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ListViewFragment())
                    .commit();
        }
    }

    public static class ListViewFragment extends Fragment {
        private static final String GROUPS_KEY = "groups_key";

        private MyAdapter mAdapter;
        private RecyclerView mRecyclerView;
        private LinearLayoutManager mLayoutManager;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
                savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.content_recyclerview);

            mRecyclerView.setHasFixedSize(true);

            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new MyAdapter(getActivity(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = mRecyclerView.getChildPosition(v);
                    mAdapter.toggleGroup(position);
                }
            });
            mRecyclerView.setAdapter(mAdapter);

            ListInfo content = getDummyContent();
            mAdapter.add(content);
            List<MyGroup> comments = getDummyComments();
            mAdapter.addAll(comments);

            if (savedInstanceState != null) {
                List<Integer> groups = savedInstanceState.getIntegerArrayList(GROUPS_KEY);
                mAdapter.restoreGroups(groups);
            }

            return rootView;
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            outState.putIntegerArrayList(GROUPS_KEY, mAdapter.saveGroups());
            super.onSaveInstanceState(outState);
        }

        private ListInfo getDummyContent() {
            return new ListInfo("Mega Sell Mega Sell sell sell  mega ");
        }

        private List<MyGroup> getDummyComments() {
            List<MyGroup> comments = new ArrayList<MyGroup>();
            //Electronic Group
            comments.add(new MyGroup("Electronics", "Electronic Products is an electronic component and technology trade magazine serving the electronic design community."));
            //Mobile
            comments.add(new MyGroup("Mobiles", "An electronic telecommunications device, often referred to as a cellular phone or cellphone. Mobile phones connect to a wireless communications network through radio wave or satellite transmissions."));
            comments.get(0).addChild(comments.get(1));

            comments.add(new MyGroup("Motorola", "Google Phone"));
            comments.get(1).addChild(comments.get(2));
            comments.add(new MyGroup("Samsung", "Samsung Phone"));
            comments.get(1).addChild(comments.get(3));
            comments.add(new MyGroup("Htc", "Htc Phone"));
            comments.get(1).addChild(comments.get(4));

            //Laptop
            comments.add(new MyGroup("Laptop", "Laptop is used"));
            comments.get(0).addChild(comments.get(5));

            comments.add(new MyGroup("HP", "Hp Laptop"));
            comments.get(5).addChild(comments.get(6));
            comments.add(new MyGroup("Lenovo", "Lenovo Laptop"));
            comments.get(5).addChild(comments.get(7));
            comments.add(new MyGroup("Asus", "Asus Laptop"));
            comments.get(5).addChild(comments.get(8));
            comments.add(new MyGroup("Compaq", "Compaq Laptop"));
            comments.get(5).addChild(comments.get(9));

            //Television
            comments.add(new MyGroup("Television", "Television is used"));
            comments.get(0).addChild(comments.get(10));

            comments.add(new MyGroup("Lg", "Lg Television"));
            comments.get(10).addChild(comments.get(11));
            comments.add(new MyGroup("Sansui", "Sansui Television"));
            comments.get(10).addChild(comments.get(12));
            comments.add(new MyGroup("Micromax", "Micromax Television"));
            comments.get(10).addChild(comments.get(13));
            comments.add(new MyGroup("Panasonic", "Panasonic Television"));
            comments.get(10).addChild(comments.get(14));
            comments.add(new MyGroup("Sony", "Sony Television"));
            comments.get(10).addChild(comments.get(15));
            comments.add(new MyGroup("Philips", "Philips Television"));
            comments.get(10).addChild(comments.get(16));

            // Home & Kitchen Needs
            comments.add(new MyGroup("Home & Kitchen Needs", "The definition of kitchen is a room or area a home or building where food is cooked or prepared. An example of a kitchen is the room in your home that has a stove, refrigerator and cabinets for storing food."));
            comments.add(new MyGroup("Kitchen & dining", "Kitchen & dining group"));
            comments.get(17).addChild(comments.get(18));
            comments.add(new MyGroup("Pressure Cookers", "Pressure Cookers for food"));
            comments.get(18).addChild(comments.get(19));
            comments.add(new MyGroup("Lunch Boxes", "Lunch Boxes for dabba"));
            comments.get(18).addChild(comments.get(20));
            comments.add(new MyGroup("Tupperware", "Tupperware in food?"));
            comments.get(18).addChild(comments.get(21));

            comments.add(new MyGroup("LED & CFL Bulbs", "LED & CFL Bulbs group"));
            comments.get(17).addChild(comments.get(22));
            comments.add(new MyGroup("Eveready", "Eveready Prncil cell"));
            comments.get(22).addChild(comments.get(23));
            comments.add(new MyGroup("Wipro", "Wipro Wipro"));
            comments.get(22).addChild(comments.get(24));
            comments.add(new MyGroup("Syska", "Syska Syska"));
            comments.get(22).addChild(comments.get(25));
            comments.add(new MyGroup("Osram", "OsramOsramOsram"));
            comments.get(22).addChild(comments.get(26));



            // Books & Media"
            comments.add(new MyGroup("Books & Media", "A publisher oftendistribution of printed works such as books (the \"book trade\") and ... and other works dealing with information, including the electronic media."));
            comments.add(new MyGroup("Books", "Books group"));
            comments.get(27).addChild(comments.get(28));
            comments.add(new MyGroup("New Releases & Pre-Order", "New Releases & Pre-Order " +
                    "Books"));
            comments.get(28).addChild(comments.get(29));
            comments.add(new MyGroup("BestSellers", "BestSellers Books"));
            comments.get(28).addChild(comments.get(30));
            comments.add(new MyGroup("Academics & professional", "Academics & professional " +
                    "Books"));
            comments.get(28).addChild(comments.get(31));


            comments.add(new MyGroup("Music", "Music group"));
            comments.get(27).addChild(comments.get(32));
            comments.add(new MyGroup("Pre-Order", "Pre-Order music"));
            comments.get(32).addChild(comments.get(33));
            comments.add(new MyGroup("Bollywood Music", "Bollywood Music Bollywood Music"));
            comments.get(32).addChild(comments.get(34));
            comments.add(new MyGroup("Devotional", "Devotional music"));
            comments.get(32).addChild(comments.get(35));


            comments.add(new MyGroup("Gaming", "Gaming group"));
            comments.get(27).addChild(comments.get(36));
            comments.add(new MyGroup("New Releases", "New Releases Game"));
            comments.get(36).addChild(comments.get(37));
            comments.add(new MyGroup("PS3 Games", "PS3 Games gamed"));
            comments.get(36).addChild(comments.get(38));

            return comments;
        }
    }
}
