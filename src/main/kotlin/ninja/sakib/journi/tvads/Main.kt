package ninja.sakib.journi.tvads

import ninja.sakib.journi.tvads.core.start

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Please put json file path as first argument.")
        return
    }

    start(args[0])
}
