package space.stanton.technicaltest

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody

class Post {
    var title: String = ""
    var content: String = ""
}

/**
 * TODO - write doc block
 */
object ApiCalls {

    fun loadAll(callBack: (Pair<ResponseBody?, Throwable?>) -> Unit) {
        var url = "https://jsonplaceholder.typicode.com/posts"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        try {
            val response = client.newCall(request).execute()
            callBack(Pair(response.body,null))
        } catch (e: Throwable) {
            callBack(Pair<ResponseBody?, Throwable?>(null, e))
        }
    }
}