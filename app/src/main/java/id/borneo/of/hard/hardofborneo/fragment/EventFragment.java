package id.borneo.of.hard.hardofborneo.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import id.borneo.of.hard.hardofborneo.R;
import id.borneo.of.hard.hardofborneo.model.EventItem;
import id.borneo.of.hard.hardofborneo.adapter.EventRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EventFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    LinearLayout ln;

    public EventFragment() {
        // Required empty public constructor
    }

    public static EventFragment newInstance(int index) {
        EventFragment fragment = new EventFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        initialAction(view);
        return view;

    }

    public void initialAction(View view)  {
        List<EventItem> rowListItem = getAllItemList();
        LinearLayoutManager layout = new LinearLayoutManager(getContext());

        RecyclerView rView = view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(layout);

        EventRecyclerAdapter rcAdapter = new EventRecyclerAdapter(getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<EventItem> getAllItemList(){

        List<EventItem> allItems = new ArrayList<>();
        allItems.add(new EventItem("Persiapan Ajang Event Dota",getString(R.string.desc), R.drawable.dummy_image));
        allItems.add(new EventItem("Acara Hackathon 2018 Telkomsel",getString(R.string.one), R.drawable.dummy_image));
        allItems.add(new EventItem("Event Samarinda bersih bersih",getString(R.string.two), R.drawable.dummy_image));
        allItems.add(new EventItem("Born to protech",getString(R.string.tree), R.drawable.dummy_image));
        allItems.add(new EventItem("Festival kuliner samarinda",getString(R.string.fore), R.drawable.dummy_image));

        return allItems;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
