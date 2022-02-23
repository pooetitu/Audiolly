package com.audiolly

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class OpenIntent : Fragment() {
    companion object {
        const val SCAN_BAR_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val intent = Intent()
        intent.action = "com.google.zxing.client.android.SCAN"
        intent.putExtras(bundleOf("SCAN_FORMATS" to "EAN_13"))
        startActivityForResult(intent, SCAN_BAR_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SCAN_BAR_CODE && resultCode == Activity.RESULT_OK) {
            val scanResult = data?.getStringExtra("SCAN_RESULT")
        }
    }
}

