package commandsPack

import commandInterfacePack.Show

open class Show protected constructor() : Info(), Show {
    override fun show() {
        for (movie in collection!!.getCollection()) {
            println(movie)
        }
    }
}