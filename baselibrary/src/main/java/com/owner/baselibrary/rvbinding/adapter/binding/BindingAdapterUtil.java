package com.owner.baselibrary.rvbinding.adapter.binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.owner.baselibrary.rvbinding.adapter.binder.DataBindingItemViewBinder;
import com.example.baselibrary.bindings.function.Function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Liuyong on 2018-10-09.It's MVVMKotlinMall
 *
 * @description:
 */
public class BindingAdapterUtil {

    public static class BindableVariables extends BaseObservable {
        @Bindable
        public Object data;
    }

    @android.databinding.BindingAdapter(value = {"itemLayout", "onBindItem"},requireAll = false)
    public static void setAdapter(RecyclerView view, int resId, DataBindingItemViewBinder.OnBindItem onBindItem) {
        final MultiTypeAdapter adapter = getOrCreateAdapter(view);
        adapter.register(Object.class, new DataBindingItemViewBinder(resId, onBindItem));
    }

    private static MultiTypeAdapter getOrCreateAdapter(RecyclerView view) {
        if (view.getAdapter() instanceof MultiTypeAdapter) {
            return (MultiTypeAdapter) view.getAdapter();
        } else {
            final MultiTypeAdapter adapter = new MultiTypeAdapter();
            view.setAdapter(adapter);
            return adapter;
        }
    }

    @android.databinding.BindingAdapter(value = {"linkers", "onBindItem"},requireAll = false)
    public static void setAdapter(RecyclerView view, List<Linker> linkers, DataBindingItemViewBinder.OnBindItem onBindItem) {
        final MultiTypeAdapter adapter = getOrCreateAdapter(view);
        final ArrayList<ItemViewBinder> binders = new ArrayList<>();

        for (Linker linker : linkers) {
            binders.add(new DataBindingItemViewBinder(linker.getLayoutId(), onBindItem));
        }
        int size = binders.size();
        ItemViewBinder[] array = binders.toArray(new ItemViewBinder[size]);
        //以下部分使用了Java1.8
        adapter.register(Object.class)
                .to(array)
                .withLinker(o -> {
                    for (int i = 0; i < linkers.size(); i++) {
                        Function<Object, Boolean> matcher = linkers.get(i).getMatcher();
                        if (matcher.apply(o)) {
                            return i;
                        }
                    }
                    return 0;
                });
    }

    @android.databinding.BindingAdapter("onLoadMore")
    public static void setItems(RecyclerView view, OnLoadMore listener) {
        if (view.getLayoutManager() instanceof LinearLayoutManager) {
            new LoadMoreDelegate(listener).attach(view);
        } else {
            throw new IllegalStateException("The OnLoadMore only support LinearLayoutManager");
        }
    }

    @android.databinding.BindingAdapter("items")
    public static void setItems(RecyclerView view, List items) {
        final MultiTypeAdapter adapter = getOrCreateAdapter(view);
        adapter.setItems(items == null ? Collections.emptyList() : items);
        adapter.notifyDataSetChanged();
    }
}
