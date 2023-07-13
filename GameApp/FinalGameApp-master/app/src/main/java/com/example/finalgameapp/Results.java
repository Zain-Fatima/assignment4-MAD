package com.example.finalgameapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Results#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Results extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Results() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Results.
     */
    // TODO: Rename and change types and number of parameters
    public static Results newInstance(String param1, String param2) {
        Results fragment = new Results();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_results, container, false);

        // Create the list of ResultsItems with the desired data
        List<ResultItem> resultsList = new ArrayList<>();
//        resultsList.add(new ResultItem("2023-07-12", 10, "Question 1", "Answer 1", "Correct Answer 1"));
//        resultsList.add(new ResultItem("2023-07-11", 6, "Question 2", "Answer 2", "Correct Answer 2"));
//        resultsList.add(new ResultItem("2023-07-11", 6, "Question 2", "Answer 2", "Correct Answer 2"));
//        resultsList.add(new ResultItem("2023-07-11", 6, "Question 2", "Answer 2", "Correct Answer 2"));
//        resultsList.add(new ResultItem("2023-07-11", 6, "Question 2", "Answer 2", "Correct Answer 2"));
        // Add more ResultsItems as needed
    Db DbH = new Db(getContext());
    resultsList=DbH.getAllResults();
        // Create the ResultsAdapter
        ResultAdapter adapter = new ResultAdapter(requireContext(), resultsList);

        // Get the ListView and set the adapter
        ListView resultsListView = view.findViewById(R.id.ResultListView);
        resultsListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;



    }




}