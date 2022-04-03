package meteor.plugins.external

import eventbus.events.*
import kext.Extension
import meteor.plugins.Plugin
import meteor.plugins.PluginDescriptor
import meteor.plugins.external.overlay.ExternalOverlay

@Extension(name = "meteor-example", version = "1.0.0", provider = "Null")
@PluginDescriptor(name = "Example", description = "The new shit", enabledByDefault = false)
class ExternalPlugin : Plugin() {
    //No dependency injection


    //No provide
    override var config = configuration<ExternalConfig>()

    //Overlays are handled automatically
    val overlay = overlay(ExternalOverlay(this))


    override fun onStart() {}
    override fun onStop() {}

    //Override, no subscribe
    override fun onGameStateChanged(it: GameStateChanged) {
        println(it.gamestate.name)
    }
}