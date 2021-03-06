package com.smit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ApiResult(
    val body: Body? = null,
    val status: String? = null,
    val statusCode: Int? = null
)

@Serializable
data class Body(
    val places: Places? = null,
    val status: String? = null,
    @SerialName("clear_client_cache")
    val clearClientCache: String? = null,
    val hashtags: ArrayList<HashTags>? = null,
    @SerialName("rank_token")
    val rankToken: String? = null,
    @SerialName("has_more")
    val hasMore: Boolean? = null,
    val users: ArrayList<IGUsers>? = null
)

@Serializable
data class Places(val position: Int? = null, val place: Place? = null)

@Serializable
data class Place(
    //
    @SerialName("media_bundles") val mediaBundles: String? = null,
    val title: String? = null,
    val location: Location? = null,
    val subtitle: String? = null
)

@Serializable
data class HashTags(val hashTag: HashTag? = null, val position: Int? = null)

@Serializable
class HashTag(
    @SerialName("search_result_subtitle")
    val searchResultSubtitle: String? = null,
    val id: Long? = null,
    @SerialName("media_count")
    val mediaCount: Long? = null,
    @SerialName("profile_pic_url")
    val profilePicUrl: String? = null,
    @SerialName("use_default_avatar")
    val useDefaultAvatar: Boolean? = null
)

@Serializable
class MediaBundles

@Serializable
data class Location(
    val pk: Int? = null,
    @SerialName("short_name") val shortName: String? = null,
    @SerialName("has_viewer_saved") val hasViewerSaved: Boolean? = null,
    val name: String? = null,
    @SerialName("external_source") val externalSource: String? = null,
    val lng: Float? = null,
    val address: String? = null,
    val city: String? = null,
    val lat: Float? = null,
    @SerialName("facebook_places_id") val facebookPlacesId: Int? = null
)

@Serializable
data class IGUsers(val user: IGUser? = null, val position: Int? = null)

@Serializable
data class IGUser(
    @SerialName("is_private") val isPrivate: Boolean? = null,
    @SerialName("has_primary_country_in_profile") val hasPrimaryCountryInProfile: Boolean? = null,
    @SerialName("full_name") val fullName: String? = null,
    @SerialName("growth_friction_info") val growthFrictionInfo: GrowthFrictionInfo? = null,
    @SerialName("has_highlight_reels") val hasHighlightReels: Boolean,
    @SerialName("has_primary_country_in_feed") val hasPrimaryCountryInFeed: Boolean? = null,
    @SerialName("should_show_category") val shouldShowCategory: Boolean,
    @SerialName("transparency_product_enabled") val transparencyProductEnabled: Boolean,
    @SerialName("friendship_status") val friendshipStatus: FriendshipStatus? = null,
    @SerialName("is_verified") val isVerified: Boolean? = null,
    val pk: Long? = null,
    @SerialName("profile_pic_id") val profilePicId: String? = null,
    val username: String? = null,
    @SerialName("has_anonymous_profile_picture") val hasAnonymousProfilePicture: Boolean? = null,
    @SerialName("live_broadcast_id") val liveBroadCastId: String? = null,
    @SerialName("follow_friction_type") val followFrictionType: Int? = null,
    @SerialName("latest_reel_media") val latestReelMedia: Long? = null,
    @SerialName("account_badges") val accountBadges: AccountBage? = null,
    @SerialName("profile_pic_url") val profilePicUrl: String? = null

)

@Serializable
class AccountBage

@Serializable
data class GrowthFrictionInfo(
    val interventions: Intervention? = null,
    @SerialName("has_active_interventions")
    val hashActiveInterventions: Boolean? = null
)

@Serializable
class Intervention

@Serializable
data class FriendshipStatus(
    @SerialName("is_private") val isPrivate: Boolean? = null,
    @SerialName("is_feed_favorite") val isFeedFavorite: Boolean? = null,
    @SerialName("incoming_request") val incomingRequest: Boolean? = null,
    @SerialName("is_restricted") val isRestricted: Boolean? = null,
    @SerialName("is_bestie") val isBestie: Boolean? = null,
    val following: Boolean? = null,
    @SerialName("outgoing_request")
    val outgoingRequest: Boolean? = null
)


