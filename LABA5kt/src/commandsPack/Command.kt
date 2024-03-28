package commandsPack


//import com.thoughtworks.xstream.XStream
import mainPack.MovieCollection
import java.util.*

/**
 * Abstract class inherited by all the commands available
 */
abstract class Command protected constructor() {
    protected var reader: Scanner? = null
    protected var collection: MovieCollection? = null
    //protected var xStream: XStream? = null
    protected var readingFromFile: Boolean = false
    protected var fileCallsCount: Int = 0

    val nextReaderLine: String
        /**
         * @return returns next line of scanner
         */
        get() = reader!!.nextLine()
    val nextReaderWord: String
        get() = reader!!.next()

    fun setReader(reader: Scanner?) {
        this.reader = reader
    }

    fun setCollection(collection: MovieCollection?) {
        this.collection = collection
    }
}