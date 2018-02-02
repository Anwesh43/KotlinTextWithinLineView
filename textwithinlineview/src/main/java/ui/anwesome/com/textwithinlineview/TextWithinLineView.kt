package ui.anwesome.com.textwithinlineview

/**
 * Created by anweshmishra on 02/02/18.
 */
import android.graphics.*
import android.content.*
import android.view.*
class TextWithinLineView(ctx:Context):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
    data class TextWithinLine(var x:Float,var y:Float,var text:String,var w:Float) {
        fun draw(canvas:Canvas,paint:Paint) {
            paint.textSize = w/10
            val tw = paint.measureText(text)
            paint.color = Color.WHITE
            paint.strokeCap = Paint.Cap.ROUND
            canvas.save()
            canvas.translate(x,y)
            canvas.rotate(90f)
            for(i in 0..1) {
                canvas.drawLine(-0.7f * tw, -w / 20, 0.7f * tw, -w/20, paint)
            }
            val path = Path()
            path.addRect(RectF(-0.7f*tw,-w/20,0.7f*tw,w/20),Path.Direction.CW)
            canvas.drawText(text,-tw/2,w/40,paint)
            canvas.restore()
        }
        fun update(stopcb:()->Unit) {

        }
        fun startUpdating(startcb:()->Unit) {

        }
    }
    data class TextWithinState(var scale:Float = 0f,var dir:Float = 0f,var prevScale:Float = 0f) {
        fun update(stopcb:(Float)->Unit) {
            scale += dir*0.1f
            if(Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                stopcb(scale)
            }
        }
        fun startUpdating(startcb:()->Unit) {
            if(dir == 0f) {
                dir = 1f - 2*scale
                startcb()
            }
        }
    }
}