package com.example.myapplication.ui.theme.androidwidgets.views

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.example.myapplication.R


class StoriesProgressView : LinearLayout {

    private val PROGRESS_MAX = 100
    private val PROGRESS_BAR_LAYOUT_PARAM = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1F)
    private val SPACE_LAYOUT_PARAM = LayoutParams(5, LayoutParams.WRAP_CONTENT)
    private var progressDrawable: Int= -1

    private val progressBars: MutableList<ProgressBar> = ArrayList()
    private val animators: MutableList<ObjectAnimator> = ArrayList()

    private var storiesCount = -1

    /**
     * pointer of running animation
     */
    private var current = 0
    private var storiesListener: StoriesListener? = null
    var isReverse = false
    var isComplete = false

    interface StoriesListener {
        fun onNext()
        fun onNextStarted()
        fun onPrev()
        fun onComplete()
        fun pauseStory()
        fun playStory()
    }

    constructor(context: Context?) : super(context)

    constructor(context: Context?, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        orientation = HORIZONTAL
        context?.let {
            val typedArray: TypedArray =
                it.obtainStyledAttributes(attrs, R.styleable.StoriesProgressView)
            storiesCount = typedArray.getInt(R.styleable.StoriesProgressView_progressCount, 0)

            val shouldUserDynamicWidth = typedArray.getBoolean(R.styleable.StoriesProgressView_shouldUseDynamicItemWidth, false)
            if(shouldUserDynamicWidth) {
                val itemWidth = typedArray.getDimension(R.styleable.StoriesProgressView_itemWidth, 0f)
                PROGRESS_BAR_LAYOUT_PARAM.width = itemWidth.toInt()
            }

             val shouldUserDynamicHeight = typedArray.getBoolean(R.styleable.StoriesProgressView_shouldUseDynamicItemHeight, false)
            if(shouldUserDynamicHeight) {
                val itemHeight = typedArray.getDimension(R.styleable.StoriesProgressView_itemHeight, 0f)
                PROGRESS_BAR_LAYOUT_PARAM.height = itemHeight.toInt()
            }

            val shouldUseDynamicProgressDrawable = typedArray.getBoolean(R.styleable.StoriesProgressView_shouldUseDynamicItemHeight, false)
            if(shouldUseDynamicProgressDrawable) {
                progressDrawable =  typedArray.getResourceId(R.styleable.StoriesProgressView_progressDrawable, -1)
            }

            typedArray.recycle()
            bindViews()
        }
    }

    private fun bindViews() {
        removeAllViews()
        progressBars.clear()
        for (i in 0 until storiesCount) {
            val p: ProgressBar = createProgressBar()
            p.max = PROGRESS_MAX
            progressBars.add(p)
            addView(p)
            if (i + 1 < storiesCount) {
                addView(createSpace())
            }
        }
    }

    private fun createProgressBar(): ProgressBar {
        val p = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        p.layoutParams = PROGRESS_BAR_LAYOUT_PARAM
        p.progressDrawable = if(progressDrawable!=-1) ContextCompat.getDrawable(context, progressDrawable)
                            else ContextCompat.getDrawable(context, R.drawable.story_progress)
        return p
    }

    private fun createSpace(): View {
        val v = View(context)
        v.layoutParams = SPACE_LAYOUT_PARAM
        return v
    }

    /**
     * Set story count and create views
     * @param storiesCount story count
     */
    fun setStoriesCount(storiesCount: Int) {
        this.storiesCount = storiesCount
        bindViews()
    }

    /**
     * Set storiesListener
     * @param storiesListener StoriesListener
     */
    fun setStoriesListener(storiesListener: StoriesListener?) {
        this.storiesListener = storiesListener
    }

    /**
     * Skip current story
     */
    fun skip() {
        if (isComplete) return
        val p = progressBars[current]
        p.progress = p.max
        animators[current].cancel()
    }

    /**
     * Reverse current story
     */
    fun reverse() {
        if (isComplete) return
        var p = progressBars[current]
        p.progress = 0
        isReverse = true
        animators[current].cancel()
        val prev = current - 1
        if (0 <= prev) {
            p = progressBars[prev]
            p.progress = 0
            animators[prev].start()
//            stopAnimation(prev)
        } else {
            animators[current].start()
//            stopAnimation(current)
        }
    }

    /**
     * Set last story for finish
     */
    fun setLast() {
        isComplete = true
        if (storiesListener != null) storiesListener?.onComplete()
    }

    /**
     * Set a story's duration
     *
     * @param duration millisecond
     */
    fun setStoryDuration(duration: Long) {
        animators.clear()
        for (i in progressBars.indices) {
            animators.add(createAnimator(i, duration))
        }
    }

    /**
     * Set stories count and each story duration
     *
     * @param durations milli
     */
    fun setStoriesCountWithDurations(@NonNull durations: LongArray) {
        storiesCount = durations.size
        bindViews()
        animators.clear()
        for (i in progressBars.indices) {
            animators.add(createAnimator(i, durations[i]))
        }
    }

    /**
     * Set stories count and with common duration
     *
     * @param durations milli
     */
    fun setStoriesCountWithDuration(@NonNull count: Int, @NonNull duration: Long) {
        storiesCount = count
        bindViews()
        animators.clear()
        for (i in progressBars.indices) {
            animators.add(createAnimator(i, duration))
        }
    }

    /**
     * Start progress animation
     */
    fun startStories() {
        animators[0].start()
    }

    /**
     * Start progress animation
     */
    fun startStoriesWithPause(position: Int) {
        animators[position].start()
//        stopAnimation(position)
    }

    /**
     * Returns animator size.
     */
    fun getAnimatorSize(): Int {
        return animators.size
    }

    /**
     * Need to call when Activity or Fragment destroy
     */
    fun destroy() {
        for (a in animators) {
            a.removeAllListeners()
            a.cancel()
        }
    }

    private var mAnimationTime: Long = 0
    fun stopAnimation(position: Int) {
        val animation = animators[position]
        animation.pause()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            animation.pause()
//        } else {
//            mAnimationTime = animation.currentPlayTime
//            animation.cancel()
//        }
    }

    fun resetAnimations(){
        animators.forEach {
            it.pause()
        }
    }

    fun playAnimation(position: Int, fromLastUsp: Boolean = false) {
        val animation = animators[position]
        if (fromLastUsp) {
            progressBars.forEach {
                it.progress = 0
            }

            animation.start()
            animation.currentPlayTime = mAnimationTime
        } else {
            animation.resume()
        }

    }



    private fun createAnimator(index: Int, duration: Long): ObjectAnimator {
        val animation = ObjectAnimator.ofInt(progressBars[index], "progress", PROGRESS_MAX)
        animation.interpolator = LinearInterpolator()
        animation.duration = duration
        animation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                current = index
            }

            override fun onAnimationEnd(animation: Animator) {
                if (isReverse) {
                    isReverse = false
                    if (storiesListener != null) storiesListener?.onPrev()
                    return
                }
                val next = current + 1
                if (next <= animators.size - 1) {
                    if (storiesListener != null) storiesListener?.onNext()
                    startStoriesWithPause(next)
                    if (storiesListener != null) storiesListener?.onNextStarted()
                } else {
                    // commented or left to false for restarting the USPs again
                    // isComplete = true
                    if (storiesListener != null) storiesListener?.onComplete()
                }
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        return animation
    }

}