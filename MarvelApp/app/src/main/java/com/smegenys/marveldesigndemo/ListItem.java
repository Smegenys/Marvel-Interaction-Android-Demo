package com.smegenys.marveldesigndemo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;

import android.os.Build;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ListItem extends RecyclerView.ViewHolder {
   public ImageView superheroImage;
   public TextView txtname,nickname,more,txtmarvel,contentmarvel,txtmovie;
   public ImageButton back1;
   CardView cardlayout;
   View view1,view2;
   LinearLayout layoutmarvel,layoutmovie;

   MyListData[] myListData;


   boolean position;

   public int AnimationDuration = 400;



    public ListItem(final View itemView, int height) {
        super(itemView);

        cardlayout=(CardView) itemView.findViewById(R.id.layout);
        cardlayout.setTranslationY(height);
        superheroImage=(ImageView)itemView.findViewById(R.id.user_profile_photo) ;
        superheroImage.setTranslationY(height - superheroImage.getLayoutParams().height/2);
        txtname =(TextView)itemView.findViewById(R.id.ironman);
        nickname=(TextView)itemView.findViewById(R.id.stark);
        back1=(ImageButton)itemView.findViewById(R.id.back1);
        more=(TextView)itemView.findViewById(R.id.more);
        view1=itemView.findViewById(R.id.view1);
        view2=itemView.findViewById(R.id.view2);
        layoutmarvel=itemView.findViewById(R.id.layoutmarvel);
        txtmarvel=itemView.findViewById(R.id.txtmarvel);
        contentmarvel=itemView.findViewById(R.id.contentmarvel);
        txtmovie=itemView.findViewById(R.id.txtmovie);
        layoutmovie=itemView.findViewById(R.id.layoutmovie);
       // cardlayout.setCardBackgroundColor(itemView.getResources().getColor(R.color.yellow));

        back1.setEnabled(false);


        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call to reverse animation
                PerformAnimation(false);
                cardlayout.setRadius(0);
            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // call method to slide down animation
                SwipeUpDownAnimationEnd(true);
            }
        });
    }


    /*
    * This method is using for animation process
    * */
    public void PerformAnimation(final boolean isReverseAnimation)
    {


        back1.setEnabled(!isReverseAnimation);
        // if  isReverseAnimation is  true button visible othewise  gone.
        more.setVisibility(isReverseAnimation ? View.VISIBLE : View.GONE);
        float y= isReverseAnimation ? cardlayout.getHeight()/2 : 0;
        cardlayout.animate().setListener(new Animator.AnimatorListener()
        {
            /*
            * This is animator listener override method, Which will fire when animation is completed.
            * So, Here we are using it for updating view and start another animation when Swipe up the card layout
            * */
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onAnimationEnd(Animator animation) {
                if(!isReverseAnimation) {
                    // to reverse animation
                    /**
                     * Here, We are passing false as iSDown parameter. So, It will work like revese animation
                     * It will Swipe down card view.
                     * */
                    SwipeUpDownAnimationEnd(false);
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });

        cardlayout.animate().translationY(y).setDuration(AnimationDuration);
        superheroImage.animate().translationY(isReverseAnimation ? (cardlayout.getHeight()/2 - superheroImage.getHeight()/2) :100).setDuration(AnimationDuration);
        superheroImage.animate().scaleY(isReverseAnimation ? 1F : 1.3F).setDuration(AnimationDuration);//millisecond
        superheroImage.animate().scaleX(isReverseAnimation ? 1F : 1.3F).setDuration(AnimationDuration);
        txtname.animate().translationY(isReverseAnimation ? 0 : superheroImage.getHeight()).setDuration(AnimationDuration);

        if(isReverseAnimation)
        {

                cardlayout.setRadius(60);
                cardlayout.setCardBackgroundColor(itemView.getResources().getColor(ListAdapter.color));
        }


        layoutmarvel.setTranslationY(isReverseAnimation ? 0 :390);
        view1.setTranslationY(isReverseAnimation ? 0 :410);
        contentmarvel.setTranslationY(isReverseAnimation ? contentmarvel.getTranslationY() :430);
        view2.setTranslationY(isReverseAnimation ? 0 :460);
        txtmovie.setTranslationY(isReverseAnimation ? 0 :470);
        layoutmovie.setTranslationY(isReverseAnimation ? 0 :490);

    }



    /*
    *
    * this is swipeupdown method use to
    * swipeup and swipdown card animation
    *
    * */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SwipeUpDownAnimationEnd(boolean isDown)
    {
        int TextColor = isDown ? Color.WHITE : Color.BLACK;
        int TextColorFrom = isDown ? R.color.black :  R.color.white;
        int TextColorTo =  isDown ?  R.color.white : R.color.black;
        int ColorFrom = isDown ? R.color.white : ListAdapter.color;
        int ColorTo =  isDown ? ListAdapter.color :  R.color.white;

        nickname.setTextColor(TextColor);
        txtname.setTextColor(TextColor);
        view1.setVisibility(isDown ? View.GONE : View.VISIBLE);
        view2.setVisibility(isDown ? View.GONE : View.VISIBLE);
        txtmarvel.setVisibility(isDown ? View.GONE : View.VISIBLE);
        contentmarvel.setVisibility(isDown ? View.GONE : View.VISIBLE);
        layoutmovie.setVisibility(isDown ? View.GONE : View.VISIBLE);
        txtmovie.setVisibility(isDown ? View.GONE : View.VISIBLE);

        int colorFrom = itemView.getResources().getColor(ColorFrom);
        int colorTo = itemView.getResources().getColor(ColorTo);




        /*
        * valueAnimator is used to give animate value to control
        *
        */


            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);// change yellow to white
            colorAnimation.setDuration(AnimationDuration); // milliseconds
            colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                /*
                 * this is override medhod fire when animation update
                 * so here we are update color yellow to white            * */
                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    cardlayout.setCardBackgroundColor((int) animator.getAnimatedValue());

                }

            });
            colorAnimation.start();

            if(isDown) {
                colorAnimation.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {

                        //perform reveres animation

                        /**
                         * Here, We are passing true as isReverse parameter. So, It will work like revese animation
                         * It will Swipe down card view.
                         * */
                        PerformAnimation(true);
                    }
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        view1.setVisibility(View.GONE);
                        view2.setVisibility(View.GONE);
                        txtmarvel.setVisibility(View.GONE);
                        contentmarvel.setVisibility(View.GONE);
                        txtmovie.setVisibility(View.GONE);
                        layoutmovie.setVisibility(View.GONE);


                    }
                });
            }



        int colorFrom1 = itemView.getResources().getColor(TextColorFrom);
        int colorTo1 = itemView.getResources().getColor(TextColorTo);
        ValueAnimator colorAnimation1 = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom1, colorTo1);
        colorAnimation1.setDuration(AnimationDuration); // milliseconds
        colorAnimation1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {

                nickname.setTextColor((int) animator.getAnimatedValue());
                txtname.setTextColor((int) animator.getAnimatedValue());

            }

        });

        colorAnimation1.start();
    }

    /*
    * this is called when holder is bind.
    * so here, we get device screen and set layout
    * and imageview transitionY
    *
    * */
    public void bind(int i) {

        if(cardlayout.getHeight() != 0)
        {

            cardlayout.setTranslationY(cardlayout.getHeight()/2);
            superheroImage.setTranslationY((cardlayout.getHeight()/2) - (superheroImage.getHeight()/2));
//           cardlayout.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.yellow));




        }


    }


}
