package adiel.mymodel.customloader;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by recntrek7 on 15/03/17.
 */

public class MyLoader extends Loader<MyCursor> {
    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public MyLoader(Context context) {
        super(context);
    }
}
