package com.udacity.loadapp.fetcher

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class PermissionUtil {

    companion object {
        private const val REQUEST_CODE = 0

        @Volatile
        @JvmStatic
        var isPermitted: Boolean = false

        fun requestPermission(activity: AppCompatActivity) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                REQUEST_CODE
            )
        }

        fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
            if (requestCode == REQUEST_CODE) {
                isPermitted = grantResults[0] == PackageManager.PERMISSION_GRANTED
            }
        }
    }

}