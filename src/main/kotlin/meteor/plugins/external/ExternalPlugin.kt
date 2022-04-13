package meteor.plugins.external

import eventbus.events.*
import meteor.plugins.Plugin
import meteor.plugins.PluginDescriptor
import meteor.plugins.external.overlay.ExternalOverlay

@PluginDescriptor(name = "Example", description = "The new shit", enabledByDefault = false, external = true)
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