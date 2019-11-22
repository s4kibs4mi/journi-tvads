package ninja.sakib.journi.tvads.utils

import java.text.SimpleDateFormat
import java.util.*

val sdt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

fun parseDateTime(datetimeStr: String): Date {
    return sdt.parse(datetimeStr)
}

fun formatDateTime(datetime: Date): String {
    return sdt.format(datetime)
}

fun getEndTime(datetime: Date): Date {
    val cl = Calendar.getInstance()
    cl.time = datetime
    cl.add(Calendar.MINUTE, 10)
    return cl.time
}

fun getTimeDiffInSeconds(startTime: Date, endTime: Date): Long {
    return endTime.time - startTime.time
}
