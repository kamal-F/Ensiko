package com.kxland.ensiko;

import android.graphics.Bitmap;

public class Sayur {
	//{Kategori, Sayur, Gambar, Deskripsi }
		//private variables    
	    int _IDx;
		String _kategori;
	    String _sayur;
	    String _deskripsi;
	    Bitmap _gambar;
	    
	    // Empty constructor
	    public Sayur(){
	         
	    }
	    
	    public void setIDx(int idx){
	        this._IDx = idx;
	    }
	    public int getIDx(){
	        return this._IDx;
	    }
	    public void setKategori(String kategori){
	        this._kategori = kategori;
	    }
	    public String getKategori(){
	        return this._kategori;
	    }
	    public void setSayur(String sayur){
	        this._sayur = sayur;
	    }
	    public String getSayur(){
	        return this._sayur;
	    }
	    public void setdeskripsi(String deskripsi){
	        this._deskripsi = deskripsi;
	    }
	    public String getDeskripsi(){
	        return this._deskripsi;
	    }
	    public void setGambar(Bitmap gambar){
	        this._gambar = gambar;
	    }
	    public Bitmap getGambar(){
	        return this._gambar;
	    }
}
