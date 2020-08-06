package com.sahoolatkar.sahoolatkar.api_models.hybrid_models

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.sahoolatkar.sahoolatkar.api_models.shared.Value
import java.io.IOException

class ValuesJsonAdapter : TypeAdapter<Value>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter?, user: Value?) {

        // Since we do not serialize EquationList by gson we can omit this part.
        // If you need you can check
        // com.google.gson.internal.bind.ObjectTypeAdapter class
        // read method for a basic object serialize implementation
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Value? {
        val deserializedObject = Value(0)

        // type of next token
        val peek: JsonToken = `in`.peek()

        // if the json field is string
        if (JsonToken.STRING == peek) {
            val stringValue: String = `in`.nextString()
            // convert string to integer and add to list as a value
            deserializedObject.add(Integer.valueOf(stringValue))
        }

        // if it is array then implement normal array deserialization
        if (JsonToken.BEGIN_ARRAY == peek) {
            `in`.beginArray()
            while (`in`.hasNext()) {
                val element: String = `in`.nextString()
                deserializedObject.add(Integer.valueOf(element))
            }
            `in`.endArray()
        }
        return deserializedObject
    }

}