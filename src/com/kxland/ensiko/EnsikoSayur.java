package com.kxland.ensiko;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class EnsikoSayur extends Activity implements MyCallback, TextToSpeech.OnInitListener{
	// Declare Variables
		ViewPager viewPager;
		PagerAdapter adapter;
		String[] kategori;
		String[] sayur;
		String[] deskripsi;	
		Bitmap[] gambar;

		//db
		dbOperation db;
		
		private static final String TAG = "TextToSpeechDemo";
		
		private TextToSpeech mTts;
		
		//callback
		@Override
		public void callbackCall(int x) {
			// callback code goes here
			
					
			bicara(deskripsi[x]);
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			//open db
	        db = new dbOperation(this);
	        
	     
	        List<Sayur> sayurs = db.getAllSayur();
	        
	               
	        kategori = new String[sayurs.size()];
	        sayur = new String[sayurs.size()];
	        deskripsi = new String[sayurs.size()];
	        gambar = new Bitmap[sayurs.size()];
	        
	        //init
	        int i = 0;
	        for (Sayur sy : sayurs) {
	        	kategori[i] = sy.getKategori();
	        	sayur[i] = sy.getSayur();
	        	deskripsi[i] = sy.getDeskripsi();
	        	gambar[i] = sy.getGambar();

	        	
	        	i ++;
	        	
			
	        }
	       
	        
			// Get the view from viewpager_main.xml
			setContentView(R.layout.viewpager_main);

			
			// Initialize text-to-speech. This is an asynchronous operation.
	        // The OnInitListener (second argument) is called after initialization completes.
	        mTts = new TextToSpeech(this,
	            this  // TextToSpeech.OnInitListener
	            );
	        
	        
			// Locate the ViewPager in viewpager_main.xml
			viewPager = (ViewPager) findViewById(R.id.pager);
			// Pass results to ViewPagerAdapter Class
			adapter = new ViewPagerAdapter(EnsikoSayur.this, kategori, sayur, deskripsi, gambar);
					
			// Binds the Adapter to the ViewPager
			viewPager.setAdapter(adapter);

			
		}
		
		// Implements TextToSpeech.OnInitListener.
	    public void onInit(int status) {
	        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
	        if (status == TextToSpeech.SUCCESS) {
	            // Set preferred language to US english.
	            // Note that a language may not be available, and the result will indicate this.
	            int result = mTts.setLanguage(Locale.US);
	            // Try this someday for some interesting results.
	            // int result mTts.setLanguage(Locale.FRANCE);
	            if (result == TextToSpeech.LANG_MISSING_DATA ||
	                result == TextToSpeech.LANG_NOT_SUPPORTED) {
	               // Lanuage data is missing or the language is not supported.
	                Log.e(TAG, "Language is not available.");
	            } else {
	                // Check the documentation for other possible result codes.
	                // For example, the language may be available for the locale,
	                // but not for the specified country and variant.

	                // The TTS engine has been successfully initialized.
	                // Allow the user to press the button for the app to speak again.
	                //mAgainButton.setEnabled(true);
	                // Greet the user.
	                //sayHello();
	            }
	        } else {
	            // Initialization failed.
	            Log.e(TAG, "Could not initialize TextToSpeech.");
	        }
	    }

	    
	    @Override
	    public void onDestroy() {
	        // Don't forget to shutdown!
	        if (mTts != null) {
	            mTts.stop();
	            mTts.shutdown();
	        }

	        super.onDestroy();
	    }

	    
	    private void bicara(String kata) {
	        
	        mTts.speak(kata,
	            TextToSpeech.QUEUE_FLUSH,  // Drop all pending entries in the playback queue.
	            null);
	    }
}
