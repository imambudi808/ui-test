package com.example.panicbuttonui.helper

import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

/**
 * Created by imambudi808 on 06/02/2021.
 */
open class ShortenMultiplePermissionListener : MultiplePermissionsListener {
    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
    }

    override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest?>?, token: PermissionToken?) {
        token?.continuePermissionRequest()
    }
}