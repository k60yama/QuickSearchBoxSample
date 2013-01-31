package com.android.quicksearchboxsample;

import android.content.SearchRecentSuggestionsProvider;

public class SearchProvider extends SearchRecentSuggestionsProvider {

	public SearchProvider() {
		this.setupSuggestions("com.android.quicksearchboxsample.suggetions.authority", 
				SearchProvider.DATABASE_MODE_QUERIES);
	}
}
