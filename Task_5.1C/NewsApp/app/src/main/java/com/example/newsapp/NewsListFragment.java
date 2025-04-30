package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class NewsListFragment extends Fragment {

    public NewsListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        RecyclerView recyclerViewTopStories = view.findViewById(R.id.recyclerViewTopStories);
        RecyclerView recyclerViewNews = view.findViewById(R.id.recyclerViewNews);

        // Top Stories Dummy Data
        List<NewsItem> topStoriesList = Arrays.asList(
                new NewsItem(
                        "Aditya-L1 Mission Launched",
                        "India's first solar observatory in space.Aditya-L1 spacecraft has completed its first halo orbit around the Sun-Earth L1 point. The Aditya-L1 mission is an Indian solar observatory at Lagrangian point L1, launched on September 2, 2023 and was inserted in its targeted halo orbit on January 6, 2024. Aditya-L1 spacecraft in the Halo orbit takes 178 days to complete a revolution around the L1 point.\n",
                        "https://cdn.pixabay.com/photo/2012/11/28/11/26/soyuz-67719_1280.jpg"
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

        // News Section Dummy Data
        List<NewsItem> newsList = Arrays.asList(
                new NewsItem(
                        "9NEWS Update",
                        "Australia celebrates science innovation.",
                        "https://cdn.pixabay.com/photo/2017/08/28/18/51/international-2690850_1280.jpg"
                ),
                new NewsItem(
                        "7NEWS Sports",
                        "T20 Cricket World Cup latest results.",
                        "https://cdn.pixabay.com/photo/2020/01/27/04/51/sport-4796426_1280.jpg"
                ),
                new NewsItem(
                        "ABC NEWS",
                        "Global climate talks in New York begin.",
                        "https://cdn.pixabay.com/photo/2021/03/27/18/16/riot-6129239_1280.jpg"
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

        // Top Stories Adapter (Horizontal RecyclerView)
        TopStoriesAdapter topStoriesAdapter = new TopStoriesAdapter(getContext(), topStoriesList, item -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, NewsDetailFragment.newInstance(item))
                    .addToBackStack(null)
                    .commit();
        });

        recyclerViewTopStories.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTopStories.setAdapter(topStoriesAdapter);

        // News Section Adapter (Grid RecyclerView)
        NewsAdapter newsAdapter = new NewsAdapter(getContext(), newsList, item -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, NewsDetailFragment.newInstance(item))
                    .addToBackStack(null)
                    .commit();
        });

        recyclerViewNews.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewNews.setAdapter(newsAdapter);

        return view;
    }
}
