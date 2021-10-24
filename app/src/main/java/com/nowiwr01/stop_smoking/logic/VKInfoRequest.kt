package com.nowiwr01.stop_smoking.logic

import com.nowiwr01.stop_smoking.data.InfoVK
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKInfoRequest(
    userId: Int
): VKRequest<InfoVK>("users.get") {

    init {
        addParam("user_ids", userId.toString())
        addParam("fields", "photo_max,screen_name")
    }

    override fun parse(r: JSONObject): InfoVK {
        val group = r.getJSONArray("response").getJSONObject(0)
        return InfoVK(
            first_name = group.optString("first_name", ""),
            last_name = group.optString("last_name", ""),
            screen_name = group.optString("screen_name", ""),
            photo_max = group.optString("photo_max", ""),
        )
    }
}