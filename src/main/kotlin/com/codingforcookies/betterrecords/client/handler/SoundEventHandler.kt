package com.codingforcookies.betterrecords.client.handler

import com.codingforcookies.betterrecords.ID
import com.codingforcookies.betterrecords.api.event.RadioInsertEvent
import com.codingforcookies.betterrecords.api.event.RecordInsertEvent
import com.codingforcookies.betterrecords.api.event.SoundStopEvent
import com.codingforcookies.betterrecords.client.sound.SoundPlayer
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side

@Mod.EventBusSubscriber(modid = ID, value = [Side.CLIENT])
object SoundEventHandler {

    @JvmStatic
    @SubscribeEvent
    fun onRecordInserted(event: RecordInsertEvent) {
        val (pos, dimension, playRadius, sounds) = event
        val player = Minecraft.getMinecraft().player

        if (playRadius > 100000 || Math.abs(Math.sqrt(Math.pow(player.posX - pos.x, 2.0) + Math.pow(player.posY - pos.y, 2.0) + Math.pow(player.posZ - pos.z, 2.0))).toFloat() < playRadius) {
            SoundPlayer.playSound(pos, dimension, playRadius, sounds.first())
        }

    }

    @JvmStatic
    @SubscribeEvent
    fun onRadioInserted(event: RadioInsertEvent) {
        val (pos, dimension, playRadius, sounds) = event
        val player = Minecraft.getMinecraft().player

        if (playRadius > 100000 || Math.abs(Math.sqrt(Math.pow(player.posX - pos.x, 2.0) + Math.pow(player.posY - pos.y, 2.0) + Math.pow(player.posZ - pos.z, 2.0))).toFloat() < playRadius) {
            SoundPlayer.playSoundFromStream(pos, dimension, playRadius, sounds.first())
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onSoundStopped(event: SoundStopEvent) {
        val (pos, dimension) = event

        SoundPlayer.stopPlayingAt(pos, dimension)
    }
}
