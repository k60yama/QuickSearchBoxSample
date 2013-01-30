package com.android.quicksearchboxsample;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class QuickSearchBoxSample extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		
		final Intent queryIntent = this.getIntent();
		final String queryAction = queryIntent.getAction();
		
		//ACTION_SEARCHのインテントで呼び出された場合
		if(Intent.ACTION_SEARCH.equals(queryAction)){
			this.doSearchWithIntent(queryIntent);
		}
		
		//クイック検索ボックスか呼び出された場合
		if(Intent.ACTION_VIEW.equals(queryAction)){
			if(queryIntent.getFlags() == Intent.FLAG_ACTIVITY_NEW_TASK){
				this.doSearchWithIntent(queryIntent);
			}
		}
	}
	
	private void doSearchWithIntent(final Intent queryIntent){
		//検索文字列は SearchManager.Queryというキーに入っている
		final String queryString = queryIntent.getStringExtra(SearchManager.QUERY);
		doSearchWithQuery(queryString);
	}
	
	private void doSearchWithQuery(String query){
		//検索文字列をTextViewに表示
		TextView textView = (TextView)this.findViewById(R.id.textView);
		textView.setText(query);
	}
	
	//検索用アクティビティから呼び出されたとき
	@Override
	protected void onNewIntent(Intent intent){
		doSearchWithIntent(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.menu_search:
			//検索ボックスを呼び出す
			this.onSearchRequested();
			return true;
		}
		return false;
	}
}
