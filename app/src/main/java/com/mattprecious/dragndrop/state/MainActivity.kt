package com.mattprecious.dragndrop.state

import android.content.ClipData
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.DragShadowBuilder

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)

    val fromView = findViewById<View>(R.id.from)
    val toView = findViewById<View>(R.id.to)

    fromView.setOnTouchListener { v, event ->
      if (event.action == MotionEvent.ACTION_DOWN) {
        v.startDragAndDrop(
            ClipData.newPlainText("test", "test"), DragShadowBuilder(v), "Local State", 0
        )

        // Starting a second drag & drop will clear the local state of the first drag.
        v.startDragAndDrop(
            ClipData.newPlainText("test", "test"), DragShadowBuilder(v), "Local State", 0
        )
        return@setOnTouchListener true
      }

      return@setOnTouchListener false
    }

    toView.setOnDragListener { _, event ->
      if (event.action == DragEvent.ACTION_DROP) {
        // The local state is now null, even though we populated it with a string above.
        requireNotNull(event.localState)
      }

      return@setOnDragListener true
    }
  }
}
