package com.beetleware.task.utils.checkNetwork;

public enum NetworkStatus {

    UNKNOWN, // Error with detecting network status
    WIFI_CONNECTED_WITH_INTERNET, // wifi enabled and there is internet
    WIFI_CONNECTED_WITHOUT_INTERNET, // Wifi is enabled but no internet
    MOBILE_DATA_CONNECTED, // Mobile data is enabled
	TWO_G, // Mobile data is enabled and has 2g connection
	THREE_G, // Mobile data is enabled and has 3g connection
	FOUR_G, // Mobile data is enabled and has 4g connection
    OFFLINE; // Wifi and mobile data is turned off
}
