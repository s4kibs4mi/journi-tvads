package ninja.sakib.journi.tvads.core

import ninja.sakib.journi.tvads.models.TvSpot
import ninja.sakib.journi.tvads.models.User
import ninja.sakib.journi.tvads.utils.getEndTime
import ninja.sakib.journi.tvads.utils.getTimeDiffInSeconds
import java.util.*

fun findNewUsersStat(tvSpots: List<TvSpot>, users: List<User>): Map<Int, Int> {
    val stats = mutableMapOf<Int, Int>()

    val ast = Calendar.getInstance().time

    for (u in users) {
        for (ts in tvSpots) {
            val st = ts.spotTime?.time!!
            val et = getEndTime(ts.spotTime!!).time
            val t = u.appearedTime?.time!!

            if (t >= st && t <= et) {
                if (stats.containsKey(ts.spotId)) {
                    val c = stats[ts.spotId]!!
                    stats[ts.spotId] = c + 1
                } else {
                    stats[ts.spotId] = 1
                }
            }
        }
    }

    val aet = Calendar.getInstance().time
    println("Algorithm took : ${getTimeDiffInSeconds(ast, aet)}/ms")

    return stats
}

fun findNewUsersStatImproved(tvSpots: List<TvSpot>, users: List<User>): Map<Int, Int> {
    val stats = mutableMapOf<Int, Int>()

    val ast = Calendar.getInstance().time

    for (u in users) {
        val spot = binarySearch(tvSpots, 0, tvSpots.size - 1, u)
        if (spot == null) {
            continue
        }

        if (stats.containsKey(spot.spotId)) {
            val c = stats[spot.spotId]!!
            stats[spot.spotId] = c + 1
        } else {
            stats[spot.spotId] = 1
        }
    }

    val aet = Calendar.getInstance().time
    println("Algorithm took : ${getTimeDiffInSeconds(ast, aet)}/ms")

    return stats
}

fun binarySearch(spots: List<TvSpot>, left: Int, right: Int, user: User): TvSpot? {
    if (left <= right) {
        val mid = (left + right) / 2;
        val spot = spots[mid]
        val st = spot.spotTime?.time!!
        val et = getEndTime(spot.spotTime!!).time
        val t = user.appearedTime?.time!!

        if (t >= st && t <= et) {
            return spot
        } else if (t < st) {
            return binarySearch(spots, left, mid - 1, user)
        } else {
            return binarySearch(spots, mid + 1, right, user)
        }
    }
    return null
}
