package ninja.sakib.journi.tvads.core

import com.eclipsesource.json.JsonObject
import ninja.sakib.journi.tvads.exceptions.NotAValidJSON
import ninja.sakib.journi.tvads.models.TvSpot
import ninja.sakib.journi.tvads.models.User
import ninja.sakib.journi.tvads.utils.parseDateTime
import ninja.sakib.journi.tvads.utils.parseJson
import java.lang.Exception

fun start(jsonFilePath: String) {
    try {
        val jsonData = parseJson(jsonFilePath)
        val tvSpots = getTvSpots(jsonData)
        val users = getUsers(jsonData)

        println("Found tvSpots : ${tvSpots.size}")
        println("Found users : ${users.size}")

        val stats = findNewUsersStatImproved(tvSpots, users)
        for ((k, v) in stats) {
            println("Spot $k: $v new users")
        }
    } catch (e: NotAValidJSON) {
        println("Content isn't a valid json : ${e.message}")
    } catch (e: Exception) {
        println("A exception occurred : ${e.message}")
    }
}

fun getTvSpots(data: JsonObject): List<TvSpot> {
    val rawTvSpots = data.get("tvSpots").asArray()
    val tvSpots = mutableListOf<TvSpot>()

    for (v in rawTvSpots) {
        val s = v.asObject()
        val ts = TvSpot()

        ts.spotId = s.getInt("spotId", 0)
        ts.spotTime = parseDateTime(s.getString("time", ""))

        tvSpots.add(ts)
    }
    return tvSpots
}

fun getUsers(data: JsonObject): List<User> {
    val rawUsers = data.get("newUsers").asArray()
    val users = mutableListOf<User>()
    for (v in rawUsers) {
        val u = v.asObject()
        val user = User()

        user.userId = u.getInt("userId", 0)
        user.appearedTime = parseDateTime(u.getString("time", ""))

        users.add(user)
    }
    return users
}
