/*
 * Copyright (c) 2019-2020, ganom <https://github.com/Ganom>
 * All rights reserved.
 * Licensed under GPL3, see LICENSE for the full scope.
 */
package meteor.plugins.external


import meteor.config.legacy.Config
import meteor.config.legacy.ConfigGroup
import meteor.config.legacy.ConfigItem

@ConfigGroup(value = "external")
interface ExternalConfig : Config {
    @ConfigItem(
        keyName = "example",
        name = "Example",
        description = "describe the configuration here",
        position = 0,
        textField = true
    )
    fun example(): String {
        return "Get to work";
    }
}