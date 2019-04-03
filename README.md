##  Android中的动画
### 一、Transition 过度动画
##### 1. Slide 滑动动画
 追踪目标视图在场景的开始和结束时的变化，
 以及移动视图从场景的一个边缘进出的变化。
 其可见性由view的setVisibility(int)的情况
 以及其父容器是否在当前视图层级中共同决定。
##### 1.1 自有属性
**slideEdge** 表示其实滑动的侧边位置 
- RIGHT/END：右侧
- LEFT/START：左侧
- BOTTOM：底部
- TOP：顶部

##### 1.2 继承属性
**Visibility** 中的属性
 
 **transitionVisibilityMode** 表示所支持出现或者消失的视图变化之一

- MODE_IN：只支持出现视图
- MODE_OUT：只支持消失视图

**Transition** 中的属性

**duration** 表示这次变换所需要的时间戳。

**interpolator** 表示这次变化中在动画中引入使用插入器。

**startDelay** 表示这次变化之前延迟的毫秒数。

##### 2.Fade 逐渐消失或者显示动画

与1.2中属性使用基本一致

##### 3.Explode 下方插入

与1.2中属性使用基本一致

##### 4.Activity中的使用

Activity中的转换动画分为2类


> 发出Intent跳转的Activity A：支持 **setExitTransition**
> 该Activity退出场景的动画；**setReenterTransition** 再次进入该场景的动画；

  
> Intent调转到的Activity B：支持 **setEnterTransition** 进入场景动画；
> **setReturnTransition**退出场景动画；

在A发出Intent时，不加入Bundle动画不能生效

`startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());`

### 二、Animation view

##### 1.AlphaAnimation 透明度动画

##### 2.RotateAnimation 旋转动画

##### 3.TranslateAnimation 位移动画

##### 4.ScaleAnimation 缩放动画

##### 5.AnimationSet 组合动画

##### 6.popupWindow 的动画

设置style 中windowEnterAnimation 与 windowExitAnimation

##### 7.RecyclerView 的动画

设置 LayoutAnimation 的动画
> TranslateAnimation动画在view本身 向上即为 - 向下即为 + 

### 三、Drawable Animation 帧动画

1. xml 中设置 animation-list 
2. 实例化 AnimationDrawable 通过addFrame添加帧

### 四、PropertyAnimation 属性动画

继承关系 从父类到子类 都可以实现同样的效果
> -- android.animation.Animator 

> -- android.animation.ValueAnimator 以数值驱动

> -- android.animation.ObjectAnimator 以对象驱动