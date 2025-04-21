package com.mikef.earthquakeapp.data

import com.google.gson.annotations.SerializedName


data class EarthquakeResponse (

  @SerializedName("type"     ) var type     : String?             = null,
  @SerializedName("metadata" ) var metadata : Metadata?           = Metadata(),
  @SerializedName("features" ) var features : ArrayList<Features> = arrayListOf(),
  @SerializedName("bbox"     ) var bbox     : ArrayList<Double>   = arrayListOf()

)