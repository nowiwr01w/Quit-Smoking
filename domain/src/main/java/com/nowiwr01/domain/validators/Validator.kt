package com.nowiwr01.domain.validators

/***
 * Base validator contract. Describes common generic contract to validate any objects.
 */
interface Validator<in T> {

    /***
     * @return `true` when object is valid, `false` otherwise
     */
    fun validate(value: T): Boolean
}