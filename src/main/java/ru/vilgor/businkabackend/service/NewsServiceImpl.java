package ru.vilgor.businkabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vilgor.businkabackend.entity.News;
import ru.vilgor.businkabackend.repository.NewsRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;

    private final int NEWS_PREVIEW_WORD_COUNT = 25;
    private final static Pattern SPACES_PATTERN = Pattern.compile("\\s+|\\z");

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> getNewsPreviewListByCountAndOffset(int count, int offset) {
        List<News> newsPreviewList = newsRepository.findByCountAndOffset(count, offset);

        for (News news : newsPreviewList) {
            news.setText(truncateAfterWordsCount(NEWS_PREVIEW_WORD_COUNT, news.getText()));
        }

        return newsPreviewList;
    }

    @Override
    public List<News> getNewsPreviewList() {
        List<News> newsPreviewList = newsRepository.findAll();

        for (News news : newsPreviewList) {
            news.setText(truncateAfterWordsCount(NEWS_PREVIEW_WORD_COUNT, news.getText()));
        }

        return newsPreviewList;
    }

    @Override
    public News getNewsItemById(int id) {
        return newsRepository.findById(id);
    }

    private static String truncateAfterWordsCount(int n, String s) {
        if (s == null) return null;
        Matcher matcher = SPACES_PATTERN.matcher(s);
        int matchStartIndex = 0, matchEndIndex = 0, wordsFound = 0;
        // Keep matching until enough words are found,
        // reached the end of the string,
        // or no more matches
        while (wordsFound < n && matchEndIndex < s.length() && matcher.find(matchEndIndex)) {
            // Keep track of both the start and end of each match
            matchStartIndex = matcher.start();
            matchEndIndex = matchStartIndex + matcher.group().length();
            // Only increment words found when not at the beginning of the string
            if (matchStartIndex != 0) wordsFound++;
        }
        // From the beginning of the string to the start of the final match
        return s.substring(0, matchStartIndex) + "...";
    }
}
