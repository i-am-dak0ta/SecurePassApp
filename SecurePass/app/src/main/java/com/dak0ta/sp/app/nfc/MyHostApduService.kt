package com.dak0ta.sp.app.nfc

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log
import com.dak0ta.sp.utils.EncryptionUtils

class MyHostApduService : HostApduService() {

    override fun processCommandApdu(commandApdu: ByteArray, extras: Bundle?): ByteArray? {
        val commandHex = commandApdu.toHexString()
        Log.d(TAG, "Received APDU: $commandHex")

        if (commandHex.startsWith(SELECT_APDU_HEADER)) {
            val uid = currentUid
            Log.d(TAG, "Current UID: $currentUid")
            if (uid != null) {
                val encryptedUid = EncryptionUtils.encrypt(uid)
                Log.d(TAG, "Отправляем UID: $uid")
                return encryptedUid + STATUS_SUCCESS
            }
        }
        return STATUS_FAILED
    }

    override fun onDeactivated(reason: Int) {
        Log.d(TAG, "NFC Deactivated: reason=$reason")
    }

    companion object {

        private const val TAG = "MyHostApduService"
        var currentUid: String? = null
        val SELECT_APDU_HEADER = "00A40400"
        val AID = "F22222222222"
        val STATUS_SUCCESS = byteArrayOf(0x90.toByte(), 0x00.toByte())
        val STATUS_FAILED = byteArrayOf(0x6F.toByte(), 0x00.toByte())
    }
}

fun ByteArray.toHexString() = joinToString("") { "%02X".format(it) }
