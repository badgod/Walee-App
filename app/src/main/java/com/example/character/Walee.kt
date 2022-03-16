package com.example.character

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Walee() : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var quote: String = ""
    var article: String? = null
    var source: String? = null

    constructor(id: Int, quote: String, article: String?, source: String?) : this() {
        this.id = id
        this.quote = quote
        this.article = article
        this.source = source
    }

}