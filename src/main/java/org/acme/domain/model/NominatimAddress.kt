package org.acme.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class NominatimAddress(
    @JsonProperty("house_number")
    val houseNumber: String? = "",
    @JsonProperty("road")
    val road: String? = "",
    @JsonProperty("residential")
    val residential: String? = "",
    @JsonProperty("suburb")
    val suburb: String? = "",
    @JsonProperty("city")
    val city: String? = "",
    @JsonProperty("municipality")
    val municipality: String? = "",
    @JsonProperty("state_district")
    val stateDistrict: String? = "",
    @JsonProperty("state")
    val state: String? = "",
    @JsonProperty("region")
    val region: String? = "",
    @JsonProperty("postcode")
    val postcode: String? = "",
    @JsonProperty("country")
    val country: String? = "",
    @JsonProperty("country_code")
    val countryCode: String? = ""
)
