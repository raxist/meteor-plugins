package meteor.plugins.external.overlay

import meteor.plugins.external.ExternalPlugin
import meteor.ui.overlay.Overlay
import java.awt.Dimension
import java.awt.Graphics2D

class ExternalOverlay(var plugin: ExternalPlugin) : Overlay() {
    override fun render(graphics: Graphics2D): Dimension? {
        client.localPlayer?.let { graphics.drawString(it.name, 0, 0) }
        return null
    }
}