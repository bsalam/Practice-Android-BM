package com.example.composenewsapp.utils

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities


fun ConnectivityManager.isConnectedToInternet(): Boolean {
    val activeNetwork: Network = this.activeNetwork ?: return false
    val capabilities: NetworkCapabilities =
        this.getNetworkCapabilities(activeNetwork) ?: return false
    return when { // return true if there is an internet connection from Wi-Fi, cellular and ethernet
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}