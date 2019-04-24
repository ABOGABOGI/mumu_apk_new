package id.hike.apps.android_mpos_mumu.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.R;

/**
 * Created by getwiz on 16/05/17.
 */

public class GreenDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public GreenDividerItemDecoration(Context context) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.green_line_divider);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
