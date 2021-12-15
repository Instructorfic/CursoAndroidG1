package com.fic.cursoandroid.capapresentacion.Student;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
    private ClickListener clickListener;
    private GestureDetector gestureDetector;

    public RecyclerTouchListener(Context context, RecyclerView recyclerView, ClickListener clickListener) {
        this.clickListener = clickListener;

        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
             public boolean onSingleTapUp(MotionEvent e){
                 return true;
             }

             public void onLongPress(MotionEvent e){
                 View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                 if(child != null && clickListener != null){
                     clickListener.onLongClick(child,recyclerView.getChildLayoutPosition(child));
                 }
             }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent e) {
        View child = recyclerView.findChildViewUnder(e.getX(),e.getY());

        if(child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
            clickListener.onClick(child,recyclerView.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
