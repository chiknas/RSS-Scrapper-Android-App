package com.chiknas.newsreader.util;

import com.rometools.rome.feed.synd.SyndEntry;

import java.util.Comparator;

/**
 * Sorts the list with the latest entries first.
 * created by NikosK on 12/15/2019
 */
public class FeedEntriesComparator implements Comparator<SyndEntry> {
    @Override
    public int compare(SyndEntry syndEntry1, SyndEntry syndEntry2) {
        return syndEntry2.getPublishedDate().compareTo(syndEntry1.getPublishedDate());
    }
}
