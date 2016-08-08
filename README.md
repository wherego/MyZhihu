# MyZhihu
MVP + Retrofit + RxJava

## 网络
使用了 `Retrofit + RxJava` 进行异步网络请求

1.创建接口
~~~ java
public interface ApiService {
    @GET("latest")
    Observable<Zhihu> getZhihuResponse();

    @GET("{id}")
    Observable<Story> getStory(@Path("id") String id);
}
~~~
2.获取 `Retrofit` 实例
~~~ java
Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
~~~
3.网络操作
~~~ java
retrofit.create(ApiService.class)
        .getZhihuResponse()
        .subscribeOn(Schedulers.io()) // 网络操作在子线程
        .observeOn(AndroidSchedulers.mainThread()) // 在主线程更新UI
        .subscribe(new Subscriber<Zhihu>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Zhihu zhihu) {
                // do something with zhihu
            }
        });
~~~
参考文章:


[Retrofit](http://square.github.io/retrofit/)


[给 Android 开发者的 RxJava 详解](http://gank.io/post/560e15be2dca930e00da1083)


[RxJava 与 Retrofit 结合的最佳实践](http://gank.io/post/56e80c2c677659311bed9841)

## 格式化 html
由于网络获取的知乎正文不是标准的 `html` 格式，因此要对格式化后才能使用 `WebView` 显示
```java
public static String formatHtml(String html) {

	// 这里将 html 中的图片占位符删掉，使用 ImageView 显示图片
    html = html.replace("<div class=\"img-place-holder\">", "");

    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"zhihu.css\" ></head>");
    stringBuffer.append("<body>");
    stringBuffer.append(html);
    stringBuffer.append("</body></html>");

    return stringBuffer.toString();
}
```

## 给 RecyclerView 加 Header
~~~ java
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Item> items = new ArrayList<>();

    public Adapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_layout, parent, false);
            return new ViewHolderHeader(view);
        } else if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout, parent, false);
            return new ViewHolderItem(view);
        }

        throw new RuntimeException("View type not available");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderHeader) {
            // cast holder to ViewHolderHeader 
        } else if (holder instanceof ViewHolderItem) {
            // cast holder to ViewHolderItem
            // item position starts at 1
            position--;
        }
    }

    @Override
    public int getItemCount() {
        // adding 1 for header view
        return items.size() + 1;
    }

    public class ViewHolderHeader extends RecyclerView.ViewHolder {

        public ViewHolderHeader(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {

        public ViewHolderItem(View itemView) {
            super(itemView);
        }
    }
}
~~~

## 使用 `ViewPager` 展示图片
自定义 `ImagePagerAdapter` 继承自 `PagerAdapter`
```java
public classpublic class ImagePagerAdapter extends PagerAdapter {

	private OnClickListener listener;

	public ImagePagerAdapter() {
		// do some initial work
	}

	@Override
	public int getCount() {

	}

	@Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

	@Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(container.getContext())
                .inflate(R.layout.vp_item_top_story, container, false);

        // initial views here

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });

        // make sure to call this method
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
```

## 实现加载更多功能
1.创建接口
```java
public void setOnClickListener(OnClickListener listener) {
    clickListener = listener;
}
```
2.对 `RecyclerVew` 绑定滚动监听器
```java
LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

private boolean isLoading = false;
private int visibleThreshold = 5;
private int lastVisibleItem;
private int totalItemCount;

recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        totalItemCount = layoutManager.getItemCount();
        lastVisibleItem = layoutManager.findLastVisibleItemPosition();

        if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold) {
            if (loadMoreListener != null) {
                loadMoreListener.onLoadMore();
            }
            isLoading = true;
        }
    }
});
```
3.`RecyclerViewAdapter` 实现回调
```java
adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
    @Override
    public void onLoadMore() {
		//加载新数据到adapter
        adapter.addItem(yourItems);
        adapter.notifyDataSetChanged();
    }
});
```
