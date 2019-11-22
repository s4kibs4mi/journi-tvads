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
