package commandsPack

import commandInterfacePack.Info
import elementsPack.Movie

/**
 * Class storing method info
 */
open class Info protected constructor() : Help(), Info {
    /**
     * This method prints main info about current collection
     */
    override fun info() {
        println(
            """Collection type: ${Movie::class.java.simpleName}
Date of initialization: ${collection!!.getDateOfCreation()}
Number of elements stored: ${collection!!.numberOfElements}"""
        )
    }
}