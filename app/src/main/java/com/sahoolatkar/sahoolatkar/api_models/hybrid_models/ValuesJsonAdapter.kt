package com.sahoolatkar.sahoolatkar.api_models.hybrid_models

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.sahoolatkar.sahoolatkar.api_models.shared.Value
import java.io.IOException
import java.lang.Exception

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
        val deserializedObject = Value(0, "")

        // type of next token
        val peek: JsonToken = `in`.peek()

        // if the json field is string
        if (JsonToken.STRING == peek) {
            try {
                val stringValue: String = `in`.nextString()
                // convert string to integer and add to list as a value
                deserializedObject.value = stringValue
            } catch (e: Exception) {

            }
        }

        // if it is array then implement normal array deserialization
        if (JsonToken.BEGIN_OBJECT == peek) {
            while (`in`.hasNext())
                `in`.skipValue()
        }

        if (JsonToken.BEGIN_ARRAY == peek) {
            while (`in`.hasNext())
                `in`.skipValue()
        }
        return deserializedObject
    }

}