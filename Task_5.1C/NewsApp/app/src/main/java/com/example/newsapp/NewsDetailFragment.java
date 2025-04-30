package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

public class NewsDetailFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_IMAGE_URL = "image_url";

    public NewsDetailFragment() {

    }

    public static NewsDetailFragment newInstance(NewsItem item) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, item.getTitle());
        args.putString(ARG_DESCRIPTION, item.getDescription());
        args.putString(ARG_IMAGE_URL, item.getImageUrl());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        TextView titleView = view.findViewById(R.id.textViewDetailTitle);
        TextView descView = view.findViewById(R.id.textViewDetailDescription);
        ImageView imageView = view.findViewById(R.id.imageViewDetail);
        RecyclerView recyclerViewRelated = view.findViewById(R.id.recyclerViewRelated);

        // Get arguments
        String title = getArguments() != null ? getArguments().getString(ARG_TITLE) : "";
        String desc = getArguments() != null ? getArguments().getString(ARG_DESCRIPTION) : "";
        String imageUrl = getArguments() != null ? getArguments().getString(ARG_IMAGE_URL) : "";

        // Set main article
        titleView.setText(title);
        descView.setText(desc);
        Glide.with(requireContext()).load(imageUrl).centerCrop().into(imageView);

        // Dummy  news list
        List<NewsItem> relatedList = Arrays.asList(
                new NewsItem(
                        "India's Tech Sector Booms Amid AI Wave",
                        "India’s IT industry sees an AI-led hiring and investment boom, with top firms expanding operations.",
                        "https://cdn.pixabay.com/photo/2023/05/08/08/41/ai-7977960_1280.jpg"
                ),
                new NewsItem(
                        "Record Monsoon Predicted for South India",
                        "The Indian Meteorological Department predicts an active monsoon with abundant rainfall across the southern states.",
                        "https://cdn.pixabay.com/photo/2021/07/03/14/20/lightning-6383992_1280.jpg"
                ),
                new NewsItem(
                        "THE AGE Headlines",
                        "Melbourne startup wins tech award.",
                        "https://cdn.pixabay.com/photo/2020/07/08/04/12/work-5382501_1280.jpg"
                ),
                new NewsItem(
                        "Chandrayaan-3 Success",
                        "Historic landing near Moon's south pole.In August last year, India became the first country to soft land a spacecraft near the South Pole of the Moon. A nail-biting 20 minutes of \"terror\" consumed most of the country as the Vikram lander module - carrying the Pragyan rover - descended to the lunar surface and touched down in one piece, releasing a flood of relief and joy at the Indian space agency's mission control.\n" +
                                "\n",
                        "https://cdn.pixabay.com/photo/2012/11/28/11/25/satellite-67718_1280.jpg"
                ),
                new NewsItem(
                        "G20 Summit",
                        "Historic landing near Moon's south pole.Prime Minister Anthony Albanese attended the Asia-Pacific Economic Cooperation (APEC) Economic Leaders’ Meeting in Lima on 15-16 November, and the G20 Leaders’ Summit in Rio de Janeiro on 18-19 November. Both APEC and the G20 play a critical role in steering the regional and global economies.\n" +
                                "\n" +
                                "At these summits, the Prime Minister worked closely with international partners to promote sustainable and inclusive economic growth, and to make progress on global challenges. This was Prime Minister Albanese's first visit to South America as Prime Minister, and his third APEC Economic Leaders' Meeting and G20 Leaders’ Summit.",
                        "https://cdn.pixabay.com/photo/2015/07/08/10/30/manfred-gunther-835824_1280.jpg"
                )
        );

        NewsAdapter adapter = new NewsAdapter(requireContext(), relatedList, item -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, NewsDetailFragment.newInstance(item))
                    .addToBackStack(null)
                    .commit();
        });

        recyclerViewRelated.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewRelated.setAdapter(adapter);
        NewsAdapter.runLayoutAnimation(recyclerViewRelated);

        return view;
    }
}
