package com.example.syncexample;

import java.sql.Timestamp;

import android.app.Application;

public class MyApplication extends Application {

    private Timestamp updatedAt = null;

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}