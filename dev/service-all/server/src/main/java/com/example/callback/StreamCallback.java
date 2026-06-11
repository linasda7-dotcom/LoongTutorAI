package com.example.callback;

import org.springframework.lang.NonNull;

import com.example.model.siliconflow.Delta;

public interface StreamCallback {
    void onMessage(@NonNull Delta chunk);
}
