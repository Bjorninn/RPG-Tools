package eu.elieser.exalted.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import eu.elieser.exalted.R;

/**
 * TODO: document your custom view class.
 */
public class IconButton extends LinearLayout
{
    public IconButton(Context context)
    {
        super(context);
        init(null, 0);
    }

    public IconButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs, 0);
    }

    public IconButton(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle)
    {
        inflate(getContext(), R.layout.widget_icon_button, this);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.IconButton, defStyle, 0);

        String label = a.getString(R.styleable.IconButton_android_text);
        int resId = a.getResourceId(R.styleable.IconButton_android_src, 0);

        a.recycle();

        TextView textView = (TextView) findViewById(R.id.label);
        ImageView imageView = (ImageView) findViewById(R.id.icon);

        textView.setText(label);
        imageView.setImageResource(resId);
    }
}
