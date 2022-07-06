package pmb.attendance.app.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseListResponse<T>(
    @SerializedName("status")
    @Expose
    val statusCode: Boolean,
    @SerializedName("messages")
    @Expose
    var message: List<String>? = ArrayList<String>(),
    @SerializedName("data")
    @Expose
    var data: List<T> = ArrayList<T>(),
    @SerializedName("total_notification")
    @Expose
    var totalNotification: Int? = null
)