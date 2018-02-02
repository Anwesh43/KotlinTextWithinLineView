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
        val state = TextWithinState()
        fun draw(canvas:Canvas,paint:Paint) {
            paint.textSize = w/10
            val tw = paint.measureText(text)
            paint.color = Color.WHITE
            paint.strokeCap = Paint.Cap.ROUND
            canvas.save()
            canvas.translate(x,y)
            canvas.rotate(90f*(1-state.scales[2]))
            val th = w/20*(state.scales[1])
            val tx = 0.7f*tw*(state.scales[0])
            for(i in 0..1) {
                canvas.drawLine(-tx, th*(1-2*i), tx, th*(1-2*i), paint)
            }
            val path = Path()
            path.addRect(RectF(-tx, -th, tx, th),Path.Direction.CW)
            canvas.clipPath(path)
            canvas.drawText(text,-tw/2,w/40,paint)
            canvas.restore()
        }
        fun update(stopcb:(Float)->Unit) {
            state.update(stopcb)
        }
        fun startUpdating(startcb:()->Unit) {
            state.startUpdating(startcb)
        }
    }
    data class TextWithinState(var dir:Float = 0f,var prevScale:Float = 0f,var j:Int = 0,var jDir:Int = 1) {
        val scales:Array<Float> = arrayOf(0f,0f,0f)
        fun update(stopcb:(Float)->Unit) {
            scales[j] += dir*0.1f
            if(Math.abs(scales[j] - prevScale) > 1) {
                scales[j] = prevScale + dir
                j+=jDir
                if(j == scales.size || j == -1) {
                    jDir*=-1
                    j+=jDir
                    prevScale = scales[j]
                    dir = 0f
                    stopcb(prevScale)
                }
            }
        }
        fun startUpdating(startcb:()->Unit) {
            if(dir == 0f) {
                dir = 1f - 2*prevScale
                startcb()
            }
        }
    }
}