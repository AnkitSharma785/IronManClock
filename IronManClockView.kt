package com.example.ironmanclock

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.*

class IronManClockView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private val calendar = Calendar.getInstance()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = Math.min(centerX, centerY) - 40f

        calendar.timeInMillis = System.currentTimeMillis()

        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        // Draw outer circle
        paint.color = Color.parseColor("#FFCC00") // Iron Man gold
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 12f
        canvas.drawCircle(centerX, centerY, radius, paint)

        // Draw hour hand
        drawHand(canvas, centerX, centerY, hour * 30f + minute * 0.5f, radius * 0.5f, Color.RED, 10f)

        // Draw minute hand
        drawHand(canvas, centerX, centerY, minute * 6f, radius * 0.7f, Color.YELLOW, 8f)

        // Draw second hand
        drawHand(canvas, centerX, centerY, second * 6f, radius * 0.9f, Color.CYAN, 4f)

        // Redraw every second
        postInvalidateDelayed(1000)
    }

    private fun drawHand(canvas: Canvas, cx: Float, cy: Float, angle: Float, length: Float, color: Int, strokeWidth: Float) {
        val radian = Math.toRadians(angle - 90.0)
        val x = (cx + Math.cos(radian) * length).toFloat()
        val y = (cy + Math.sin(radian) * length).toFloat()

        paint.color = color
        paint.strokeWidth = strokeWidth
        paint.style = Paint.Style.STROKE
        canvas.drawLine(cx, cy, x, y, paint)
    }
}
