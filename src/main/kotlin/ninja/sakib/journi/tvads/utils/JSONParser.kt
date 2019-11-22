package ninja.sakib.journi.tvads.utils

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
import ninja.sakib.journi.tvads.exceptions.NotAValidJSON
import java.io.BufferedReader
import java.io.FileReader

/**
 * parseJson extracts data from json file and throws exception NotAValidJSON if content isn't a valid json
 */
fun parseJson(jsonFilePath: String): JsonObject {
    val data = Json.parse(BufferedReader(FileReader(jsonFilePath)))
    if (data.isObject) {
        return data.asObject()
    }
    throw NotAValidJSON()
}
