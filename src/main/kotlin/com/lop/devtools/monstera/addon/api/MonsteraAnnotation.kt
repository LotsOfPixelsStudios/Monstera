package com.lop.devtools.monstera.addon.api

/**
 * indicates a field that should not be modified directly by an addon as it contains build information that might
 * corrupt the addon build if modified directly
 */
@Target(AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@RequiresOptIn("This field should not be modified within an addon", RequiresOptIn.Level.ERROR)
annotation class MonsteraBuildSetter

/**
 * this annotation indicates code that should only be used for debug purposes, this can also mean that one might want to
 * retrieve this information for their plugin/feature to give a better debug message if a user of set code makes a mistake
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@RequiresOptIn("This field/function/class contains/returns debug related information.", RequiresOptIn.Level.WARNING)
annotation class DebugMarker

@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@RequiresOptIn("This field should not be used within an addon", RequiresOptIn.Level.WARNING)
annotation class MonsteraUnsafeField

@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class HolidayCreatorFeature(val since: String = "current", val till: String = "current")

@DslMarker
annotation class MonsteraApi

@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@RequiresOptIn(
    "Requires explicit opt in as this feature has a high probability of containing bugs, or can change rapidly",
    RequiresOptIn.Level.WARNING
)
annotation class MonsteraExperimental