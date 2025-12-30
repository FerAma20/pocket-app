package com.example.mypocketapp.ui

class MainViewModel {
    init {
        System.loadLibrary("native-lib")
    }

    external fun sum(a: Int, b: Int): Int
}